package ru.drudenko.liquibaseconfasync.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import ru.drudenko.liquibaseconfasync.AsyncSpringLiquibase;

import javax.sql.DataSource;

@Configuration
public class AsyncLiquibaseConfiguration {

    @Bean
    @DependsOn({"liquibase"})
    public AsyncSpringLiquibase asyncLiquibase(DataSource dataSource) {
        AsyncSpringLiquibase liquibase = new AsyncSpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/async/db.changelog-master.xml");
        liquibase.setDataSource(dataSource);
        liquibase.setShouldRun(false);
        return liquibase;
    }

}
