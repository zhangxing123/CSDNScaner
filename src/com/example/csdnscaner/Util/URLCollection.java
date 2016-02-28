package com.example.csdnscaner.Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

public class URLCollection {
	
	public static List<String> getURLCollection(String address){
        List<String> list = new LinkedList<String>();
        try{
            URL url = new URL(address);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in = conn.getInputStream();
            InputStreamReader input = new InputStreamReader(in, "UTF-8");
            BufferedReader buf = new BufferedReader(input);
            String nextLine = buf.readLine();
 
            while(nextLine != null){
                list.add(nextLine);
                nextLine = buf.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
	
}
