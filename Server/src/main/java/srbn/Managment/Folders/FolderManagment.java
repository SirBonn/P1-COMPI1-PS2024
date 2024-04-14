package srbn.Managment.Folders;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FolderManagment {

    private final String apacheSv = "SetupSv/ApacheSv";
    private final String sitesSv = "SetupSv/SitesSv";

    public FolderManagment() {
    }

    public void setup() {
        File dir = new File("tmp/");
        if (dir.exists()) {
            System.out.println("folder already exists");
        }

        dir = new File(apacheSv);
        dir.mkdirs();
        dir = new File(sitesSv);
        dir.mkdirs();
    }

}
