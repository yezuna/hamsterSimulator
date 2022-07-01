import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

public class hamsterSimulator extends JFrame {
    private final Font mainFont = new Font("Segoe print", Font.BOLD, 16);

    //  Fields
    JTextField tfhamsterName;
    private final int mainPanelWidth = 400;
    private final int mainPanelHeight = 450;
    private boolean hamsterIsDead = true;
    private boolean isCleaned = true;

    public void initialize() {
        // ------------------------------------------------------------------

        // input Panel Labels
        JLabel lbhamsterName = new JLabel("Hamster's Name:");
        lbhamsterName.setFont(mainFont);

        tfhamsterName = new JTextField();
        tfhamsterName.setFont(mainFont);

        // input Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 1, 5, 5));
        formPanel.add(lbhamsterName, BorderLayout.NORTH);
        formPanel.add(tfhamsterName, BorderLayout.AFTER_LAST_LINE);

        // ------------------------------------------------------------------

        // hamster panel labels
        JLabel lbhamsterImage = new JLabel(new ImageIcon("hamster.jpg"));
        JLabel lbhamsterDeadImage = new JLabel(new ImageIcon("deadHamster.jpg"));
        // JLabel lbhamsterBlankImage = new JLabel(new ImageIcon("blankHamster.jpg"));

        JLabel lbWelcome = new JLabel("", SwingConstants.CENTER);
        lbWelcome.setFont(mainFont);

        // hamster panel
        JPanel hamsterPanel = new JPanel();
        hamsterPanel.setBorder(new EmptyBorder(25, 0, 25, 0));
        hamsterPanel.setLayout(new BorderLayout());
        hamsterPanel.add(lbhamsterImage, BorderLayout.CENTER);
        hamsterPanel.add(lbWelcome, BorderLayout.NORTH);

        // ------------------------------------------------------------------

        // Buttons | Logic
        JButton btnNewHamster = new JButton("New Hamster");
        btnNewHamster.setFont(mainFont);
        btnNewHamster.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String hamsterName = tfhamsterName.getText();
                if (hamsterName.equals(""))                     // fix??
                    hamsterName = "Bobby";
                if (hamsterIsDead && isCleaned) {
                    lbWelcome.setText("<html><div style='text-align: center;'>Welcome hamster " + hamsterName + " to this world!</div></html>");
                    hamsterIsDead = false;
                } else if (isCleaned) {
                    lbWelcome.setText("<html><div style='text-align: center;'>Kill hamster first :)</div></html>");
                } else {
                    lbWelcome.setText("<html><div style='text-align: center;'>Clean mess first.</div></html>");
                } 
            }
            
        });

        JButton btnKill = new JButton("Kill");
        btnKill.setFont(mainFont);
        btnKill.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String hamsterName = tfhamsterName.getText();
                if (hamsterName.equals(""))                  // fix??
                    hamsterName = "Bobby";

                if (hamsterIsDead)
                    lbWelcome.setText("<html><div style='text-align: center;'>There is no Hamster left to kill!</div></html>");
                else {
                    hamsterPanel.remove(lbhamsterImage);
                    hamsterPanel.add(lbhamsterDeadImage, BorderLayout.CENTER);
                    pack();
                    setVisible(true);

                    lbWelcome.setText("<html><div style='text-align: center;'>OMG! " + hamsterName + " got killed! rip.</div></html>");
                    hamsterIsDead = true;
                    isCleaned = false;                  
                }
            }
            
        });

        JButton btnClearMess = new JButton("Clear Mess");
        btnClearMess.setFont(mainFont);
        btnClearMess.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String hamsterName = tfhamsterName.getText();
                if (hamsterName.equals(""))                  // fix??
                    hamsterName = "Bobby";

                if (hamsterIsDead && !isCleaned) {
                    tfhamsterName.setText("");
                    lbWelcome.setText("<html><div style='text-align: center;'>Bye bye bloody mess!<br/> welcome a new hamster to the world if you wish!</div></html>");

                    hamsterPanel.remove(lbhamsterDeadImage);
                    hamsterPanel.add(lbhamsterImage, BorderLayout.CENTER);
                    pack();
                    setVisible(true);
                    isCleaned = true;
                } else if (!isCleaned) {
                    lbWelcome.setText("<html><div style='text-align: center;'>Kill " + hamsterName + " first</div></html>");
                } else {
                    lbWelcome.setText("<html><div style='text-align: center;'>Hamster has already been cleaned.</div></html>");
                }

            }
            
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3, 5, 5));
        buttonsPanel.add(btnNewHamster);
        buttonsPanel.add(btnKill);
        buttonsPanel.add(btnClearMess);



        JPanel  mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(128, 128, 255));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(hamsterPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setTitle("Welcome to Hamster Simulator");
        setSize(mainPanelWidth, mainPanelHeight);
        setMinimumSize(new Dimension(mainPanelWidth, mainPanelHeight));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        hamsterSimulator myFrame = new hamsterSimulator();
        myFrame.initialize();
    }
}
