package org.prosjekt.staticmaps;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Alfredo
 */
public class ImageGetter {
    
    /**
     *
     * @param address
     * @return
     */
    public static byte [] getImage(String address) {
        ByteArrayOutputStream bis = new ByteArrayOutputStream();
        InputStream is = null;
        URL url = null;
        
        try {
            url = new URL(address);
        }
        
        catch (MalformedURLException e) {
            
        }
        
        try {
            is = url.openStream ();
            byte[] bytebuff = new byte[4096]; 
            int n;
            while ( (n = is.read(bytebuff)) > 0 ) {
                bis.write(bytebuff, 0, n);
            }
        }
        catch (IOException e) {
            
        }
        
        return (bis.toByteArray());
        
    }
    
    /**
     *
     * @param byteArray
     * @param outFile
     */
    public static void toFile(byte[] byteArray, File outFile){
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(outFile);
            outStream.write(byteArray);
        }
        
        catch (IOException e){
            
        }
        
    }
    
    /**
     *
     * @param address
     * @return
     */
    public static BufferedImage getBufferedImage(String address) {
        URL url = null;
        InputStream is = null;
        BufferedImage bufferedImage = null;
        
        try {
            url = new URL(address);
        }
        catch (MalformedURLException e) {
            
        }
        
        try {
            is = url.openStream();
            bufferedImage = ImageIO.read(is);
        }
        catch (IOException e){
            
        }
        
        return bufferedImage;
        
    }
    
}
