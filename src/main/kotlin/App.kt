import react.*
import react.dom.*
import react.redux.*
import redux.RAction
import state.actions.fetchMoreVideosIfNeeded
import state.reducers.State

interface AppProps : RProps {
    var dispatch: (RAction) -> Unit
}

class App : RComponent<AppProps, RState>() {
    override fun componentDidMount() =
        props.dispatch(fetchMoreVideosIfNeeded())

    override fun RBuilder.render() {
        div {
            div {
                refreshButton {
                    +"Fetch more videos"
                }
                reduxVideos {
                }
            }
            reduxVideoPlayer {
            }
        }
    }
}

interface AppOwnProps : RProps

// NOTE: Somehow this maps the dispatch function to AppProps but I don't know how.
val app =
    rConnect<State, AppOwnProps, AppProps>(
        { _, _ ->
        },
    )(App::class.js.unsafeCast<RClass<AppProps>>())
