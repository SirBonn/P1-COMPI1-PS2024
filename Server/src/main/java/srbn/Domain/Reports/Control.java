package srbn.Domain.Reports;

public class Control {

    private String siteId;
    private String pageId;
    private String ParentId;
    private int count;

    public Control() {
    }

    public Control(String siteId, String pageId, String ParentId, int count) {
        this.siteId = siteId;
        this.pageId = pageId;
        this.ParentId = ParentId;
        this.count = count;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
