package srbn.Domain.Components;

public class Component {

    private String id;
    private String page;
    private int type;

    public Component(String id, String page, int type) {
        this.id = id;
        this.page = page;
        this.type = type;
    }

    public String getId() { 
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "id='" + id + '\'' +
                ", page='" + page + '\'' +
                ", type=" + type +
                '}';
    }
}
