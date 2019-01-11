package JDBC.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Customer {
    private Integer id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private Integer loyaltyCardId;
}
