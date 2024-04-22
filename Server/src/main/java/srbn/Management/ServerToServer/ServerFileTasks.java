package srbn.Management.ServerToServer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ServerFileTasks {


    //en windows
    private static final String HOSTS_FILE = "C:\\Windows\\System32\\drivers\\etc\\hosts";
    private static final String APACHE_CONF_FILE = "C:\\xampp\\apache\\conf\\extra\\httpd-vhosts.conf";
    public ServerFileTasks() {
    }

    public static void addHost(String URL) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter( HOSTS_FILE, true))) {
            bw.write("\n127.0.0.1\twww." + URL+".com"); // Agregar una nueva línea con el nuevo contenido
            bw.write("\n127.0.0.1\t" + URL+".com"); // Agregar una nueva línea con el nuevo contenido
            System.out.println("Contenido agregado al archivo de configuración.");
        } catch (IOException e) {
            System.err.println("Error al agregar contenido al archivo de configuración: " + e.getMessage());
        }
    }

    public static void addConfig(StringBuilder config){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter( APACHE_CONF_FILE, true))) {
            bw.write("\n" + config.toString()); // Agregar una nueva línea con el nuevo contenido
            System.out.println("Contenido agregado al archivo de configuración.");
        } catch (IOException e) {
            System.err.println("Error al agregar contenido al archivo de configuración: " + e.getMessage());
        }
    }

}