import react.*
import react.dom.*
import react.redux.*
import redux.RAction
import redux.WrapperAction
import state.actions.SelectVideo
import state.reducers.State
import state.reducers.Video

external interface VideosProps : RProps {
    var watchedVideos: List<Video>
    var unwatchedVideos: List<Video>
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
                attrs.videos = props.unwatchedVideos
                attrs.selectedVideo = props.selectedVideo
                attrs.onSelectVideo = props.onSelectVideo
            }
            h3 {
                +"Videos watched"
            }
            child(VideoList::class) {
                attrs.videos = props.watchedVideos
                attrs.selectedVideo = props.selectedVideo
                attrs.onSelectVideo = props.onSelectVideo
            }
        }
    }
}

private interface ReduxVideosStateProps : RProps {
    var watchedVideos: List<Video>
    var unwatchedVideos: List<Video>
    var selectedVideo: Video?
}

private interface ReduxVideosDispatchProps : RProps {
    var onSelectVideo: (Video) -> Unit
}

interface OwnProps : RProps

val reduxVideos: RClass<OwnProps> =
    rConnect<State, RAction, WrapperAction, OwnProps, ReduxVideosStateProps, ReduxVideosDispatchProps, VideosProps>(
        { state, _ ->
            watchedVideos = state.viewer.watchedVideos.asList()
            unwatchedVideos = state.videos.filterNot { state.viewer.watchedVideos.contains(it) }
            selectedVideo = state.viewer.selectedVideo
        },
        { dispatch, _ ->
            onSelectVideo = { dispatch(SelectVideo(it)) }
        }
    )(Videos::class.js.unsafeCast<RClass<VideosProps>>())