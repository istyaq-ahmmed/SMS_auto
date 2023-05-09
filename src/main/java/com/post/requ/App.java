package com.post.requ;

import java.io.IOException;

import org.json.JSONObject;

import com.vonage.client.VonageClient;

public class App  {
    public static JSONObject  configueJSON=null;
    public static boolean isPaused=false;
    public static boolean finished=false;
    static errorService ErrorService;
    public static int error_code=0;
    static String 
                numbers="Y",
                massage=" ",
                API_KEY="dfc3dbec",                 // Set Your API_KEY
                API_SECRET="mbjB73I4BzDoBH4A",      // Set Your API_SECRET
                FROM_NUMBER="12046900991"                // Set Your FROM_NUMBER
                //,TO_NUMBER="8801705559808"
                ;

    static String url;
    public static VonageClient client;

    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args)   {
        configueJSON=fileMannager.JSONefiedConfig("configue.json");
          client = VonageClient.builder()
              .apiKey(API_KEY)
              .apiSecret(API_SECRET)
             .build();
        //client=null;
        
        ErrorService= new errorService( new AppMain());
          
}

   
}