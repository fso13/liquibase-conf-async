package ru.drudenko.liquibaseconfasync;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StopWatch;

public class AsyncSpringLiquibase extends SpringLiquibase {

    private final Logger log = LoggerFactory.getLogger(AsyncSpringLiquibase.class);

    private final ThreadPoolTaskExecutor executor;

    public AsyncSpringLiquibase() {
        log.debug("Creating Async Task Executor");
        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.initialize();
    }

    @Override
    public void afterPropertiesSet() {
        executor.execute(() -> {
            try {
                log.warn("Starting Liquibase asynchronously, your database might not be ready at startup!");
                initDb();
            } catch (LiquibaseException e) {
                log.error("Liquibase could not start correctly, your database is NOT ready: {}", e.getMessage(), e);
            }
        });
    }

    protected void initDb() throws LiquibaseException {
        StopWatch watch = new StopWatch();
        watch.start();
        super.afterPropertiesSet();
        watch.stop();
        log.debug("Started Liquibase in {} ms", watch.getTotalTimeMillis());
    }
}
