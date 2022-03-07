package com.holiday.services.impl;

import com.holiday.network.DataReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublicHolidaysServiceImplementationTest {

    PublicHolidaysServiceImplementation si = new PublicHolidaysServiceImplementation();

    @BeforeEach
    void setUp(){
        DataReader dr = new DataReader();
        si.setReader(dr);
    }

    @Test
    void isHolidayForCountryYes() {
        assertEquals("yes", si.isPublicHolidayForCountry("01112017","AT"));
        assertEquals("yes", si.isPublicHolidayForCountry("26052022","FI"));
        assertEquals("yes", si.isPublicHolidayForCountry("27072022","CU"));
        assertEquals("yes", si.isPublicHolidayForCountry("23062021","LV"));
        assertEquals("yes", si.isPublicHolidayForCountry("31102022","DE"));
    }
    @Test
    void isHolidayForCountryNo() {
        assertEquals("no", si.isPublicHolidayForCountry("11111111","AT"));
        assertEquals("no", si.isPublicHolidayForCountry("01112017","11"));
        assertEquals("no", si.isPublicHolidayForCountry("11111111","11"));

        assertEquals("no", si.isPublicHolidayForCountry("qwertyui","AT"));
        assertEquals("no", si.isPublicHolidayForCountry("01112017","at"));
        assertEquals("no", si.isPublicHolidayForCountry("qweasdqw","at"));

        assertEquals("no", si.isPublicHolidayForCountry("QWEQWEQW","AT"));
        assertEquals("no", si.isPublicHolidayForCountry("QWEQWEQW","ATT"));

        assertEquals("no", si.isPublicHolidayForCountry("!@#$%^&*","AT"));
        assertEquals("no", si.isPublicHolidayForCountry("01112017","$#"));
        assertEquals("no", si.isPublicHolidayForCountry("$#@!%$#!","#@"));
    }

    @Test
    void getNumberOfHolidays() {
        assertEquals(13, si.numberOfPublicHolidays("2017","AT"));
        assertEquals(15, si.numberOfPublicHolidays("2022","FI"));
        assertEquals(9, si.numberOfPublicHolidays("2022","CU"));
        assertEquals(14, si.numberOfPublicHolidays("1997","LV"));
        assertEquals(19, si.numberOfPublicHolidays("2022","DE"));
    }

    //Empty or invalid value -> 0
    @Test
    void getNumberOfHolidaysEmptyOrInvalidValue() {
        assertEquals(0, si.numberOfPublicHolidays("2017","0"));
        assertEquals(0, si.numberOfPublicHolidays("-1","FI"));
        assertEquals(0, si.numberOfPublicHolidays("1","1"));
        assertEquals(0, si.numberOfPublicHolidays("sfa","LV"));
        assertEquals(0, si.numberOfPublicHolidays("2022","fa"));
    }

}