package srbn.Generators;

import srbn.Domain.Action;
import srbn.Domain.ErrorE;
import srbn.Managment.Folders.DocumentManager;
import srbn.Managment.Folders.FolderManagment;

import javax.swing.text.Document;

public class SiteGenerator {

    private Action newSite;
    private String idSite;
    private String userCreator;
    private String creationDate;
    private String userModification;
    private String modificationDate;
    private String PATH;

    public SiteGenerator(Action newSite) {
        this.newSite = newSite;
        this.idSite = newSite.getId();
        this.userCreator = newSite.getUserCreation();
        this.creationDate = newSite.getCreationDate();
        this.userModification = newSite.getUserModification();
        this.modificationDate = newSite.getModificationDate();
        this.PATH = newSite.getId() + ".html";
    }

    public void generateSite() throws ErrorE {

        this.PATH = FolderManagment.createSiteFolder(idSite) + "/"+newSite.getId() + ".html";
        DocumentManager.writeHTMLToFile(setupHtml().toString(), PATH);

    }

    private StringBuilder setupHtml() {
        StringBuilder site = new StringBuilder(HtmlMainGenerator.headHTML + '\n');
        site.append("        <title>").append(idSite).append("</title>\n");
        site.append("</head>\n");
        site.append("<body>\n");
        site.append("    <div id=").append(idSite).append(">\n");
        site.append("        <h1>").append(idSite).append("</h1>\n");
        site.append("    </div>\n");
        site.append("    <div>\n");
        site.append("<footer>\n");
        site.append("        <p>© Organizacion de lenguajes y compiladores 2024</p><p>Todos los derechos reservados.</p>\n");
        site.append("        <p>Created by ").append(userCreator).append(" on ").append(creationDate).append("</p>\n");
        site.append("        <p>Last modified by ").append(userModification).append(" on ").append(modificationDate).append("</p>\n");
        site.append("      </footer>\n");
        site.append("    </div>\n");
        site.append("</body>\n");
        site.append("</html>\n");
        return site;
    }

    public String getIdSite() {
        return idSite;
    }

}
