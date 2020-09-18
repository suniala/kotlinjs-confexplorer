import kotlinx.css.*
import kotlinx.html.js.*
import react.*
import styled.*

interface ButtonProps : RProps {
    var active: Boolean
    var onClick: () -> Unit
}

class Button(props: ButtonProps) : RComponent<ButtonProps, RState>(props) {
    override fun RBuilder.render() {
        styledButton {
            attrs.onClickFunction = { props.onClick() }
            attrs.disabled = props.active
            css {
                marginLeft = 4.px
            }
            children()
        }
    }
}