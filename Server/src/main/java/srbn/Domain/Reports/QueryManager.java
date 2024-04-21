package srbn.Domain.Reports;

import com.fasterxml.jackson.databind.ObjectMapper;
import srbn.Managment.Folders.DocumentManager;
import srbn.Managment.Folders.FolderManagment;
import srbn.Managment.ServerToServer.TaskManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class QueryManager {

    private String response;
    TaskManager taskManager;


    public QueryManager(TaskManager taskManager) {
        this.response = "";
        this.taskManager = taskManager;
    }


    public void executeQueries(ArrayList<Query> queries) {

        ArrayList<Control> controls = getControls();

        for (Query query : queries) {
            if (query.getType() == QueryTypes.GET_VISITS_PAGE.ordinal()) {

            }
            if (query.getType() == QueryTypes.GET_VISITS_SITE.ordinal()) {

            }
            if (query.getType() == QueryTypes.GET_POPULAR_PAGES.ordinal()) {

            }
            if (query.getType() == QueryTypes.GET_COMPONENTS.ordinal()) {

            }
        }

    }

    public void updateControls(String site, String page, String parent) {
        ArrayList<Control> controls = getControls();
        ObjectMapper mapper = new ObjectMapper();

        try {
            for (Control control : controls) {
                if (control.getSiteId().equals(site) && control.getPageId().equals(page) && control.getParentId().equals(parent)) {
                    control.setCount(control.getCount() + 1);
                }
            }
            //delete n write
            DocumentManager.deleteFile(FolderManagment.APACHE_SV_FOLD + "/control.json");
            taskManager.setControls(controls);
            DocumentManager.writeControls(taskManager);
            File jsonControl = new File(FolderManagment.APACHE_SV_FOLD + "/control.json");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Control> getControls() {
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

}
