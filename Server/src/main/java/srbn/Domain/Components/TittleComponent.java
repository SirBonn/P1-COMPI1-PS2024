package srbn.Domain.Components;


public class TittleComponent extends Component {

    private String text;
    private String color;
    private int align;

    public TittleComponent(int type, String text, String color, int align) {
        super("", "", type);
        this.text = text;
        this.color = color;
        this.align = align;
    }

    public String getText() {
        return text;
    }

    public String getColor() {
        return color;
    }

    public int getAlign() {
        return align;
    }

    @Override
    public String toString() {
        return "TittleComponent{" +
                "text='" + text + '\'' +
                ", color='" + color + '\'' +
                ", align=" + align +
                '}';
    }
}
