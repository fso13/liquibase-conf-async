package ru.drudenko.liquibaseconfasync.conf;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.drudenko.liquibaseconfasync.AsyncSpringLiquibase;
import ru.drudenko.liquibaseconfasync.autoconfigure.AsyncLiquibaseProperties;

import javax.sql.DataSource;

@EnableConfigurationProperties({AsyncLiquibaseProperties.class})
@Configuration
public class AsyncLiquibaseConfiguration {

    @Bean
    public AsyncSpringLiquibase asyncLiquibase(DataSource dataSource, AsyncLiquibaseProperties asyncLiquibaseProperties) {
        AsyncSpringLiquibase liquibase = new AsyncSpringLiquibase();
        liquibase.setShouldRun(false);
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(asyncLiquibaseProperties.getChangeLog());
        liquibase.setClearCheckSums(asyncLiquibaseProperties.isClearChecksums());
        liquibase.setContexts(asyncLiquibaseProperties.getContexts());
        liquibase.setDefaultSchema(asyncLiquibaseProperties.getDefaultSchema());
        liquibase.setLiquibaseSchema(asyncLiquibaseProperties.getLiquibaseSchema());
        liquibase.setLiquibaseTablespace(asyncLiquibaseProperties.getLiquibaseTablespace());
        liquibase.setDatabaseChangeLogTable(asyncLiquibaseProperties.getDatabaseChangeLogTable());
        liquibase.setDatabaseChangeLogLockTable(asyncLiquibaseProperties.getDatabaseChangeLogLockTable());
        liquibase.setDropFirst(asyncLiquibaseProperties.isDropFirst());
        liquibase.setLabels(asyncLiquibaseProperties.getLabels());
        liquibase.setChangeLogParameters(asyncLiquibaseProperties.getParameters());
        liquibase.setRollbackFile(asyncLiquibaseProperties.getRollbackFile());
        liquibase.setTestRollbackOnUpdate(asyncLiquibaseProperties.isTestRollbackOnUpdate());
        liquibase.setTag(asyncLiquibaseProperties.getTag());
        return liquibase;
    }
}
