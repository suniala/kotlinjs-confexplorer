package state.actions

import kotlinx.browser.*
import kotlinx.coroutines.*
import redux.RAction
import redux.WrapperAction
import state.reducers.State
import state.reducers.Video
import state.reducers.VideoId
import util.RThunk
import util.nullAction
import kotlin.reflect.KFunction0

private suspend fun fetchVideo(id: Int): Video {
    return window
        .fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
        .await()
        .json()
        .await()
        .unsafeCast<Video>()
}

private suspend fun fetchVideos2(from: Int, amount: Int): List<Video> = coroutineScope {
    (from until from + amount).map { id ->
        async {
            fetchVideo(id)
        }
    }.awaitAll()
}

class RequestMoreVideos : RAction

fun fetchMoreVideosIfNeeded(): RThunk = object : RThunk {
    override fun invoke(
        dispatch: (RAction) -> WrapperAction,
        getState: KFunction0<*>
    ): WrapperAction {
        val state = getState().unsafeCast<State>().videos
        return if (!state.fetching)
            dispatch(fetchVideos(state.videos.size + 1, 5))
        else
            nullAction
    }
}

private fun fetchVideos(from: Int, amount: Int): RThunk = object : RThunk {
    private val mainScope = MainScope()

    override fun invoke(
        dispatch: (RAction) -> WrapperAction,
        getState: KFunction0<*>
    ): WrapperAction {
        dispatch(RequestMoreVideos())
        mainScope.launch {
            val videos = fetchVideos2(from, amount)
            dispatch(MoreVideosReceived(videos.toTypedArray()))
        }
        return nullAction
    }
}

class MoreVideosReceived(val videos: Array<Video>) : RAction

class SelectVideo(val video: Video) : RAction

class MarkWatched(val video: VideoId) : RAction
