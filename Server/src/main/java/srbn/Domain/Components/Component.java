package srbn.Domain.Components;

public class Component {

    private String idComp;
    private String pageComp;
    private int type;

    public Component(String idComp, String pageComp, int type) {
        this.idComp = idComp;
        this.pageComp = pageComp;
        this.type = type;
    }

    public Component(int type) {
        this.idComp = "";
        this.pageComp = "";
        this.type = type;
    }

    public String getIdComp() {
        return idComp;
    }

    public void setIdComp(String idComp) {
        this.idComp = idComp;
    }

    public String getPageComp() {
        return pageComp;
    }

    public void setPageComp(String pageComp) {
        this.pageComp = pageComp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Component{" +
                "id='" + idComp + '\'' +
                ", page='" + pageComp + '\'' +
                ", type=" + type +
                '}';
    }
}
