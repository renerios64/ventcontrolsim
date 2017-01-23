import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class VentCntrlWidgetGUI {
    private int deviceNumber = 0;
    private JLabel name = new JLabel();
    private JPanel contentPanel = new JPanel(new GridBagLayout());

    private JLabel status = new JLabel("Status:");
    private JLabel temp = new JLabel("Temperature Reading:");
    private JLabel position = new JLabel("Position Reading:");

    private JCheckBox statusGood = new JCheckBox();
    private JCheckBox statusBad = new JCheckBox();
    private JCheckBox statusUnknown = new JCheckBox();

    private JTextField tempReading = new JTextField();
    private JTextField positionReading = new JTextField();

    private JButton addTempBtn = new JButton("+");
    private JButton minusTempBtn = new JButton("-");
    private JButton openPositionBtn  = new JButton("+");
    private JButton closePositionBtn = new JButton("-");
    private JButton sendBtn = new JButton("SEND");


    public VentCntrlWidgetGUI(int instanceNumber){
        deviceNumber = instanceNumber;
        GridBagConstraints gbc = new GridBagConstraints();

        // Layout GUI
        contentPanel.setSize(150, 120);

        name.setText("Device Number : " + Integer.toString(instanceNumber));
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(name, gbc);

        //STATUS
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(status, gbc);

        statusGood.setText("Good");
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(statusGood, gbc);

        statusBad.setText("Bad");
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(statusBad, gbc);

        statusUnknown.setText("Unknown");
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(statusUnknown, gbc);

        //TEMP
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(temp, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.ipadx = 5;
        gbc.ipady = 5;
        contentPanel.add(tempReading, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(addTempBtn, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(minusTempBtn, gbc);

        //POSITION
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(position, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.ipadx = 5;
        gbc.ipady = 5;
        contentPanel.add(positionReading, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(openPositionBtn, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(closePositionBtn, gbc);

        //SEND
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(sendBtn, gbc);

        contentPanel.setVisible(true);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.black));

//        dataField.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e){
//                out.println(dataField.getText());
//                String response;
//                try {
//                    response = in.readLine();
//                    if (response == null || response.equals(".")) {
//                        System.exit(0);
//                    }
//                } catch (IOException ex) {
//                    response = "Error: " + ex;
//                }
//                messageArea.append(response + "\n");
//                dataField.selectAll();
//            }
//        });
//        frame.setVisible(true);
//        try {
//            connectToServer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public JPanel getGUI(){
        return contentPanel;
    }

    public String getDeviceName(){
        return name.getText();
    }
}


