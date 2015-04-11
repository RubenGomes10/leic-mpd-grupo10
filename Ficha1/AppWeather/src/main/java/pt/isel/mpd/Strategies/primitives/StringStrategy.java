package pt.isel.mpd.Strategies.primitives;

import pt.isel.mpd.jsonzai.JsonParser;
import pt.isel.mpd.parseUtils.ParseUtils;


/**
 * Created by HS on 08/04/2015.
 */
public class StringStrategy extends PrimitiveStrategies<String> {

    @Override
    public String process(String strJson, Class<?> c, JsonParser jsonParser) {
        //jsonParser.consume(strJson,jsonParser::consumeSpaces);
        String res = ParseUtils.parseString(strJson, '\"', jsonParser.getPos()+1, jsonParser.getPos()+1);
        jsonParser.setPos(jsonParser.getPos() + res.length()+2);
        return res;
    }
}