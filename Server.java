import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 1234; // Port number for the server

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for clients...");

            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Setup input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            // Receive message from client
            String clientMessage = input.readLine();
            System.out.println("Client says: " + clientMessage);

            // Send response to client
            String serverResponse = "Hello from Server, Brahmjot";
            output.println(serverResponse);

            // Close resources
            input.close();
            output.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Brahmjot Singh AI-ML 03913211621");
    }
}
