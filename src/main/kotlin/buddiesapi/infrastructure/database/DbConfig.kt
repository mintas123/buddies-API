package buddiesapi.infrastructure.database

import buddiesapi.infrastructure.database.account.AccountRepositoryConfig
import buddiesapi.infrastructure.database.account.AccountRepositoryImpl
import buddiesapi.infrastructure.database.rental.RentalRepositoryConfig
import buddiesapi.infrastructure.database.rental.RentalRepositoryImpl
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@Import(
    DataSourceAutoConfiguration::class,
    HibernateJpaAutoConfiguration::class,
    TransactionAutoConfiguration::class,
    ValidationAutoConfiguration::class,
    AccountRepositoryConfig::class,
    RentalRepositoryConfig::class
)
@EnableJpaRepositories(
    basePackageClasses = [RentalRepositoryImpl::class, AccountRepositoryImpl::class
    ], considerNestedRepositories = true
)
@EntityScan(basePackages = ["buddiesapi.infrastructure.database"])
class DbConfig 
