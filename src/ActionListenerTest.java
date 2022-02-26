import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ActionListenerTest extends JButton implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Connection conn = DriverManager.getConnection("JDBC:SQLite:library.db");
            Statement stmt = conn.createStatement();

            String all = "SELECT * from books;";
            stmt.execute(all);
            ResultSet test = stmt.executeQuery(all);

            while(test.next()){
                String test2 = test.getString("name");
                System.out.printf("Nazwa: " + test2 + " | ilość: ");
                test2 = test.getString("quantity");
                System.out.printf(test2 + " \n");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
