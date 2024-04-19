package srbn.Domain.Queries;

import java.util.ArrayList;

public class Query {

    private int type;
    private int componentType;
    private ArrayList<String> paths;
    private String uniqId;

    public Query() {
    }

    public Query(int type, String componentType, ArrayList<String> paths) {
        this.type = type;
        this.componentType = Integer.parseInt(componentType);
        this.paths = paths;
    }

    public Query(int type, ArrayList<String> paths) {
        this.type = type;
        this.paths = paths;
    }

    public Query(int type, String uniqId) {
        this.type = type;
        this.uniqId = uniqId;
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
