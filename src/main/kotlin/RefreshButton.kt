import react.*
import react.redux.*
import redux.RAction
import redux.WrapperAction
import state.actions.fetchMoreVideosIfNeeded
import state.reducers.State

interface RefreshButtonProps : RProps {
}

private interface ButtonStateProps : RProps {
    var active: Boolean
}

private interface ButtonDispatchProps : RProps {
    var onClick: () -> Unit
}

// NOTE: Type safety between prop types seems to be lacking. For example, differences between ButtonDispatchProps and
// ButtonProps do not cause compilation errors.
val refreshButton: RClass<RefreshButtonProps> =
    rConnect<State, RAction, WrapperAction, RefreshButtonProps, ButtonStateProps, ButtonDispatchProps, ButtonProps>(
        { state, _ ->
            active = !state.videos.fetching
        },
        { dispatch, _ ->
            onClick = { dispatch(fetchMoreVideosIfNeeded()) }
        }
    )(Button::class.js.unsafeCast<RClass<ButtonProps>>())
