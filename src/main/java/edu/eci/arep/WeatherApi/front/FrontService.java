package edu.eci.arep.WeatherApi.front;

import edu.eci.arep.WeatherApi.connection.WeatherConnectionByHttp;

public class FrontService {
	
	public static String getHome() {
		return "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "	<meta charset=\"utf-8\">\r\n"
				+ "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
				+ "	<title>ECI | Weather Service</title>\r\n"
				+ "	<style type=\"text/css\">\r\n"
				+ "		body {\r\n"
				+ "			background-image: radial-gradient(circle, #5b0c6b, #560c6b, #500c6a, #4b0c6a, #450c69);\r\n"
				+ "		}\r\n"
				+ "		* {\r\n"
				+ "			Font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif;\r\n"
				+ "		}\r\n"
				+ "		html, body {\r\n"
				+ "			height: 100%;\r\n"
				+ "			width: 100%;\r\n"
				+ "			margin: 0%;\r\n"
				+ "			padding: 0%;\r\n"
				+ "			font-size: 100%;\r\n"
				+ "		}\r\n"
				+ "		.container {\r\n"
				+ "			text-align: center;\r\n"
				+ "			width: 100%;\r\n"
				+ "			color: white;\r\n"
				+ "		}\r\n"
				+ "		.field {\r\n"
				+ "			text-align: center;\r\n"
				+ "			font-size: 1.5rem;\r\n"
				+ "		}\r\n"
				+ "	</style>\r\n"
				+ "	<script type=\"text/javascript\">\r\n"
				+ "		var apiWeather = (function() {\r\n"
				+ "\r\n"
				+ "			function test() {\r\n"
				+ "				$(\"#result\").text(\"jajvnaknfakfnjseanfkej\");\r\n"
				+ "			}\r\n"
				+ "\r\n"
				+ "			return {\r\n"
				+ "				test: test\r\n"
				+ "			}\r\n"
				+ "		})();\r\n"
				+ "	</script>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<p class=\"container\" style=\"font-size:2rem\">ECI - Weather Service</p>\r\n"
				+ "	<p class=\"container\" style=\"font-size:1.2rem\">Search for a city to get weather data</p>\r\n"
				+ "	<p class=\"container\">\r\n"
				+ "		<input id=\"city\" class=\"field\" type=\"text\" name=\"citySearch\" placeholder=\"Search for a city!\">\r\n"
				+ "	</p>\r\n"
				+ "	<p class=\"container field\">\r\n"
				+ "		<button id=\"btn\" style=\"background-color:black; color:white;\" onclick=\"apiWeather.test()\">SEARCH</button>\r\n"
				+ "	</p>\r\n"
				+ "	<p id=\"result\" class=\"container\"></p>\r\n"
				+ "</body>\r\n"
				+ "</html>";
	
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