import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectToPostgres {
    // constants to connect
    public static final String url = "jdbc:postgresql://127.0.0.1:5432/notes";
    public static final String username = "postgres";
    public static final String password = "123qwe123/zxc";

    // add a task to database
    public static void insertNoteIntoDatabase(String note) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO notes(note) " +
                            "VALUES(?)"
            );

            preparedStatement.setString(1, note);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
