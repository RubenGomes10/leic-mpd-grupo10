package pt.isel.mpd.streamUtils;

import java.io.BufferedInputStream;
import java.io.IOException;

public class IOUtils {

    public static String getStringFromInputStream(BufferedInputStream rd){
        StringBuilder sb = new StringBuilder();
        int c;

        try {
            while((c = rd.read()) != -1){
             sb.append((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
