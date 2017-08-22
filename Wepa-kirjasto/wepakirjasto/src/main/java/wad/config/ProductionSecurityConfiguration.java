package wad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import wad.auth.JpaAuthenticationProvider;

@Profile("profile")
@Configuration
@EnableWebSecurity
public class ProductionSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
            
        http.authorizeRequests()
                .antMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                 .and().
                httpBasic().disable();


        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .invalidateHttpSession(true);
        
        
    }
    
    
    @Profile("production")
    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        
        @Autowired
        private JpaAuthenticationProvider jpaAuthenticationProvider;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(jpaAuthenticationProvider)
                .inMemoryAuthentication()
                .withUser("admin").password("password").roles("ADMIN");
        }
    }
}