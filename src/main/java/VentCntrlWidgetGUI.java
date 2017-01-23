import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class VentCntrlWidgetGUI implements ActionListener {
    private int deviceNumber = 0;
    private JLabel name = new JLabel();
    private JPanel contentPanel = new JPanel(new GridBagLayout());

    private JLabel status = new JLabel("Status:");
    private JLabel temp = new JLabel("Temperature Reading:");
    private JLabel position = new JLabel("Position Reading:");

    private JRadioButton statusGood = new JRadioButton();
    private JRadioButton statusBad = new JRadioButton();
    private JRadioButton statusUnknown = new JRadioButton();
    private ButtonGroup statusGroup = new ButtonGroup();

    private JTextField tempReading = new JTextField();
    private JTextField positionReading = new JTextField();

    private JButton addTempBtn = new JButton("+");
    private JButton minusTempBtn = new JButton("-");
    private JButton openPositionBtn = new JButton("+");
    private JButton closePositionBtn = new JButton("-");
    private JButton sendBtn = new JButton("SEND");

    public enum Status { GOOD, BAD, UNKNOWN }

    public VentCntrlWidgetGUI(int instanceNumber) {
        deviceNumber = instanceNumber;
        GridBagConstraints gbc = new GridBagConstraints();

        // Layout GUI
        contentPanel.setSize(200, 120);

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
        statusGroup.add(statusGood);
        statusGroup.add(statusBad);
        statusGroup.add(statusUnknown);

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
        statusGood.addActionListener(this);

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
        statusBad.addActionListener(this);

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
        statusUnknown.addActionListener(this);

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
        tempReading.setText("72");

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(addTempBtn, gbc);
        addTempBtn.addActionListener(this);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(minusTempBtn, gbc);
        minusTempBtn.addActionListener(this);

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
        positionReading.setText("50");

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(openPositionBtn, gbc);
        openPositionBtn.addActionListener(this);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        contentPanel.add(closePositionBtn, gbc);
        closePositionBtn.addActionListener(this);

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

    public JPanel getGUI() {
        return contentPanel;
    }

    public String getDeviceName() {
        return name.getText();
    }

    public JButton getSendBtn() {
        return sendBtn;
    }

    public String getTempValue() {
        return tempReading.getText();
    }

    public String getPositionValue() {
        return positionReading.getText();
    }

    public Status getStatus(){
        Status retVal;
        if (statusGood.isSelected()){
            retVal = Status.GOOD;
        } else if (statusBad.isSelected()){
            retVal = Status.BAD;
        } else {
            retVal = Status.UNKNOWN;
        }
        return retVal;
    }

    public void setTemp(double temperature) {
        tempReading.setText(Double.toString(temperature));
    }

    public void setPosition(double position) {
        positionReading.setText(Double.toString(position));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTempBtn) {
            increaseTemp();
        } else if (e.getSource() == minusTempBtn) {
            decreaseTemp();
        } else if (e.getSource() == openPositionBtn) {
            increasePosition();
        } else if (e.getSource() == closePositionBtn) {
            decreasePosition();
        } else if (e.getSource() == statusGood) {
            setStatus(Status.GOOD);
        } else if (e.getSource() == statusBad) {
            setStatus(Status.BAD);
        }else if (e.getSource() == statusUnknown){
            setStatus(Status.UNKNOWN);
        }
    }

    private void decreasePosition() {
        double currentPosition = Double.valueOf(positionReading.getText());
        currentPosition = currentPosition - .5;
        positionReading.setText(Double.toString(currentPosition));
        System.out.println(name.getText() + " position set to: " + positionReading.getText());
    }

    private void increasePosition() {
        double currentPosition = Double.valueOf(positionReading.getText());
        currentPosition = currentPosition + .5;
        positionReading.setText(Double.toString(currentPosition));
        System.out.println(name.getText() + " position set to: " + positionReading.getText());
    }

    private void decreaseTemp() {
        double currentTemp = Double.valueOf(tempReading.getText());
        currentTemp--;
        tempReading.setText(Double.toString(currentTemp));
        System.out.println(name.getText() + " temp set to: " + tempReading.getText());
    }

    private void increaseTemp() {
        double currentTemp = Double.valueOf(tempReading.getText());
        currentTemp++;
        tempReading.setText(Double.toString(currentTemp));
        System.out.println(name.getText() + " temp set to: " + tempReading.getText());
    }

    public void setStatus(Status status) {
        if (status == Status.GOOD){
            statusGood.setSelected(true);
        }else if (status == Status.BAD){
            statusBad.setSelected(true);
        }else {
            statusUnknown.setSelected(true);
        }
        System.out.println(name.getText() + " state is: " + status);
    }
}


