package com.zw.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Encoder;

public class GetImageBase64 {
    
	public static String getImageString(String filename) throws IOException {  
        InputStream in = null;  
        byte[] data = null;  
        try {  
            in = new FileInputStream(filename);  
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        } catch (IOException e) {  
            throw e;  
        } finally {  
            if(in != null) in.close();  
        }  
        BASE64Encoder encoder = new BASE64Encoder();  
        return data != null ? encoder.encode(data) : "";  
   }  
	
	
}
