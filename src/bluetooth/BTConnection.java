package bluetooth;


import gnu.io.*;
import java.io.*;

public class BTConnection {

    InputStream in;
    OutputStream out;

    boolean available;

    public BTConnection()
    {
//		listPorts();
        try
        {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM3");
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

    public void sendData(int data) {
        try {
            out.write(data);
            in.read();
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
