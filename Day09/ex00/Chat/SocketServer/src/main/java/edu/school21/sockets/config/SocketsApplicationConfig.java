package edu.school21.sockets.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("edu.school21.sockets")
public class SocketsApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean("hikariDataSource")
    public HikariDataSource hikariDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl(
                environment.getProperty("db.url"));
        hikariDataSource.setUsername(
                environment.getProperty("db.username"));
        hikariDataSource.setPassword(
                environment.getProperty("db.password"));
        hikariDataSource.setDriverClassName(
                environment.getProperty("db.driver.name"));
        return hikariDataSource;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
