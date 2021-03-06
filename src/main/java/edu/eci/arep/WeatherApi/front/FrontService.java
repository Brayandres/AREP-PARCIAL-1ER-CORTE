package edu.eci.arep.WeatherApi.front;

import edu.eci.arep.WeatherApi.connection.WeatherConnectionByHttp;

/**
 * 
 * @author Brayan Macias
 */
public class FrontService {
	
	/**
	 * Return the HTML WebPage containing the city search service  
	 * 
	 * @return String with the code of the HTML webpage
	 */
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
				+ "			const URL = \"https://arep-test.herokuapp.com/consulta?lugar=\";\r\n"
				+ "\r\n"
				+ "			async function getResource() {\r\n"
				+ "				let varCity = $(\"#city\").val();\r\n"
				+ "				if (!(varCity === \"\" || varCity == null)) {\r\n"
				+ "					const response = await fetch(URL+varCity);\r\n"
				+ "					const data = await response.json();\r\n"
				+ "					$(\"#result\").text(JSON.stringify(data));\r\n"
				+ "				}\r\n"
				+ "				else {\r\n"
				+ "					$(\"#result\").text(\"The 'city' field cannot be empty\");\r\n"
				+ "				}\r\n"
				+ "			}\r\n"
				+ "\r\n"
				+ "			return {\r\n"
				+ "				getCityData: getResource\r\n"
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
				+ "		<button id=\"btn\" style=\"background-color:black; color:white;\" onclick=\"apiWeather.getCityData()\">SEARCH</button>\r\n"
				+ "	</p>\r\n"
				+ "	<p id=\"result\" class=\"container\"></p>\r\n"
				+ "</body>\r\n"
				+ "</html>";
	}
	
	/**
	 * Handles the input request when a city is given to search
	 * 
	 * @param city
	 * @return
	 */
	public static String getCityData(String city) {
		return WeatherConnectionByHttp.getWeatherByCity(city);
	}
	
	/**
	 * When URL path does no exist, it shows 404 custom page
	 * 
	 * @return String with the code of the HTML webpage
	 */
	public static String badRequest() {
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
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<p class=\"container\" style=\"font-size:2rem\">ECI - Weather Service</p>\r\n"
				+ "	<p class=\"container\" style=\"font-size:1.2rem\">404 - NOT FOUND</p>\r\n"
				+ "</body>\r\n"
				+ "</html>";
	}
	
	/**
	 * If you try to make a non-get request, you are redirected to the main page
	 * 
	 * @return String with the code of the HTML webpage
	 */
	public static String redirect() {
		return getHome();
	}
}