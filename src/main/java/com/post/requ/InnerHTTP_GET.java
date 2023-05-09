package com.post.requ;

import java.io.IOException;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;

    class InnerHTTP_GET implements Runnable {
        private String massage,TO_NUMBER;
        AppMain appMain;
        InnerHTTP_GET(AppMain app, String massage, String TO_NUMBER){
            this.appMain=app;
            this.massage=massage;
            this.TO_NUMBER=TO_NUMBER;
           run();
        }
    
        private  void sendGET_() throws IOException {
            SmsSubmissionResponse responses = App.client.getSmsClient().submitMessage(
                new TextMessage(
                    App.FROM_NUMBER,
                    this.TO_NUMBER,
                    this.massage));
                    
                    if (responses.getMessages().get(0).getStatus() == MessageStatus.OK) {
                        registerOutput(" Message sent successfully.",1,massage);
                        
                    } else {
                        System.out.println(responses.getMessages());
                        registerOutput(" Message failed with error.",0,responses.getMessages().get(0).getErrorText());
                        
                        App.ErrorService.setError(4);
                    }
        }
        
        private  void registerOutput(String massage,int Type,String displayMessage ) throws IOException {
           String color = (Type==1) ? "Green" : "Red";
           appMain.addReadable("<html><body style='padding-left:20px;color:black'><span style='font:15;color:black'>"
           +TO_NUMBER+"</span>"+
           "<span style='font:15;color:"+ color+"'> "+massage+
           "</span><br/><span>" + new java.util.Date()+"</span><hr/>"+
           "<span style='font:12;color:black'>"+displayMessage+"</span><br/>_</body></html>", 1);
        }
        @Override
        public void run() {
            try {
                //registerOutput(" Message sent successfully."+App.API_KEY+App.API_SECRET+App.FROM_NUMBER,1,massage);
                sendGET_();
            } catch (Exception e) {
                App.ErrorService.setError(2);
                e.printStackTrace();
            }
        }

}