package srbn.Domain.Components;

public class VideoComponent extends Component{

    private String src;
    private int height;
    private int width;

    public VideoComponent(int type, String src, String height, String width) {
        super( type);
        this.src = src;
        this.height = Integer.parseInt(height);
        this.width = Integer.parseInt(width);
    }

    public VideoComponent(String idComp, String pageComp, int type, String src, String height, String width) {
        super(idComp, pageComp, type);
        this.src = src;
        this.height = Integer.parseInt(height);
        this.width = Integer.parseInt(width);
    }

    public String getSrc() {
        return src;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "VideoComponent{" +
                "src='" + src + '\'' +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
