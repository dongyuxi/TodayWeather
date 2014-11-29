package weather.today.orange.com.todayweather.weather.today.orange.com.todayweather.bean;

/**
 * Created by Orange on 2014/11/24.
 */
public class WeatherInfo {
    // Current date
    private String currentDate;
    // Current city
    private String currentCity;
    // Current temperature
    private String currentTemperature;

    // Current PM2.5
    private int pm25;

    // Today temperature status
    private String[] status;

    // Temperature - today, tomorrow
    private String[] temperature;

    // Wind - today, tomorrow
    private String[] wind;



    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public String[] getStatus() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

    public String[] getTemperature() {
        return temperature;
    }

    public void setTemperature(String[] temperature) {
        this.temperature = temperature;
    }

    public String[] getWind() {
        return wind;
    }

    public void setWind(String[] wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("当前日期：" + this.getCurrentDate() + "\n");
        sb.append("当前城市：" + this.getCurrentCity() + "\n");
        sb.append("当前温度：" + this.getCurrentTemperature() + "\n");
        sb.append("当前PM2.5：" + this.getPm25() + "\n");

        sb.append("\n今天\n");
        sb.append("\t天气状态：" + this.getStatus()[0] + "\n");
        sb.append("\t温度状态：" + this.getTemperature()[0] + "\n");
        sb.append("\t风向状态：" + this.getWind()[0] + "\n");

        sb.append("\n明天\n");
        sb.append("\t天气状态：" + this.getStatus()[1] + "\n");
        sb.append("\t温度状态：" + this.getTemperature()[1] + "\n");
        sb.append("\t风向状态：" + this.getWind()[1] + "\n");

        return sb.toString();
    }
}
