import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentControlLayoutManager implements ActionListener {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JTextField ipAddressText;
    private JButton createButton;
    private JPanel contentPanel;
    private JPanel infoPanel;
    private JScrollPane scrollPanel;
    private int instanceCounter = 0;
    private Box box;

    public VentControlLayoutManager(){
        createGUIObjects();
        addGUIObjectsToGUI();
    }

    private void createGUIObjects() {
        box = Box.createVerticalBox();
        mainFrame = new JFrame("Vent Controller Sim");

        infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setPreferredSize(new Dimension(200, 60));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        headerLabel = new JLabel("Add Device:", JLabel.LEFT);

        ipAddressText = new JTextField("Enter IP Address here!", 8);
        ipAddressText.setSize(100, 30);

        createButton = new JButton("New Device");
        createButton.setSize(50,30);
        createButton.addActionListener(this);

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0,1));
        scrollPanel = new JScrollPane();
        scrollPanel.setPreferredSize(new Dimension(400,800));

        mainFrame.add(box, BorderLayout.CENTER);
        mainFrame.setSize(400, 800);

    }

    private void addGUIObjectsToGUI() {

        GridBagConstraints gbc = new GridBagConstraints();

        scrollPanel.setViewportView(contentPanel);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.gridx = 0;
        gbc.gridy = 0;
        infoPanel.add(headerLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 160;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        infoPanel.add(ipAddressText, gbc);

        gbc.ipadx = 5;
        gbc.gridx = 4;
        gbc.gridy = 1;
        infoPanel.add(createButton, gbc);

        box.add(infoPanel);
        box.add(scrollPanel);
        mainFrame.add(box, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == createButton){
            instanceCounter++;
            VentCntrlWidget newWidget = new VentCntrlWidget(instanceCounter);
            JPanel newPanel = newWidget.getGUI();
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            gbc.gridy = instanceCounter;
            gbc.gridheight = 1;
            gbc.ipadx = 0;
            gbc.ipady = 0;

            contentPanel.add(newPanel, gbc);
            mainFrame.revalidate();
            System.out.println("Working?!? " + newWidget.getDeviceName());
        }
    }
}
