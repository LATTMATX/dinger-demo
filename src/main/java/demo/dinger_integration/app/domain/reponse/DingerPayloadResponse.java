package demo.dinger_integration.app.domain.reponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DingerPayloadResponse {

//    private String encodedPayload;
//    private String hashValue;
    private String url;

}