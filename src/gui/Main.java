package gui;

import bluetooth.BTConnection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main{

    public static MainPanel mp;
    public static Set<BTConnection> bots;


    public static void main(String args[])
    {
        bots = new HashSet<>();
        mp = new MainPanel();
    }
}