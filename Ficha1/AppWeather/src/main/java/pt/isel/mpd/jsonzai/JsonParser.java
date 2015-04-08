package pt.isel.mpd.jsonzai;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class JsonParser {

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

            strValue=getValueFromKeyOnSrc(src, field.getName());
            //System.out.println(field.getName()+"="+s);
            if (strValue != null) {

                Integer i;
                if(isStringJustNumeric(strValue)) {
                    try {
                        i = Integer.parseInt(strValue);
                        field.setAccessible(true);
                        field.set(newT, (int) i);
                    }
                    catch (NumberFormatException n){
                    }
                }
                else {field.set(newT, strValue);}
            }
        }
        return newT;
    }

    private boolean isStringJustNumeric(String s) {
        int i;
        for (i=0; i<s.length();i++){
            if (s.charAt(i)<'0'||s.charAt(i)>'9') return false;
        }
        return true;
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

        return null;
    }
}