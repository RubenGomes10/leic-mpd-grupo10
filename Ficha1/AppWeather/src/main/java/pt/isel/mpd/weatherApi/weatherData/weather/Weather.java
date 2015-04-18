package pt.isel.mpd.weatherApi.weatherData.weather;

import java.util.List;


public class Weather {

    public List<Astronomy> day_astronomy;
    public List<Hourly> hourly;
    public String  date;
    public String  maxTempC;
    public String  minTempC;
    public String  uvIndex;

}