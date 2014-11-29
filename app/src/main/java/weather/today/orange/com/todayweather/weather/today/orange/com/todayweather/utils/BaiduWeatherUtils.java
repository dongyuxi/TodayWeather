package weather.today.orange.com.todayweather.weather.today.orange.com.todayweather.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import weather.today.orange.com.todayweather.weather.today.orange.com.todayweather.bean.WeatherInfo;

/**
 * Created by Orange on 2014/11/25.
 */
public class BaiduWeatherUtils {

    private static String BAIDU_WEATHER_API = "http://api.map.baidu.com/telematics/v3/weather?output=json&ak=SHtu4p917XO1l4ti2gfoUQpd&location=";
    private static final String ENCODE_CHARSET = "utf-8";

    public static WeatherInfo getWeatherInfo(String city) {
        try {
            String originalWeatherInfo = HttpUtils.getURLString(BAIDU_WEATHER_API + URLEncoder.encode(city, ENCODE_CHARSET));
            JSONObject json = new JSONObject(originalWeatherInfo);
            if (0 == json.getInt("error") && "success".equals(json.getString("status"))) {
                WeatherInfo weatherInfo = new WeatherInfo();
                weatherInfo.setCurrentDate(json.getString("date"));
                JSONObject results = json.getJSONArray("results").getJSONObject(0);
                weatherInfo.setCurrentCity(results.getString("currentCity"));
                weatherInfo.setPm25(results.getInt("pm25"));
                JSONArray weatherData = results.getJSONArray("weather_data");
                String[] status = new String[2];
                String[] wind = new String[2];
                String[] temperature = new String[2];
                for (int i = 0; i < 2; i++) {
                    JSONObject object = weatherData.getJSONObject(i);
                    if (0 == i) {
                        String todayInfo = object.getString("date");
                        Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
                        Matcher matcher = pattern.matcher(todayInfo);
                        if (matcher.find()) {
                            weatherInfo.setCurrentTemperature(matcher.group().split("：")[1]);
                        }
                    }
                    status[i] = object.getString("weather");
                    wind[i] = object.getString("wind");
                    temperature[i] = object.getString("temperature");
                }
                weatherInfo.setStatus(status);
                weatherInfo.setWind(wind);
                weatherInfo.setTemperature(temperature);
                return weatherInfo;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "周二 11月25日 (实时：3℃)";
        Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.group().split("：")[1]);
        }
    }
}
