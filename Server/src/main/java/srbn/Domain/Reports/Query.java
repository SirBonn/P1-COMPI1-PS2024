package srbn.Domain.Reports;

import java.util.ArrayList;

public class Query {

    private int type;
    private int componentType;
    private ArrayList<String> paths;
    private String uniqId;

    public Query() {
    }

    public Query(String type, String componentType, ArrayList<String> paths) {
        this.type = setQueryType(type);
        this.componentType = Integer.parseInt(componentType);
        this.paths = paths;
    }

    public Query(String type, ArrayList<String> paths) {
        this.type = setQueryType(type);
        this.paths = paths;
    }

    public Query(String type, String uniqId) {
        this.type = setQueryType(type);
        this.uniqId = uniqId;
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

    public ArrayList<String> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<String> paths) {
        this.paths = paths;
    }

    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

}
