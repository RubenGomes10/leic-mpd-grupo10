package pt.isel.mpd.jsonzai;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.Strategies.arrays.ArrayStrategy;
import pt.isel.mpd.Strategies.objects.ObjectStrategy;
import pt.isel.mpd.Strategies.primitives.BooleanStrategy;
import pt.isel.mpd.Strategies.primitives.CharStrategy;
import pt.isel.mpd.Strategies.primitives.IntegerStrategy;
import pt.isel.mpd.Strategies.primitives.StringStrategy;
import pt.isel.mpd.consumers.SpaceConsumer;
import pt.isel.mpd.parseUtils.ParseUtils;

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
        initializeMap();
    }

    private void initializeMap() {
        map.put('\"', new StringStrategy());
        map.put('\'', new CharStrategy());
        map.put('{', new ObjectStrategy());
        map.put('[', new ArrayStrategy());
        map.put('f',new BooleanStrategy());
        map.put('t',new BooleanStrategy());
    }

    /**
     * @param <T>  - Generic type
     * @param src  - Jason code
     * @param dest - Destiny Class
     * @return
     */
    public <T> T toObject(String src, Class<T> dest) throws InstantiationException, IllegalAccessException{

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

   public <T>T  parseJson(String s, T instance) throws InstantiationException, IllegalAccessException {

       return internalParseJson(s,instance);
   }

    private <T> T internalParseJson(String jasonStr, T instance) throws InstantiationException, IllegalAccessException{

        char character ;
        if(pos == 0){
            consume(jasonStr,this::consumeSpacesAndComma);
            character = jasonStr.charAt(pos++);
            if(character == '[' || character == '{'){
                this.consume(jasonStr,this::consumeSpacesAndComma);
                return internalParseJson(jasonStr,instance);
            }
        }
        else{
            if(jasonStr.charAt(pos)=='}')
                character = '}';
            else {
                character = jasonStr.charAt(jasonStr.indexOf(":", pos++) + 2);
                if(character == '{') {
                    consume(jasonStr, this::consumeSpacesAndComma);
                    return internalParseJson(jasonStr, instance);
                }
            }
        }
        if(character != '}') {

            TypeStrategy ts = map.get(character);
            if (ts== null) ts= new IntegerStrategy();

            this.consume(jasonStr,this::consumeSpacesAndComma);
            String fieldName = ParseUtils.parseStringWithoutSpaces(jasonStr, ':',pos-1);
            pos+= fieldName.length()+1;
            this.consume(jasonStr,this::consumeSpacesAndComma);

            Field field = getField(fieldName,instance);

            if (field != null)
                field.set(instance, ts.process(jasonStr, instance.getClass(), this));
            else{
                if(character == '\"')
                    pos+=ParseUtils.parseString(jasonStr,'\"',pos+1,pos+1).length() +2;
                else
                    pos+=ParseUtils.parseString(jasonStr,',',pos,pos).length();
            }

            this.consume(jasonStr,this::consumeSpacesAndComma);
            return internalParseJson(jasonStr,instance);

        }
        return instance;
    }

    public void consumeSpacesAndComma(String strJson) {

        int currentPos = this.getPos();
        char character= strJson.charAt(currentPos);
        while(character == ' ' || character == '\n' || character == ',' || character == ':'){
           character = strJson.charAt(++currentPos);
        }
        this.setPos(currentPos);
    }


    public void consume(String strJson,SpaceConsumer consumer){
        consumer.accept(strJson);
    }


    private Field getField(String fieldName,Object instance) {
       Field field = null;

        try {
            field =  instance.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return field;
    }

}