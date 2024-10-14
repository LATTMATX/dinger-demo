package demo.dinger_integration.app.domain;

import com.google.gson.Gson;
import demo.dinger_integration.app.constant.DingerConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DingerPayload {

    private String customerName;
    private int totalAmount;
    private String merchantOrderId;
    private String clientId;
    private String publicKey;
    private String merchantKey;
    private String projectName;
    private String merchantName;
    private String items;

    public static DingerPayload of(DingerPayloadRequest request) {
        DingerPayload payload = new DingerPayload();
        payload.customerName = request.getCustomerName();
        payload.totalAmount = request.getTotalAmount();
        payload.merchantOrderId = request.getMerchantOrderId();
        payload.items = toJson(request.getItems());

        payload.clientId = DingerConstant.CLIENT_ID;
        payload.publicKey = DingerConstant.PUBLIC_KEY;
        payload.merchantKey = DingerConstant.MERCHANT_KEY;
        payload.projectName = DingerConstant.PROJECT_NAME;
        payload.merchantName = DingerConstant.MERCHANT_NAME;

        return payload;

    }

    private static String toJson(Object objects) {
        return new Gson().toJson(objects);
    }
}