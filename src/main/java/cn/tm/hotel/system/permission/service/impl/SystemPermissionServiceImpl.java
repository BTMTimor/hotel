package cn.tm.hotel.system.permission.service.impl;

import cn.tm.hotel.system.permission.model.RolePermission;
import cn.tm.hotel.system.permission.model.SystemPermission;
import cn.tm.hotel.system.permission.model.UserPermission;
import cn.tm.hotel.system.permission.service.SystemPermissionService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import org.tm.common.base.model.ICondition;
import org.tm.common.base.service.impl.BaseServiceImpl;

import java.util.List;

@Bean
public class SystemPermissionServiceImpl extends BaseServiceImpl<SystemPermission> implements SystemPermissionService {

    @Override
    public Page<SystemPermission> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }

    @Override
    public List<SystemPermission> getPermissionByRoleId(String roleId) {
        return DAO.template("getPermissionByRoleId", roleId).find();
    }

    @Override
    public boolean addPermissionToRole(RolePermission rolePermission) {
        return rolePermission.save();
    }

    @Override
    public boolean deletePermissionForRole(RolePermission rolePermission) {
        return 0 == Db.delete("deletePermissionForRole", rolePermission.getRoleId(), rolePermission.getPermissionId());
    }

    @Override
    public List<SystemPermission> getPermissionByUserId(String userId) {
        return DAO.template("getPermissionByUserId", userId).find();
    }

    @Override
    public boolean addPermissionToUser(UserPermission userRole) {
        return false;
    }

    @Override
    public boolean deletePermissionForUser(UserPermission userRole) {
        return false;
    }
}