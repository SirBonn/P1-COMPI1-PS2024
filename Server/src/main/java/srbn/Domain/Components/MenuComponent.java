package srbn.Domain.Components;

import srbn.Domain.Reports.Control;
import srbn.Management.Folders.DocumentManager;

import java.util.ArrayList;

public class MenuComponent extends Component{

    private ArrayList<Control> controls;

    public MenuComponent(String page, int type) {
        super("Menu to" + page +"'s pages" , page, type);

        this.controls = new ArrayList<>();
        ArrayList<Control> staticControls = new DocumentManager().getControls();
        for (Control control : staticControls) {
            if (control.getSiteId().equals(page)) {
                controls.add(control);
            }
        }

    }

    public ArrayList<Control> getControls() {
        return controls;
    }
}
