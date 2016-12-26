package bluetooth;


import gnu.io.*;
import java.io.*;

public class BTConnection {

    InputStream in;
    OutputStream out;

    int posx;
    int posy;
    String comPort;
    String name;

    private int oldData = -1;

    public BTConnection(String name, String port, int xHome, int yHome)
    {
        this.posx = xHome;
        this.posy = yHome;
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
        this.posx = xPos;
        this.posy = yPos;}

    public String getName(){
        return this.name;}

    public int getHomex() {
        return this.posx;}

    public int getHomey() {
        return this.posy;}

    public String getPort(){
        return comPort;}

    public void sendData(int data) {
        if(data != oldData)
            try {
                out.write(data);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        oldData = data;
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