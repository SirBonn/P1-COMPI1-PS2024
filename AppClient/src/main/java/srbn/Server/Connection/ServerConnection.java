package srbn.Server.Connection;

import srbn.Server.Magnament.ClientConnectionManager;

import javax.swing.*;
import java.net.Socket;

public class ServerConnection {

    private JTextArea console;

    public ServerConnection(JTextArea console) {
        this.console = console;
    }

    public String CreateSocket() {
        String response = "NO HAY RESPUESTA";

        try {
            Socket socket = new Socket("127.0.0.1", 80);
            ClientConnectionManager sm = new ClientConnectionManager(socket.getInputStream(), socket.getOutputStream());

            sm.sendString(this.console.getText());

            response = "Conexión establecida";

        } catch (Exception e) {
            console.append("ERROR -> No hay respuesta del servidor\n");
        }

        return response;
    }

}
