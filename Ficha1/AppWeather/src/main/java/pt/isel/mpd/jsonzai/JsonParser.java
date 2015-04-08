package pt.isel.mpd.jsonzai;

import pt.isel.mpd.Github.GithubRepo;
import pt.isel.mpd.Strategies.GithubRepoStrategy;
import pt.isel.mpd.Strategies.IntegerStrategy;
import pt.isel.mpd.Strategies.StringStrategy;
import pt.isel.mpd.Strategies.TypeStrategy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {

    private Map<Class , TypeStrategy> map;


    public JsonParser (){
        map=new HashMap<Class, TypeStrategy>();

        inicializeMap();
    }

    private void inicializeMap() {
        map.put(String.class,new StringStrategy());
        map.put(int.class,new IntegerStrategy());
        map.put (GithubRepo.class, new GithubRepoStrategy());
    }
    /**
     *
     * @param src       - Jason code
     * @param dest      - Destiny Class
     * @param <T>       - Generic type
     * @return
     */
    public <T>T toObject(String src, Class<T> dest) throws  NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException {

        if(dest == null) throw new IllegalArgumentException("no dest");
        if(src == null) throw new IllegalArgumentException("no src");

        T newT = dest.newInstance();

        Field[] fields = dest.getDeclaredFields();
        String strValue=null;

        for (Field field :fields){

            Class<?> type = field.getType();
            strValue=getValueFromKeyOnSrc(src, field.getName());

            TypeStrategy ts = map.get(type);
            field.set(newT, ts.method(strValue));

        }
        return newT;
    }

    private String getValueFromKeyOnSrc(String src, String fieldName) { // output's the value of the corresponding key if exists
        if (fieldName == null)return null;

        src=src.replaceAll("\\s*:", ":");
        src=src.replaceAll(":\\s*",":");

        int idxTwoPoints;
        int idxKey=src.indexOf(fieldName+":");
        String value = "";
        if (idxKey>0) {
            src = src.substring(idxKey);

            idxTwoPoints = src.indexOf(":");
            int idxVirgula = src.indexOf(",");
            int idxFirst = src.indexOf("\"");
            int idxSecond = src.indexOf("\"", idxFirst + 1);

            if (src.charAt(idxTwoPoints + 1) == '\"') {
                value = src.substring(idxFirst + 1, idxSecond);
            } else
                value = src.substring(idxTwoPoints + 1, idxVirgula);
        }
        return value;
    }


        /**
     *
     * @param src - input data to change in JsonArray
     * @param dest - Class to instantiate a generic T
     * @param <T> - Generic parameter w who refers which object instantiate
     * @return returns a list of object`s t
     */
    public <T> List<T> toList(String src, Class<T> dest) {
        if(dest == null) throw new IllegalArgumentException("no dest");
        if(src == null) throw new IllegalArgumentException("no src");

        if (src.charAt(0)=='[')
            

        return null;
    }
}