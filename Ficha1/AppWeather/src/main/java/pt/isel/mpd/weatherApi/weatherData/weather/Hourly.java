package pt.isel.mpd.weatherApi.weatherData.weather;

import java.util.List;

/**
 * Created by Ruben Gomes on 14/04/2015.
 */
public class Hourly {

    public int FeelsLikeC;
    public int HeatIndexC;
    public int humidity;
    public int tempC;
    public String time;
    public String windspeedKmph;
    List<WeatherDesc> weatherDesc;
    List<WeatherIconUrl> weatherIconUrl;
}
