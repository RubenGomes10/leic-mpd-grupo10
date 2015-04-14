package pt.isel.mpd.streamUtils;

import com.google.common.io.ByteStreams;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Supplier;

public class UrlStreamSupplier implements Supplier<InputStream> {

    private final String url;

    public UrlStreamSupplier(String url){
        this.url = url;
    }

    @Override
    public InputStream get() {
        HttpURLConnection urlConnection = null;
        InputStream connInputStream = null;

        try {
            URL uri = new URL(this.url);
            urlConnection = (HttpURLConnection)uri.openConnection();
            urlConnection.connect();
            if(urlConnection.getResponseCode() != 200) {
                new ResponseTypeError().accept(urlConnection.getResponseCode());
                return null;
            }
            int size = urlConnection.getContentLength();
            System.out.printf("%d bytes available\n", size);

            connInputStream = urlConnection.getInputStream();
            // int read = connInputStream.read(data);
            byte[] data = ByteStreams.toByteArray(connInputStream); // byteStream is the solution!!
            System.out.printf("Read %d bytes\n", data.length);
            return new ByteArrayInputStream(data);


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(connInputStream != null){
                try {
                    connInputStream.close();
                } catch (IOException e) {

                }
            }
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }
    }

    public String getUrl() {
        return url;
    }
}
