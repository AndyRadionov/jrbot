package com.radionov.jrbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Andrey Radionov
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseDTO {
    private List<WeatherListObject> list;

    public List<WeatherListObject> getList() {
        return list;
    }

    public void setList(List<WeatherListObject> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        if (list == null || list.size() == 0) return "Нет данных";
        WeatherListObject weather = list.get(0);
        return "Погода в " + weather.name + ":" +
                "\n  температура = " + weather.main.temp +
                "\n  давление = " + weather.main.pressure +
                "\n  влажность = " + weather.main.humidity +
                "\n  мин температура = " + weather.main.tempMin +
                "\n  макс температура = " + weather.main.tempMax +
                "\n  скорость ветра = " + weather.wind.speed +
                "\n  дождь = " + weather.getRain() +
                "\n  снег = " + weather.getSnow();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class WeatherListObject {
        private String name;
        private MainWeatherParams main;
        private WindParams wind;
        private String rain;
        private String snow;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public MainWeatherParams getMain() {
            return main;
        }

        public void setMain(MainWeatherParams main) {
            this.main = main;
        }

        public WindParams getWind() {
            return wind;
        }

        public void setWind(WindParams wind) {
            this.wind = wind;
        }

        public String getRain() {
            if (rain == null) return "нет";
            return rain;
        }

        public void setRain(String rain) {
            this.rain = rain;
        }

        public String getSnow() {
            if (snow == null) return "нет";
            return snow;
        }

        public void setSnow(String snow) {
            this.snow = snow;
        }
    }

    private static class MainWeatherParams {
        private String temp;
        private String pressure;
        private String humidity;
        @JsonProperty("temp_min")
        private String tempMin;
        @JsonProperty("temp_max")
        private String tempMax;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getTempMin() {
            return tempMin;
        }

        public void setTempMin(String tempMin) {
            this.tempMin = tempMin;
        }

        public String getTempMax() {
            return tempMax;
        }

        public void setTempMax(String tempMax) {
            this.tempMax = tempMax;
        }
    }

    private static class WindParams {
        private String speed;
        private String deg;

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getDeg() {
            return deg;
        }

        public void setDeg(String deg) {
            this.deg = deg;
        }
    }
}
