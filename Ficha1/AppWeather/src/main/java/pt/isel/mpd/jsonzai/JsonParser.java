package pt.isel.mpd.jsonzai;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pt.isel.mpd.weather.WeatherDay;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruben Gomes on 31/03/2015.
 */
public class JsonParser {



    /**
     *
     * @param src - Object weather
     * @param dest - Class to instantiate a generic T
     *
     * @return - returns a new object T using api reflection
     *
     */

    public <T> T toObject(String src, Class<T> dest) {
        if(dest == null) throw new IllegalArgumentException("no dest");//no object -> thow exception
        if(src == null) throw new IllegalArgumentException("no src");//no src -> thow exception

        JSONObject jsonObj = null;
        T instance = null;

        try {
            jsonObj = new JSONObject(src);
            instance = dest.newInstance();


            Field[] x = dest.getDeclaredFields(); // TODO: verificar a diferença getFields e getDeclaredFields
            String s=null;

            for (Field f :x){                           // por cada Field da Classe vai verificar se existe o mesmo nome na source
                Object value = jsonObj.get(f.getName());

                f.setAccessible(true);
                f.set(instance, (value instanceof String) ? (String)value:(Integer)value);

                }
                //instance = (T) dest.getConstructors()[0].newInstance(new Object[]{jsonObj});
        } catch (JSONException | InstantiationException | IllegalAccessException e) {
            e.getMessage();
        }
        return instance;

    }


        /**
     *
     * @param src - input data to change in JsonArray
     * @param dest - Class to instantiate a generic T
     * @param <T> - Generic parameter w who refers which object instantiate
     * @return returns a list of object`s t
     */
    public <T> List<T> toList(String src, Class<T> dest) {
        List<T> returnList =  new ArrayList<T>(); ;
        JSONArray response;
        try {
             response = new JSONArray(src);

            for (int i = 0; i < response.length(); i++) {
                JSONObject jsonObject = response.getJSONObject(i);
                if (jsonObject.toString().contains("weather")) {
                    String weatherArray = new String(jsonObject.toString());
                    JSONArray weathers = new JSONArray(weatherArray);
                    for (int j = 0; j < weathers.length(); j++) {
                        T instance = (T)this.toObject(weathers.getJSONObject(j).toString(),WeatherDay.class);
                        returnList.add(instance);
                    }
                }
            }
        } catch (JSONException e){
            e.getMessage();
        }
        return returnList;
    }



}