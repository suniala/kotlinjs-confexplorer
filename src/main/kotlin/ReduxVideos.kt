import react.*
import react.dom.*
import react.redux.*
import redux.RAction
import redux.WrapperAction
import state.reducers.State
import state.reducers.Video

external interface VideosProps : RProps {
    var videos: List<Video>
    var onSelectVideo: (Video) -> Unit
}

class Videos : RComponent<VideosProps, RState>() {
    override fun RBuilder.render() {
        div {
            h3 {
                +"Videos to watch"
            }
            child(VideoList::class) {
                attrs.videos = props.videos
                attrs.selectedVideo = null
                attrs.onSelectVideo = props.onSelectVideo
            }
        }
    }
}

private interface ReduxVideosStateProps : RProps {
    var videos: List<Video>
}

private interface ReduxVideosDispatchProps : RProps {
}

interface OwnProps : RProps {
    var onSelectVideo: (Video) -> Unit
}

val reduxVideos: RClass<OwnProps> =
    rConnect<State, RAction, WrapperAction, OwnProps, ReduxVideosStateProps, ReduxVideosDispatchProps, VideosProps>(
        { state, _ ->
            videos = state.videos.asList()
        },
        { _, _ ->
        },
        { sp, _, op ->
            videos = sp.videos
            onSelectVideo = op.onSelectVideo
        }
    )(Videos::class.js.unsafeCast<RClass<VideosProps>>())