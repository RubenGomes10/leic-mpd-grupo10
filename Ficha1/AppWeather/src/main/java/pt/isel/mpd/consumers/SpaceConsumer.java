package pt.isel.mpd.consumers;

import java.util.function.Consumer;

/**
 * Created by Ruben Gomes on 11/04/2015.
 */

@FunctionalInterface
public interface SpaceConsumer extends Consumer<String> {

    void accept(String strJson);
}
