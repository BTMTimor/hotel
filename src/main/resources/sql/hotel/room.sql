#namespace("hotel.room")

#sql("getAllRoomTypeForHotel")
select *
from room_type
where hotel_id = ?
#end


#sql("isCustomerRoom")
select count(*)
from room_engaged
where id = ? and user_id = ?
#end

### 预定房间
#sql("bookRoom")
insert into room_engaged
(id, room_id, user_id, hotel_id, engaged_time, status, create_time)
values (#(con.id), #(con.roomId), #(con.userId), #(con.hotelId), #(con.engagedTime), #(con.status), #(con.createTime));
#end

### 取消预定房间
#sql("unBookRoom")
update room_engaged
set status = #(con.status), update_time = #(con.updateTime)
where id = #(con.id)
#end


#sql("test_date")
SELECT * FROM cms_book_statistics WHERE update_time between '2017-09-27 00:00:00' and '2017-09-27 23:59:59'
#end


### 多条件查询房间
#define findRoomByCondition(cols, con)
select #(cols)
from room_engaged
where hotel_id = #(con.hotelId) and status = #(con.status)
#if(con.roomId)
    and (room_id = #(con.roomId))
#end #if(con.userId)
    and (user_id = #(con.userId))
#end #if(con.engagedTime)
    and (engaged_time > #(con.engagedTime))
#end #if(con.engagedInTime)
    and (engaged_in_time > #(con.engagedInTime))
#end #if(con.engagedOutTime)
    and (engaged_out_time < #(con.engagedOutTime))
#end #if(con.checkInTime)
    and (check_in_time > #(con.checkInTime))
#end #if(con.checkOutTime)
    and (check_out_time < #(con.checkOutTime))
#end
#end


### 获取被预定的房间
#sql("getBookedRoom")
    #@findRoomByCondition("*", con)
#end

### 获取没有被预定的房间
#sql("getUnBookedRoom")
select *
from room
where hotel_id = #(con.hotelId)
and id not in(#@findRoomByCondition("id", con))
#end

### 获取预定且已入住的房间
#sql("getUsedRoom")
    #@findRoomByCondition("*", con)
#end

### 获取预定但未入住的房间
#sql("getUnUsedRoom")
    #@findRoomByCondition("*", con)
#end

#end