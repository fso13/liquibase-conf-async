package ru.drudenko.liquibaseconfasync.autoconfigure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "async.liquibase", ignoreUnknownFields = false)
@Qualifier("asyncLiquibaseProperties")
public class AsyncLiquibaseProperties extends LiquibaseProperties {
}
