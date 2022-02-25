package edu.eci.arep.WeatherApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import edu.eci.arep.WeatherApi.front.FrontService;

public class HttpServer {

    public static void main(String[] args) throws IOException {
    	
    	int port = getPort();
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("--- Server socket initialized...");
        } catch (IOException e) {
            System.err.println("-x- Could not listen on port: "+port);
            System.exit(1);
        }
        
        Boolean isRunning = true;
        while (isRunning) {
        	try {
                clientSocket = serverSocket.accept();
                System.out.println("--- Ready to receive...");
            } catch (IOException e) {
                System.err.println("-x- Accept failed.");
                System.exit(1);
            }
        	
        	PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String input = "";
            String clientRequest = "";
            while ((input = in.readLine()) != null) {
            	if (clientRequest.equals("")) {
            		clientRequest = input;
            	}
            	if (input.equals("")) {
            		break;
            	}
            }
            if ((clientRequest != null) && !(clientRequest.equals(""))) {
            	out.println(manageResponse(managePetition(clientRequest)));
            	
            }
            out.close();
            in.close();
        	clientSocket.close();
        }
        serverSocket.close();
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        else {
            return 35000;
        }
    }
    
    private static String managePetition(String clientRequest) {
    	String responseType = "";
        System.out.println("-------------------------");
        System.out.println("REQUEST: \n"+clientRequest);
        String[] solitudeParts = clientRequest.split(" ");
        String method = solitudeParts[0];
        System.out.println("    -- Method: "+method+".");
        String path = solitudeParts[1];
        System.out.println("    -- Path: "+path+".");
        if (method.startsWith("GET")) {
        	if (path.startsWith("/clima")) {
            	System.out.println("--- SE ESTÁ CONSULTANDO LA PÁGINA...");
            	responseType = "clima";
            }
            else if (path.startsWith("/consulta?lugar=")) {
            	String city = (path.split("lugar="))[1];
            	System.out.println("--- SE ESTÁ CONSULTANDO EL CLIMA DE LA CIUDAD: "+city);
            	responseType = "city."+city;
            }
            else {
            	System.out.println("BAD REQUEST >:v");
            	responseType = "bad";
            }
        }
        else {
        	responseType = "redirect";
        }
        return responseType;
    }
    
    private static String manageResponse(String type) {
    	String response = "";
    	if (type.startsWith("clima")) {
    		response = ""+
    			"HTTP/1.1 200 OK\r\n"+
                "Content-Type: "+"text/html"+"\r\n"+
                "\r\n" + FrontService.getHome();
    	}
    	else if (type.startsWith("city.")) {
    		String city = (type.split("."))[1];
    		response = ""+
        		"HTTP/1.1 200 OK\r\n"+
                "Content-Type: "+"application/json"+"\r\n"+
                "\r\n" + FrontService.getCityData(city);
    	}
    	else if (type.startsWith("bad")) {
    		response = ""+
        		"HTTP/1.1 200 OK\r\n"+
                "Content-Type: "+"text/html"+"\r\n"+
                "\r\n" + FrontService.badRequest();
    	}
    	else {
    		response = ""+
        		"HTTP/1.1 200 OK\r\n"+
                "Content-Type: "+"text/html"+"\r\n"+
                "\r\n" + FrontService.redirect();
    	}
    	return response;
    }
}