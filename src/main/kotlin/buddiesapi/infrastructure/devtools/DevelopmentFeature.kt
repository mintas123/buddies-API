package buddiesapi.infrastructure.devtools

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("development")
class DevelopmentFeature(
    val mockData: Boolean
) {
}