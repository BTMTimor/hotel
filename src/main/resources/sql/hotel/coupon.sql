#namespace("hotel.coupon")

#sql("getAll")
    select *
    from hotel_coupon
#end

#sql("getAllForUser")
    select *
    from hotel_coupon
    where id in (select coupon_id from user_cuppon_mapping where user_id = ?)
#end

#sql("getAllForUser2")
    select uc.*, hc.user_id
    from user_cuppon_mapping as uc,hotel_coupon as hc
    where uc.user_id = ? and uc.cuppon_id = hc.id
#end

#sql("getAllPublic")
    select *
    from hotel_coupon
    where status = 1
#end



#end