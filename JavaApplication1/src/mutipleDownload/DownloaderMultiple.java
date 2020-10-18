/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutipleDownload;

/**
 *
 * @author abhi
 */

public class DownloaderMultiple extends Thread {
    private String imageUrl;
    private String destinationPath;
    private Lock lock;
 
    public DownloaderMultiple(String imageUrl, String destinationPath, Lock lock) {
        this.imageUrl = imageUrl;
        this.destinationPath = destinationPath;
        this.lock = lock;
    }
 
    @Override
    public void run() {
        try {
            lock.addRunningThread();
 
            new ImageDownload(imageUrl, destinationPath).download();
 
            lock.removeRunningThread();
 
            synchronized (lock) {
                lock.notify();
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

