import react.*
import react.dom.div
import react.dom.h3

data class Video(val id: Int, val title: String, val speaker: String, val videoUrl: String)

val unwatchedVideos = listOf(
    Video(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
    Video(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
    Video(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
)

val watchedVideos = listOf(
    Video(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E")
)

external interface AppState : RState {
    var currentVideo: Video?
}

class App : RComponent<RProps, AppState>() {
    override fun RBuilder.render() {
        div {
            h3 {
                +"Videos to watch"
            }
            // Method 1 for instantiating custom components.
            child(VideoList::class) {
                attrs.videos = unwatchedVideos
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
                videos = watchedVideos
                selectedVideo = state.currentVideo
                onSelectVideo = { video ->
                    setState {
                        currentVideo = video
                    }
                }
            }
        }
    }
}