import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class connectionJDBC {
    static Connection conn;
    public static void main(String[] args) throws SQLException{
        File f = new File("Library.db");
        if(!f.exists()){
            createDB();
        }
        conn = DriverManager.getConnection("jdbc:sqlite:library.db");
        System.out.printf("Connected to database");
    }

    public static void createDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:library.db");

        try {
            Statement stmt = conn.createStatement();
            String tab1 = "CREATE TABLE if not exists books (" +
                    "    id_book INTEGER   PRIMARY KEY AUTOINCREMENT" +
                    "                    NOT NULL," +
                    "    name TEXT (32) NOT NULL" +
                    ");";

            stmt.execute(tab1);
            System.out.printf("Table has been generated\n");
        }finally{
            conn.close();
        }
    }
}
