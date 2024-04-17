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

    public ActionManager(ArrayList<Action> actions) {
        this.actions = actions;
        this.response = "";
    }

    public void executeActions() throws ErrorE {
        for (Action act : actions) {
            if (act.getActionType() == ActionTypes.NEW_SITE.ordinal()) {
                new SiteGenerator(act).generate();
            }
            if (act.getActionType() == ActionTypes.DELETE_SITE.ordinal()) {
                if (delecteAct(act)) {
                    this.response += "\n" + act.getId() + " overrited successfully (ok)";
                }
            }
            if (act.getActionType() == ActionTypes.NEW_PAGE.ordinal()) {
                new PageGenerator(act).generate();
            }
            if (act.getActionType() == ActionTypes.MODIFY_PAGE.ordinal()) {
                Action actDecl = act;
                act = getJsonObject(act.getPage());

                act.setId(actDecl.getId());
                act.setTittle(actDecl.getTittle());
                act.setLabels(actDecl.getLabels());

                if (delecteAct(act)) {
                    this.response += "\n" + act.getId() + " overrited successfully (ok)";
                }
                new PageGenerator(act).generate();

            }
            if (act.getActionType() == ActionTypes.DELETE_PAGE.ordinal()) {
                if (delecteAct(act)) {
                    this.response += "\n" + act.getId() + " deleted successfully (ok)";
                }

            }
            if (act.getActionType() == ActionTypes.ADD_COMPONENT.ordinal()) {
                Action actDecl = act;
                act = getJsonObject(act.getPage());

                for (Component c : actDecl.getComponents()) {
                    if (act.getComponent(c.getIdComp()) != null) {
                        this.response += "\n" + act.getId() + " Component already exist in " + act.getSite() + "\\" + act.getPage() + "(overrided)\n";
                    } else {
                        this.response += "\n" + act.getId() + " Component added in" + act.getSite() + "\\" + act.getPage() + "(ok)\n";

                    }
                    act.addComponent(c);
                }
                if (delecteAct(act)) {
                    this.response += "\n" + act.getId() + " component added successfully (ok)";
                }

                new PageGenerator(act).generate();
            }
            if (act.getActionType() == ActionTypes.MODIFY_COMPONENT.ordinal()) {

                Action actDecl = act;
                act = getJsonObject(act.getPage());

                for (Component c : actDecl.getComponents()) {

                    if (act.getComponent(c.getIdComp()) != null) {
                        act.addComponent(c);
                    } else {
                        this.response += "\n" + act.getId() + " Component not found in " + act.getSite() + "\\" + act.getPage() + "(error)\n";
                    }
                }
                if (delecteAct(act)) {
                    this.response += "\n" + act.getId() + " overrited successfully (ok)";
                }

                new PageGenerator(act).generate();
            }

        }

    }

    private Action getJsonObject(Action act) {
        try {
            act = new DocumentManager().getJsonObject(act);
        } catch (ErrorE e) {
            this.response += "\n" + act.getId() + " INTERNAL SERVER ERROR (Crical error)\n";
        }

        return act;
    }

    private Action getJsonObject(String id) {
        Action act = null;

        try {
            act = new DocumentManager().getJsonObject(id);
        } catch (ErrorE e) {
            this.response += "\n" + act.getId() + " INTERNAL SERVER ERROR (Crical error)\n";
        }

        return act;
    }

    private boolean delecteAct(Action act) {

        act = getJsonObject(act);

        String HTML_FOLD_PATH = FolderManagment.SITES_SV_FOLD + "/" + DocumentManager.getFilePathByActionType(act);
        String JSON_PATH = FolderManagment.ACTIONS_FOLD + "/" + act.getId() + ".json";
        if (FolderManagment.deleteFolder(HTML_FOLD_PATH) && DocumentManager.deleteFile(JSON_PATH)) {

            return true;
        } else {
            this.response += "\n" + act.getId() + " could not be deleted (error)";
            return false;
        }

    }

    public String getResponse() {
        return response;
    }
}

