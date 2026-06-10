package com.kendall;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {

    public List<Query> parseLogFileInfo() throws FileNotFoundException {
        List<Query> queries = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("src/main/resources/access.log");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String logEntry;
            String regex = "^(\\S+) (\\S+) (\\S+) \\[(.*?)\\] \"(.*?) (.*?) (.*?)\" (\\d{3}) (\\S+) \"(.*?)\" \"(.*?)\"";
            Pattern pattern = Pattern.compile(regex);

            while ((logEntry = bufferedReader.readLine()) != null){
                Matcher matcher = pattern.matcher(logEntry);

                if (matcher.find()){
                    String ip = matcher.group(1);
                    String timeStamp = matcher.group(4);
                    String method = matcher.group(5);
                    String endPoint = matcher.group(6);
                    int statusCode = Integer.parseInt(matcher.group(8));
                    int responseSize = Integer.parseInt(matcher.group(9));
                    String userAgent = matcher.group(11);

                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
                    ZonedDateTime dateTime = ZonedDateTime.parse(timeStamp, inputFormatter);
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    timeStamp = dateTime.format(outputFormatter);

                    queries.add(new Query(ip, timeStamp, endPoint, method, statusCode, responseSize, userAgent));

                }

            }

        } catch (FileNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return queries;
    }
}
