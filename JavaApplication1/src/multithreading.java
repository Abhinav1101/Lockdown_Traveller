/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abhi
 */

class one extends Thread{
    public void run(){
    for(int i=0;i<5;i++)
    System.out.println("thread one "+i);
}
}

public class multithreading {
    public static void main(String args[]){
    one t1 = new one();
    Thread t2 = new two();
    Thread t3 = new three();
    Thread t4 = new Thread();
    t1.start();
    t2.start();
    t3.start();
}
}
    


class two extends Thread{
    public void run(){
    for(int i=0;i<5;i++)
    System.out.println("thread two "+i);
}
}

class three extends Thread{
    public void run(){
    for(int i=0;i<5;i++)
    System.out.println("thread three "+i);
} 
}

