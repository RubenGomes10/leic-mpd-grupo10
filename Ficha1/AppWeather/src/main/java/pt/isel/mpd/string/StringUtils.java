package pt.isel.mpd.string;

/**
 * Created by Ruben Gomes on 10/04/2015.
 */
public class StringUtils {

    public static String parseStringWithoutSpaces(String s,char character,int currentPos){
        return s.substring(currentPos,s.indexOf(character,currentPos)).trim();
    }

    public static String parseString(String s , int startIndex, int endIndex){
        return s.substring(startIndex,endIndex);
    }

    public static String parseString(String s, char character,int startPosition,int stringPosition){
        return s.substring(startPosition,s.indexOf(character,stringPosition));
    }
    public static int parseStringLength(String s,char character,int currentPos, int numberToIncrement){
        return s.substring(currentPos,s.indexOf(character,currentPos) + numberToIncrement).length();
    }

    public static int parseStringLength(String s,int startIndex,int endIndex){
        return s.substring(startIndex,endIndex).length();
    }

    public static int getIndexCharacter(String s,char characterToStop, int startIndex){
        return s.indexOf(characterToStop,startIndex);
    }

}
