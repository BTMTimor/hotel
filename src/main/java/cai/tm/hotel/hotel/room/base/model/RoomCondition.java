package cai.tm.hotel.hotel.room.base.model;

import lombok.Getter;
import lombok.Setter;
import org.tm.common.base.model.BaseCondition;

import java.sql.Date;

/*
    author: Timor
    date: 2020/4/7 0007
*/
public class RoomCondition extends BaseCondition {
    @Getter @Setter
    private String roomId;
    @Getter @Setter
    private String hotelId;
    @Getter @Setter
    private String userId;

    @Getter @Setter
    private Date engagedTime;
    @Getter @Setter
    private Date beginEngagedTime;
    @Getter @Setter
    private Date endEngagedTime;
    @Getter @Setter
    private Date engagedInTime;
    @Getter @Setter
    private Date engagedOutTime;
    @Getter @Setter
    private Date checkInTime;
    @Getter @Setter
    private Date checkOutTime;
    @Getter @Setter
    private int status;

}
