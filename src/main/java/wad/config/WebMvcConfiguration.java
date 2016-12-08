/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;


public class WebMvcConfiguration {
    
   
    
//    @Bean
//    public TemplateEngine templateEngine() {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        
//        engine.setTemplateResolver(templateResolver());
//        return engine;
//    }
//    
////    <bean id="templateEngine" class="org.thymeleaf.spring4.SpringTemplateEngine">
////      ...
////      <property name="additionalDialects">
////        <set>
////          <!-- Note the package would change to 'springsecurity3' if you are using that version -->
////          <bean class="org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect"/>
////        </set>
////      </property>
////      ...
////    </bean>
//    
}
