/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
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
        List<Employee> emps = employeeRepository.findAll();
        if(emps.isEmpty()){
            ArrayList<String> r = new ArrayList<>();
            r.add("ADMIN");
            Employee emp = new Employee();
            emp.setForename("def");
            emp.setSurname("def");
            emp.setUsername("def");
            emp.setPassword("def");
            emp.setUserRoles(r);
            employeeRepository.save(emp);
        }
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
