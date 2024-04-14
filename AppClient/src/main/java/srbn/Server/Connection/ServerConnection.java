package srbn.Server.Connection;

import srbn.Server.Magnament.ClientConnection.ClientConnectionManager;

import javax.swing.*;
import java.net.Socket;

public class ServerConnection {

    private JTextArea console;
    private JTextArea outputTextArea;

    public ServerConnection(JTextArea console, JTextArea outputTextArea) {
        this.console = console;
        this.outputTextArea = outputTextArea;
    }

    public void CreateSocket() {
        String response = "NO HAY RESPUESTA";

        try {
            this.outputTextArea.setText("");

            Socket socket = new Socket("127.0.0.1", 80);
            ClientConnectionManager sm = new ClientConnectionManager(socket.getInputStream(), socket.getOutputStream());

            sm.sendString(this.console.getText());
            response = "Conexión establecida\n";
            response += sm.receiveString();
            outputTextArea.append(response + "\n");


        } catch (Exception e) {
            outputTextArea.append("ERROR -> No hay respuesta del servidor\n");
        }

    }

}
