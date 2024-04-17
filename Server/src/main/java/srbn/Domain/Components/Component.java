package srbn.Domain.Components;

public class Component {

    private String idComp;
    private String page;
    private int type;

    public Component(String idComp, String page, int type) {
        this.idComp = idComp;
        this.page = page;
        this.type = type;
    }

    public String getIdComp() {
        return idComp;
    }

    public void setIdComp(String idComp) {
        this.idComp = idComp;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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
                ", page='" + page + '\'' +
                ", type=" + type +
                '}';
    }
}
