package srbn.Generators;

import srbn.Domain.Action;
import srbn.Domain.ErrorE;
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

    public void generate() throws ErrorE {

        DocumentManager.writeJSONobject(newPage);
        this.PATH = FolderManagment.createSiteFolder(newPage) + "/" + newPage.getId() + ".html";
        DocumentManager.writeHTMLToFile(setupHtml().toString(), PATH);

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
        site.append("    </div>\n");
        site.append(new CompGenerator().generateComponents(newPage.getComponents()));
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

    public String getPageId() {
        return pageId;
    }

}
