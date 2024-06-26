package srbn.Domain.Actions;

import srbn.Domain.Components.Component;
import srbn.Domain.Errors.ErrorE;
import srbn.Generators.PageGenerator;
import srbn.Generators.SiteGenerator;
import srbn.Management.Folders.DocumentManager;
import srbn.Management.Folders.FolderManagment;
import srbn.Management.ServerToServer.TaskManager;

import java.util.ArrayList;

public class ActionManager {

    private ArrayList<Action> actions;
    private String response;
    TaskManager taskManager;

    public ActionManager(ArrayList<Action> actions, TaskManager taskManager) {
        this.actions = actions;
        this.response = "";
        this.taskManager = taskManager;
    }

    public void executeActions() throws ErrorE {
        for (Action act : actions) {
            if (act.getActionType() == ActionTypes.NEW_SITE.ordinal() && !taskManager.isDomainOcuped(this.response, act.getId())) {
                SiteGenerator siteGenerator = new SiteGenerator(act);
                if (siteGenerator.generate()) {
                    taskManager.addDomain(act.getId());
                    DocumentManager.writeDomains(taskManager);
                    this.response += "\n" + act.getId() + " created successfully (ok)";
                }

            }
            if (act.getActionType() == ActionTypes.DELETE_SITE.ordinal() && taskManager.isDomainOcuped(this.response, act.getId())) {

                if (delecteAct(act)) {
                    taskManager.removeDomain(act.getId());
                    DocumentManager.writeDomains(taskManager);
                    this.response += "\n" + act.getId() + " deleted successfully (ok)";
                }

            }
            if (act.getActionType() == ActionTypes.NEW_PAGE.ordinal() && !taskManager.isDomainOcuped(this.response, act.getSite() + "." + act.getId())) {
                PageGenerator pageGenerator = new PageGenerator(act);

                if (pageGenerator.generate()) {
                    taskManager.addDomain(act.getSite() + "." + act.getId());
                    DocumentManager.writeDomains(taskManager);
                    this.response += "\n" + act.getId() + " created successfully (ok)";
                }
            }
            if (act.getActionType() == ActionTypes.MODIFY_PAGE.ordinal()) {

                Action actDecl = act;
                act = getJsonObject(act.getPage());

                act.setId(actDecl.getId());
                act.setTittle(actDecl.getTittle());
                act.setLabels(actDecl.getLabels());

                if (delecteAct(act)) {
                    new PageGenerator(act).generate();
                    this.response += "\n" + act.getId() + " overrited successfully (ok)";
                }

            }
            if (act.getActionType() == ActionTypes.DELETE_PAGE.ordinal() && taskManager.isDomainOcuped(this.response, act.getSite() + "." + act.getId())) {

                if (delecteAct(act)) {
                    taskManager.removeDomain(act.getSite() + "." + act.getId());
                    DocumentManager.writeDomains(taskManager);
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
            if (act.getActionType() == ActionTypes.DELETE_COMPONENT.ordinal()) {

                Action actDecl = act;
                act = getJsonObject(act.getTittle());

                for (Component c : act.getComponents()) {
                    if (c.getIdComp().equals(actDecl.getId())) {
                        act.getComponents().remove(c);
                        break;
                    }
                }

                if (delecteAct(act)) {
                    this.response += "\n" + act.getId() + " removed of "+ act.getTittle() +" successfully (ok)";
                }

                new PageGenerator(act).generate();
            }

        }

    }

    private Action getJsonObject(String id) {
        Action act = null;

        try {
            act = new DocumentManager().getJsonObject(id);
        } catch (ErrorE e) {
            this.response += "\n INTERNAL SERVER ERROR, " + id + ".json NOT FOUND (Crical error)\n";
        }

        return act;
    }

    private boolean delecteAct(Action act) {

        act = getJsonObject(act.getId());

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

