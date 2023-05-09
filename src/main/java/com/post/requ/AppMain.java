package com.post.requ;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class AppMain implements ActionListener{
    private boolean running=false;
    JFrame leadFrame = new JFrame("Send Request:");
    JButton startButton= new JButton();
    JScrollPane centerScrollPanel;
    JPanel centerPanel=new JPanel(new GridLayout(0,1));
    JScrollBar vertical;
    AppMain appMain=this;
    AppMain(){
        leadFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        leadFrame.setAlwaysOnTop(false);
        leadFrame.setResizable(true);
        startButton.setText("Start");
        startButton.addActionListener(this);
      
        centerScrollPanel=new JScrollPane(centerPanel);
        leadFrame.add(centerScrollPanel,BorderLayout.CENTER);
        leadFrame.add(startButton,BorderLayout.SOUTH); 
        leadFrame.setSize(1000,500 );
        leadFrame.setVisible(true);   
    }

    public void addReadable(String massage,int Type){
        JLabel jLabel=new JLabel(massage);
        centerPanel.add(jLabel);
        
        vertical = centerScrollPanel.getVerticalScrollBar();
        vertical.setValue( vertical.getMaximum() );
        leadFrame.repaint();
        leadFrame.setVisible(true);   
    }
    public void updateStartButton(Boolean state,Boolean finished) {
        updateStartButton(state,finished,null);
    }


    public void updateStartButton(Boolean state,Boolean finished,String massaGe) {
        App.isPaused=state;
        if(App.finished) return;
        //System.out.println("F:"+finished+"E:"+App.error_code);
        String printableState=App.isPaused ? "Paused":"Resumed";
        String notPrintableState=App.isPaused ? "Resume":"Pause";
        if(finished){
            printableState="Finished";
            notPrintableState="Finished";
            App.finished=true;
        }
        if(massaGe!=null){
            printableState=massaGe;
            notPrintableState="Error";
        }
        String color="Blue";
        if(App.error_code!=0) color="Red";
        startButton.setText(notPrintableState);
        addReadable("<html><body style='padding-left:20px;color:"+color+"'><span style='font:15'>" 
        + printableState +
        "</span><hr/><span style='color:black'>"
        + new java.util.Date()+"</span></body></html>",1);
    }









    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(!running){
                Thread thread = new Thread(){
                    public void run(){
                        try {
                            new randomSringRun(appMain);
                        } catch (IOException e) {
                            App.ErrorService.setError(5);
                        }
                        updateStartButton(true,true);
                      System.out.println("Thread Running");
                    }
                };
                
                System.out.println("Th");
                thread.start();
                running=true;
                updateStartButton(App.isPaused,false);
            }
            else {
                updateStartButton(!App.isPaused,false);
                App.error_code=0;
            }
        } catch (Exception ei) {
            App.ErrorService.setError(1);
        }
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
}
