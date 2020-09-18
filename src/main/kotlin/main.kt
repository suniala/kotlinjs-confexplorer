import kotlinx.browser.document
import react.dom.render
import react.redux.*
import redux.RAction
import redux.compose
import redux.createStore
import redux.rEnhancer
import state.reducers.State
import state.reducers.combinedReducers

@Suppress("UnsafeCastFromDynamic")
val store = createStore<State, RAction, dynamic>(
    combinedReducers(), State(), compose(
        rEnhancer(),
        js("if(window.__REDUX_DEVTOOLS_EXTENSION__ )window.__REDUX_DEVTOOLS_EXTENSION__ ();else(function(f){return f;});")
    )
)

fun main() {
    render(document.getElementById("root")) {
        provider(store) {
            child(App::class) {
            }
        }
    }
}