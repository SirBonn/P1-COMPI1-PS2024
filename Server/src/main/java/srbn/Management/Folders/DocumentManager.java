package srbn.Management.Folders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import srbn.Domain.Actions.Action;
import srbn.Domain.Components.*;
import srbn.Domain.Errors.ErrorE;
import srbn.Domain.Label;
import srbn.Domain.Reports.Control;
import srbn.Management.ServerToServer.TaskManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DocumentManager {

    public static String writeHTMLToFile(String htmlContent, String filePath) throws ErrorE {
        String response = "";
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(htmlContent);
            response = "Archivo HTML generado correctamente en: " + filePath;
        } catch (IOException e) {
            throw new ErrorE("Error al generar el archivo HTML: " + e.getMessage());
        }
        return file.getAbsolutePath();
    }

    public static String writeJSONobject(Action action) throws ErrorE {
        String response = "";
        String filePath = FolderManagment.ACTIONS_FOLD + "/" + action.getId() + ".json";

        ObjectMapper mapper = new ObjectMapper();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)))) {

            mapper.writeValue(writer, action);
            System.out.println("Archivo JSON generado correctamente en: " + filePath);
            response = "Archivo JSON generado correctamente en: " + filePath;

        } catch (IOException e) {
            throw new ErrorE("Error al generar el archivo JSON: " + e.getMessage());
        }

        return response;
    }

    public static void writeDomains(TaskManager taskManager) throws ErrorE {
        try {

            deleteFile(FolderManagment.APACHE_SV_FOLD + "/domains.json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(FolderManagment.APACHE_SV_FOLD + "/domains.json"), taskManager.getDomainsOcuped());
            System.out.println("JSON escrito exitosamente en el archivo domains.json");
            writeControls(taskManager);
        } catch (IOException e) {
            throw new ErrorE("Error al generar el archivo JSON: " + e.getMessage());
        }
    }

    public static void writeControls(TaskManager taskManager) throws ErrorE {
        try {
            deleteFile(FolderManagment.APACHE_SV_FOLD + "/control.json");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(FolderManagment.APACHE_SV_FOLD + "/control.json"), taskManager.getControls());
            System.out.println("JSON escrito exitosamente en el archivo control.json");
        } catch (IOException e) {
            throw new ErrorE("Error al generar el archivo JSON: " + e.getMessage());
        }
    }

    public Action getJsonObject(Action act) throws ErrorE {

        String filePath = FolderManagment.ACTIONS_FOLD + "/" + act.getPage() + ".json";
        ObjectMapper mapper = new ObjectMapper();
        return getJsonObject(act.getPage());
    }

    public ArrayList<String> getDomains (){
        ArrayList<String> domains = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            File jsonControl = new File(FolderManagment.APACHE_SV_FOLD + "/domains.json");
            String[] domain = mapper.readValue(jsonControl, String[].class);
            domains = new ArrayList<>(Arrays.asList(domain));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return domains;
    }

    public ArrayList<Control> getControls() {
        ArrayList<Control> controls = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            File jsonControl = new File(FolderManagment.APACHE_SV_FOLD + "/control.json");
            Control[] control = mapper.readValue(jsonControl, Control[].class);
            controls = new ArrayList<>(Arrays.asList(control));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return controls;

    }

    public Action getJsonObject(String fileName) throws ErrorE {

        String filePath = FolderManagment.ACTIONS_FOLD + "/" + fileName + ".json";
        Action act = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
//          act = mapper.readValue(new File(filePath), Action.class);

            JsonNode jsNode = mapper.readTree(new File(filePath));

            String id = jsNode.get("id").asText();
            String title = jsNode.get("tittle").asText();
            String site = jsNode.get("site").asText();
            String parent = jsNode.get("parent").asText();
            String userCreation = jsNode.get("userCreation").asText();
            String creationDate = jsNode.get("creationDate").asText();
            String userModification = jsNode.get("userModification").asText();
            String modificationDate = jsNode.get("modificationDate").asText();
            int actionType = jsNode.get("actionType").asInt();

            JsonNode labelsNode = jsNode.get("labels");
            ArrayList<Label> labels = new ArrayList<>();

            for (JsonNode label : labelsNode) {
                String labelValue = label.get("value").asText();
                labels.add(new Label(labelValue));
            }

            JsonNode componentsNode = jsNode.get("components");
            ArrayList<Component> components = new ArrayList<>();
            String componentPage = "";
            for (JsonNode cmpNode : componentsNode) {

                String componentId = cmpNode.get("idComp").asText();
                componentPage = cmpNode.get("pageComp").asText();
                int componentType = cmpNode.get("type").asInt();
                String componentText = "";
                String componentColor = "";
                String componentJustify = "";
                String componentSrc = "";
                String componentHeight = "";
                String componentWidth = "";

                if (cmpNode.has("text")) {
                    componentText = cmpNode.get("text").asText();
                }
                if (cmpNode.has("color")) {
                    componentColor = cmpNode.get("color").asText();
                }
                if (cmpNode.has("justify")) {
                    componentJustify = cmpNode.get("justify").asText();
                }
                if (cmpNode.has("src")) {
                    componentSrc = cmpNode.get("src").asText();
                }
                if (cmpNode.has("height")) {
                    componentHeight = cmpNode.get("height").asText();
                }
                if (cmpNode.has("width")) {
                    componentWidth = cmpNode.get("width").asText();
                }

                if (componentType == ComponentType.IMAGE.ordinal()) {
                    components.add(new ImageComponent(componentId, componentPage, componentType, componentSrc, componentHeight,
                            componentWidth, componentJustify));
                } else if (componentType == ComponentType.TITTLE.ordinal()) {
                    components.add(new TittleComponent(componentId, componentPage, componentType, componentText, componentColor,
                            componentJustify));
                } else if (componentType == ComponentType.VIDEO.ordinal()) {
                    components.add(new VideoComponent(componentId, componentPage, componentType, componentSrc, componentHeight,
                            componentWidth));
                } else if (componentType == ComponentType.PARAGRAPH.ordinal()) {
                    components.add(new ParagraphComponent(componentId, componentPage, componentType, componentText,
                            componentJustify, componentColor));
                } else if (componentType == ComponentType.MENU.ordinal()) {
//                    components.add(new MenuComponent())
                }

            }
            act = new Action(id, title, site, parent, userCreation, creationDate, userModification,
                    modificationDate, componentPage, "", actionType, labels, components);
        } catch (IOException e) {
            throw new ErrorE("Error al leer el archivo JSON: " + e.getMessage());
        }

        return act;
    }

    public static boolean deleteFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        return file.delete();
    }

    public static String getFilePathByActionType(Action action) {
        String path = "";

        if (action.getSite() == null | action.getSite().equals("")) {
            path = action.getId();
        } else {
            path = "" + action.getSite() + "/" + action.getId();
        }
        return path;
    }

}
