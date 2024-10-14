package demo.dinger_integration.app.service;


import demo.dinger_integration.app.domain.DingerPayloadRequest;
import demo.dinger_integration.app.domain.reponse.DingerPayloadResponse;

public interface DingerService {

    DingerPayloadResponse getEncryptedPayload(DingerPayloadRequest request);

}