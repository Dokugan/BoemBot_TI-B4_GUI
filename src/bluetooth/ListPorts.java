package bluetooth;

import gnu.io.*;
import jssc.SerialPortList;
import gui.Main;
import gui.NewBoebot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ListPorts {
    public static void getPorts()
    {
//        java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
//        while ( portEnum.hasMoreElements() )
//        {
//            if(NewBoebot.ports.contains("Poorten laden...")) {
//                NewBoebot.ports.clear();
//            }
//
//            CommPortIdentifier portIdentifier = portEnum.nextElement();
//                if (Objects.equals(getPortTypeName(portIdentifier.getPortType()), "Serial")) {
//                    Main.mp.newBoebotPanel.ports.add(portIdentifier.getName());
//                }
//            System.out.println(portIdentifier.getName()  +  " - " +  getPortTypeName(portIdentifier.getPortType()));
//
//        }
        NewBoebot.ports = new ArrayList(Arrays.asList(SerialPortList.getPortNames()));

        if(Main.mp.newBoebotPanel.ports.size() == 0){
            Main.mp.newBoebotPanel.ports.add("Geen poorten beschikbaar");
            System.out.println("No serial ports available");
        }
        System.out.println("done");
    }

//    private static String getPortTypeName ( int portType )
//    {
//        switch ( portType )
//        {
//            case CommPortIdentifier.PORT_I2C:
//                return "I2C";
//            case CommPortIdentifier.PORT_PARALLEL:
//                return "Parallel";
//            case CommPortIdentifier.PORT_RAW:
//                return "Raw";
//            case CommPortIdentifier.PORT_RS485:
//                return "RS485";
//            case CommPortIdentifier.PORT_SERIAL:
//                return "Serial";
//            default:
//                return "unknown type";
//        }
//    }
}
