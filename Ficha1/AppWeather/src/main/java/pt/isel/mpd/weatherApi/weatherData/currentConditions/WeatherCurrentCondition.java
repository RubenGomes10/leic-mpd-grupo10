package pt.isel.mpd.weatherApi.weatherData.currentConditions;

import pt.isel.mpd.weatherApi.weatherData.weather.WeatherDesc;
import pt.isel.mpd.weatherApi.weatherData.weather.WeatherIconUrl;

import java.util.List;

/**
 * Created by Ruben Gomes on 14/04/2015.
 */
public class WeatherCurrentCondition {


    public int FeelsLikeC;
    public int humidity;
    public int temp_C;
    public String observation_time;
    public String windspeedKmph;
    List<WeatherDesc> weatherDesc;
    List<WeatherIconUrl> weatherIconUrl;
}
