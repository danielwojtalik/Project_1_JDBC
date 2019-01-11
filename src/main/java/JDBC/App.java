package JDBC;

import JDBC.connection.DbConnection;
import JDBC.exceptions.MyException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        try{
            DbConnection dbConnection = DbConnection.getInstance();
        }catch (MyException e){
            System.err.println(e.getExcepionInfo().getExceptionCode());
            System.out.println(e.getExcepionInfo().getExceptionDateTime());
            System.out.println(e.getExcepionInfo().getExceptionMassage());

        }

    }
}
