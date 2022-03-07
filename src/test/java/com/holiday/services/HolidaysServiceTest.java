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
        assertEquals("yes", hs.isPublicHolidayForCountry("01112017","AT"));
        assertEquals("yes", hs.isPublicHolidayForCountry("26052022","FI"));
        assertEquals("yes", hs.isPublicHolidayForCountry("27072022","CU"));
        assertEquals("yes", hs.isPublicHolidayForCountry("23062021","LV"));
        assertEquals("yes", hs.isPublicHolidayForCountry("31102022","DE"));
    }
    @Test
    void isHolidayForCountryNo() {
        assertEquals("no", hs.isPublicHolidayForCountry("11111111","AT"));
        assertEquals("no", hs.isPublicHolidayForCountry("01112017","11"));
        assertEquals("no", hs.isPublicHolidayForCountry("11111111","11"));

        assertEquals("no", hs.isPublicHolidayForCountry("qwertyui","AT"));
        assertEquals("no", hs.isPublicHolidayForCountry("01112017","at"));
        assertEquals("no", hs.isPublicHolidayForCountry("qweasdqw","at"));

        assertEquals("no", hs.isPublicHolidayForCountry("QWEQWEQW","AT"));
        assertEquals("no", hs.isPublicHolidayForCountry("QWEQWEQW","ATT"));

        assertEquals("no", hs.isPublicHolidayForCountry("!@#$%^&*","AT"));
        assertEquals("no", hs.isPublicHolidayForCountry("01112017","$#"));
        assertEquals("no", hs.isPublicHolidayForCountry("$#@!%$#!","#@"));
    }

    @Test
    void getNumberOfHolidays() {
        assertEquals(13, hs.getNumberOfPublicHolidays("2017","AT"));
        assertEquals(15, hs.getNumberOfPublicHolidays("2022","FI"));
        assertEquals(9, hs.getNumberOfPublicHolidays("2022","CU"));
        assertEquals(14, hs.getNumberOfPublicHolidays("1997","LV"));
        assertEquals(19, hs.getNumberOfPublicHolidays("2022","DE"));
    }

    //Empty or invalid value -> 0
    @Test
    void getNumberOfHolidaysEmptyOrInvalidValue() {
        assertEquals(0, hs.getNumberOfPublicHolidays("2017","0"));
        assertEquals(0, hs.getNumberOfPublicHolidays("-1","FI"));
        assertEquals(0, hs.getNumberOfPublicHolidays("1","1"));
        assertEquals(0, hs.getNumberOfPublicHolidays("sfa","LV"));
        assertEquals(0, hs.getNumberOfPublicHolidays("2022","fa"));
    }
}