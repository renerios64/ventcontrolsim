import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class VentCntrlWidget {
    private BufferedReader in;
    private PrintWriter out;
    private String serverAddress = "";
    private String port = "";
    private int status = -1;
    private double temp = -100.00;
    private double position = -1.00;
    private String randomUUIDString = "";
    private UUID uuid = null;
    private VentCntrlWidgetGUI gui = null;


    public VentCntrlWidget(int instanceNumber){
        setupWidget();
        gui = new VentCntrlWidgetGUI(instanceNumber);
    }

    public JPanel getGUI(){
        return gui.getGUI();
    }

    public String getDeviceName(){
        return gui.getDeviceName();
    }

    private void setupWidget() {
        uuid = UUID.randomUUID();
        randomUUIDString = uuid.toString();
    }

    public String getRandomUUID(){
        return randomUUIDString;
    }

    public String getUUIDVersion(){
        return String.valueOf(uuid.version());
    }

    public String getUUIDVariant(){
        return String.valueOf(uuid.variant());
    }

    public double setTemp(double incomingTemp){
        temp = incomingTemp;
        return temp;
    }

    public double getTemp(){
        return temp;
    }

    public double setPosition(double incomingPosition){
        position = incomingPosition;
        return position;
    }

    private int setStatus(int incomingStatus){
        status = incomingStatus;
        return status;
    }

    public int getStatus(){
        return status;
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
