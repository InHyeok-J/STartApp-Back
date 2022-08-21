package seoultech.startapp.global.util;

import java.time.LocalDate;

public class TimeUtil {

    public static LocalDate makeStartTime(int year,int month){
        return LocalDate.of(year,month,1);
    }

    public static LocalDate makeEndTime(int year,int month){
        return LocalDate.of(year,month + 1,1);
    }
}
