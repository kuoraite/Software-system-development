package org.example.demo1.services;

import java.io.Serializable;

public class BookProcessingService implements Serializable {
    public void processBook() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException " + e);
        }
    }
}
