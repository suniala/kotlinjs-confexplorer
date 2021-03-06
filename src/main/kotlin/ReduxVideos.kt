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
            videoList {
                videos = props.unwatchedVideos
                selectedVideo = props.selectedVideo
                onSelectVideo = props.onSelectVideo
            }
            h3 {
                +"Videos watched"
            }
            videoList {
                videos = props.watchedVideos
                selectedVideo = props.selectedVideo
                onSelectVideo = props.onSelectVideo
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
            watchedVideos = state.videos.videos.filter { state.viewer.watchedVideos.contains(it.id) }
            unwatchedVideos = state.videos.videos.filterNot { state.viewer.watchedVideos.contains(it.id) }
            selectedVideo = state.videos.videos.find { it.id == state.viewer.selectedVideo }
        },
        { dispatch, _ ->
            onSelectVideo = { dispatch(SelectVideo(it)) }
        }
    )(Videos::class.js.unsafeCast<RClass<VideosProps>>())