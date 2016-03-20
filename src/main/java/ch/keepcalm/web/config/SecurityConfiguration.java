package ch.keepcalm.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * Created by marcelwidmer on 20/03/16.
 *
 * @EnableWebSecurity to enable Spring Security’s web security support and provide the Spring MVC integration
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * The following paths are not require any authentication
     * <ul>
     * <li>"/"</li>
     * <li>"/home"</li>
     * </ul>
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // exclusion of security
                .antMatchers("/", "/home").permitAll()
                // needed for login page
                .antMatchers(HttpMethod.GET, "/styles/*.css").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    /**
     * @param env
     * @param auth
     * @param dataSource
     * @param hibernateDefaultSchema
     * @throws Exception
     */
    @Autowired
    protected void configureDatabaseAuthentication(Environment env,
                                                   AuthenticationManagerBuilder auth,
                                                   DataSource dataSource,
                                                   @Value("${spring.jpa.properties.hibernate.default_schema:}")
                                                           String hibernateDefaultSchema) throws Exception {
        if (env.acceptsProfiles("local")) {
            auth
                    .inMemoryAuthentication()
                    .withUser("user").password("password").roles("USER");

        } else {
            JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer = new JdbcUserDetailsManagerConfigurer<>();
            String schema = isEmpty(hibernateDefaultSchema) ? "" : hibernateDefaultSchema + '.';
            configurer.usersByUsernameQuery("select username, password, enabled from " + schema + "ls_users"
                    + " where username = ?");
            configurer.authoritiesByUsernameQuery("select username, authority from " + schema + "ls_authorities"
                    + " where username = ?");
            auth.apply(configurer)
                    .passwordEncoder(new BCryptPasswordEncoder())
                    .dataSource(dataSource);
        }
    }


}
