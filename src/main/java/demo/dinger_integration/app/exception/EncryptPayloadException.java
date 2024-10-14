package demo.dinger_integration.app.exception;

public class EncryptPayloadException extends RuntimeException {

    public EncryptPayloadException(String message){
        super(message);
    }

}