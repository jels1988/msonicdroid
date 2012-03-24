package net.msonic.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class UploadFileUtil {

	public static String GenerarFileName(int modulo,String extension){
		
		   Random randInt = new Random();
		   int numero = randInt.nextInt(32000);
		   
		   String file_name = String.format("%d_%d_%d.jpg",modulo,numero,  System.currentTimeMillis(),extension);

		   return file_name;
	}
	
	
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
