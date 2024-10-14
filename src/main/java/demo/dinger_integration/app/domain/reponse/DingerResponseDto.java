package demo.dinger_integration.app.domain.reponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DingerResponseDto {

    private String id;
    private double totalAmount;
    private String createdAt;
    private String transactionStatus;
    private String methodName;
    private String merchantOrderId;
    private String transactionId;
    private String customerName;
    private String providerName;

}