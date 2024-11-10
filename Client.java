import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Server IP address
        int port = 1234; // Port number to connect to

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to the server.");

            // Setup input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Send message to server
            String clientMessage = "Hello from Brahmjot";
            output.println(clientMessage);

            // Receive response from server
            String serverResponse = input.readLine();
            System.out.println("Server says: " + serverResponse);

            // Close resources
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Brahmjot Singh AI-ML 03913211621");
    }
}
