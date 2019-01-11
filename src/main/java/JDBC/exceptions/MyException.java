package JDBC.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MyException extends RuntimeException {

    private ExceptionInfo excepionInfo;
    public MyException(ExceptionCode exceptionCode, String exceptionMassage){
        this.excepionInfo = new ExceptionInfo(exceptionCode, exceptionMassage);
    }
}
