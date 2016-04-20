import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dexter on 20.04.16.
 */
public class DropTables {

    private static Logger LOGGER = Logger.getLogger(DropTables.class);

    public static void drop() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/phonebook", "root", "root")) {

            Statement statement = connection.createStatement();
            statement.execute("drop table hibernate_sequence");
            statement.execute("drop table contacts");
            statement.execute("drop table users");
            LOGGER.info("********DataBase was removed");

        } catch (SQLException e) {
            LOGGER.error("Error connection");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        DropTables.drop();
    }
}
