import kotlinx.browser.document
import kotlinx.html.HTMLTag
import react.dom.*

data class Video(val id: Int, val title: String, val speaker: String, val videoUrl: String)

val unwatchedVideos = listOf(
    Video(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
    Video(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
    Video(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
)

val watchedVideos = listOf(
    Video(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E")
)

fun main() {
    render(document.getElementById("root")) {
        h1 {
            +"KotlinConf Explorer"
        }
        div {
            h3 {
                +"Videos to watch"
            }
            for (video in unwatchedVideos) {
                videoItem(video)
            }
            h3 {
                +"Videos watched"
            }
            for (video in watchedVideos) {
                videoItem(video)
            }
        }
        div {
            h3 {
                +"John Doe: Building and breaking things"
            }
            img {
                attrs {
                    src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
                }
            }
        }
    }
}

private fun RDOMBuilder<HTMLTag>.videoItem(video: Video) {
    p {
        +"${video.speaker}: ${video.title}"
    }
}