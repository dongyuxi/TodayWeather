package weather.today.orange.com.todayweather.weather.today.orange.com.todayweather.utils;

import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Orange on 2014/11/22.
 */
public class WeatherUtils {

    private static final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini?city=";
    private static final String ENCODE_CHARSET = "utf-8";

    public static JSONObject getWeatherInfoJsonObject(String city) {
        try {
            String originalWeatherInfo = HttpUtils.getURLString(WEATHER_API + URLEncoder.encode(city, ENCODE_CHARSET));
            JSONObject json = new JSONObject(originalWeatherInfo);
            if ("OK".equals(json.getString("desc")) && 1000 == json.getInt("status")) {
                return json.getJSONObject("data");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getWeatherInfo(JSONObject weatherInfoJson) {
        if (null == weatherInfoJson) {
            return null;
        }

        StringBuffer weatherInfo = new StringBuffer();
        String[] array = new String[]{"今天", "明天", "后天"};

        try {
            weatherInfo.append("城市 " + weatherInfoJson.getString("city") + "\n");
            weatherInfo.append("当前温度 " + weatherInfoJson.get("wendu") + "\n");

            JSONArray forecast = weatherInfoJson.getJSONArray("forecast");

            for (int i = 0; i < 3; i++)  {
                JSONObject object = forecast.getJSONObject(i);
                weatherInfo.append("\n");
                weatherInfo.append(array[i]);
                weatherInfo.append("\n");
                weatherInfo.append("    " + object.getString("high") + "\n");
                weatherInfo.append("    " + object.getString("low") + "\n");
                weatherInfo.append("    风向 " + object.getString("fengxiang") + "\n");
                weatherInfo.append("    风力 " + object.getString("fengli") + "\n");
                weatherInfo.append("    状态 " + object.get("type") + "\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return weatherInfo.toString();
    }
}
