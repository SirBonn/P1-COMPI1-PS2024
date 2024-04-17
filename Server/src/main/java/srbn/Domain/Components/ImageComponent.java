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
        this.justify = setJustify(justify);
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

    public int setJustify(String justify) {
        if(justify.equals("left")){
            return 0;
        }else if(justify.equals("center")){
            return 1;
        } else if(justify.equals("right")){
            return 2;
        } else {
            return 3;
        }
    }

    public String getJustify() {
        if(justify == 0){
            return "left";
        }else if(justify == 1){
            return "center";
        } else if(justify == 2){
            return "right";
        } else {
            return "justify";
        }
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
