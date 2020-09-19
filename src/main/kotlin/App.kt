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

external interface AppState : RState {
    var currentVideo: Video?
    var unwatchedVideos: List<Video>
    var watchedVideos: List<Video>
}

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        unwatchedVideos = listOf()
        watchedVideos = listOf()

//        val mainScope = MainScope()
//        mainScope.launch {
//            val videos = fetchVideos()
//            setState {
//                unwatchedVideos = videos
//            }
//        }
    }

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
            h3 {
                +"Videos to watch"
            }
            // Method 1 for instantiating custom components.
            child(VideoList::class) {
                attrs.videos = state.unwatchedVideos
                attrs.selectedVideo = state.currentVideo
                attrs.onSelectVideo = { video ->
                    setState {
                        currentVideo = video
                    }
                }
            }

            h3 {
                +"Videos watched"
            }
            // Method 2 for instantiating custom components.
            videoList {
                videos = state.watchedVideos
                selectedVideo = state.currentVideo
                onSelectVideo = { video ->
                    setState {
                        currentVideo = video
                    }
                }
            }
            state.currentVideo?.let { currentVideo ->
                videoPlayer {
                    video = currentVideo
                    unwatchedVideo = currentVideo in state.unwatchedVideos
                    onWatchedButtonPressed = {
                        if (video in state.unwatchedVideos) {
                            setState {
                                unwatchedVideos -= video
                                watchedVideos += video
                            }
                        } else {
                            setState {
                                watchedVideos -= video
                                unwatchedVideos += video
                            }
                        }
                    }
                }
            }
        }
    }
}