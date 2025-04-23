package org.example.serviciotecnico.Utils;

import java.time.LocalDate;

public class Utils {
    public static boolean compareDates(LocalDate date1, LocalDate date2){
        if (date1.isAfter(date2)){
            return true;
        }else{
            return false;
        }
    }
}
