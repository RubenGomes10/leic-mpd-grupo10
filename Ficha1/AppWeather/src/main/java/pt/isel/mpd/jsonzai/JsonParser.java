package pt.isel.mpd.jsonzai;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.Strategies.lists.ListStrategy;
import pt.isel.mpd.Strategies.objects.ObjectStrategy;
import pt.isel.mpd.Strategies.primitives.BooleanStrategy;
import pt.isel.mpd.Strategies.primitives.IntegerStrategy;
import pt.isel.mpd.Strategies.primitives.StringStrategy;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonParser {

   private int pos;
    private Map<Character, TypeStrategy> map;

    public JsonParser() {
        map = new HashMap<>();
        this.pos = 1;
        initializeMap();
    }

    public int getPos() {
        return pos;
    }
    public void setPos(int pos) {
        this.pos = pos;
    }

    private void initializeMap() {
        map.put('\"', new StringStrategy());
        map.put('{', new ObjectStrategy());
        map.put('[', new ListStrategy());
        map.put('f',new BooleanStrategy());
        map.put('t',new BooleanStrategy());
        map.put('0',new IntegerStrategy());
        map.put('1',new IntegerStrategy());
        map.put('2',new IntegerStrategy());
        map.put('3',new IntegerStrategy());
        map.put('4',new IntegerStrategy());
        map.put('5',new IntegerStrategy());
        map.put('6',new IntegerStrategy());
        map.put('7',new IntegerStrategy());
        map.put('8',new IntegerStrategy());
        map.put('9',new IntegerStrategy());
    }

    public <T> T toObject(String src, Class<T> dest) throws Exception {

        if (dest == null) throw new IllegalArgumentException("no dest");
        if (src == null) throw new IllegalArgumentException("no src");

        T instance = dest.newInstance();

        return internalParseJson(src, instance);
    }

    private <T> T internalParseJson(String jsonStr, T instance) throws Exception {

        while(true) {
            String jsonFieldName = getNextJsonFieldName(jsonStr);
            Field field = getField(jsonFieldName, instance.getClass());

            char nextType = getNextFieldType(jsonStr);
            TypeStrategy ts = this.map.get(nextType);

            ts.process(jsonStr, instance, field, this);

            if (finishedObject(jsonStr))
                break;
        }

        return instance;
    }

    public <T> List<T> toList(String src, Class<T> dest) throws Exception {
        if (dest == null) throw new IllegalArgumentException("no dest");
        if (src == null) throw new IllegalArgumentException("no src");

        List<T> returnList = new LinkedList<>();

        while(src.charAt(this.pos+1) != ']'){
            returnList.add(this.toObject(src,dest));
        }
        return returnList;
    }

    private char getNextFieldType(String jasonStr) {
        char character = jasonStr.charAt(this.pos);
        while ( character != '{' && character != '[' &&
                character != '"' && character != '\''&&
                character != 'f' && character != 't' &&
                !(character>='0' && character <='9')){
            character = jasonStr.charAt(++pos);
        }
        return jasonStr.charAt(pos);
    }

    private boolean finishedObject(String substring) {
        char c = getNextCloseJasonObject(substring);
        if (c == '}') return  true;
        else return false;
    }

    private char getNextCloseJasonObject(String substring) {

        char c = substring.charAt(pos);
        while ( c !=']'&&
                c !=','&&
                c !='}') {
            pos++;
            c = substring.charAt(pos);
        }
        return c;
    }

    private String getNextJsonFieldName(String str) {
        int auxPos1=pos,auxPos2;

        while (str.charAt(auxPos1) != '\"') { //Find first quotes
            auxPos1++;
        }
        auxPos2=auxPos1+1;
        while (str.charAt(auxPos2) != '\"') { //Find second quotes
            auxPos2++;
        }
        pos=auxPos2+1;
        return str.substring(auxPos1 + 1, auxPos2);
    }

    private Field getField(String fieldName,Class<?> instance) {
        try {
            return instance.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
}