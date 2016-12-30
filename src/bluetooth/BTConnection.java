package bluetooth;


import gnu.io.*;
import java.io.*;

public class BTConnection {

    InputStream in;
    OutputStream out;

    int currentPosx;
    int currentPosy;
    int homePosx;
    int homePosy;
    String comPort;
    String name;

    private int oldData = -1;

    public BTConnection(String name, String port, int xHome, int yHome)
    {
        this.homePosx = xHome;
        this.homePosy = yHome;
        this.currentPosx = xHome;
        this.currentPosy = yHome;
        this.name = name;
        this.comPort = port;

        try
        {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(comPort);
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
            SerialPort serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            in = serialPort.getInputStream();
            out = serialPort.getOutputStream();

            System.out.println("BT connection initialized");
        }
        catch(UnsupportedCommOperationException e){
            e.printStackTrace();
        } catch (PortInUseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPortException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setName(String name) {
        this.name = name;}

    public void setHome(int xPos, int yPos) {
        this.homePosx = xPos;
        this.homePosy = yPos;}

    public void setCurrentPos(int posX, int posY){
        this.currentPosx = posX;
        this.currentPosy = posY;}

    public int getCurrentPosy() {
        return currentPosy;
    }

    public int getCurrentPosx() {
        return currentPosx;
    }

    public String getName(){
        return this.name;}

    public int getHomex() {
        return this.homePosx;}

    public int getHomey() {
        return this.homePosy;}

    public String getPort(){
        return comPort;}

    public void sendData(int data) {
            try {
                out.write(data);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    public int receiveData()
    {
        try {
            return in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }
}