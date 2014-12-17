package weather.today.orange.com.todayweather;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.youmi.android.AdManager;
import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import net.youmi.android.banner.AdViewListener;

import weather.today.orange.com.todayweather.weather.today.orange.com.todayweather.bean.WeatherInfo;
import weather.today.orange.com.todayweather.weather.today.orange.com.todayweather.utils.BaiduWeatherUtils;

public class TodayWeatherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_weather);

        // 初始化接口，应用启动的时候调用
        // 参数：appId, appSecret, 调试模式
        AdManager.getInstance(this).init("a274aa293ee86b3e", "f56dc6286bb90442", false);
        // 实例化LayoutParams(重要)
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        // 设置广告条的悬浮位置
        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT; // 这里示例为右下角
        // 实例化广告条
        AdView adView = new AdView(this, AdSize.FIT_SCREEN);
        // 调用Activity的addContentView函数
        this.addContentView(adView, layoutParams);

        // 监听广告条接口
        adView.setAdListener(new AdViewListener() {
            @Override
            public void onSwitchedAd(AdView arg0) {
                Log.i("YoumiAd", "广告条切换");
            }

            @Override
            public void onReceivedAd(AdView arg0) {
                Log.i("YoumiAd", "请求广告成功");
            }

            @Override
            public void onFailedToReceivedAd(AdView arg0) {
                Log.i("YoumiAd", "请求广告失败");
            }
        });

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
