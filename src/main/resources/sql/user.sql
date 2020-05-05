#namespace("user")


### - - - - - - 用户信息相关 - - - - - - - - - - - - - - - - - - - - - - - - - -

#sql("findByNameAndPassword")
    select * from user
    where username = ? and password = ?
#end






### - - - - - - 人脸信息相关 - - - - - - - - - - - - - - - - - - - - - - - - - -

### 这个是展示给前端的，不查询特征数据
#sql("getAllUserFace")
    select id, user_id, face_file_path, create_time, update_time
    from user_face
#end







### - - - - - - 角色权限相关 - - - - - - - - - - - - - - - - - - - - - - - - - -

#sql("findCardByUserId")
    select *
    from user_card
    where id = (select card_id from user where id = ? limit 1)
#end

#sql("getRoleByUserId")
select *
from user_role
where id in (select role_id from user_role_mapping where user_id = ?)
#end

#sql("deleteRoleForUser")
delete from user_role_mapping where user_id = ? and role_id = ? limit 1
#end

#sql("getPermissionByRoleId")
select *
from user_permission
where id in (select permission_id from role_permission_mapping where role_id = ?)
#end

#sql("deletePermissionForRole")
delete from user_role_mapping where user_id = ? and role_id = ? limit 1
#end


#sql("getPermissionByUserId")
select *
from user_permission
where id in (select permission_id from role_permission_mapping where role_id
 in (select role_id from user_role_mapping where user_id = ?))
#end

### - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -





#end