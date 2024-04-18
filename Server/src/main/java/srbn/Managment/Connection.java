package srbn.Managment;

import srbn.Domain.Actions.Action;
import srbn.Domain.Actions.ActionManager;
import srbn.Domain.Errors.ErrorP;
import srbn.Lexer.Lexer;
import srbn.Parser.Parser;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Connection extends Thread {

    private JTextArea inputTextArea;
    private String response = "";



    public Connection(JTextArea inputTextArea) {
        this.inputTextArea = inputTextArea;
    }

    @Override
    public void run() {

        DataInputStream inputStream;
        DataOutputStream outputStream;
        int PORT = 81;

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
                    sintax.parse();

                    this.response = "";
                    if (sintax.isRight()) {
                        ActionManager actmng = new ActionManager(sintax.getActions());
                        actmng.executeActions();
                        response += actmng.getResponse();
                        response += "\n\n" + getResponseOk(sintax.getActions()) + actmng.getResponse();
                    }
                    response += "\n\n" + getResponseEr(sintax.getErrors());
                    outputStream.writeInt(response.length());
                    outputStream.writeBytes(response);
                    System.out.println("yup[");
                } catch (Exception e) {
                    System.out.println("ErrorP: " + e.getMessage());
                    inputTextArea.append("ErrorP: " + e.getMessage() + "\n");
                    e.printStackTrace();
                } finally {
                    System.out.println("Connection closed");
                }

            }

        } catch (Exception e) {
        }


    }

    public String getResponseOk(ArrayList<Action> actions) {
        String Result = "";
        for (Action action : actions) {
            if (action != null)
                Result += action.getId() + ": registered (OK)\n";
        }
        return Result;
    }


    public String getResponseEr(ArrayList<ErrorP> errors) {
        String Result = "";

        Result += "Errors: \n";
        for (ErrorP error : errors) {
            if (error != null)
                Result += error.toString() + "\n";
        }
        return Result;
    }

}
