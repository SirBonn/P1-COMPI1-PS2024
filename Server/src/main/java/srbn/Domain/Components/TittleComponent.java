package srbn.Domain.Components;


public class TittleComponent extends Component {

    private String text;
    private String color;
    private int justify;

    public TittleComponent(int type, String text, String color, String align) {
        super(type);
        this.text = text;
        this.color = color;
        this.justify = setJustify(align);
    }

    public TittleComponent(String idComp, String pageComp, int type, String text, String color, String align) {
        super(idComp, pageComp, type);
        this.text = text;
        this.color = color;
        this.justify = setJustify(align);
    }

    public String getText() {
        return text;
    }

    public String getColor() {
        return color;
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
        return "TittleComponent{" +
                "text='" + text + '\'' +
                ", color='" + color + '\'' +
                ", align=" + justify +
                '}';
    }
}
