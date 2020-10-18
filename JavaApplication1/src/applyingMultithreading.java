
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abhi
 */

class add implements Callable{
    int sum=0;
    public Object call() throws Exception{
        for(int i=0;i<5;i++){
            sum +=i;     
            //System.out.println(sum);
        }
        return sum;
    }
    
}
class multiply implements Callable{
    public Object call() throws Exception{
        int a =10;
        int b = 20;
        return a*b;
    }
}

public class applyingMultithreading {
    public static void main(String args[]) throws Exception{
        FutureTask[] a = new FutureTask[2];
        
        
      Callable callable; 
            callable = new add();
      Callable mul = new multiply();
  
      // Create the FutureTask with Callable 
      a[1] = new FutureTask(callable); 
  
      Thread t = new Thread(a[1]); 
      t.start(); 
    
      a[0] = new FutureTask(mul);
      Thread t2 = new Thread(a[0]);
      t2.start();
      
      int val1 = (int)a[1].get();
      int val2 = (int)a[0].get();
      
      System.out.println(val1);
      System.out.println(val2);
      
      System.out.println(val1+val2);
    
    }
}
