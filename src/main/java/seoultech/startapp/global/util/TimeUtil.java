package seoultech.startapp.global.util;

import java.time.LocalDateTime;

public class TimeUtil {

    public static LocalDateTime makeStartTime(int year,int month){
        return LocalDateTime.of(year,month,1,0,0);
    }

    public static LocalDateTime makeEndTime(int year,int month){
        return LocalDateTime.of(year,month + 1,1,0,0);
    }
}
