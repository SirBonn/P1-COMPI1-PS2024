package srbn.ProjectManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilesManager {

    public static String saveFile(String content, String filePath) {
        String response = "";
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file+".txt"))) {
            writer.write(content);
            response = "Archivo HTML generado correctamente en: " + filePath;
        } catch (IOException e) {
            System.out.println("Error al generar el archivo HTML: " + e.getMessage());
        }
        return file.getAbsolutePath();
    }


}
