package state.reducers

import kotlinx.browser.*
import kotlinx.coroutines.*
import redux.RAction
import state.actions.FetchVideos
import state.actions.VideosReceived

suspend fun fetchVideo(id: Int): Video {
    return window
        .fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
        .await()
        .json()
        .await()
        .unsafeCast<Video>()
}

suspend fun fetchVideos(): List<Video> = coroutineScope {
    (1..25).map { id ->
        async {
            fetchVideo(id)
        }
    }.awaitAll()
}

fun videos(state: Videos = Videos(emptyArray(), false), action: RAction): Videos = when (action) {
    is FetchVideos -> {
        Videos(emptyArray(), true)
    }
    is VideosReceived -> {
        Videos(action.videos, false)
    }
    else -> state
}