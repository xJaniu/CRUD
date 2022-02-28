import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class BookListFrame extends JFrame {

    JLabel welcomeText = new JLabel("Library Management");
    
    JButton addButton = new JButton("Add");
    JButton delButton = new JButton("Delete");
    JButton exitButton = new JButton("Exit");
    JButton editButton = new JButton("Edit");

    JPanel optionsPanel = new JPanel(new FlowLayout());

    JList<String> list;

    public BookListFrame() throws SQLException {
        super("Library management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int width = 640;
        int height = 480;
        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        welcomeText.setFont(new Font("Arial", Font.BOLD, 44));
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeText.setBounds(SwingConstants.CENTER, SwingConstants.CENTER, width, 100);
        add(welcomeText);

        Connection conn = DriverManager.getConnection("JDBC:SQLite:library.db");
        Statement stmt = conn.createStatement();

        DefaultListModel<String> model = new DefaultListModel<>();
        list = new JList<>(model);

        String selectQuery = "SELECT * from books;";
        stmt.execute(selectQuery);
        ResultSet result = stmt.executeQuery(selectQuery);

        while(result.next()){
            String name = result.getString("name");
            String quantity = result.getString("quantity");
            model.addElement(name + ", ilość:" + quantity);
        }

        JScrollPane jsp = new JScrollPane(list);
        jsp.setBounds(50,100,540,200);
        list.setSelectedIndex(0);
        add(jsp);



        addButton.addActionListener(b -> {
            dispose();
            Frame AddBookFrame = new AddBookFrame("Add book");
            AddBookFrame.setVisible(true);
        });

        editButton.addActionListener(d -> {
            String bookName = list.getSelectedValue();

            if(bookName == null){
                JOptionPane.showMessageDialog(new JFrame(), "Nothing selected!", "Alert",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                dispose();
                String[] arrOfStr = bookName.split(", ilość:");
                Frame EditBookFrame = new EditBookFrame("Add book", arrOfStr[0], Integer.parseInt(arrOfStr[1]));
                EditBookFrame.setVisible(true);
            };

        });

        delButton.addActionListener(a -> {

            String bookName = list.getSelectedValue();
            if(bookName == null) {
                JOptionPane.showMessageDialog(new JFrame(), "Nothing selected!", "Alert",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                String[] nameAndQuantity = bookName.split(", ilość:");
                String del = "DELETE FROM books WHERE name='" + nameAndQuantity[0] + "';";

                try {
                    stmt.execute(del);
                    dispose();
                    new BookListFrame();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                JOptionPane.showMessageDialog(new JFrame(), "Book " + nameAndQuantity[0] + " deleted!", "Alert",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        exitButton.addActionListener(c -> {
            System.exit(0);
        });

        optionsPanel.setBounds(0,400,640,35);
        optionsPanel.add(addButton);
        optionsPanel.add(editButton);
        optionsPanel.add(delButton);
        optionsPanel.add(exitButton);

        add(optionsPanel);
        setVisible(true);
        stmt.close();
    }

}
