package edu.eci.arep.WeatherApi.front;

import edu.eci.arep.WeatherApi.connection.WeatherConnectionByHttp;

public class FrontService {
	
	public static String getHome() {
		return "";
	}
	
	public static String getCityData(String city) {
		return WeatherConnectionByHttp.getWeatherByCity(city);
	}
	
	public static String badRequest() {
		return "";
	}
	
	public static String redirect() {
		return "";
	}
}