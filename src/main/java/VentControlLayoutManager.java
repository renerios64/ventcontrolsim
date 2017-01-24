import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentControlLayoutManager implements ActionListener {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JTextField ipAddressText;
    private JTextField portText;
    private JButton createButton;
    private JPanel contentPanel;
    private JPanel infoPanel;
    private JScrollPane scrollPanel;
    private int instanceCounter = 0;
    private Box box;
    private Box contentPanelBox;
    private IpAddressValidator addressValidator = new IpAddressValidator();
    private PortAssignmentValidator portValidator = new PortAssignmentValidator();

    public VentControlLayoutManager() {
        createGUIObjects();
        addGUIObjectsToGUI();
    }

    private void createGUIObjects() {
        box = Box.createVerticalBox();
        contentPanelBox = Box.createVerticalBox();

        mainFrame = new JFrame("Vent Controller Sim");

        infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        headerLabel = new JLabel("Add Device:", JLabel.LEFT);

        ipAddressText = new JTextField("Enter IP Address here!", 8);
        ipAddressText.setSize(100, 30);

        createButton = new JButton("New Device");
        createButton.setSize(50, 30);
        createButton.addActionListener(this);

        portText = new JTextField("Enter Port here!", 8);
        portText.setSize(100, 30);

        contentPanel = new JPanel();
        scrollPanel = new JScrollPane();
        scrollPanel.setPreferredSize(new Dimension(400, 800));
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
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        infoPanel.add(createButton, gbc);

        gbc.ipadx = 5;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        infoPanel.add(portText, gbc);

        box.add(infoPanel);
        box.add(scrollPanel);
        mainFrame.add(box, BorderLayout.CENTER);
        mainFrame.setSize(400, 800);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {

            String address = ipAddressText.getText();
            String port = portText.getText();
            boolean ipValidated  = addressValidator.ValidateIpAddress(address);
            boolean portValidated = portValidator.ValidatePortAssignment(port);

            if (!ipValidated){
                JOptionPane.showMessageDialog(mainFrame,
                        "Please check your IP Address!",
                        "Invalid IP Address",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!portValidated){
                JOptionPane.showMessageDialog(mainFrame,
                        "Please check our Port Assignment!",
                        "Invalid Port Assignment",
                        JOptionPane.ERROR_MESSAGE);
            }else {
                instanceCounter++;
                VentCntrlWidget newWidget = new VentCntrlWidget(address, port, instanceCounter);
                JPanel newPanel = newWidget.getGUI();
                contentPanelBox.add(newPanel);
                contentPanel.add(contentPanelBox, BorderLayout.NORTH);
                mainFrame.validate();
                System.out.println(newWidget.getDeviceName() + " is" +
                        " talking to Server: " + newWidget.getServerAddress() +
                        " on Port: " + newWidget.getPort());
            }
        }
    }
}
