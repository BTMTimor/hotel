package cn.tm.hotel.system.role.service;

import cn.tm.hotel.system.role.model.SystemRole;
import cn.tm.hotel.system.role.model.UserRole;
import org.tm.common.base.service.BaseService;

import java.util.List;

public interface SystemRoleService extends BaseService<SystemRole>{

    List<SystemRole> getRoleByUserId(String id);

    boolean addRoleToUser(UserRole userRole);

    boolean deleteRoleForUser(UserRole userRole);
}