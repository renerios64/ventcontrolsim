import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class VentCntrlWidget implements ActionListener{
    private BufferedReader in;
    private PrintWriter out;
    private String serverAddress = "";
    private String port = "";
    private int status = -1;
    private double temp = 72;
    private double position = 50.0;
    private String randomUUIDString = "";
    private UUID uuid = null;
    private VentCntrlWidgetGUI gui = null;
    private JButton actionBtn;

    public VentCntrlWidget(String IpAddress, String Port, int instanceNumber){
        setupWidget(IpAddress, Port);
        gui = new VentCntrlWidgetGUI(instanceNumber);
        actionBtn = getActionBtn();
        actionBtn.addActionListener(this);
        gui.setStatus(VentCntrlWidgetGUI.Status.UNKNOWN);
        gui.setTemp(temp);
        gui.setPosition(position);
    }

    public JPanel getGUI(){
        return gui.getGUI();
    }

    public String getDeviceName(){
        return gui.getDeviceName();
    }

    public JButton getActionBtn() { return gui.getSendBtn(); }

    private void setupWidget(String IpAddress, String Port) {
        serverAddress = IpAddress;
        port = Port;
        uuid = setUUID();
        randomUUIDString = uuid.toString();
    }

    public String getServerAddress(){ return serverAddress; }

    public String getPort() { return port; }

    public String getRandomUUID(){
        return randomUUIDString;
    }

    public String getUUIDVersion(){
        return String.valueOf(uuid.version());
    }

    public String getUUIDVariant(){
        return String.valueOf(uuid.variant());
    }

    public double getTemp(){
        return temp;
    }

    public double getPosition() { return position; }

    public int getStatus(){
        return status;
    }

    private UUID setUUID() {
        return UUID.randomUUID();
    }

    private double setTemp(double incomingTemp){
        temp = incomingTemp;
        return temp;
    }

    private double setPosition(double incomingPosition){
        position = incomingPosition;
        return position;
    }

    private int setStatus(int incomingStatus){
        status = incomingStatus;
        return status;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actionBtn) {
            VentCntrlWidgetGUI.Status status = gui.getStatus();
            String temperature = gui.getTempValue();
            String position = gui.getPositionValue();

            System.out.println(gui.getDeviceName() +
                               " status: " + status +
                               " temperature: " + temperature +
                               " position:" + position);
        }
    }


//    public void connectToServer() throws IOException {
//        Socket socket = new Socket(serverAddress, 8675);
//        in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
//        out = new PrintWriter(socket.getOutputStream(), true);
//
//        for (int i = 0; i < 3; i++){
//            messageArea.append(in.readLine() + "\n");
//        }
//    }
}
