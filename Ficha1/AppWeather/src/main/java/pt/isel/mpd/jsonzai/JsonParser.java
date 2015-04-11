package pt.isel.mpd.jsonzai;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.Strategies.arrays.ArrayStrategy;
import pt.isel.mpd.Strategies.objects.ObjectStrategy;
import pt.isel.mpd.Strategies.primitives.CharStrategy;
import pt.isel.mpd.Strategies.primitives.IntegerStrategy;
import pt.isel.mpd.Strategies.primitives.StringStrategy;
import pt.isel.mpd.string.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {

    private Map<Class<?>, TypeStrategy> map1;
            private Map<Character, TypeStrategy> map;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    private int pos;

    public JsonParser() {

        map = new HashMap<Character, TypeStrategy>();
        this.pos = 0;
        inicializeMap2();
    }

    private void inicializeMap2() {
        map.put('"', new StringStrategy());
        map.put('\'', new CharStrategy());
        map.put('{', new ObjectStrategy());
        map.put('[', new ArrayStrategy());
    }

    /**
     * @param <T>  - Generic type
     * @param src  - Jason code
     * @param dest - Destiny Class
     * @return
     */
    public <T> Object toObject(String src, Class<T> dest) throws InstantiationException, IllegalAccessException {

        if (dest == null) throw new IllegalArgumentException("no dest");
        if (src == null) throw new IllegalArgumentException("no src");
       T instance = null;

        try {
            instance = dest.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return parseJson(src, instance);

    }

    /**
     * @param src  - input data to change in JsonArray
     * @param dest - Class to instantiate a generic T
     * @param <T>  - Generic parameter w who refers which object instantiate
     * @return returns a list of object`s t
     */
    public <T> List<T> toList(String src, Class<T> dest) {
        if (dest == null) throw new IllegalArgumentException("no dest");
        if (src == null) throw new IllegalArgumentException("no src");
        return null;
    }

   public Object  parseJson(String s, Object instance) throws InstantiationException, IllegalAccessException {

       return internalParseJson(s,instance);
   }

    private Object internalParseJson(String jasonStr, Object instance) throws InstantiationException, IllegalAccessException {

        char character ;
        if(pos == 0){
            character = jasonStr.charAt(pos++); // TODO procura o caracter não nulo (removendo os espaços em branco)
        }
        else{
            if(jasonStr.charAt(pos)=='}')
                character = '}';
            else
                character = jasonStr.charAt(jasonStr.indexOf(":",pos++)+ 2);
        }
        if(character != '}') {

            TypeStrategy ts = map.get(character);
            if (ts== null) ts= new IntegerStrategy();

            String fieldName = StringUtils.parseStringWithoutSpaces(jasonStr, ':', pos);

            // TODO retirar espaços
            pos+= StringUtils.parseStringLength(jasonStr,':',pos,1);
            //pos += valueToPutInField.length() +1;

            Field field = null;

            try {
                field =instance.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

            if (field != null)
                field.set(instance, ts.process(jasonStr, instance.getClass(), this));

            return internalParseJson(jasonStr,instance);

        }
        return instance;
    }




}