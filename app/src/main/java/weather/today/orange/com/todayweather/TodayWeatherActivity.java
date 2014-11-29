package weather.today.orange.com.todayweather;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import weather.today.orange.com.todayweather.weather.today.orange.com.todayweather.bean.WeatherInfo;
import weather.today.orange.com.todayweather.weather.today.orange.com.todayweather.utils.BaiduWeatherUtils;
import weather.today.orange.com.todayweather.weather.today.orange.com.todayweather.utils.HttpUtils;
import weather.today.orange.com.todayweather.weather.today.orange.com.todayweather.utils.WeatherUtils;

public class TodayWeatherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_weather);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //JSONObject weatherInfoJson = WeatherUtils.getWeatherInfoJsonObject("北京");
        WeatherInfo weatherInfo = BaiduWeatherUtils.getWeatherInfo("北京");
        TextView textView = (TextView) findViewById(R.id.weather_info);
        textView.setText(weatherInfo.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_today_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
