package school21.spring.service.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("school21.spring.service")
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean("hikariDataSource")
    public HikariDataSource hikariDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl(
                environment.getProperty("db.url"));
        hikariDataSource.setUsername(
                environment.getProperty("db.user"));
        hikariDataSource.setPassword(
                environment.getProperty("db.password"));
        hikariDataSource.setDriverClassName(
                environment.getProperty("db.driver.name"));
        return hikariDataSource;
    }

    @Bean("driverManagerDataSource")
    public DriverManagerDataSource driverManagerDataSource() {
        DriverManagerDataSource driverManagerDataSource =
                new DriverManagerDataSource();
        driverManagerDataSource.setUrl(
                environment.getProperty("db.url"));
        driverManagerDataSource.setUsername(
                environment.getProperty("db.username"));
        driverManagerDataSource.setPassword(
                environment.getProperty("db.password"));
        driverManagerDataSource.setDriverClassName(
                environment.getProperty("db.driver.name"));
        return driverManagerDataSource;
    }
}
