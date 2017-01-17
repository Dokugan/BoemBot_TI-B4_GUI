package gui;

import bluetooth.BTConnection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main implements Runnable{

    public static MainPanel mp;
    public static Set<BTConnection> bots;


    public static void main(String args[])
    {
        bots = new HashSet<>();
        mp = new MainPanel();

        Thread t = new Thread("t1");
        t.start();
    }

    @Override
    public void run() {
        while(true) {
            for (BTConnection b : bots) {
                if(b.receiveData(1)[0] == 254){
                    b.setDirection(b.receiveData(0)[0]);
                }
                if(b.receiveData(1)[0] == 255){
                    int[] coord = new int[2];
                    coord = b.receiveData(2);
                    b.setCurrentPos(coord[0], coord[1]);
                }
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}