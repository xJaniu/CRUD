import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFrame extends JFrame implements Runnable {

    public AddFrame(String title){
        super(title);
        setLocationRelativeTo(null);
        setSize(400,250);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        JButton submitButton = new JButton("submit");
        add(submitButton);
        submitButton.setBounds(100,175,80,25);

        JButton exitButton = new JButton("close");
        add(exitButton);
        exitButton.setBounds(200,175,80,25);

        exitButton.addActionListener(c -> {
            dispose();
        });

        JTextField textField = new JTextField();
        textField.setText("This is a text");
        textField.setColumns(20);





        setVisible(true);
    }
    @Override
    public void run() {
    };
}
