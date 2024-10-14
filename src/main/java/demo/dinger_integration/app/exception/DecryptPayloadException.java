package demo.dinger_integration.app.exception;

public class DecryptPayloadException extends RuntimeException {

    public DecryptPayloadException(String message){
        super(message);
    }

}