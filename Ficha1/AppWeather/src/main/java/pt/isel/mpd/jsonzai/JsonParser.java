package pt.isel.mpd.jsonzai;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.Strategies.lists.ListStrategy;
import pt.isel.mpd.Strategies.objects.ObjectStrategy;
import pt.isel.mpd.Strategies.primitives.BooleanStrategy;
import pt.isel.mpd.Strategies.primitives.IntegerStrategy;
import pt.isel.mpd.Strategies.primitives.StringStrategy;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonParser {

    private int pos;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Map<Character, TypeStrategy> map;

    public JsonParser() {
        map = new HashMap<>();
        this.pos = 1;
        initializeMap();

    }
    public DateFormat getDateFormat() {
        return dateFormat;
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

    private <T> T internalParseJson(String src, T instance) throws Exception {

         do {
            String jsonFieldName = getNextJsonFieldName(src);
            Field field = getField(jsonFieldName, instance.getClass());

            char nextType = getNextFieldType(src);
            TypeStrategy ts = this.map.get(nextType);

            ts.process(src, instance, field, this);
        }
         while(!finishedObject(src));
        pos++;
        return instance;
    }

    public <T> List<T> toList(String src, Class<T> dest) throws Exception {
        if (dest == null) throw new IllegalArgumentException("no dest");
        if (src == null) throw new IllegalArgumentException("no src");

        List<T> returnList = new LinkedList<>();

        do {
            returnList.add(this.toObject(src,dest));
        }
        while(!finishedList(src));

        return returnList;
    }

    private char getNextFieldType(String src) {
        char character = src.charAt(this.pos);
        while ( character != '{' && character != '[' &&
                character != '"' && character != '\''&&
                character != 'f' && character != 't' &&
                !(character>='0' && character <='9')){
            character = src.charAt(++pos);
        }
        return src.charAt(pos);
    }

    private boolean finishedObject(String src) {
        return getNextJsonStateChar(src) == '}';
    }

    private boolean finishedList(String src) {
        return getNextJsonStateChar(src) == ']';
    }

    private char getNextJsonStateChar(String src) {
        char c = src.charAt(pos);
        while ( c !=']'&&
                c !=','&&
                c !='}') {
            pos++;
            c = src.charAt(pos);
        }
        return c;
    }

    private String getNextJsonFieldName(String src) {
        int auxPos1=pos,auxPos2;

        while (src.charAt(auxPos1) != '\"') {
            auxPos1++;
        }
        auxPos2=auxPos1+1;
        while (src.charAt(auxPos2) != '\"') {
            auxPos2++;
        }
        pos=auxPos2+1;
        return src.substring(auxPos1 + 1, auxPos2);
    }

    private Field getField(String fieldName, Class<?> instance) {
        try {
            return instance.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
}