package gui;

import bluetooth.BTConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static BTConnection conn;
    public static void main(String args[])
    {
        MainPanel mainP = new MainPanel();
        conn = new BTConnection();
    }
}
