package zinc0214.whatdo.data

import java.io.Serializable

enum class ViewType {
    BLUE, PINK
}

class CardInfo : Serializable {
    val num: Int = 0
    val title: String = ""
    val content: String = ""
}

