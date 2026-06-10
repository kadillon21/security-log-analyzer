package com.kendall;

import org.apache.commons.cli.*;

import java.io.FileNotFoundException;
import java.util.List;

public class AnalyzerApp {
    public static void main(String[] args) throws FileNotFoundException {

        FileParser fileParser = new FileParser();

        DatabaseManager databaseManager = new DatabaseManager();

        List<Query> queries = fileParser.parseLogFileInfo();
        databaseManager.createNewTable("Testing", fileParser.parseLogFileInfo());


//        Options options = new Options();
//        CommandLineParser parser = new DefaultParser();
//
//        options.addOption("h", "help", false, "Print help message");
//        options.addOption("f", "file", true, "The input file path");
//        options.addOption("s", "summary", false, "shows total requests, unique IPs, and top endpoints");
//        options.addOption(Option.builder("u")
//                .longOpt("user")
//                .hasArg()
//                .desc("Username for authentication")
//                .build());
//
//        try {
//            CommandLine cmd = parser.parse(options, args);
//
//            if (cmd.hasOption("h")) {
//                System.out.println("Working");
//            }
//
//            if(cmd.hasOption("s")){
//                System.out.println("Summary of analysis");
//            }
//        } catch (ParseException e){
//            System.err.println("Parsing failed: " + e.getMessage());
//        }

    }
}
