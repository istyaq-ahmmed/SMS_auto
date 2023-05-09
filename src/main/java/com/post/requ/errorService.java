package com.post.requ;

public class errorService {
    AppMain appMain;
    errorService(AppMain appMain){
        this.appMain=appMain;
    }
    public  void setError(int code){
        App.error_code=code;
        switch(code){
            case 1: pauseOrresime("Thread Failure"); break;
            case 2: System.out.println();pauseOrresime("Could Not send Get Request"); break;
            case 3: System.out.println();pauseOrresime("Could Not Open TXT file"); break;
            case 4: System.out.println();pauseOrresime("Vonage failed to send Massage"); break;
            case 5: pauseOrresime("JSON Incorrect"); break;
            default : break;
        }
    }
    public  void pauseOrresime(String errorText){
        this.appMain.updateStartButton(true,false,errorText);
    }
    
}
