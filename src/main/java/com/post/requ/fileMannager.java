package com.post.requ;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;  

public class fileMannager  {  
    List<String> the_hole_List=Collections.emptyList();
    int i=0;
    fileMannager()  throws IOException{
        i=0;
        the_hole_List = readFileInList("Number.txt"); 
    }  

    private List<String> readFileInList(String string) {
            File fl = new File(string);
            List<String> lines = Collections.emptyList();
            try{
                if(!fl.exists()) fl.createNewFile();
                lines = Files.readAllLines(Paths.get(string), StandardCharsets.UTF_8);
            }
            catch (IOException e){
                App.ErrorService.setError(3);
            }
        return lines;
        }

    public String get_Numbers(){
            if (i>=the_hole_List.size()) return null;
            return  the_hole_List.get(i++);        
        }  

      
        static public String getFileContent(String file_path){
            String content=new String();
            File fl = new File(file_path);
                try{
                    if(!fl.exists()) fl.createNewFile();
                    Scanner myReader = new Scanner(fl);
                    while (myReader.hasNextLine()) {
                        content += myReader.nextLine();
                    }
                    myReader.close();
                    //lines = Files.re(Paths.get(file_path), StandardCharsets.UTF_8);
                }
                catch (IOException e){
                    App.ErrorService.setError(3);
                }
                
            return content;
        }
        //"configue.json"
    public static JSONObject JSONefiedConfig(String file_path) {
        try {
             App.configueJSON=new JSONObject(getFileContent(file_path));
             App.API_KEY=App.configueJSON.getString("API_KEY");
             App.API_SECRET=App.configueJSON.getString("API_SECRET");
             App.FROM_NUMBER=App.configueJSON.getString("FROM_NUMBER");
        } catch (Exception e) {
            App.error_code=5;
        }
        if(App.configueJSON==null) 
            App.configueJSON= new JSONObject("{\"array\":[]}");
        return App.configueJSON;
    }
    static public ArrayList<String[]> getConfig(){
        ArrayList<String[]> toReturnArrayList= new  ArrayList<String[]>();
        try {       
            JSONArray jArray= App.configueJSON.getJSONArray("array");
            if(jArray.length()==0) return null;
            for (int i=0;i<jArray.length();i++) {
                JSONArray elementJSONArray=(JSONArray) jArray.get(i);
                String arayString[]= new String[elementJSONArray.length()];
                for (int j=0;j<elementJSONArray.length();j++) {
                    arayString[j]=(String) elementJSONArray.get(j);
                }
                toReturnArrayList.add(arayString);
                //System.out.println(arayString[0]);
            }
        } catch (Exception e) {
            toReturnArrayList=null;
            App.ErrorService.setError(5);
        }
        //System.out.println(getFileContent("dddd"));
        return toReturnArrayList;
        }
    } 
