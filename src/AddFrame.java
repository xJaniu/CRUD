import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFrame extends JFrame implements Runnable {

    JButton submitButton = new JButton("submit");
    JButton exitButton = new JButton("close");

    JLabel bookNameText = new JLabel("Book name:");
    JLabel bookQuantityText = new JLabel("Book quantity:");

    JTextField bookName = new JTextField("Book name", 25);
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

        exitButton.addActionListener(c -> {
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

        submitButton.addActionListener(new ActionListenerAddBook(bookName.getText(), bookQuantity.getText()));


        setVisible(true);
    }
    @Override
    public void run() {
    };
}
