package cn.tm.hotel.system.role.service.impl;
import cn.tm.hotel.system.role.model.SystemRole;
import cn.tm.hotel.system.role.model.UserRole;
import cn.tm.hotel.system.role.service.SystemRoleService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import org.tm.common.base.model.ICondition;
import org.tm.common.base.service.impl.BaseServiceImpl;

import java.util.List;

@Bean
public class SystemRoleServiceImpl extends BaseServiceImpl<SystemRole> implements SystemRoleService {

    @Override
    public Page<SystemRole> findAllByCondition(ICondition condition) {
        return findAllByCondition(condition);
    }

    @Override
    public List<SystemRole> getRoleByUserId(String userId) {
        return DAO.template("getRoleByUserId", userId).find();
    }

    public boolean addRoleToUser(UserRole userRole){
        return userRole.save();
    }

    public boolean deleteRoleForUser(UserRole userRole){
        return 1 == Db.delete("deleteRoleForUser", userRole.getRoleId(), userRole.getUserId());
    }
}