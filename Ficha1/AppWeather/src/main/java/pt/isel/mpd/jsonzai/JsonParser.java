package pt.isel.mpd.jsonzai;

import pt.isel.mpd.Strategies.TypeStrategy;
import pt.isel.mpd.Strategies.lists.ListStrategy;
import pt.isel.mpd.Strategies.objects.ObjectStrategy;
import pt.isel.mpd.Strategies.primitives.BooleanStrategy;
import pt.isel.mpd.Strategies.primitives.CharStrategy;
import pt.isel.mpd.Strategies.primitives.IntegerStrategy;
import pt.isel.mpd.Strategies.primitives.StringStrategy;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonParser {


    //Falta implementar os tokens que o engenheiro quer! ;) o resto ja mudei e ja verifiquei nos testes e esta funcional!!

    private int pos;
    private Map<Character, TypeStrategy> map;

    public JsonParser() {
        map = new HashMap<>();
        this.pos = 0;
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
        map.put('\'', new CharStrategy());
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
           // e.printStackTrace();
        } catch (IllegalAccessException e) {
           // e.printStackTrace();
        }
        return parseJsonToObject(src, instance);
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

        List<T> returnList = new LinkedList<>();

        while(src.charAt(this.pos) != ']'){
            try {
                returnList.add(this.toObject(src,dest));
            } catch (InstantiationException e) {
                //e.printStackTrace();
            } catch (IllegalAccessException e) {
                //e.printStackTrace();
            }
        }
        return returnList;
    }

   public <T>T parseJsonToObject(String s, T instance) throws InstantiationException, IllegalAccessException {
       ++pos;
       return internalParseJson(s,instance);
   }

    private <T> T internalParseJson(String jasonStr, T instance) throws InstantiationException, IllegalAccessException {

        char jsonType = getNextJasonType(jasonStr); // isto passa a ser token acho
        TypeStrategy ts ;

        String jasonFieldKey = getNextJasonFieldKey(jasonStr);     // no final o ponteiro deve estar depois dos dois pontos
        Field field = getField(jasonFieldKey,instance.getClass());

            char nextType = getNextType(jasonStr); // isto também
            ts = this.map.get(nextType);

            if (field != null) {
                field.set(instance, ts.process(jasonStr,field, this));
            } else {
                ts.process(jasonStr,field, this);
            }

        if (finishedObject(jasonStr)) return instance;
        else internalParseJson(jasonStr, instance);

        return instance;
    }

    private char getNextType(String jasonStr) {
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

    private String getNextJasonFieldKey(String str) {
        int auxPos1=pos,auxPos2;

        while (str.charAt(auxPos1) != '\"') { //Find first quotes
            auxPos1++;
        }
        auxPos2=auxPos1+1;
        while (str.charAt(auxPos2) != '\"') { //Find second quotes
            auxPos2++;
        }
        pos=auxPos2+1;
        return str.substring(auxPos1+1,auxPos2);
    }

    private char getNextJasonType(String s) {

        while ( s.charAt(pos) != '{' &&
                s.charAt(pos) != '[' &&
                s.charAt(pos) != '"') {
            pos++;
        }
        return s.charAt(pos);
    }


    private Field getField(String fieldName,Class<?> instance) {
       Field field = null;

        try {
            field =  instance.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
           // e.printStackTrace();
        }
        return field;
    }

}