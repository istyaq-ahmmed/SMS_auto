package com.post.requ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.vonage.client.VonageClient;

class randomSringRun extends Thread{
    AppMain appMain;
    String 
    numbers=" ",
    massage=" ";
    Random random= new Random(System.currentTimeMillis());
    ArrayList<String[]> random_array;
    int arry_index[];
    fileMannager file;
    VonageClient client;
        
            /**
             * 
             */
            public void random_values(){
                random_array=fileMannager.getConfig();
                try {
                    arry_index = new int[random_array.size()];
                } catch (Exception e) {
                    App.ErrorService.setError(5);
                }
            }
        
    


        public String get_values(){
            String assamble=new String();
            psudoRandom();
            for (int i=0;i<random_array.size();i++){
                assamble+=random_array.get(i)[arry_index[i]];
            }
            return assamble;
        } 
        void psudoRandom(){
            
            for(int i=0;i< arry_index.length;i++){
                int len=random_array.get(i).length;
                int randomint=positveRandom(len);
                if (len!=1 && arry_index[i]==randomint) {
                    i--;
                continue;
                }
            arry_index[i]=randomint;

            }
        }      
        private int positveRandom(int upto){
            int x=random.nextInt();
            if(x<0) x*=-1;
            return x%upto;
        }
        randomSringRun(AppMain app) throws IOException{
            this.appMain=app;
            random_array=new ArrayList<String[]>();
            file=new fileMannager();

            random_values();
            
            while(true){
                    if(numbers==null) break;
                    try {
                        
                        run_threds();
                    } catch (Exception e) {
                        App.ErrorService.setError(1);
                    }
                }
        }
        /**
         * 
         */
        void run_threds(){
            try{Thread.sleep(600);}
            catch(InterruptedException e){
                App.ErrorService.setError(1);
            }
            catch(Exception e){
                App.ErrorService.setError(5);
            }
            
            if(!App.isPaused){
                numbers=file.get_Numbers();
                if(numbers!=null){
                    massage=get_values();
                    new InnerHTTP_GET(this.appMain,massage,numbers);
                }
                System.out.println(App.error_code);
            } 
    
        }
            public void extracted(Runnable r) {
                Thread the= new Thread(r);
                the.start();
        }
}