package demo.dinger_integration.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import demo.dinger_integration.app.domain.DingerPayloadRequest;
import demo.dinger_integration.app.domain.reponse.DingerPayloadResponse;
import demo.dinger_integration.app.service.DingerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dinger")
@AllArgsConstructor
public class DingerController{

    private final DingerService dingerService;

    @PostMapping("/payload")
    public DingerPayloadResponse getEncryptedPayload(@RequestBody DingerPayloadRequest request) throws JsonProcessingException {
        return dingerService.getEncryptedPayload(request);

    }

}