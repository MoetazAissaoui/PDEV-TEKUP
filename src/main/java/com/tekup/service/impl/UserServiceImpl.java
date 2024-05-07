package com.tekup.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tekup.constants.RoleConst;
import com.tekup.repository.UserRepository;
import com.tekup.entity.Role;
import com.tekup.entity.User;
import com.tekup.model.UserDto;
import com.tekup.service.RoleService;
import com.tekup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    // Load user by username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    // Get user authorities
    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    // Find all users
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    // Find user by username
    @Override
    public User findOne(String username) {
        return userDao.findByUsername(username);
    }

    // Save user
    @Override
    public User save(UserDto user) {

        User nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        // Set default role as Employee
        Role role = roleService.findByName(RoleConst._EmployeeRoleNameConst);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        // If email domain is hr.tekup.de, add HR role
        if(nUser.getEmail().split("@")[1].equals("hr.tekup.de")){
            role = roleService.findByName(RoleConst._HRRoleNameConst);
            roleSet.add(role);
        }

        nUser.setRoles(roleSet);
        return userDao.save(nUser);
    }

    @Override
    public User createEmployee(UserDto user) {
        User nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role employeeRole = roleService.findByName(RoleConst._EmployeeRoleNameConst);

        Set<Role> roleSet = new HashSet<>();
        if (employeeRole != null) {
            roleSet.add(employeeRole);
        }

        nUser.setRoles(roleSet);
        return userDao.save(nUser);
    }

    @Override
    public User createManager(UserDto user) throws Exception {
        User nUser = user.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role managerRole = roleService.findByName(RoleConst._ManagerRoleNameConst);

        Set<Role> roleSet = new HashSet<>();
        if (managerRole != null) {
            roleSet.add(managerRole);
        }else {
            throw new Exception(RoleConst._ManagerRoleNameConst
                    + " Does not exist.");
        }

        nUser.setRoles(roleSet);
        return userDao.save(nUser);

    }
}