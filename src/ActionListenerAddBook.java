import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ActionListenerAddBook extends JButton implements ActionListener {
    String bookName;
    int bookQuantity;

    public ActionListenerAddBook(String text, String text2) {
        bookName = text;
        bookQuantity = Integer.parseInt(text2.trim());
        System.out.printf(bookName);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        try {
            Connection conn = DriverManager.getConnection("JDBC:SQLite:library.db");
            Statement stmt = conn.createStatement();
//            String all = "SELECT * from books;";
//            stmt.execute(all);

            System.out.printf(bookName + " ilosc: " + bookQuantity);

//            String insert = "INSERT INTO books (name, quantity) VALUES ('"+ bookName + "', " + bookQuantity + ");";
//            stmt.execute(insert);
//
//            ResultSet test = stmt.executeQuery(all);
//
//            while(test.next()){
//                String test2 = test.getString("name");
//                System.out.printf(test2 + " \n");
//            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
