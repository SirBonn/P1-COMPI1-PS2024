package srbn.Domain.Actions;

public enum ActionTypes {

    NEW_SITE(0),
    DELETE_SITE(1),
    NEW_PAGE(2),
    MODIFY_PAGE(3),
    DELETE_PAGE(4),
    ADD_COMPONENT(5),
    MODIFY_COMPONENT(6);

    private final int index;

    ActionTypes(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
