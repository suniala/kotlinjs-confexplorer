import react.*
import react.dom.*
import react.redux.*
import redux.RAction
import redux.WrapperAction
import state.actions.SelectVideo
import state.reducers.State
import state.reducers.Video

external interface VideosProps : RProps {
    var videos: List<Video>
    var selectedVideo: Video?
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
                attrs.selectedVideo = props.selectedVideo
                attrs.onSelectVideo = props.onSelectVideo
            }
        }
    }
}

private interface ReduxVideosStateProps : RProps {
    var videos: List<Video>
    var selectedVideo: Video?
}

private interface ReduxVideosDispatchProps : RProps {
    var onSelectVideo: (Video) -> Unit
}

interface OwnProps : RProps

val reduxVideos: RClass<OwnProps> =
    rConnect<State, RAction, WrapperAction, OwnProps, ReduxVideosStateProps, ReduxVideosDispatchProps, VideosProps>(
        { state, _ ->
            videos = state.videos.asList()
            selectedVideo = state.viewer.selectedVideo
        },
        { dispatch, _ ->
            onSelectVideo = { dispatch(SelectVideo(it)) }
        }
    )(Videos::class.js.unsafeCast<RClass<VideosProps>>())