package app

class City {

    var id: Long? = null
    var name: String? = null
    var state: String? = null
    var country: String? = null

    override fun toString(): String =
            id?.toString() + "," + name + "," + state + "," + country
}
