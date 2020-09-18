import react.*
import react.redux.*
import redux.RAction
import redux.WrapperAction
import state.actions.FetchVideos
import state.reducers.State

interface RefreshButtonProps : RProps {
}

private interface ButtonStateProps : RProps {
}

private interface ButtonDispatchProps : RProps {
    var onClick: () -> Unit
}

// NOTE: Type safety between prop types seems to be lacking. For example, differences between ButtonDispatchProps and
// ButtonProps do not cause compilation errors.
val refreshButton: RClass<RefreshButtonProps> =
    rConnect<State, RAction, WrapperAction, RefreshButtonProps, ButtonStateProps, ButtonDispatchProps, ButtonProps>(
        { _, _ ->
        },
        { dispatch, _ ->
            onClick = { dispatch(FetchVideos()) }
        }
    )(Button::class.js.unsafeCast<RClass<ButtonProps>>())
