package app

class Employee {

    var id: Int? = null
    var name: String? = null
    var age: Int? = null

    override fun toString(): String = "Employee [id=" + id?.toString() + ", name=" + name + ", age=" + age?.toString() + "]"
}

