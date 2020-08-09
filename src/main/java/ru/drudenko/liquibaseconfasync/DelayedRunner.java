package ru.drudenko.liquibaseconfasync;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Component
final class DelayedRunner implements ApplicationListener {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final SpringLiquibase springLiquibase;
    private final AsyncSpringLiquibase asyncSpringLiquibase;
    @Value("${ru.drudenko.liquibaseconfasync.delay:15000}")
    private int delay;

    @Autowired
    public DelayedRunner(SpringLiquibase liquibase, AsyncSpringLiquibase asyncSpringLiquibase) {
        this.springLiquibase = liquibase;
        this.asyncSpringLiquibase = asyncSpringLiquibase;
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            if (delay > 0) {
                new java.util.Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        migrate();
                    }
                }, delay);
            } else {
                migrate();
            }
        }
    }

    private void migrate() {
        springLiquibase.setShouldRun(true);
        try {
            springLiquibase.afterPropertiesSet();
        } catch (LiquibaseException e) {
            log.error("Delayed Liquibase changes application failed.", e);
        }

        asyncSpringLiquibase.setShouldRun(true);

        try {
            asyncSpringLiquibase.afterPropertiesSet();
        } catch (Exception e) {
            log.error("Migrate promises dates stage 1 failed.", e);
        }
    }
}
