package srbn.Domain.Components;

public class ParagraphComponent extends Component{

    private String text;
    private int align;
    private String color;

    public ParagraphComponent(int type, String text, int align, String color) {
        super("", "", type);
        this.text = text;
        this.align = align;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public int getAlign() {
        return align;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "ParagraphComponent{" +
                "text='" + text + '\'' +
                ", align=" + align +
                ", color='" + color + '\'' +
                '}';
    }
}
