package srbn.Managment.Folders;
import com.fasterxml.jackson.databind.ObjectMapper;
import srbn.Domain.Action;
import srbn.Domain.ErrorE;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DocumentManager {

    public static String writeHTMLToFile(String htmlContent, String filePath) throws ErrorE {
        String response = "";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)))) {
            writer.write(htmlContent);
            System.out.println("Archivo HTML generado correctamente en: " + filePath);
            response = "Archivo HTML generado correctamente en: " + filePath;
        } catch (IOException e) {
            throw new ErrorE("Error al generar el archivo HTML: " + e.getMessage());
        }
        return response;
    }

    public static String writeJSONobject(Action action) throws ErrorE {
        String response = "";
        String filePath = FolderManagment.ACTIONS_FOLD+"/" + action.getId() + ".json";

        ObjectMapper mapper = new ObjectMapper();

        try{

            mapper.writeValue(new File(filePath), action);
            System.out.println("Archivo JSON generado correctamente en: " + filePath);
            response = "Archivo JSON generado correctamente en: " + filePath;

        } catch (IOException e) {
            throw new ErrorE("Error al generar el archivo JSON: " + e.getMessage());
        }

        return response;
    }



}
