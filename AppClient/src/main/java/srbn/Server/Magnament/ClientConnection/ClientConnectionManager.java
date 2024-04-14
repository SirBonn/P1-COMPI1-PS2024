
package srbn.Server.Magnament.ClientConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author ADMIN
 */
public class ClientConnectionManager {

    DataInputStream inputStream;
    DataOutputStream outputStream;

    public ClientConnectionManager(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new DataInputStream(inputStream);
        this.outputStream = new DataOutputStream(outputStream);
    }

    public void sendString(String inputText) {

        try {

            this.outputStream.writeInt(inputText.length());

            // Enviar la cadena como bytes
            this.outputStream.writeBytes(inputText);

            System.out.println("Mensaje enviado con éxito.");

        } catch (Exception e) {
            System.out.println("Error al enviar el mensaje: " + e.getMessage());
        }


    }


    public String receiveString() {
        String response = "NO HAY RESPUESTA";
        try {
            int messageLength = this.inputStream.readInt();

            // Leer la cadena como bytes
            byte[] messageBytes = new byte[messageLength];
            this.inputStream.readFully(messageBytes);

            response = new String(messageBytes);

        } catch (Exception e) {
            System.out.println("Error al recibir el mensaje: " + e.getMessage());
        }

        return response;
    }

}
