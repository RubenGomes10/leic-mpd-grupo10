package pt.isel.mpd.weatherApi;

import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.mpd.jsonzai.JsonParser;
import pt.isel.mpd.streamUtils.IOUtils;
import pt.isel.mpd.streamUtils.UrlStreamSupplier;
import pt.isel.mpd.weatherApi.weatherData.requests.WeatherRequest;

import java.io.BufferedInputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ruben Gomes on 14/04/2015.
 */
public class WeatherDataTest {

    private static final String APP_KEY = "&key=bc7c2e7a68a6f65cab1533b2c9ce9";
    private static final String URL_API = "http://api.worldweatheronline.com/free/v2/weather.ashx?";
    private static final String LOCATION = "q=Lisbon";
    private static final String FORMAT = "&format=json";
    private static final String DATE = "&date=2015-03-20";
    private static final String ENDDATE = "&enddate=2015-03-30";
    private static String uri;

    @BeforeClass
    public static void setUri(){
        uri = URL_API+LOCATION+FORMAT+APP_KEY+DATE+ENDDATE;
    }

    @Test
    public  void testWeatherDayDataRequest() throws Exception {

        UrlStreamSupplier responseUrl = new UrlStreamSupplier(uri);
        BufferedInputStream reader = new BufferedInputStream(responseUrl.get());
        String response = IOUtils.getStringFromInputStream(reader);
        JsonParser parser = new JsonParser();

        WeatherDay weatherDay = parser.<WeatherDay>toObject(response,WeatherDay.class);

        WeatherRequest weatherRequest = weatherDay.data.request.get(0);

        assertEquals(weatherRequest.query,"Lisbon, Portugal");
        assertEquals(weatherRequest.type,"City");
        




    }


}
