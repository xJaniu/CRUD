import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddBookFrame extends JFrame {

    JButton submitButton = new JButton("submit");
    JButton exitButton = new JButton("back");

    JLabel bookNameText = new JLabel("Book name:");
    JLabel bookQuantityText = new JLabel("Book quantity:");

    JTextField bookName = new JTextField("book name", 24);;
    JTextField bookQuantity = new JTextField("0", 16);

    public AddBookFrame(String title){
        super(title);
        setSize(640,480);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);

        exitButton.setBounds(200,175,80,25);
        submitButton.setBounds(100,175,80,25);

        add(exitButton);
        add(submitButton);

        exitButton.addActionListener(a -> {
            dispose();
            try {
                new BookListFrame().setVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        bookName.setBounds(120,20,150,25);
        bookQuantity.setBounds(120,50,150,25);

        add(bookName);
        add(bookQuantity);

        bookNameText.setBounds(20,20,150,25);
        bookQuantityText.setBounds(20,50,150,25);

        add(bookNameText);
        add(bookQuantityText);


        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JOptionPane.showMessageDialog(new JFrame(), "Book " + bookName.getText() + " added!", "Alert",
                        JOptionPane.INFORMATION_MESSAGE);
                int quantity = Integer.parseInt(bookQuantity.getText());

                try {
                    AddBook(bookName.getText(), quantity);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public void AddBook(String bookName, int bookQuantity) throws SQLException {

        Connection conn = DriverManager.getConnection("JDBC:SQLite:library.db");
        Statement stmt = conn.createStatement();
        String insert = "INSERT INTO books (name, quantity) VALUES ('"+ bookName + "', " + bookQuantity + ");";
        stmt.execute(insert);
    }

}
