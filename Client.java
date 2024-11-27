import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";  
        int port = 5000;  

        try (Socket socket = new Socket(serverAddress, port)) {
           
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage;
            String serverResponse;

            while (true) {
                System.out.print("Client: ");
                clientMessage = userInput.readLine();

                output.println(clientMessage);

                if (clientMessage.equalsIgnoreCase("exit")) {
                    break;
                }

                serverResponse = input.readLine();
                System.out.println("Server: " + serverResponse);
            }

            
            input.close();
            output.close();
            userInput.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
