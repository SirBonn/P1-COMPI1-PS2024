package srbn.Managment.ServerToServer;

import java.util.ArrayList;

public class TaskManager {

    private ArrayList<String> domainsOcuped;

    public TaskManager() {
        domainsOcuped = new ArrayList<>();
    }

    public void addDomain(String domain) {
        domainsOcuped.add(domain);
    }

    public void removeDomain(String domain) {
        domainsOcuped.remove(domain);
    }

    public boolean isDomainOcuped(String response, String domain) {
        if(domainsOcuped.contains(domain)){
            response = "the domain " + domain +" already in use (error)";
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> getDomainsOcuped() {
        return domainsOcuped;
    }
}
