package pt.isel.mpd.Strategies.primitives;

import pt.isel.mpd.jsonzai.JsonParser;

/**
 * Created by HS on 08/04/2015.
 */
public class IntegerStrategy extends PrimitiveStrategies<Integer> {

    @Override
    public Integer process(String s, Class<?> c, JsonParser jsonParser) {
        int pos = jsonParser.getPos();
        int lastNumberPos=pos;
        int i=0;


        while (s.charAt(lastNumberPos)>='0' && s.charAt(lastNumberPos)<='9') {
            lastNumberPos++;
        }

        jsonParser.setPos(++lastNumberPos);

        try {
            i = Integer.parseInt(s.substring(pos,lastNumberPos));
        }
        catch (NumberFormatException n){
        }

        return (int)i;
    }
}
