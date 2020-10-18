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
public class Download{
    public static void main(String[] args){
String[] imagesToDownload = new String[] {
//        "http://IMG_20190909_230942.jpg",
//        "http://IMG_20190909_231140.jpg",
        "http://fbapp.itcuties.com/middle/_DSC4796.jpg",
        "http://fbapp.itcuties.com/middle/_DSC4776.jpg",
        "http://fbapp.itcuties.com/middle/_DSC4505.jpg",
        "http://fbapp.itcuties.com/middle/_DSC4448.jpg",
        "http://fbapp.itcuties.com/middle/_DSC4590.jpg",
        "http://fbapp.itcuties.com/middle/_DSC4514.jpg",
        "http://fbapp.itcuties.com/middle/_DSC4698.jpg",
        "http://fbapp.itcuties.com/middle/_DSC4434.jpg"
};
 
String destinationFolder = "/home/abhi/Desktop";
try{
    int nameIndex   = 0;
    long startTime  = 0;
    long endTime    = 0;
 
    // Download images in this thread
    System.out.println("Downloading "+imagesToDownload.length+" images with single thread");
    startTime = System.currentTimeMillis();
    for (String url: imagesToDownload) {
        new ImageDownload(url,destinationFolder + "single" + (nameIndex++) + ".jpg").download();
    }
    endTime = System.currentTimeMillis();
    System.out.println("\tdownload time: " + (endTime-startTime)+" ms");
 
    Lock lock = new Lock(); // A lock object to synchronize threads on it.
    System.out.println("Downloading "+imagesToDownload.length+" images with multiple threads");
    startTime = System.currentTimeMillis();
    for (String url: imagesToDownload) {
        DownloaderMultiple dt = new DownloaderMultiple(url, destinationFolder + "multiple" + (nameIndex++) + ".jpg", lock);
        dt.start(); // Start download in another thread
    }
 
    // Wait here for all the threads to end
    while (lock.getRunningThreadsNumber() > 0)
        synchronized (lock) {
            lock.wait();
        }
 
    endTime = System.currentTimeMillis();
    System.out.println("\tdownload time: " + (endTime-startTime)+" ms");
}catch(Exception e){
    System.out.println("Exception is "+e);
}
    
    
    }

}