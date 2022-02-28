import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EditBookFrame extends JFrame {

    String name;
    int quantity;

    JButton changeNameButton = new JButton("Change name");
    JButton changeQuantityButton = new JButton("Change quantity");
    JButton exitButton = new JButton("Back");


    JLabel actualName = new JLabel();
    JLabel newName = new JLabel();
    JLabel actualQuantity = new JLabel();
    JLabel newQuantity = new JLabel();

    JTextField newBookName;
    JTextField newBookQuantity;

    JPanel optionsPanel = new JPanel(new FlowLayout());

    public EditBookFrame(String title, String name, int quantity){
        super(title);
        this.name = name;
        this.quantity = quantity;
        setSize(640,480);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        actualName.setText("Actual name:    " + name);
        newName.setText("New name:  ");
        actualName.setBounds(50, 30, 250, 20);
        newName.setBounds(50, 60, 250, 20);
        add(actualName);
        add(newName);

        actualQuantity.setText("Actual quantity: " + quantity);
        newQuantity.setText("New quantity: ");
        actualQuantity.setBounds(50,90,250,20);
        newQuantity.setBounds(50,120,250,20);
        add(actualQuantity);
        add(newQuantity);

        newBookName = new JTextField(name, 24);
        newBookQuantity = new JTextField(Integer.toString(quantity), 16);
        newBookName.setBounds(135,60,250,25);
        newBookQuantity.setBounds(135,120,250,25);
        add(newBookName);
        add(newBookQuantity);

        changeNameButton.addActionListener(b -> {
                EditName(newBookName.getText(), name);
                dispose();
                new EditBookFrame(title, newBookName.getText(), quantity).setVisible(true);
        });

        changeQuantityButton.addActionListener(b -> {
            try{
                int newQuantity = Integer.parseInt(newBookQuantity.getText());
                EditQuantity(newQuantity, name);
                dispose();
                new EditBookFrame(title, name, newQuantity).setVisible(true);
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(new JFrame(), "Quantity must be a number", "Alert",
                        JOptionPane.INFORMATION_MESSAGE);
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
        optionsPanel.add(changeNameButton);
        optionsPanel.add(changeQuantityButton);
        optionsPanel.add(exitButton);
        add(optionsPanel);

        setVisible(true);
    }

    public void EditName(String bookName, String oldName){

        try {
            Connection conn = DriverManager.getConnection("JDBC:SQLite:library.db");
            Statement stmt = conn.createStatement();

            String check = "SELECT name FROM books WHERE name ='" + bookName + "';";
            stmt.execute(check);
            ResultSet result = stmt.executeQuery(check);

            if(result.next()){
                JOptionPane.showMessageDialog(new JFrame(), "Book already exist", "Alert",
                        JOptionPane.INFORMATION_MESSAGE);
            }else {
                String insert = "UPDATE books SET name='" + bookName + "' WHERE name = '" + oldName + "';";
                stmt.execute(insert);
                JOptionPane.showMessageDialog(new JFrame(), "Book " + name + " changed to " + newBookName.getText() + "!", "Alert",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void EditQuantity(int bookQuantity, String bookName) {
        try {
            Connection conn = DriverManager.getConnection("JDBC:SQLite:library.db");
            Statement stmt = conn.createStatement();
            String insert = "UPDATE books SET quantity=" + bookQuantity + " WHERE name = '" + bookName + "';";
            stmt.execute(insert);
            JOptionPane.showMessageDialog(new JFrame(), "Book " + name + " changed quantity to " + bookQuantity + "!", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
