import react.*
import react.dom.*
import react.redux.*
import redux.RAction
import redux.WrapperAction
import state.actions.MarkWatched
import state.reducers.State
import state.reducers.Video
import state.reducers.VideoId

external interface VideoPlayerWrapperProps : RProps {
    var video: Video?
    var unwatchedVideo: Boolean
    var onWatchedButtonPressed: (VideoId) -> Unit
}

// Use a wrapper to handle the case where a video has not been selected.
class VideoPlayerWrapper : RComponent<VideoPlayerWrapperProps, RState>() {
    override fun RBuilder.render() {
        div {
            props.video?.let { selectedVideo ->
                videoPlayer {
                    video = selectedVideo
                    unwatchedVideo = props.unwatchedVideo
                    onWatchedButtonPressed = props.onWatchedButtonPressed
                }
            }
            if (props.video == null) {
                span {
                    +"Please select a video"
                }
            }
        }
    }
}

private interface ReduxVideoPlayerStateProps : RProps {
    var video: Video?
    var unwatchedVideo: Boolean
}

private interface ReduxVideoPlayerDispatchProps : RProps {
    var onWatchedButtonPressed: (VideoId) -> Unit
}

interface ReduxVideoPlayerOwnProps : RProps

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
val reduxVideoPlayer: RClass<ReduxVideoPlayerOwnProps> =
    rConnect<State, RAction, WrapperAction, ReduxVideoPlayerOwnProps,
            ReduxVideoPlayerStateProps, ReduxVideoPlayerDispatchProps, VideoPlayerWrapperProps>(
        { state, _ ->
            video = state.videos.find { it.id == state.viewer.selectedVideo }
            unwatchedVideo = state.viewer.run {
                if (selectedVideo != null) !watchedVideos.contains(selectedVideo)
                else true
            }
        },
        { dispatch, _ ->
            onWatchedButtonPressed = { dispatch(MarkWatched(it)) }
        }
    )(VideoPlayerWrapper::class.js.unsafeCast<RClass<VideoPlayerWrapperProps>>())