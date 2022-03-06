package com.holiday.services;

import com.holiday.network.DataReader;
import com.holiday.services.impl.PublicHolidaysServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HolidaysServiceTest {

    HolidaysService hs = new HolidaysService();

    @BeforeEach
    void setUp(){
        DataReader dr = new DataReader();
        PublicHolidaysServiceImplementation si = new PublicHolidaysServiceImplementation();
        si.setReader(dr);
        hs.setSi(si);
    }

    @Test
    void isHolidayForCountryYes() {
        hs.getNumberOfPublicHolidays("2017", "AT");

        assertEquals(hs.isPublicHolidayForCountry("01112017","AT"), "yes");
        assertEquals(hs.isPublicHolidayForCountry("dwa","AT"), "no");

    }

    @Test
    void getNumberOfHolidays() {
    }
}