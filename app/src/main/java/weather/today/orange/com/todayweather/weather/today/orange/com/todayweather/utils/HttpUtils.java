package weather.today.orange.com.todayweather.weather.today.orange.com.todayweather.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Orange on 2014/11/20.
 */
public class HttpUtils {

    private static final int CONNECTION_TIMEOUT = 3000;
    private static final int READ_TIMEOUT = 5000;
    private static final String ENCODE_CHARSET = "utf-8";

    public static String getURLString(String urlString) {
        URL url = null;
        HttpURLConnection httpUrlConn = null;
        StringBuffer urlOutputString = new StringBuffer();

        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            // Create URL connection
            url = new URL(urlString);
            httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");

            // Get input stream
            inputStreamReader = new InputStreamReader(httpUrlConn.getInputStream(), ENCODE_CHARSET);
            bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            while (null != (line = bufferedReader.readLine())) {
                urlOutputString.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
                if (null != inputStreamReader) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return urlOutputString.toString();
    }
}
