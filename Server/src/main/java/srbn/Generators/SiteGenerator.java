package srbn.Generators;

import srbn.Domain.Actions.Action;
import srbn.Domain.Errors.ErrorE;
import srbn.Domain.Label;
import srbn.Management.Folders.DocumentManager;
import srbn.Management.Folders.FolderManagment;

public class SiteGenerator {


    private Action newSite;
    private String idSite;
    private String userCreator;
    private String creationDate;
    private String userModification;
    private String modificationDate;
    private String PATH;
    private String completePath;
    private String URL;

    public SiteGenerator(Action newSite) {
        this.newSite = newSite;
        this.idSite = newSite.getId();
        this.userCreator = newSite.getUserCreation();
        this.creationDate = newSite.getCreationDate();
        this.userModification = newSite.getUserModification();
        this.modificationDate = newSite.getModificationDate();
        this.PATH = newSite.getId() + ".html";
    }

    public boolean generate() throws ErrorE {

        try {
            DocumentManager.writeJSONobject(newSite);
            this.PATH = FolderManagment.createSiteFolder(newSite) + "/index.html";
            this.completePath = DocumentManager.writeHTMLToFile(setupHtml().toString(), PATH);
            return true;

        } catch (ErrorE e) {
            throw new ErrorE("Error generating site: " + e.getMessage());
        }
    }

    private StringBuilder setupHtml() {
        StringBuilder site = new StringBuilder(HtmlMainGenerator.headHTML + '\n');
        site.append("        <title>").append(idSite).append("</title>\n");
        site.append("</head>\n");
        site.append("<body>\n");
        site.append(HtmlMainGenerator.getScript(idSite, "", ""));
        site.append("    <div id=\"").append(idSite).append("\">\n");
        site.append("        <h1>").append(idSite).append("</h1>\n");
        site.append("    </div>\n");
        site.append("    <div>\n");
        site.append("        <h1>Nuevo sitio web creado desde aplicacion cliente</h1>\n");
        site.append("    </div>\n");
        site.append("    </div>\n");
        for (Label lbl : newSite.getLabels()) {
            site.append("<a href=\"").append(lbl.getValue()).append("\">").append(lbl.getValue()).append("</a>");
        }
        site.append("    <div>\n");
        site.append(new CompGenerator().generateComponents(newSite.getComponents()));
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

    public StringBuilder getSiteConfSv() {
        StringBuilder conf = new StringBuilder("");

        conf.append("<VirtualHost *:80>\n");
        conf.append("    DocumentRoot ").append("C:\\xampp\\htdocs").append(idSite).append("\n");
        conf.append("    ServerName www.").append(idSite.toLowerCase()).append(".com\n");
        conf.append("    ServerAlias ").append(idSite.toLowerCase()).append(".com\n");
        conf.append("    ErrorLog \"logs/").append(idSite.toLowerCase()).append("-error.log\"\n");
        conf.append("    CustomLog \"logs/").append(idSite.toLowerCase()).append("-access.log\" common\n");
        conf.append("</VirtualHost>\n");

        return conf;
    }

    public String getIdSite() {
        return idSite;
    }

}
