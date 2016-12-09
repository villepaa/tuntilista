/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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


@Profile("production")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private EmployeeUserRoleService userRoleService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       
        http.headers().frameOptions().sameOrigin();
        
        http.authorizeRequests()
                
                
                .antMatchers("/plan").hasAnyAuthority("PLANNER,ADMIN,READER") 
                .antMatchers("/tasks").hasAnyAuthority("PLANNER,ADMIN") 
                .antMatchers("/employees").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated();
        
        http.formLogin()
                .permitAll();
        http.logout()
                .permitAll();
    }
    
     @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password("admin").authorities(new SimpleGrantedAuthority("ADMIN"));
        auth.userDetailsService(userRoleService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
