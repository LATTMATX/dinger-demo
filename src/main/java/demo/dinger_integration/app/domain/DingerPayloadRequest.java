package demo.dinger_integration.app.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DingerPayloadRequest {

    private String customerName;
    private String merchantOrderId;
    private int totalAmount;
    private List<Item> items;

    @Getter
    @Setter
    public static class Item {
        private String name;
        private int amount;
        private int quantity;
    }

}