package srbn.Generators;

import srbn.Domain.Actions.Action;
import srbn.Domain.Components.Component;
import srbn.Domain.Errors.ErrorE;
import srbn.Domain.Label;
import srbn.Managment.Folders.DocumentManager;
import srbn.Managment.Folders.FolderManagment;

import java.util.ArrayList;

public class PageGenerator {


    private Action newPage;
    private String pageId;
    private String tittlePage;
    private String siteId;
    private String partentPage;
    private String userCreator;
    private String creationDate;
    private String userModification;
    private String modificationDate;
    private ArrayList<String> labelsValues;
    private String PATH;
    private String completePath;

    public PageGenerator(Action newPage) {
        this.newPage = newPage;
        this.pageId = newPage.getId();
        this.tittlePage = newPage.getTittle();
        this.siteId = newPage.getSite();
        this.partentPage = newPage.getParent();
        this.userCreator = newPage.getUserCreation();
        this.creationDate = newPage.getCreationDate();
        this.userModification = newPage.getUserModification();
        this.modificationDate = newPage.getModificationDate();

        this.labelsValues = new ArrayList<>();

        for (Label lbl : newPage.getLabels()) {
            labelsValues.add(lbl.getValue());
        }

        this.PATH = newPage.getId() + ".html";
    }

    public boolean generate() throws ErrorE {


        try {
            DocumentManager.writeJSONobject(newPage);
            this.PATH = FolderManagment.createSiteFolder(newPage) + "/index.html";
            this.completePath = DocumentManager.writeHTMLToFile(setupHtml().toString(), PATH);

            return true;

        } catch (ErrorE e) {
            throw new ErrorE("Error generating site: " + e.getMessage());
        }

    }

    private StringBuilder setupHtml() {
        StringBuilder site = new StringBuilder(HtmlMainGenerator.headHTML + '\n');
        site.append("        <title>").append(tittlePage).append("</title>\n");
        site.append("</head>\n");
        site.append("<body>\n");
        site.append("    <div id=\"").append(pageId).append("\">\n");
        site.append("        <h1>").append(pageId).append("</h1>\n");
        site.append("    </div>\n");
        site.append("    <div>\n");
        site.append("        <h1>Nueva pagina creada desde aplicacion cliente</h1>\n");
        site.append("        <h3>Sitio al que pertenece: ").append(siteId).append("</h3>\n");
        site.append("        <h3>Pagina de la que deriva (padre): ").append(partentPage).append("</h3>\n");
//        site.append("    </div>\n");
//        for (Label lbl : newPage.getLabels()) {
//            site.append(new CompGenerator().generateComponent(comp)); todo generate labels html
//        }
//        site.append("    <div>\n");
        site.append("    </div>\n");
        for (Component comp : newPage.getComponents()) {
            site.append(new CompGenerator().generateComponent(comp));
        }
        site.append("    <div>\n");
        site.append("       <footer>\n");
        site.append("           <p>© Organizacion de lenguajes y compiladores 2024</p><p>Todos los derechos reservados.</p>\n");
        site.append("           <p>Created by ").append(userCreator).append(" on ").append(creationDate).append("</p>\n");
        site.append("           <p>Last modified by ").append(userModification).append(" on ").append(modificationDate).append("</p>\n");
        site.append("       </footer>\n");
        site.append("    </div>\n");
        site.append("</body>\n");
        site.append("</html>\n");
        return site;
    }

    public StringBuilder getPageConfigSv(){
        StringBuilder conf = new StringBuilder("");

        conf.append("<VirtualHost *:80>\n");
        conf.append("    DocumentRoot ").append("C:\\xampp\\htdocs").append(siteId).append(".").append(pageId).append("\n");
        conf.append("    ServerName www.").append(siteId.toLowerCase()).append(".").append(pageId.toLowerCase()).append(".com\n");
        conf.append("    ServerAlias ").append(siteId.toLowerCase()).append(".").append(pageId.toLowerCase()).append(".com\n");
        conf.append("    ErrorLog \"logs/").append(siteId.toLowerCase()).append("-error.log\"\n");
        conf.append("    CustomLog \"logs/").append(siteId.toLowerCase()).append("-access.log\" common\n");
        conf.append("</VirtualHost>\n");

        return conf;
    }


    public String getPageId() {
        return pageId;
    }

}
