package ch.keepcalm.web.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by marcelwidmer on 21/03/16.
 *
 * Integration tests, we’re going to use a Spring Context to wire up beans to support our tests.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"ch.keepcalm.web.model"})
@EnableJpaRepositories(basePackages = {"ch.keepcalm.web.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
