
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 5000; 

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for client connection...");

          
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            String clientMessage;
            String serverMessage;

            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                clientMessage = input.readLine();
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client: " + clientMessage);

                System.out.print("Server: ");
                serverMessage = serverInput.readLine();
                
                output.println(serverMessage);
            }

            // Close resources
            clientSocket.close();
            System.out.println("Connection closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
