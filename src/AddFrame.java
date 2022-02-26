import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddFrame extends JFrame implements Runnable {

    JButton submitButton = new JButton("submit");
    JButton exitButton = new JButton("close");

    JLabel bookNameText = new JLabel("Book name:");
    JLabel bookQuantityText = new JLabel("Book quantity:");

    JTextField bookName = new JTextField("book name", 24);;
    JTextField bookQuantity = new JTextField("0", 16);

    public AddFrame(String title){
        super(title);
        setLocationRelativeTo(null);
        setSize(400,250);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        exitButton.setBounds(200,175,80,25);
        submitButton.setBounds(100,175,80,25);

        add(exitButton);
        add(submitButton);

        exitButton.addActionListener(a -> {
            dispose();
        });

        bookName.setBounds(120,20,150,25);
        bookQuantity.setBounds(120,50,150,25);

        add(bookName);
        add(bookQuantity);

        bookNameText.setBounds(20,20,150,25);
        bookQuantityText.setBounds(20,50,150,25);

        add(bookNameText);
        add(bookQuantityText);

        //submitButton.addActionListener(new ActionListenerAddBook());

        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                int quantity = Integer.parseInt(bookQuantity.getText());
                new ActionListenerAddBook(bookName.getText(), quantity);
            }
        });

        setVisible(true);
    }


    @Override
    public void run() {
    };
}
