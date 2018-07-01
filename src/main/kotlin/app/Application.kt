package app

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application(private val cityMapper: CityMapper)

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
