package org.example.serviciotecnico.Utils;

import java.time.LocalDate;

public class Utils {
    /**
     * Compara dos fechas.
     * @param date1 Fecha 1 a comparar.
     * @param date2 Fecha 2 a comparar.
     * @return true si date1 es posterior a date2, false en caso contrario.
     */
    public static boolean compareDates(LocalDate date1, LocalDate date2){
        if (date1.isAfter(date2)){
            return true;
        }else{
            return false;
        }
    }
}
