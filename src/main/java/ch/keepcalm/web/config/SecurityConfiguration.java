package ch.keepcalm.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by marcelwidmer on 21/03/16.
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests().antMatchers("/").permitAll().and()
                // Allow all access to the url path /console/*.
                .authorizeRequests().antMatchers("/console/**").permitAll();
        // Disable CRSF (Cross-Site Request Forgery). By default, Spring Security will protect against CRSF attacks.
        httpSecurity.csrf().disable();
        // Since the H2 database console runs inside a frame, you need to enable this in in Spring Security.
        httpSecurity.headers().frameOptions().disable();
    }
}



