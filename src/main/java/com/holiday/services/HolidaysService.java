package com.holiday.services;

import com.holiday.services.impl.PublicHolidaysServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HolidaysService {

    @Autowired
    PublicHolidaysServiceImplementation si;

    public String isPublicHolidayForCountry(String date, String code) {
        return si.isPublicHolidayForCountry(date, code);
    }

    public int getNumberOfPublicHolidays(String date, String code) {
        return si.numberOfPublicHolidays(date,code);
    }

    public void setSi(PublicHolidaysServiceImplementation si) {
        this.si = si;
    }
}
