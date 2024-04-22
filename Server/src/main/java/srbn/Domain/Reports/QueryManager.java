package srbn.Domain.Reports;

import com.fasterxml.jackson.databind.ObjectMapper;
import srbn.Domain.Actions.Action;
import srbn.Domain.Components.Component;
import srbn.Domain.Components.ComponentType;
import srbn.Domain.Errors.ErrorE;
import srbn.Management.Folders.DocumentManager;
import srbn.Management.Folders.FolderManagment;
import srbn.Management.ServerToServer.TaskManager;

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
        controls.sort((o1, o2) -> o2.getCount() - o1.getCount());
        Control[] topResult = new Control[5];

        for (Query query : queries) {
            if (query.getType() == QueryTypes.GET_VISITS_PAGE.ordinal()) {

                int count = 0;

                for (Control control : query.getPaths()) {
                    String siteId = control.getSiteId();
                    String pageId = control.getPageId();
                    for (Control _control : controls) {
                        if (_control.getSiteId().equals(siteId) && _control.getPageId().equals(pageId)) {
                            topResult[count] = _control;
                            count++;
                        }
                    }
                }

                printReport(topResult, "THE NUMBER OF VISITS A PAGES ARE: ", count);

            }
            if (query.getType() == QueryTypes.GET_VISITS_SITE.ordinal()) {

                int count = 0;

                for (Control control : query.getPaths()) {
                    String path = control.getSiteId();
                    for (Control _control : controls) {
                        if (_control.getSiteId().equals(path)) {
                            topResult[count] = _control;
                            count++;
                        }
                    }
                }

                printReport(topResult, "THE NUMBER OF VISITS A PAGES ARE: ", count);

            }
            if (query.getType() == QueryTypes.GET_POPULAR_PAGES.ordinal()) {

                int count = 0;

                for (Control control : controls) {
                    if (!control.getPageId().isEmpty() && control.getPageId() != null) {
                        topResult[count] = control;
                        count++;
                    }
                }

                printReport(topResult, "THE MOST VISITED PAGES ARE: ", count);

            }
            if (query.getType() == QueryTypes.GET_COMPONENTS.ordinal()) {
                Control control = query.getUniqId();
                Action action;
                try {
                    if (control.getPageId().isEmpty()) {

                        action = new DocumentManager().getJsonObject(control.getSiteId());
                    } else {
                        action = new DocumentManager().getJsonObject(control.getPageId());
                    }

                    int comps = countComps(query.getComponentType(), action.getComponents());
                    response += "The number of components in " + control.getSiteId() + "/" + control.getPageId() +
                            " are: " + comps + "\n";
                } catch (ErrorE e) {
                    e.printStackTrace();
                }
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

    private void printReport(Control[] control, String report, int count) {
        response += report + "\n";
        for (int i = 0; i < count; i++) {
            response += (i + 1) + ") " + control[i].getSiteId() + "/" + control[i].getPageId() + ":" +
                    "numero de visitas:" + control[i].getCount() + "\n";
        }
    }

    private int countComps(int type, ArrayList<Component> comps) {
        int count = 0;
        if (type == ComponentType.ALL.ordinal()) return comps.size();

        for (Component comp : comps) {
            if (comp.getType() == type) {
                count++;
            }
        }
        return count;
    }

    public String getResponse() {
        return response;
    }
}
