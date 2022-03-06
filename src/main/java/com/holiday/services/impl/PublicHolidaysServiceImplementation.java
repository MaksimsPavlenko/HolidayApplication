package com.holiday.services.impl;

import com.holiday.network.DataReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.holiday.constants.ProjectConstants.*;

@Component
public class PublicHolidaysServiceImplementation {

    @Autowired
    DataReader reader;

    public int numberOfHolidays(String year, String code) {
        try{
            JSONArray json = reader.readJsonFromUrl(V3 + String.format("/PublicHolidays/%s/%s", year,code));
            return json.length();
        }catch (JSONException | IOException ex){
            System.out.println("Exception : " + ex);
            return 0;
        }
    }

    public String isHolidayForCountry(String date, String code) {
        String jsonDate;
        char[] dateArray = date.toCharArray();
        //check if input str.size is valid (dd = 2 chars , mm = 2 chars)
        if(dateArray.length > 4){
            String dd = new String(dateArray, 0, 2);
            String mm = new String(dateArray, 2, 2);
            String yyyy = new String(dateArray, 4, dateArray.length - 4);

            if (isValidDate(dd, mm, yyyy)) {
                String convertedDate = String.format("%s-%s-%s", yyyy, mm, dd);

                try{
                    JSONArray json = reader.readJsonFromUrl(V3 + String.format("/PublicHolidays/%s/%s", yyyy, code));

                    for (int i = 0; i < json.length(); i++) {
                        jsonDate = json.getJSONObject(i).get("date").toString();

                        if (jsonDate.equals(convertedDate)) {
                            return "yes";
                        }
                    }
                }catch (JSONException | IOException ex){
                    System.out.println("Exception : " + ex);
                }
            }
        }
        return "no";
    }

    private boolean isValidDate(String dd, String mm, String yyyy) {
        String dateStr = String.format("%s/%s/%s", yyyy, mm, dd);
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(LOCAL_DATE_FORMAT));
        } catch (DateTimeParseException exception) {
            System.out.println("Exception : " + exception);
            return false;
        }
        return true;
    }

    public void setReader(DataReader reader) {
        this.reader = reader;
    }
}
