package dtu.project.app.application;

public class InvalidOperationException extends Exception{

    public InvalidOperationException(String errorMessage) {
        super(errorMessage);
    }
}
