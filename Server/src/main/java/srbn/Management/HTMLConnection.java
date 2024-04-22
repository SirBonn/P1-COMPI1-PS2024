package srbn.Management;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HTMLConnection extends Thread{


    private JTextArea inputTextArea;
    private String response = "";


    public HTMLConnection(JTextArea inputTextArea) {
        this.inputTextArea = inputTextArea;
    }

    @Override
    public void run() {

        int HTMLPORT = 82;

        try {
            ServerSocket HTML_SERVER_SOCKET = new ServerSocket(HTMLPORT);

            while (true) {

                Socket socketHTML = null;

                if (HTML_SERVER_SOCKET.isBound()) {
                    try {
                        socketHTML = HTML_SERVER_SOCKET.accept();
                        InputStream inputStream = socketHTML.getInputStream();
                        DataOutputStream outputStream = new DataOutputStream(socketHTML.getOutputStream());

                        htmlServerExec(socketHTML, inputStream, outputStream);
                    } catch (Exception e) {
                        System.out.println("ErrorHTML: " + e.getMessage());
                        inputTextArea.append("ErrorHTML: " + e.getMessage() + "\n");
                        e.printStackTrace();
                    } finally {
                        socketHTML.close();
                        System.out.println("ClientConnection closed");
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("ErrorHTML: " + e.getMessage());
            inputTextArea.append("ErrorHTML: " + e.getMessage() + "\n");
            e.printStackTrace();
        }

    }

    private void htmlServerExec(Socket socket, InputStream inputStream, DataOutputStream outputStream) throws Exception {

        System.out.println("html connection established");

        // Lee la solicitud HTTP
        byte[] buffer = new byte[2048];
        int bytesRead = inputStream.read(buffer);

        String requestData = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);

        response = new ConnectionManager().executeHtml(requestData);

        outputStream.writeInt(response.length());
        outputStream.writeBytes(response);
        System.out.println("html client connection terminated");
    }

}
