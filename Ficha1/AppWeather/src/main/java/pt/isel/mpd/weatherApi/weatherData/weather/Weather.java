package pt.isel.mpd.weatherApi.weatherData.weather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ruben Gomes on 31/03/2015.
 */
public class Weather {

    public List<Astronomy> day_astronomy;
    public List<Hourly> hourly;

    public String  date;

    public String  maxTempC,
               minTempC;

    public String  uvIndex;


    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

}