import kotlinx.browser.*
import kotlinx.coroutines.*
import react.*
import react.dom.*
import state.reducers.Video

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

class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            div {
                h3 {
                    +"New impl"
                }
                refreshButton {
                    +"Refresh videos"
                }
                reduxVideos {
                }
            }
            reduxVideoPlayer {
            }
        }
    }
}