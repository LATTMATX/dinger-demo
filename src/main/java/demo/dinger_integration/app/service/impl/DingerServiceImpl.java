package demo.dinger_integration.app.service.impl;

import com.google.gson.Gson;
import demo.dinger_integration.app.constant.DingerConstant;
import demo.dinger_integration.app.domain.DingerCallBackPayload;
import demo.dinger_integration.app.domain.DingerPayload;
import demo.dinger_integration.app.domain.DingerPayloadRequest;
import demo.dinger_integration.app.domain.reponse.DingerPayloadResponse;
import demo.dinger_integration.app.domain.reponse.DingerResponseDto;
import demo.dinger_integration.app.service.DingerService;
import demo.dinger_integration.app.utils.DingerUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DingerServiceImpl implements DingerService {

    private final DingerUtils dingerUtils;

    @Override
    public DingerPayloadResponse getEncryptedPayload(DingerPayloadRequest request) {

        DingerPayload dingerPayload =  DingerPayload.of(request);
        String JSON = toJson(dingerPayload);

        String hashValue = DingerUtils.getHashValue(JSON);
        String encodedPayload = DingerUtils.getEncodedPayload(JSON);

        return DingerPayloadResponse.builder()
                .url(String.format("%s?payload=%s&hashValue%s", DingerConstant.BASE_URL, encodedPayload, hashValue)).build();

    }

    private static String toJson(Object objects) {
        Gson gson = new Gson();
        return gson.toJson(objects);
    }

}