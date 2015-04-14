package pt.isel.mpd.consumers;

import java.util.function.Consumer;

@FunctionalInterface
public interface SpaceConsumer extends Consumer<String> {

    void accept(String strJson);
}
