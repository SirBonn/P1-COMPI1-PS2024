package srbn.Domain.Components;

public class ImageComponent extends Component{

    private String src;
    private int height;
    private int width;
    private int justify;

    public ImageComponent (int type, String src, String height, String width, String justify) {
        super("", "", type);
        this.src = src;
        this.height = Integer.parseInt(height);
        this.width = Integer.parseInt(width);
        this.justify = Integer.parseInt(justify);
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

    public int getJustify() {
        return justify;
    }

    @Override
    public String toString() {
        return "ImageComponent{" +
                "src='" + src + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", justify=" + justify +
                '}';
    }
}
