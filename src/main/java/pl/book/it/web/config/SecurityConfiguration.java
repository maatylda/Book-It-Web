package pl.book.it.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //temporary
                .antMatchers("api/bia/admin/**").permitAll()
                .antMatchers("/api/bia/**").permitAll()
                .antMatchers("/h2/**").authenticated().and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .logout()
                .and()
                //temporary
                .csrf().disable()
                .headers().frameOptions().disable();
    }
}
