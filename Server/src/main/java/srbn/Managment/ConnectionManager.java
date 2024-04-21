package srbn.Managment;

import srbn.Domain.Actions.Action;
import srbn.Domain.Actions.ActionManager;
import srbn.Domain.Errors.ErrorP;
import srbn.Domain.Reports.QueryManager;
import srbn.Lexer.Lexer;
import srbn.Managment.ServerToServer.TaskManager;
import srbn.Parser.Parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionManager {

    private String response;
    TaskManager taskManager;
    QueryManager queryManager;

    public ConnectionManager() {
        this.response = "";
        taskManager = new TaskManager();
        queryManager = new QueryManager(taskManager);
    }

    public String executeXml(String receivedMessage) throws Exception {

        StringReader sr = new StringReader(receivedMessage);
        Lexer lex = new Lexer(sr);
        Parser sintax = new Parser(lex);
        sintax.parse();

        this.response = "";
        if (sintax.isRight()) {
            ActionManager actmng = new ActionManager(sintax.getActions(), taskManager);
            queryManager.executeQueries(sintax.getQueries());
            actmng.executeActions();
            response += actmng.getResponse();
            response += "\n\n" + getResponseOk(sintax.getActions()) + actmng.getResponse();
        }

        return response;
    }

    public String executeHtml(String receivedMessage) {

        System.out.println("Received message: " + receivedMessage);

        Pattern patron = Pattern.compile("\\{([^}]*)\\}");
        Matcher matcher = patron.matcher(receivedMessage);

        if (matcher.find()) {
            String info = matcher.group(1);
            info.replace(" ", "");
            String[] parts = info.split("\\,");

            if (parts.length == 3) {
                queryManager.updateControls(parts[0], parts[1], parts[2]);
            } else if (parts.length == 2) {
                queryManager.updateControls(parts[0], parts[1], "");
            } else {
                queryManager.updateControls(parts[0], "", "");
            }

        } else {
            System.out.println("No matched");
        }

        return response;
    }

    private String getResponseOk(ArrayList<Action> actions) {
        String Result = "";
        for (Action action : actions) {
            if (action != null)
                Result += action.getId() + ": registered (OK)\n";
        }
        return Result;
    }


    private String getResponseEr(ArrayList<ErrorP> errors) {
        String Result = "";

        Result += "Errors: \n";
        for (ErrorP error : errors) {
            if (error != null)
                Result += error.toString() + "\n";
        }
        return Result;
    }

}
