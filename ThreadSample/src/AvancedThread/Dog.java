/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AvancedThread;

/**
 *
 * @author Nguyen Thai Bao
 */
public class Dog implements Runnable {

    @Override
    public void run() {
        try {
            
            for (int i = 0; i < 10; i++) {
                System.out.println("Dog: Watching, Watching, Watching");
                if (i == 9) {
                    System.out.println("Dog : Go Go Go ...");
                }
                Thread.sleep(1000);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
    } 
}
