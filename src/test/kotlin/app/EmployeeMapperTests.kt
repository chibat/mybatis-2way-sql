package app

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class EmployeeMapperTests() {

    @Autowired
    var employeeMapper: EmployeeMapper? = null

    @Test
    fun testFindById() {
        val result = this.employeeMapper?.findById(Integer(1))
        println(result);
    }

}
