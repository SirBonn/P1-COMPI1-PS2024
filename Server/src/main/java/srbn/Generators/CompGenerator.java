package srbn.Generators;

import srbn.Domain.Components.*;

import java.util.ArrayList;

public class CompGenerator {

    private Component comp;
    private String id;
    private String txt;
    private String color;
    private String align;
    private String src;
    private String height;
    private String width;
    private StringBuilder html  ;
    private int type;

    public CompGenerator(Component comp) {
        this.comp = compIdentifier(comp);
        this.id = comp.getIdComp();
        this.html = new StringBuilder("");
    }

    public CompGenerator() {
        this.html = new StringBuilder("");
    }

    public String generateComponents(ArrayList<Component> comps) {
        if (comps == null) {
            return "";
        } else {
            for (Component comp : comps) {
                this.comp = compIdentifier(comp);
                generate();
                html.append("\n");
            }
            return html.toString();
        }

    }

    public Component compIdentifier(Component comp) {
        this.id = comp.getIdComp();
        if (comp.getType() == ComponentType.IMAGE.ordinal()) {
            ImageComponent compI = (ImageComponent) comp;
            this.src = compI.getSrc();
            this.height = Integer.toString(compI.getHeight());
            this.width = Integer.toString(compI.getWidth());
            this.align = compI.getJustify();
            return compI;
        } else if (comp.getType() == ComponentType.TITTLE.ordinal()) {
            TittleComponent compT = (TittleComponent) comp;
            this.txt = compT.getText();
            this.color = compT.getColor();
            this.align = compT.getJustify();

            return compT;
        } else if (comp.getType() == ComponentType.VIDEO.ordinal()) {
            VideoComponent compV = (VideoComponent) comp;
            this.src = compV.getSrc();
            this.height = Integer.toString(compV.getHeight());
            this.width = Integer.toString(compV.getWidth());
            return compV;
        } else if (comp.getType() == ComponentType.PARAGRAPH.ordinal()) {
            ParagraphComponent compP = (ParagraphComponent) comp;
            this.txt = compP.getText();
            this.color = compP.getColor();
            this.align = compP.getJustify();

            return comp;
        } else if (comp.getType() == ComponentType.MENU.ordinal()) {
            MenuComponent compM = (MenuComponent) comp;
            return comp;
        } else {
            return comp;
        }

    }

    public StringBuilder generate() {

        if (comp.getType() == ComponentType.IMAGE.ordinal()) {
            generateImage();
        } else if (comp.getType() == ComponentType.TITTLE.ordinal()) {
            generateTittle();
        } else if (comp.getType() == ComponentType.VIDEO.ordinal()) {
            generateVideo();
        } else if (comp.getType() == ComponentType.PARAGRAPH.ordinal()) {
            generateParagraph();
        } else if (comp.getType() == ComponentType.MENU.ordinal()) {
            generateMenu();
        }

        return html;
    }

    private void generateMenu() {

    }

    private void generateParagraph() {
        html.append("   <div id=\"").append(id).append("\">\n");
        html.append("       <p style=\"color: ").append(color).append(";");
        html.append("text-align: ").append(align).append(";\">").append(txt).append("</p>\n");
        html.append("   </div>\n");
    }

    private void generateVideo() {
        html.append("   <div id=\"").append(id).append("\">\n");
        html.append("       <img src=\"").append(src).append("\"");
        html.append("style=\"display: block;");
        html.append("width: ").append(width).append("px; height: ").append(height).append("px;\">\n");
        html.append("   </div>\n");
    }

    private void generateTittle() {
        html.append("   <div id=\"").append(id).append("\">\n");
        html.append("       <h1 style=\"color: ").append(color).append(";");
        html.append("text-align: ").append(align).append(";\">").append(txt).append("</h1>\n");
        html.append("   </div>\n");
    }

    private void generateImage() {
        html.append("   <div id=\"").append(id).append("\">\n");
        html.append("       <img src=\"").append(src).append("\"");
        html.append("style=\"display: block;");
        html.append("width: ").append(width).append("px; height: ").append(height).append("px;\">\n");
        html.append("   </div>\n");
    }

    public String getId() {
        return id;
    }
}
