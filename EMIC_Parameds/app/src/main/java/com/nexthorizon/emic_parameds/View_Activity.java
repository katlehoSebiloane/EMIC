package com.nexthorizon.emic_parameds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class View_Activity extends AppCompatActivity {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
            getSupportActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }



        Timer poll = new Timer();
        TimerTask simulator = new TimerTask() {
            @Override
            public void run() {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(socket==null){
                                socket = new Socket("192.168.43.53",15000);
                            }
                            printWriter = new PrintWriter(socket.getOutputStream(),true);
                            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            System.out.println("Done Client Side Setup");
                            printWriter.println("SIMULATOR|"+"D3_TEST_ROUND_TWO"+"\n\n");
                            Scanner scanner = new Scanner(bufferedReader);
                            while(scanner.hasNextLine()){
                                String message_segment = scanner.nextLine();
                                System.out.println(message_segment);
                                if(message_segment.contains("MDC_PULS_OXIM_SAT_O2")){
                                    String mod1 = message_segment.replace("{{","");
                                    String mod2 = mod1.replace("}}","");
                                    String mod3 = mod2.replace("Document","");
                                    String[] message = mod3.split("[,]");
                                    String reading = message[4];
                                    String reading2 = message[5];
                                    String[] process = reading.split("[|]");
                                    String reader = process[6];
                                    String reader2 = reading2.split("[|]")[6];
                                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+reader);
                                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+reader2);

                                    runOnUiThread(()->{
                                   });

                                }


                                if(message_segment.contains("MDC_PULS_OXIM_SAT_O2")){
                                    String mod1 = message_segment.replace("{{","");
                                    String mod2 = mod1.replace("}}","");
                                    String mod3 = mod2.replace("Document","");
                                    String[] message = mod3.split("[,]");
                                    String reading = message[4];
                                    String reading2 = message[5];
                                    String[] process = reading.split("[|]");
                                    String reader = process[6];
                                    String reader2 = reading2.split("[|]")[6];
                                    runOnUiThread(()->{

                                    });

                                }


                            }

                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                    }
                };
                Thread t = new Thread(r);
                t.start();

            }
        };
        poll.schedule(simulator,1000,1200);


    }
}