package srbn.Domain.Components;

import srbn.Domain.ActionTypes;
import srbn.Domain.Action;
import srbn.Domain.ErrorE;
import srbn.Generators.SiteGenerator;
import srbn.Managment.Folders.DocumentManager;

import java.util.ArrayList;

public class ActionManager {

    private ArrayList<Action> actions;

    public ActionManager(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public void executeActions() throws ErrorE {
        for (Action act : actions) {
            int actionType = ActionTypes.NEW_SITE.ordinal();
            if (act.getActionType() == 2) {
                new SiteGenerator(act).generateSite();
                DocumentManager.writeJSONobject(act);
            }
            if (act.getActionType() == ActionTypes.DELETE_SITE.ordinal()){
                new SiteGenerator(act).generateSite();

            }
            if (act.getActionType() == ActionTypes.NEW_PAGE.ordinal()){

            }
            if (act.getActionType() == ActionTypes.MODIFY_PAGE.ordinal()){

            }
            if (act.getActionType() == ActionTypes.DELETE_PAGE.ordinal()){

            }
            if (act.getActionType() == ActionTypes.ADD_COMPONENT.ordinal()){

            }
            if (act.getActionType() == ActionTypes.MODIFY_COMPONENT.ordinal()){

            }

        }

    }
}

