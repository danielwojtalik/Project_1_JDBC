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
public class Movie {

    private Integer id;
    private String title;
    private String genre;
    private BigDecimal price;
    private Integer duration;
    private Date releaseData;
}
