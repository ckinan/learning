package com.ckinan;

import org.tinylog.Logger;

public class App {

    public static void main(String[] args) {
        for(int i=0; i<6000; i++) {
            Logger.info(i);
        }
    }

}
