package srbn.Managment.Folders;

import java.io.File;

public class FolderManagment {

    public static final String APACHE_SV_FOLD = "SetupSv/ApacheSv";
    public static final String SITES_SV_FOLD = "SetupSv/SitesSv";
    public static final String ACTIONS_FOLD = "SetupSv/ActionsSv";


    public FolderManagment() {
    }

    public void setup() {
        File dir = new File("SetupSv/");
        if (dir.exists()) {
            System.out.println("folder already exists");
        } else {

            dir = new File(APACHE_SV_FOLD);
            dir.mkdirs();
            dir = new File(SITES_SV_FOLD);
            dir.mkdirs();
            dir = new File(ACTIONS_FOLD);
            dir.mkdirs();
        }
    }


    public static String createSiteFolder(String siteName) {
        File dir = new File(SITES_SV_FOLD + "/" + siteName);
        if (dir.exists()) {
            System.out.println("folder already exists");
        } else {
            dir.mkdirs();
        }

        return SITES_SV_FOLD + "/" + siteName;
    }


}
