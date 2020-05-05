package cn.tm.hotel.system.permission.service;

import cn.tm.hotel.system.permission.model.RolePermission;
import cn.tm.hotel.system.permission.model.SystemPermission;
import cn.tm.hotel.system.permission.model.UserPermission;
import org.tm.common.base.service.BaseService;

import java.util.List;

public interface SystemPermissionService extends BaseService<SystemPermission>{

    List<SystemPermission> getPermissionByRoleId(String id);

    boolean addPermissionToRole(RolePermission userRole);

    boolean deletePermissionForRole(RolePermission userRole);

    List<SystemPermission> getPermissionByUserId(String userId);

    boolean addPermissionToUser(UserPermission userRole);

    boolean deletePermissionForUser(UserPermission userRole);
}