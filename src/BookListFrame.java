import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class BookListFrame extends JFrame {

    JLabel welcomeText = new JLabel("Library Management");
    
    JButton addButton = new JButton("Add");
    JButton delButton = new JButton("Delete");
    JButton exitButton = new JButton("Exit");
    JButton editButton = new JButton("Edit");
    
    public BookListFrame() throws SQLException {
        super("Library management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //pack();
        int width = 640;
        int height = 480;
        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        Connection conn = DriverManager.getConnection("JDBC:SQLite:library.db");
        Statement stmt = conn.createStatement();

        welcomeText.setFont(new Font("Arial", Font.BOLD, 44));
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeText.setBounds(SwingConstants.CENTER, SwingConstants.CENTER, width, 100);
        add(welcomeText);

        



        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);


        String all = "SELECT * from books;";
        stmt.execute(all);
        ResultSet test = stmt.executeQuery(all);

        while(test.next()){
            String test2 = test.getString("name");
            String test3 = test.getString("quantity");
            model.addElement(test2 + ", ilość:" + test3);
        }
        list.setModel(model);

        list.setSelectedIndex(0);

        addButton.addActionListener(b -> {
            dispose();
            Frame AddBookFrame = new AddBookFrame("Add book");
            AddBookFrame.setVisible(true);
        });

        editButton.addActionListener(d -> {
            dispose();
            String tekst2 = list.getSelectedValue();
            String[] arrOfStr = tekst2.split(", ilość:");
            Frame EditBookFrame = new EditBookFrame("Add book", arrOfStr[0], arrOfStr[1]);
            EditBookFrame.setVisible(true);
        });

        exitButton.addActionListener(c -> {
            System.exit(0);
        });

        delButton.addActionListener(a -> {
            String tekst = list.getSelectedValue();

            String[] arrOfStr = tekst.split(", ilość:");

            String del = "DELETE FROM books WHERE name='" + arrOfStr[0] + "';";
            try {
                stmt.execute(del);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            dispose();
            try {
                new BookListFrame();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JOptionPane.showMessageDialog(new JFrame(), "Book " + arrOfStr[0] + " deleted!", "Alert",
                    JOptionPane.ERROR_MESSAGE);

        });

        //list.setBounds(80,100,200,200);
        JScrollPane jsp = new JScrollPane(list);
        jsp.setBounds(50,100,540,200);

        add(jsp);

        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.setBounds(0,400,640,35);

        optionsPanel.add(addButton);
        optionsPanel.add(editButton);
        optionsPanel.add(delButton);
        optionsPanel.add(exitButton);
        add(optionsPanel);
        setVisible(true);
    }

}
