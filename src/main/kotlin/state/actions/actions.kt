package state.actions

import kotlinx.browser.*
import kotlinx.coroutines.*
import redux.RAction
import redux.WrapperAction
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

private suspend fun fetchVideos(amount: Int): List<Video> = coroutineScope {
    (1..amount).map { id ->
        async {
            fetchVideo(id)
        }
    }.awaitAll()
}

class RequestVideos() : RAction

fun fetchVideos(): RThunk = object : RThunk {
    private val mainScope = MainScope()

    override fun invoke(
        dispatch: (RAction) -> WrapperAction,
        getState: KFunction0<*>
    ): WrapperAction {
        dispatch(RequestVideos())
        mainScope.launch {
            val videos = fetchVideos(25)
            dispatch(VideosReceived(videos.toTypedArray()))
        }
        return nullAction
    }
}

class VideosReceived(val videos: Array<Video>) : RAction

class SelectVideo(val video: Video) : RAction

class MarkWatched(val video: VideoId) : RAction
