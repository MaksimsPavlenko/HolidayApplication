package com.holiday.controller;

import com.holiday.services.HolidaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HolidaysController {

    @Autowired
    HolidaysService holidaysService;

    @RequestMapping(value = "holidayapi/numberOfPublicHolidays/{code}/{year}", method = RequestMethod.GET)
    public int numberOfPublicHolidays(@PathVariable("code") String code, @PathVariable("year") String year) {
        return holidaysService.getNumberOfPublicHolidays(year,code);
    }

    @RequestMapping(value = "holidayapi/isPublicHolidayForCountry/{code}/{date}", method = RequestMethod.GET)
    public String isPublicHolidayForCountry(@PathVariable("code") String code, @PathVariable("date") String date) {
        return holidaysService.isPublicHolidayForCountry(date,code);
    }

}
