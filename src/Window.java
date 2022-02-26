import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Window extends JFrame {

    public Window(){
        super("Library management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //pack();
        int width = 1024;
        int height = 720;
        setSize(width,height);
        setLocationRelativeTo(null);
        setLayout(null);

        setResizable(false);
//        getContentPane().setBackground(new Color(20,20,20));

        JLabel welcomeText = new JLabel("Test");
        welcomeText.setText("Library Management");
        welcomeText.setFont(new Font("Arial", Font.BOLD, 44));
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeText.setBounds(SwingConstants.CENTER,SwingConstants.CENTER,width,100);
        add(welcomeText);

        JButton p1 = new JButton("Start");
        JButton p2 = new JButton("Add");
        JButton p3 = new JButton("Exit");
        p1.setBounds(20,640,80,25);
        p2.setBounds(120,640,80,25);
        p3.setBounds(220,640,80,25);

        p1.addActionListener(new ActionListenerTest());

        p2.addActionListener(a -> {
            Frame AddFrame = new AddFrame("add book");
            AddFrame.setVisible(true);
        });

        p3.addActionListener(b -> {
           System.exit(0);
        });

        add(p1);
        add(p2);
        add(p3);

        JTextField textField = new JTextField("test",20);
        textField.setColumns(20);
        add(textField);

        setVisible(true);
    }
}
