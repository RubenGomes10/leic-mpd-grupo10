package pt.isel.mpd.streamUtils;

import java.util.function.IntConsumer;

public class ResponseTypeError implements IntConsumer{


    public void accept(int value) {
        System.out.println("Server response code : "+value);
    }
}
