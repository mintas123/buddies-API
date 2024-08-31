package buddiesapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication


@SpringBootApplication
@ConfigurationPropertiesScan
class BuddiesApiApplication

fun main(args: Array<String>) {
    runApplication<BuddiesApiApplication>(*args)
}
