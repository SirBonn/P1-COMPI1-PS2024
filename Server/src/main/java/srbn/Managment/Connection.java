package srbn.Managment;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;

import srbn.Domain.Action;
import srbn.Lexer.Lexer;
import srbn.Parser.Parser;

public class Connection extends Thread {

    private JTextArea inputTextArea;

    public Connection(JTextArea inputTextArea) {
        this.inputTextArea = inputTextArea;
    }

    @Override
    public void run() {

        DataInputStream inputStream;
        DataOutputStream outputStream;
        int PORT = 80;

        try {
            ServerSocket server = new ServerSocket(PORT);

            while (true) {
                try (Socket socket = server.accept()) {

                    System.out.println("Connection established");
                    inputStream = new DataInputStream(socket.getInputStream());
                    outputStream = new DataOutputStream(socket.getOutputStream());
                    int messageLength = inputStream.readInt();

                    // Leer la cadena como bytes
                    byte[] messageBytes = new byte[messageLength];
                    inputStream.readFully(messageBytes);

                    String receivedMessage = new String(messageBytes);

                    StringReader sr = new StringReader(receivedMessage);
                    Lexer lex = new Lexer(sr);
                    Parser sintax = new Parser(lex);
                    /*Query query = (Query) */
                    sintax.parse();
                    System.out.println("yup[");
                } catch (Exception e) {
                    System.out.println("ErrorP: " + e.getMessage());
                } finally {
                    System.out.println("Connection closed");
                }

            }

        } catch (Exception e) {
        }


    }
}
