package pl.book.it.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("api/bia/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/users").authenticated()
                .anyRequest().authenticated()
//                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                //.loginPage("/login_page").permitAll()
//                .loginProcessingUrl("/appLogin")
                .defaultSuccessUrl("/api/bia/places/index", true).failureUrl("/loginPage")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/logoutView")
                .and()
                .rememberMe().key("cookie").tokenValiditySeconds(10)
                .and()
                .httpBasic()
                .and()
                .csrf().ignoringAntMatchers("/h2/**")
                .and().csrf().disable()
                .headers().frameOptions().disable();
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder());
//    }
}
