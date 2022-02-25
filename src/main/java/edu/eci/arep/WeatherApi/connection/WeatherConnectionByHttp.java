package edu.eci.arep.WeatherApi.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherConnectionByHttp {

    public static String getWeatherByCity(String city) {
    	String key = "00237403ed6ea29f616b60642dc5fc0d";
    	StringBuilder response = new StringBuilder();
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+key);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return response.toString();
    }
}