package pt.isel.mpd.weatherApi;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ruben Gomes on 31/03/2015.
 */
public class WeatherDay {

    public Astronomy day_astronomy;

    public Date date;

    public int maxTempC,
            maxTempF,
            minTempC,
            minTempF;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

}