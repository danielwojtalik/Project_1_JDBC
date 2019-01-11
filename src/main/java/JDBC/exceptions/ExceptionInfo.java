package JDBC.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ExceptionInfo {
    private ExceptionCode exceptionCode;
    private String exceptionMassage;
    private LocalDateTime exceptionDateTime;

    public ExceptionInfo(ExceptionCode exceptionCode, String exceptionMassage) {
        this.exceptionCode = exceptionCode;
        this.exceptionMassage = exceptionMassage;
        this.exceptionDateTime = LocalDateTime.now();
    }
}
