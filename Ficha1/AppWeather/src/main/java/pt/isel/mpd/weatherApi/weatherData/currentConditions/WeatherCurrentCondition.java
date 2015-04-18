package pt.isel.mpd.weatherApi.weatherData.currentConditions;

import pt.isel.mpd.weatherApi.weatherData.weather.WeatherDesc;
import pt.isel.mpd.weatherApi.weatherData.weather.WeatherIconUrl;

import java.util.List;


public class WeatherCurrentCondition {


    public String FeelsLikeC;
    public String humidity;
    public String temp_C;
    public String observation_time;
    public String windspeedKmph;
    public List<WeatherDesc> weatherDesc;
    public List<WeatherIconUrl> weatherIconUrl;
}
