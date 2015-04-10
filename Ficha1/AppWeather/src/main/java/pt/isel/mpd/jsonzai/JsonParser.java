package pt.isel.mpd.jsonzai;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.Strategies.arrays.ArrayStrategy;
import pt.isel.mpd.Strategies.lists.ListStrategy;
import pt.isel.mpd.Strategies.objects.ObjectsStrategy;
import pt.isel.mpd.Strategies.primitives.*;
import pt.isel.mpd.string.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {

    private Map<Class<?>, TypeStrategy> map;

    public int pos = 0;

    public JsonParser() {
        map = new HashMap<Class<?>, TypeStrategy>();

        inicializeMap();
    }

    private void inicializeMap() {
        map.put(String.class,new StringStrategy());
        map.put(int.class,new IntegerStrategy());
        map.put(char.class,new CharStrategy());
        map.put(ObjectsStrategy.class,new ObjectsStrategy());
        map.put(ArrayStrategy.class, new ArrayStrategy());
        map.put(ListStrategy.class, new ListStrategy());
    }

    /**
     * @param src  - Jason code
     * @param dest - Destiny Class
     * @param <T>  - Generic type
     * @return
     */
    public <T> T toObject(String src, Class<T> dest) throws InstantiationException {

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

        parseJson(src,dest,instance);

        return instance;

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




   public String  parseJson(String s,Class<?> c,Object instance) throws InstantiationException {
       return internalParseJson(s,c,instance);
   }

    private String internalParseJson(String s,Class<?> c,Object instance) throws InstantiationException {
        char character ;
        if(pos == 0){
        character = s.charAt(pos++);
        }
        else{
            if(s.charAt(pos)=='}')
                character = '}';
            else
                character = s.charAt(s.indexOf(":",pos++)+ 2);
        }
        if(character != '}') {
            switch (character) {
                case '[': return processArray(s, c,instance);
                case '{': return internalParseJson(s, c,instance);
                case '\'':return processChar(s, c,instance);
                case '\"': return processString(s, c,instance);
                case 'f' : return processBoolean(s,c,instance);
                case 't' : return processBoolean(s,c,instance);
                default: return processInt(s, c,instance);
            }
        }
        return s;
    }

    private String processBoolean(String s, Class<?> c, Object instance) throws InstantiationException {
        String fieldName = StringUtils.parseStringWithoutSpaces(s, ':', pos);

        pos+= StringUtils.parseStringLength(s,':',pos,1);

        String valueToPutInField = StringUtils.parseString(s, ',', pos + 1, pos);
        pos += valueToPutInField.length() +1;

        PrimitiveStrategies<Boolean> primitiveStrategy = new BooleanStrategy();

        boolean value = primitiveStrategy.process(valueToPutInField,c);

        Field field = getField(c,fieldName);
        if (field != null) {
           setField(field,instance,value);
        }
        return internalParseJson(s,c,instance);
    }

    private String processInt(String s, Class<?> c, Object instance) throws InstantiationException {
        String fieldName = StringUtils.parseStringWithoutSpaces(s, ':', pos);
        pos+= StringUtils.parseStringLength(s, ':', pos, 1);

        String valueToPutInField = StringUtils.parseString(s, ',', pos + 1, pos);
        pos += valueToPutInField.length() +1;

        PrimitiveStrategies<Integer> primitiveInteger = new IntegerStrategy();
        int value = primitiveInteger.process(valueToPutInField,c);

        Field field = getField(c,fieldName);

        if (field != null) {
            setField(field,instance,value);
        }
        return internalParseJson(s,c,instance);
    }

    private String processString(String s, Class<?> c, Object instance) throws InstantiationException {
        String fieldName = StringUtils.parseStringWithoutSpaces(s, ':', pos);
        pos+= StringUtils.parseStringLength(s,':',pos,1);

        int firstDoubleQuote = StringUtils.getIndexCharacter(s,'\"',pos);
        int secondDoubleQuote = StringUtils.getIndexCharacter(s,'\"',firstDoubleQuote+1);

        String valueToPutinField = StringUtils.parseString(s, firstDoubleQuote + 1, secondDoubleQuote);

        pos += StringUtils.parseStringLength(s,pos,firstDoubleQuote) +
                StringUtils.parseStringLength(s,firstDoubleQuote,secondDoubleQuote);

        char endOfObject = s.charAt(pos+3);
        if( endOfObject != '}')
            pos += StringUtils.parseStringLength(s,',',secondDoubleQuote,0);
        else
            pos += 3;

        PrimitiveStrategies<String> stringPrimitive = new StringStrategy();
        String value = stringPrimitive.process(valueToPutinField,c);

        Field field = getField(c,fieldName);
        if(field != null) {
            setField(field,instance,value);
        }
        return internalParseJson(s,c,instance) ;
    }

    private String processChar(String s, Class<?> c, Object instance) {
        return null;
    }

    private String processArray(String s, Class<?> c, Object instance) {
        return null;
    }

    private Field getField(Class<?> c, String fieldName) {
        Field field = null;

        try {
            field = c.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return field;
    }

    private void setField(Field field, Object instance, Object value) {
        try {
            field.set(instance,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}