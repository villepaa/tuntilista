
package wad.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wad.domain.Employee;
import wad.repository.EmployeeRepository;


@Service
public class EmployeeUserRoleService implements UserDetailsService{
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        Employee employee = employeeRepository.findByUsername(username);
        if (employee== null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        List<String> list= employee.getUserRoles();
        for(String role: list){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return new org.springframework.security.core.userdetails.User(
        employee.getUsername(),
        employee.getPassword(),
        true,
        true,
        true,
        true,
        roles);
    }
    
}
