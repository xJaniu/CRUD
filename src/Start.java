import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Start {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    File f = new File("Library.db");
                    if(!f.exists()){
                        createDB();
                    }
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:library.db");
                    System.out.printf("Connected to database");

                    new BookListFrame();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

    public static void createDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:library.db");

        try {
            Statement stmt = conn.createStatement();
            String tab1 = "CREATE TABLE if not exists books (" +
                    "    id_book INTEGER   PRIMARY KEY AUTOINCREMENT" +
                    "                    NOT NULL," +
                    "    name TEXT (32) NOT NULL," +
                    "    quantity INTEGER       NOT NULL" +
                    ");";

            stmt.execute(tab1);
            System.out.printf("Table has been generated\n");
        } finally {
            conn.close();
        }
    }
}
