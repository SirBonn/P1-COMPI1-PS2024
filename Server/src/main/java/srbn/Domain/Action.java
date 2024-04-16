package srbn.Domain;

import srbn.Domain.Components.Component;

import java.util.ArrayList;


public class Action {

    private String id = "";
    private String tittle = "";
    private String site = "";
    private String parent= "";
    private String userCreation = "";
    private String creationDate = "";
    private String userModification = "";
    private String modificationDate = "";
    private String page = "";
    private String clase = "";
    private int actionType = 99;
    private ArrayList<Label> labels;
    private Component component;


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
        component = null;
        labels = null;
    }

    public Action(String id, int actionType) {
        this.id = id;
        this.actionType = actionType;
    }

    public Action (String id, String userCreation, String creationDate, String userModification, String modificationDate, int actionType){
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
        component = null;
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
        actionType = 99;
        component = null;
    }

    public Action(String id, String page, Component component, int actionType) {
        this.id = id;
        this.page = page;
        this.component = component;
        this.actionType = actionType;
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

    public String getId() {
        return id;
    }

    public ArrayList<Label> getLabels() {
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

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
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
                ", component=" + component +
                '}';
    }



}
