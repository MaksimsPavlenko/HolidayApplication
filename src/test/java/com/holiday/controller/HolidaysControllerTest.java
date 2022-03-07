package com.holiday.controller;

import com.holiday.network.DataReader;
import com.holiday.services.HolidaysService;
import com.holiday.services.impl.PublicHolidaysServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HolidaysControllerTest {

    HolidaysController hc = new HolidaysController();

    @BeforeEach
    void setUp(){
        DataReader dr = new DataReader();
        PublicHolidaysServiceImplementation si = new PublicHolidaysServiceImplementation();
        HolidaysService hs = new HolidaysService();
        si.setReader(dr);
        hs.setSi(si);
        hc.setHolidaysService(hs);
    }

    @Test
    void isHolidayForCountryYes() {
        assertEquals("yes", hc.isPublicHolidayForCountry("AT","01112017"));
        assertEquals("yes", hc.isPublicHolidayForCountry("FI","26052022"));
        assertEquals("yes", hc.isPublicHolidayForCountry("CU","27072022"));
        assertEquals("yes", hc.isPublicHolidayForCountry("LV","23062021"));
        assertEquals("yes", hc.isPublicHolidayForCountry("DE","31102022"));
    }
    @Test
    void isHolidayForCountryNo() {
        assertEquals("no", hc.isPublicHolidayForCountry("AT","11111111"));
        assertEquals("no", hc.isPublicHolidayForCountry("11","01112017"));
        assertEquals("no", hc.isPublicHolidayForCountry("11","11111111"));

        assertEquals("no", hc.isPublicHolidayForCountry("AT","qwertyui"));
        assertEquals("no", hc.isPublicHolidayForCountry("at","01112017"));
        assertEquals("no", hc.isPublicHolidayForCountry("at","qweasdqw"));

        assertEquals("no", hc.isPublicHolidayForCountry("AT","QWEQWEQW"));
        assertEquals("no", hc.isPublicHolidayForCountry("ATT","QWEQWEQW"));

        assertEquals("no", hc.isPublicHolidayForCountry("AT","!@#$%^&*"));
        assertEquals("no", hc.isPublicHolidayForCountry("$#","01112017"));
        assertEquals("no", hc.isPublicHolidayForCountry("#@","$#@!%$#!"));
    }

    @Test
    void getNumberOfHolidays() {
        assertEquals(13, hc.numberOfPublicHolidays("AT","2017"));
        assertEquals(15, hc.numberOfPublicHolidays("FI","2022"));
        assertEquals(9, hc.numberOfPublicHolidays("CU","2022"));
        assertEquals(14, hc.numberOfPublicHolidays("LV","1997"));
        assertEquals(19, hc.numberOfPublicHolidays("DE","2022"));
    }

    //Empty or invalid value -> 0
    @Test
    void getNumberOfHolidaysEmptyOrInvalidValue() {
        assertEquals(0, hc.numberOfPublicHolidays("0","2017"));
        assertEquals(0, hc.numberOfPublicHolidays("FI","-1"));
        assertEquals(0, hc.numberOfPublicHolidays("1","1"));
        assertEquals(0, hc.numberOfPublicHolidays("LV","sfa"));
        assertEquals(0, hc.numberOfPublicHolidays("fa","2022"));
    }
}
