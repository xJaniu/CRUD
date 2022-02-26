import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ActionListenerAddBook extends JButton implements ActionListener {

    String bookName;
    int bookQuantity;


    public ActionListenerAddBook(String bookName, int bookQuantity) {

        this.bookName = bookName;
        this.bookQuantity = bookQuantity;

        try {
            Connection conn = DriverManager.getConnection("JDBC:SQLite:library.db");
            Statement stmt = conn.createStatement();
            String insert = "INSERT INTO books (name, quantity) VALUES ('"+ bookName + "', " + bookQuantity + ");";
            stmt.execute(insert);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

