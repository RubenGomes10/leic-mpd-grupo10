package pt.isel.mpd.weatherApi.weatherData;

import pt.isel.mpd.weatherApi.weatherData.currentConditions.WeatherCurrentCondition;
import pt.isel.mpd.weatherApi.weatherData.requests.WeatherRequest;
import pt.isel.mpd.weatherApi.weatherData.weather.Weather;

import java.util.List;


public class WeatherData {

    public List<WeatherCurrentCondition> current_condition;
    public List<WeatherRequest> request;
    public List<Weather> weather;
}
