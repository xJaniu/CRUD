import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddBookFrame extends JFrame {

    JButton submitButton = new JButton("Submit");
    JButton exitButton = new JButton("Back");

    JLabel bookNameText = new JLabel("Book name:");
    JLabel bookQuantityText = new JLabel("Book quantity:");

    JTextField bookName = new JTextField("book name", 24);;
    JTextField bookQuantity = new JTextField("0", 16);

    JPanel optionsPanel = new JPanel(new FlowLayout());

    public AddBookFrame(String title){
        super(title);
        setSize(640,480);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);

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

                String name = bookName.getText();

                try {
                    int quantity = Integer.parseInt(bookQuantity.getText());
                    try {
                        AddBook(name, quantity);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Quantity must be a number", "Alert",
                            JOptionPane.INFORMATION_MESSAGE);
                }


            }
        });

        exitButton.addActionListener(a -> {
            dispose();
            try {
                new BookListFrame().setVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        optionsPanel.setBounds(0,400,640,35);
        optionsPanel.add(submitButton);
        optionsPanel.add(exitButton);
        add(optionsPanel);

        setVisible(true);
    }

    public void AddBook(String bookName, int bookQuantity) throws SQLException {
        Connection conn = DriverManager.getConnection("JDBC:SQLite:library.db");
        Statement stmt = conn.createStatement();

        String check = "SELECT name FROM books WHERE name ='" + bookName + "';";
        stmt.execute(check);
        ResultSet result = stmt.executeQuery(check);
        if(result.next()){
            JOptionPane.showMessageDialog(new JFrame(), "Book already exist", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
        }else {
            String insert = "INSERT INTO books (name, quantity) VALUES ('" + bookName + "', " + bookQuantity + ");";
            stmt.execute(insert);
            JOptionPane.showMessageDialog(new JFrame(), "Book " + bookName + " added!", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        stmt.close();
    }
}
