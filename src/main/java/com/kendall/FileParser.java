package com.kendall;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileParser {
    private String ip;
    private LocalDateTime timeStamp;
    private String endPoint;
    private int method;

    public void loadLogFile() throws FileNotFoundException {
        try {
            FileReader fileReader = new FileReader("src/main/resources/access.log");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String input;
            while ((input = bufferedReader.readLine()) != null){
                System.out.println(input);
            }


        } catch (FileNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
