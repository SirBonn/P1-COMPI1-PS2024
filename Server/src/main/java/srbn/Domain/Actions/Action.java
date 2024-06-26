package srbn.Domain.Actions;

import srbn.Domain.Components.Component;
import srbn.Domain.Label;

import java.util.ArrayList;


public class Action {

    private String id = "";
    private String tittle = "";
    private String site = "";
    private String parent = "";
    private String userCreation = "";
    private String creationDate = "";
    private String userModification = "";
    private String modificationDate = "";
    private String page = "";
    private String clase = "";
    private int actionType = 99;
    private ArrayList<Label> labels;
    private ArrayList<Component> components;


    public Action(String id, String tittle, String site, String parent, String userCreation, String creationDate,
                  String userModification, String modificationDate, String page, String clase, int actionType) {
        this.id = id;
        this.tittle = tittle;
        this.site = site;
        this.parent = parent;
        this.userCreation = userCreation;
        this.creationDate = creationDate;
        this.userModification = userModification;
        this.modificationDate = modificationDate;
        this.page = page;
        this.clase = clase;
        this.actionType = actionType;
        components = null;
        labels = null;
    }

    public Action(String id, int actionType) {
        this.page = id;
        this.actionType = actionType;
    }

    public Action(String id, String userCreation, String creationDate, String userModification, String modificationDate, int actionType) {
        this.id = id;
        this.userCreation = userCreation;
        this.creationDate = creationDate;
        this.userModification = userModification;
        this.modificationDate = modificationDate;
        this.actionType = actionType;
    }

    public Action(String id, String tittle, int actionType) {
        this.id = id;
        this.tittle = tittle;
        this.actionType = actionType;
        components = null;
    }

    public Action() {
        this.id = "";
        this.tittle = "";
        this.site = "";
        this.parent = "";
        this.userCreation = "";
        this.creationDate = "";
        this.userModification = "";
        this.modificationDate = "";
        this.page = "";
        this.clase = "";
        this.actionType = 99;
        components = null;
    }

    public Action(String id, String page, Component component, int actionType) {
        this.id = id;
        this.page = page;
        this.components = new ArrayList<>();
        this.components.add(component);
        this.actionType = actionType;
    }

    public Action(String id, String tittle, String site, String parent, String userCreation, String creationDate, String userModification, String modificationDate, String page, String clase, int actionType, ArrayList<Label> labels, ArrayList<Component> components) {
        this.id = id;
        this.tittle = tittle;
        this.site = site;
        this.parent = parent;
        this.userCreation = userCreation;
        this.creationDate = creationDate;
        this.userModification = userModification;
        this.modificationDate = modificationDate;
        this.page = page;
        this.clase = clase;
        this.actionType = actionType;
        this.labels = labels;
        this.components = components;
    }

    public Action(String idP, String tittleP, String siteP, String parentP, String userCrtP, String creationDateP, String modDatP, String userModP, int typeAction) {
        this.id = idP;
        this.tittle = tittleP;
        this.site = siteP;
        this.parent = parentP;
        this.userCreation = userCrtP;
        this.creationDate = creationDateP;
        this.modificationDate = modDatP;
        this.userModification = userModP;
        this.actionType = typeAction;
    }

    public void addComponent(Component component) {

        if (components == null) {
            components = new ArrayList<>();
        }

        for(Component c : components){
            if(c.getIdComp().equals(component.getIdComp())){
                components.remove(c);
                break;
            }
        }

        components.add(component);
    }

    public Component getComponent(String idComp){
        for(Component c : components){
            if(c.getIdComp().equals(idComp)){
                return c;
            }
        }
        return null;
    }


    public String getId() {
        return id;
    }

    public ArrayList<Label> getLabels() {
        if(labels == null)
            labels = new ArrayList<>();
        return labels;
    }

    public void setLabels(ArrayList<Label> labels) {
        this.labels = labels;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUserModification() {
        return userModification;
    }

    public void setUserModification(String userModification) {
        this.userModification = userModification;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public ArrayList<Component> getComponents() {
        if(components == null)
            components = new ArrayList<>();

        return components;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id='" + id + '\'' +
                ", tittle='" + tittle + '\'' +
                ", site='" + site + '\'' +
                ", parent='" + parent + '\'' +
                ", userCreation='" + userCreation + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", userModification='" + userModification + '\'' +
                ", modificationDate='" + modificationDate + '\'' +
                ", page='" + page + '\'' +
                ", clase='" + clase + '\'' +
                ", actionType=" + actionType +
                ", labels=" + labels +
                ", components=" + components +
                '}';
    }
}
