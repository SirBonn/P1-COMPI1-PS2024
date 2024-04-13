package srbn.Domain;

public class ErrorP {

    private int line;
    private int column;
    private String content;
    // 0 Lexer, 1 Sintactic
    private int typeError;
    private String message;

    public ErrorP(int line, int column, String content, int typeError, String message) {
        this.line = line;
        this.column = column;
        this.content = content;
        this.typeError = typeError;
        this.message = message;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTypeError() {
        return typeError;
    }

    public void setTypeError(int typeError) {
        this.typeError = typeError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
