/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onsite;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author abhi
 */
public class dateSeparate {
    
    LocalDate today = LocalDate.now();
    //System.out.println()
    LocalDate bday  = LocalDate.of(2000, Month.DECEMBER, 28);
    Period p = Period.between(bday,today);
    long p1 = ChronoUnit.DAYS.between(bday,today);
    //System.out.println("year = "+p.getYears()+"months = "+p.getMonths()+"days = ");
        
}
