package srbn.Management;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientConnection extends Thread {

    private JTextArea inputTextArea;
    private String response = "";


    public ClientConnection(JTextArea inputTextArea) {
        this.inputTextArea = inputTextArea;
    }

    @Override
    public void run() {


        int PORT = 81;

        try {
            ServerSocket CLIENT_SERVER_SOCKET = new ServerSocket(PORT);

            while (true) {

                Socket socketClient = null;

                if (CLIENT_SERVER_SOCKET.isBound()) {
                    try {
                        socketClient = CLIENT_SERVER_SOCKET.accept();
                        DataInputStream inputStream = new DataInputStream(socketClient.getInputStream());
                        DataOutputStream outputStream = new DataOutputStream(socketClient.getOutputStream());

                        xmlServerExec(socketClient, inputStream, outputStream);

                    } catch (Exception e) {
                        System.out.println("ErrorP: " + e.getMessage());
                        inputTextArea.append("ErrorP: " + e.getMessage() + "\n");
                        e.printStackTrace();
                    } finally {
                        socketClient.close();
                        System.out.println("ClientConnection closed");
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("ErrorP: " + e.getMessage());
            inputTextArea.append("ErrorP: " + e.getMessage() + "\n");
            e.printStackTrace();
        }

    }


    private void xmlServerExec(Socket socket, DataInputStream inputStream, DataOutputStream outputStream) throws Exception {
        System.out.println("app client connection established");

        int messageLength = inputStream.readInt();
        byte[] messageBytes = new byte[messageLength];
        inputStream.readFully(messageBytes);
        String xmlReceived = new String(messageBytes);

        response = new ConnectionManager().executeXml(xmlReceived);

        outputStream.writeInt(response.length());
        outputStream.writeBytes(response);
        System.out.println("app client connection terminated");
    }

}
