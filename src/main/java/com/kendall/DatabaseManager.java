package com.kendall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseManager {
    public void createNewTable(String tableName, List<Query> sqlStatements){
        String databaseUrl = "jdbc:sqlite:/Users/kendall/projects/security-log-analyzer/LogAnalyzerDatabase" ;

        String databaseSetup =
                "CREATE TABLE " + tableName + " (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    ip TEXT NOT NULL,\n" +
                "    time TEXT NOT NULL,\n" +
                "    method TEXT NOT NULL,\n" +
                "    endpoint TEXT NOT NULL,\n" +
                "    status INT NOT NULL,\n" +
                "    response INT NOT NULL,\n" +
                "    user_agent TEXT NOT NULL\n" +
                ");";

        try (Connection connection = DriverManager.getConnection(databaseUrl)){
            Statement statement = connection.createStatement();
            statement.execute(databaseSetup);
            for(Query sql : sqlStatements){
                statement.execute("INSERT INTO " + tableName + " (ip, time, endpoint, method, status, response, user_agent)" + sql.values());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
