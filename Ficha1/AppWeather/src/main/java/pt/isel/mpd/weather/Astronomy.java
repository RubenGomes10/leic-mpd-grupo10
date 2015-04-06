package pt.isel.mpd.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ruben Gomes on 31/03/2015.
 */
public class Astronomy {

    private String moonRise,
                   moonSet,
                   sunRise,
                   sunSet;

    public Astronomy(JSONObject astronomy) throws JSONException {
        this.moonRise = astronomy.getString("moonrise");
        this.moonSet = astronomy.getString("moonset");
        this.sunRise = astronomy.getString("sunrise");
        this.sunSet = astronomy.getString("sunset");
    }

    public String getMoonRise() {
        return moonRise;
    }

    public String getMoonSet() {
        return moonSet;
    }

    public String getSunRise() {
        return sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }
}
