package srbn.Domain;

import srbn.Domain.Components.Component;
import srbn.Generators.PageGenerator;
import srbn.Generators.SiteGenerator;
import srbn.Managment.Folders.DocumentManager;
import srbn.Managment.Folders.FolderManagment;

import java.util.ArrayList;

public class ActionManager {

    private ArrayList<Action> actions;
    private String response;

    public ActionManager(ArrayList<Action> actions, String response) {
        this.actions = actions;
        this.response = response;
    }

    public void executeActions() throws ErrorE {
        for (Action act : actions) {
            if (act.getActionType() == ActionTypes.NEW_SITE.ordinal()) {
                new SiteGenerator(act).generate();
            }
            if (act.getActionType() == ActionTypes.DELETE_SITE.ordinal()) {
                delecteAct(act);
            }
            if (act.getActionType() == ActionTypes.NEW_PAGE.ordinal()) {
                new PageGenerator(act).generate();
            }
            if (act.getActionType() == ActionTypes.MODIFY_PAGE.ordinal()) {

            }
            if (act.getActionType() == ActionTypes.DELETE_PAGE.ordinal()) {
                delecteAct(act);
            }
            if (act.getActionType() == ActionTypes.ADD_COMPONENT.ordinal()) {
                Action actDecl = act;
                act = new DocumentManager().getJsonObject(act.getPage());

                for (Component c : actDecl.getComponents()) {
                    act.addComponent(c);
                }
                delecteAct(act);
                new PageGenerator(act).generate();

            }
            if (act.getActionType() == ActionTypes.MODIFY_COMPONENT.ordinal()) {

            }

        }

    }

    private void delecteAct(Action act) {

        try {
            act = new DocumentManager().getJsonObject(act);
        } catch (ErrorE e) {
            response += "\n" + act.getId() + " INTERNAL SERVER ERROR (Crical error)\n";
        }

        String HTML_FOLD_PATH = FolderManagment.SITES_SV_FOLD + "/" + DocumentManager.getFilePathByActionType(act);
        String JSON_PATH = FolderManagment.ACTIONS_FOLD + "/" + act.getId() + ".json";
        if (FolderManagment.deleteFolder(HTML_FOLD_PATH) && DocumentManager.deleteFile(JSON_PATH)) {
            response += "\n" + act.getId() + " deleted successfully (ok)";
        } else {
            response += "\n" + act.getId() + " could not be deleted (error)";
        }

    }
}

