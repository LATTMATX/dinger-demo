package demo.dinger_integration.app.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DingerCallBackPayload {

    private String paymentResult;
    private String checksum;

}