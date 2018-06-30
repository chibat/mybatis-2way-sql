package app

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application(private val cityMapper: CityMapper) {

    @Bean
    fun sampleCommandLineRunner(): CommandLineRunner {
        return CommandLineRunner { args ->
            run {
                println(this.cityMapper.find("CA", "US"))
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
