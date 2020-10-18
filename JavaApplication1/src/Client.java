/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abhi
 */
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//@Override
class download implements Callable<OutputStream>{
    public OutputStream call() throws Exception{
        int bytesRead;
        Socket sock = new Socket("localhost", 4999);
        InputStream in = sock.getInputStream();
         
        // Writing the file to disk
        // Instantiating a new output stream object
        System.out.print("Enter the path where you want to download : ");
        
        Scanner sc = new Scanner(System.in);
        
        String st = sc.next();
        OutputStream output = new FileOutputStream(st);

	try{
		System.out.println(""+sock.getInetAddress());
		System.out.println(""+sock.getPort());

	}catch(Exception e){}

           
        byte[] mybuffer = new byte[100000];
        while ((bytesRead = in.read(mybuffer)) != -1) {
            output.write(mybuffer, 0, bytesRead);
        }
        // Closing the FileOutputStream handle
        output.close();
        return output.flush();
        


   
    }
}
 
public class Client {
     
    public static void main(String[] args) throws Exception {
        int bytesRead;
 
        Socket sock = new Socket("localhost", 4999);
        Scanner sc = new Scanner(System.in);

        System.out.print("1. Upload files \n2. Download files\nEnter choice:  ");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
            System.out.print("Enter File name you want to upload ");
        
        String str = sc.next();
        File myFile = new File(str);
        byte[] buffer = new byte[(int) myFile.length()];
         
        FileInputStream fis = new FileInputStream(myFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read(buffer, 0, buffer.length);
         
        OutputStream os = sock.getOutputStream();
         
        os.write(buffer, 0, buffer.length);
         
        os.flush();
         
        sock.close();
        break;

        case 2:
            
            FutureTask[] down = new FutureTask[1];
            Callable d = new download();
            Thread t1 = new Thread(down[0]);
            t1.start();
            down[0] = new FutureTask(d);
            OutputStream o = (OutputStream)down[0].get();
            break;
        }
        // sendfile
        
    }
}
