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

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
 
/**
 * Download image files.
 *
 * @author itcuties
 *
 */
public class ImageDownload {
    // Image path
    private String imageUrl;
    // Destionation local file
    private String destinationPath;
 
    public ImageDownload(String imageUrl, String destinationPath) {
        this.imageUrl = imageUrl;
        this.destinationPath = destinationPath;
    }
 
    /**
     * Download image to local drive
     * @throws Exception
     */
    public void download() throws Exception {
        // Open connetction to the image
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        // Stream to the destionation file
        FileOutputStream fos = new FileOutputStream(destinationPath);
 
        // Read bytes from URL to the local file
        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        while ((bytesRead = is.read(buffer)) != -1)
            fos.write(buffer,0,bytesRead);
 
        // Close destination stream
        fos.close();
        // Close URL stream
        is.close();
    }
}


