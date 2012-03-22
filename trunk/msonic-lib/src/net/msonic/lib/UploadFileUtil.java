package net.msonic.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UploadFileUtil {

	
	
    public static String FileToByteArray(String fileName){
        File file = new File(fileName);

        byte[] b = new byte[(int) file.length()];
        try {
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(b);
       
        } catch (FileNotFoundException e) {
        System.out.println("File Not Found.");
        e.printStackTrace();
        }
        catch (IOException e1)
        {
        System.out.println("Error Reading The File.");
        e1.printStackTrace();
        }
       
        
        
        
        byte[] encoded = Base64.encodeBytesToBytes(b);
        return new String(encoded);
}
    
}
