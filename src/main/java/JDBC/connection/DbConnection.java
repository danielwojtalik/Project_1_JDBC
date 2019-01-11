package JDBC.connection;

import JDBC.exceptions.ExceptionCode;
import JDBC.exceptions.MyException;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnection {

    private static DbConnection ourInstance = new DbConnection();

    public static DbConnection getInstance() {
        return ourInstance; // i need this method to have access to ourInstance outside of this class
    }

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DATABASE = "jdbc:sqlite:test.db"; // this is a path where the data base file is stored
    private Connection connection; // this is reference to interface, which allows to create connection between java application and db

    private DbConnection() { // this constructor invokes two methods...
        connect();
        createTable();
    }

    public Connection getConnection() { // this method is use only for have an access to connection pool from outside of this class
        return connection;
    }

    private void connect() {
        try {
            Class.forName(DRIVER);

            SQLiteConfig sqLiteConfig = new SQLiteConfig();
            sqLiteConfig.enforceForeignKeys(true);

            connection = DriverManager.getConnection(DATABASE, sqLiteConfig.toProperties());
        } catch (Exception e) {
            throw new MyException(ExceptionCode.DATABASE, e.getMessage());
        }
    }

    private void createTable() {
        try (Statement statement = connection.createStatement()) {

            final String sqlMovies =
                    SqlTableCommand.builder()
                            .table("movies")
                            .primaryKey("id")
                            .stringColumn("title", 50, "not null")
                            .stringColumn("genre", 50, "not null")
                            .decimalColumn("price", 4, 2, "not null")
                            .intColumn("duration", "")
                            .column("release_date", "DATE", "not null")
                            .build().toString();
            System.out.println(sqlMovies);
            statement.execute(sqlMovies);

            final String sqlCustomers =
                    SqlTableCommand.builder()
                            .table("customers")
                            .primaryKey("id")
                            .stringColumn("name", 50, "not null")
                            .stringColumn("surname", 50, "not null")
                            .intColumn("age", "default 18") // what means INT(11)????????????
                            .stringColumn("email", 50, "not null")
                            .intColumn("loyalty_card_id", "")
                            .foreignKey("loyalty_card_id", "loyalty_cards", "id", "on delete cascade")
                            .build().toString();
            System.out.println(sqlCustomers);
            statement.execute(sqlCustomers);

            final String sqlSalesStand =
                    SqlTableCommand.builder()
                            .table("sales_stands")
                            .primaryKey("id")
                            .intColumn("customer_id", "")
                            .intColumn("movie_id", "")
                            .column("start_date_time", "TimeStamp", "")
                            .foreignKey("customer_id","customers", "id", "on delete cascade")
                            .foreignKey("movie_id", "movies", "id", "on delete cascade")
                            .build().toString();
            System.out.println(sqlSalesStand);
            statement.execute(sqlSalesStand);

            final String sqlloyalty_cards =
                    SqlTableCommand.builder()
                    .table("loyalty_cards")
                    .primaryKey("id")
                    .column("expiration_date", "date", "not null")
                    .decimalColumn("discount", 4,2,"not null")
                    .intColumn("movies_number", "not null")
                    .build().toString();
            System.out.println(sqlloyalty_cards);
            statement.execute(sqlloyalty_cards);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.DATABASE, e.getMessage());
        }
    }
}
