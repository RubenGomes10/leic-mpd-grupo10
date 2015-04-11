package pt.isel.mpd.streamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Supplier;

/**
 * Created by Ruben Gomes on 31/03/2015.
 */
public class UrlStreamSupplier implements Supplier<InputStream> {

    private final String url;

    public UrlStreamSupplier(String url){
        this.url = url;
    }

    @Override
    public InputStream get() {
        HttpURLConnection urlConnection = null;
        InputStream inputStreamConnection = null;

        try {
            URL uri = new URL(this.url);
            urlConnection = (HttpURLConnection)uri.openConnection();
            urlConnection.connect();
            if(urlConnection.getResponseCode() != 200) {
                new ResponseTypeError().accept(urlConnection.getResponseCode());
                return null;
            }
            int size = urlConnection.getContentLength();
            byte[]data = new byte[size];

            inputStreamConnection = urlConnection.getInputStream();
            int read = inputStreamConnection.read(data);
            return new ByteArrayInputStream(data);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(inputStreamConnection != null){
                try {
                    inputStreamConnection.close();
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
