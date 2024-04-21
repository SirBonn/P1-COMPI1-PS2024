package srbn.Domain.Reports;

import java.util.ArrayList;

public class Query {

    private int type;
    private int componentType;
    private ArrayList<Control> paths;
    private Control uniqId;

    public Query() {
    }

    public Query(String type, int componentType, ArrayList<Control> controlPath) {
        this.type = setQueryType(type);
        this.componentType = componentType;
        this.uniqId = controlPath.get(0);
    }

    public Query(String type, ArrayList<Control> paths) {
        this.type = setQueryType(type);
        this.paths = paths;
    }


    private int setQueryType(String type) {

        if (type.equals("GET_VISITS_PAGE")) {
            return QueryTypes.GET_VISITS_PAGE.ordinal();
        }
        if (type.equals("GET_VISITS_SITE")) {
            return QueryTypes.GET_VISITS_SITE.ordinal();
        }
        if (type.equals("GET_POPULAR_PAGES")) {
            return QueryTypes.GET_POPULAR_PAGES.ordinal();
        }
        if (type.equals("GET_COMPONENTS")) {
            return QueryTypes.GET_COMPONENTS.ordinal();
        } else {
            return -1;
        }

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getComponentType() {
        return componentType;
    }

    public void setComponentType(int componentType) {
        this.componentType = componentType;
    }

    public ArrayList<Control> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<Control> paths) {
        this.paths = paths;
    }

    public Control getUniqId() {
        return uniqId;
    }

    public void setUniqId(Control uniqId) {
        this.uniqId = uniqId;
    }
}
