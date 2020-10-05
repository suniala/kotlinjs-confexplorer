import kotlinx.browser.document
import react.dom.render
import react.redux.*
import redux.*
import state.reducers.State
import state.reducers.combinedReducers
import util.rThunk

@Suppress("UnsafeCastFromDynamic")
val store = createStore<State, RAction, dynamic>(
    combinedReducers(), State(), compose(
        rThunk(),
        rEnhancer(),
        js("if(window.__REDUX_DEVTOOLS_EXTENSION__ )window.__REDUX_DEVTOOLS_EXTENSION__ ();else(function(f){return f;});")
    )
)

fun main() {
    render(document.getElementById("root")) {
        provider(store) {
            app {}
        }
    }
}