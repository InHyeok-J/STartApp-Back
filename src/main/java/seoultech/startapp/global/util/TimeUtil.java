package seoultech.startapp.global.util;

import java.time.LocalDate;

public class TimeUtil {

    public static LocalDate makeStartTime(int year,int month){
        return LocalDate.of(year,month,1);
    }

    public static LocalDate makeEndTime(int year,int month){
        if(month == 12){
            return LocalDate.of(year + 1,1,1);
        }
        return LocalDate.of(year,month + 1,1);
    }
}
