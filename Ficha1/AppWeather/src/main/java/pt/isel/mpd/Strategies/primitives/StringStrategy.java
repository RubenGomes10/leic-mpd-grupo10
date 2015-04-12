package pt.isel.mpd.Strategies.primitives;

import pt.isel.mpd.jsonzai.JsonParser;


/**
 * Created by HS on 08/04/2015.
 */
public class StringStrategy extends PrimitiveStrategies<String> {

    @Override
    public String process(String strJson, Class<?> c, JsonParser jsonParser) {
       //Ficou equivalente ao do IntegerStrategy
       //Fiz isto para resolver o bug de par chave valor ( key : " ",), ou seja, o valor ser vazio;
       // TODO implementar mais tarde um método genérico para ser chamado aqui com expressão lambda!

        int pos = jsonParser.getPos();
        int currentPos = pos;
        char character = strJson.charAt(++currentPos);

        while(character != '\"'){
            character = strJson.charAt(++currentPos);
        }
        jsonParser.setPos(currentPos+1);

        return strJson.substring(pos+1,currentPos);

    }
}