package com.kendall;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.CharacterIterator;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    private String ip;
    private String timeStamp;
    private String endPoint;
    private String method;

    public void loadLogFile() throws FileNotFoundException {
        try {
            FileReader fileReader = new FileReader("src/main/resources/access.log");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String logEntry;
            String regex = "^(\\S+) (\\S+) (\\S+) \\[(.*?)\\] \"(.*?) (.*?) (.*?)\" (\\d{3}) (\\S+) \"(.*?)\" \"(.*?)\"";
            Pattern pattern = Pattern.compile(regex);

            while ((logEntry = bufferedReader.readLine()) != null){
                Matcher matcher = pattern.matcher(logEntry);

                if (matcher.find()){
                    ip = matcher.group(1);
                    timeStamp = matcher.group(4);
                    endPoint = matcher.group(6);
                    method = matcher.group(5);
                }
                System.out.println("IP: " + ip + " Time Stamp: " + timeStamp + " End Point " + endPoint + " Method " + method);


            }



        } catch (FileNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
