package pl.mroz.buddiesapi.infrastructure.database;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.mroz.buddiesapi.infrastructure.database.account.AccountRepositoryImpl;
import pl.mroz.buddiesapi.infrastructure.database.rental.RentalRepositoryImpl;

@Configuration
@Import({
    DataSourceAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class,
    TransactionAutoConfiguration.class,
    ValidationAutoConfiguration.class
})
@EnableJpaRepositories(basePackageClasses = {
    RentalRepositoryImpl.class,
    AccountRepositoryImpl.class
}, considerNestedRepositories = true)
@EntityScan
@ComponentScan
public class DbConfig {
}
