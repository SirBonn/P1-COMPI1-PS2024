
package srbn.Server.Magnament;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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


}
