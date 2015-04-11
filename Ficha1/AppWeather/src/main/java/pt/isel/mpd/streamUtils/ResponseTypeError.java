package pt.isel.mpd.streamUtils;


import java.util.function.IntConsumer;

/**
 * Created by Ruben Gomes on 31/03/2015.
 */
public class ResponseTypeError implements IntConsumer{


    public void accept(int value) {
        System.out.println("Server response code : "+value);
    }
}
