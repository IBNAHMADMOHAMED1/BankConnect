package ma.bankconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
    private String AccountNumberFrom;
    private Double amount;
    private String type;
    private String AccountNumberTo;
}
