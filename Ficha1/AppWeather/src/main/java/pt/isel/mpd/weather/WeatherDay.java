package pt.isel.mpd.weather;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ruben Gomes on 31/03/2015.
 */
public class WeatherDay {

    private Astronomy day_astronomy;

    private Date date;

    private int maxTempC,
            maxTempF,
            minTempC,
            minTempF;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

}