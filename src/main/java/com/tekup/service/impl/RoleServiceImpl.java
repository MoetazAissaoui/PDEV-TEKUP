package com.tekup.service.impl;

import com.tekup.dao.RoleDao;
import com.tekup.model.Role;
import com.tekup.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        // Find role by name using the roleDao
        Role role = roleDao.findRoleByName(name);
        return role;
    }
}
