package srbn.Domain.Components;

public class ParagraphComponent extends Component{

    private String text;
    private int justify;
    private String color;

    public ParagraphComponent(int type, String text, String align, String color) {
        super(type);
        this.text = text;
        this.justify = setJustify(align);
        this.color = color;
    }

    public ParagraphComponent(String idComp, String pageComp, int type, String text, String align, String color) {
        super(idComp, pageComp, type);
        this.text = text;
        this.justify = setJustify(align);
        this.color = color;
    }

    public String getText() {
        return text;
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

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "ParagraphComponent{" +
                "text='" + text + '\'' +
                ", align=" + justify +
                ", color='" + color + '\'' +
                '}';
    }
}
