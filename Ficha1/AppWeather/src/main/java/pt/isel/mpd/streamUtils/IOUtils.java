package pt.isel.mpd.streamUtils;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Ruben Gomes on 12/04/2015.
 */
public class IOUtils {

    public static String getStringReader(BufferedReader rd){
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            while((line = rd.readLine()) != null){
             sb.append(line);
             //System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
