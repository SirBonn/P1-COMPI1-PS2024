package srbn.Management.ServerToServer;

import srbn.Domain.Reports.Control;
import srbn.Management.Folders.DocumentManager;

import javax.print.Doc;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<String> domainsOcuped;
    private ArrayList<Control> controls;

    public TaskManager() {

        domainsOcuped = new DocumentManager().getDomains();
        controls = new DocumentManager().getControls();
    }

    public void addDomain(String domain) {
        domainsOcuped.add(domain);
        addControl(domain);
    }

    public void removeDomain(String domain) {
        domainsOcuped.remove(domain);
    }

    public boolean isDomainOcuped(String response, String domain) {
        if (domainsOcuped.contains(domain)) {
            response = "the domain " + domain + " already in use (error)";
            return true;
        } else {
            return false;
        }
    }

    private void addControl(String domain) {

        String[] parts = domain.split("\\.");

        if (parts.length == 3) {
            controls.add(new Control(parts[0], parts[1], parts[2], 0));
        } else if (parts.length == 2) {
            controls.add(new Control(parts[0], parts[1], "", 0));
        } else {
            controls.add(new Control(parts[0], "", "", 0));
        }
    }

    public ArrayList<String> getDomainsOcuped() {
        return domainsOcuped;
    }

    public ArrayList<Control> getControls() {
        return controls;
    }

    public void setControls(ArrayList<Control> controls) {
        this.controls = controls;
    }
}
