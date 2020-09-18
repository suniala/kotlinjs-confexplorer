package state.reducers

data class Video(val id: Int, val title: String, val speaker: String, val videoUrl: String)

data class State(
    val videos: Array<Video> = emptyArray()
)

fun combinedReducers() = combineReducers(
    mapOf(
        State::videos to ::videos
    )
)
