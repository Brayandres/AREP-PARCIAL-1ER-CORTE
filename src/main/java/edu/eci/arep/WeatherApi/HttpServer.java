package edu.eci.arep.WeatherApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import edu.eci.arep.WeatherApi.front.FrontService;

/**
 * 
 * @author Brayan Macias
 *
 */
public class HttpServer {
	
	/**
	 * Main method, that allow start and run application
	 * 
	 * @param args 
	 * @throws IOException 
	 */
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
    
    /**
     * Generate the port for the socket connection.
     * If there are not a defined PORT in Environment, it return a default port.
     * 
     * @return Integer that represents the port
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        else {
            return 35000;
        }
    }
    
    /**
     * Once the request is received from the client, the type of request
     * and the details are generated to know how to process it.
     * 
     * @param clientRequest String that carries all data of the user request
     * @return String indicating the type of request being made.
     */
    private static String managePetition(String clientRequest) {
    	String responseType = "";
        String[] solitudeParts = clientRequest.split(" ");
        String method = solitudeParts[0];
        String path = solitudeParts[1];
        if (method.startsWith("GET")) {
        	if (path.startsWith("/clima")) {
            	responseType = "clima";
            }
            else if (path.startsWith("/consulta?lugar=")) {
            	String city = (path.split("lugar="))[1];
            	responseType = "city "+city;
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
    
    /**
     * Identifies which response should be sent based on the type of request received
     * 
     * @param type String indicating the type of resource requested
     * @return String that contains the HTML page or JSON file that should be shown to client
     */
    private static String manageResponse(String type) {
    	String response = "";
    	if (type.startsWith("clima")) {
    		response = ""+
    			"HTTP/1.1 200 OK\r\n"+
                "Content-Type: text/html\r\n"+
                "\r\n"+FrontService.getHome();
    	}
    	else if (type.startsWith("city ")) {
    		String city = (type.split(" "))[1];
    		response = ""+
        		"HTTP/1.1 200 OK\r\n"+
                "Content-Type: application/json\r\n"+
                "\r\n"+FrontService.getCityData(city);
    	}
    	else if (type.startsWith("bad")) {
    		response = ""+
        		"HTTP/1.1 200 OK\r\n"+
                "Content-Type: text/html\r\n"+
                "\r\n"+FrontService.badRequest();
    	}
    	else {
    		response = ""+
        		"HTTP/1.1 200 OK\r\n"+
                "Content-Type: text/html\r\n"+
                "\r\n"+FrontService.redirect();
    	}
    	return response;
    }
}