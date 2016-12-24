package gui;

import bluetooth.BTConnection;

public class Main {

    static MainPanel mp;
    static BTConnection conn;

    public static void main(String args[])
    {
        System.out.println("sdafasd");
        mp = new MainPanel();
        conn = new BTConnection();
    }
}
