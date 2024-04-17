package srbn.Managment.Folders;

import srbn.Domain.Action;

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


    public static String createSiteFolder(Action action) {
        File dir = new File(SITES_SV_FOLD + "/" + DocumentManager.getFilePathByActionType(action));
        if (dir.exists()) {
            System.out.println("folder already exists");
        } else {
            dir.mkdirs();
        }

        return SITES_SV_FOLD + "/" + DocumentManager.getFilePathByActionType(action);
    }

    public static boolean deleteFolder(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            return false;
        }

        File[] archivos = dir.listFiles();
        if (archivos != null) {
            for (File a : archivos) {
                if (a.isDirectory()) {
                    deleteFolder(a.getPath());
                } else {
                    a.delete();
                }
            }
        }

        return dir.delete();
    }
}
