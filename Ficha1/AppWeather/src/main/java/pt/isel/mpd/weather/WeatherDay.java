package pt.isel.mpd.weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ruben Gomes on 31/03/2015.
 */
public class WeatherDay {

    private Astronomy day_astronomy ;

    private Date date;

    private int maxTempC,
        maxTempF,
        minTempC,
        minTempF;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public WeatherDay(JSONObject weather) throws JSONException, ParseException {
        this.day_astronomy =  new Astronomy (weather.getJSONObject("astronomy"));
        this.date = format.parse(weather.getString("date"));
        this.maxTempC = weather.getInt("maxTempC");
        this.maxTempF = weather.getInt("maxTempF");
        this.minTempC = weather.getInt("minTempC");
        this.minTempF = weather.getInt("minTempF");
    }


    public int getMaxTempC() {
        return maxTempC;
    }

    public Astronomy getDay_astronomy() {
        return day_astronomy;
    }

    public Date getDate() {
        return date;
    }

    public int getMaxTempF() {
        return maxTempF;
    }

    public int getMinTempC() {
        return minTempC;
    }

    public int getMinTempF() {
        return minTempF;
    }
}
