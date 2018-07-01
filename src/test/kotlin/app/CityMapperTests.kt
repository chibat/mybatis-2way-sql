package app

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CityMapperTests {

    @Autowired
    var cityMapper: CityMapper? = null

    @Test
    fun testFindByAnnotation() {
        val result = this.cityMapper?.findByAnnotation("CA", "US")
        println(result);
    }

}
