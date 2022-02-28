import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EditBookFrame extends JFrame {

    private final String name;
    private final int quantity;
    JButton exitButton = new JButton("back");
    JButton editButton = new JButton("edit");

    JLabel actualName = new JLabel();
    JLabel newName = new JLabel();
    JLabel actualQuantity = new JLabel();
    JLabel newQuantity = new JLabel();


    public EditBookFrame(String title, String name, String quantity){
        super(title);
        this.name = name;
        this.quantity = Integer.parseInt(quantity);
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


        JTextField newBookName = new JTextField(name, 24);;
        JTextField newBookQuantity = new JTextField(quantity, 16);

        newBookName.setBounds(135,60,250,25);
        newBookQuantity.setBounds(135,120,250,25);

        add(newBookName);
        add(newBookQuantity);

        exitButton.setBounds(350,375,80,25);
        editButton.setBounds(200,375,80,25);

        editButton.addActionListener(b -> {
            EditBook(newBookName.getText(), newBookQuantity.getText(), name);
            JOptionPane.showMessageDialog(new JFrame(), "Book " + name + " changed to " + newBookName.getText() + "!", "Alert",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        exitButton.addActionListener(a -> {
            dispose();
            try {
                new BookListFrame().setVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        add(exitButton);
        add(editButton);
        setVisible(true);
    }

    public void EditBook(String bookName, String bookQuantity, String oldName){

        try {
            Connection conn = DriverManager.getConnection("JDBC:SQLite:library.db");
            Statement stmt = conn.createStatement();
            String insert = "UPDATE books SET name='" + bookName + "', quantity=" + bookQuantity + " WHERE name = '" + oldName + "';";
            stmt.execute(insert);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
