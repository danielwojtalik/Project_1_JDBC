package JDBC.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoyaltyCard {

    private Integer id;
    private Date expirationDate;
    private BigDecimal discount;
    private Integer moviesNumber;
}
