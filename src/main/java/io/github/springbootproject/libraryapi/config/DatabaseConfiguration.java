package io.github.springbootproject.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

    /**
     * configuração Hikari:
     * https://github.com/brettwooldridge/hikaricp
     * @return
     */
    @Bean
    public DataSource hikariConfig() { // Pool de conexões que funciona melhor em produção

        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);

        config.setMaximumPoolSize(10); // no máximo, esse pool vai liberar 10 conexões
        config.setMinimumIdle(1); // tamanho inicial do pool
        config.setPoolName("library-db-pool"); // nome do pool
        config.setMaxLifetime(600000); // 600 mil ms (10 minutos) -> conexão vai durar no maximo 10min e morrer, e ser criado uma nova
        config.setConnectionTimeout(100000); // timeout para conseguir uma conexão
        config.setConnectionTestQuery("select 1"); // query de teste no banco

        return new HikariDataSource(config);
    }
}
