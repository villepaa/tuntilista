package wad.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import wad.service.EmployeeUserRoleService;

@Profile({"default"})
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class DefaultSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmployeeUserRoleService userRoleService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // sallitaan h2-konsolin käyttö
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        
        http.authorizeRequests()
                .antMatchers("/h2-console/*").permitAll()
                
                .antMatchers("/plans").hasAnyAuthority("PLANNER,ADMIN,READER") 
                .antMatchers("/tasks").hasAnyAuthority("PLANNER,ADMIN") 
                .antMatchers("/employees/new").hasAnyAuthority("ADMIN")
                .antMatchers("/employees").hasAnyAuthority("PLANNER,ADMIN")
                .anyRequest().authenticated().and()
                .exceptionHandling().accessDeniedPage("/403");
                
                
                
        http.formLogin()
                .permitAll();
        http.logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin")
                    .authorities(new SimpleGrantedAuthority("ADMIN"));
                    
                
                
        auth.inMemoryAuthentication()
                .withUser("reader").password("reader")
                    .authorities(new SimpleGrantedAuthority("READER"));
                    
        
        auth.inMemoryAuthentication()
                .withUser("planner").password("planner")
                    .authorities(new SimpleGrantedAuthority("PLANNER"));
                    
        auth.userDetailsService(userRoleService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
}