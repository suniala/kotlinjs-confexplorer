import react.*
import react.dom.*

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