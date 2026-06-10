package com.kendall;

public record Query(String ip, String timeStamp, String endPoint, String method, int statusCode, int responseSize, String userAgent) {
    public String values(){
        return "VALUES ('" + ip + "', '" + timeStamp + "', '" + endPoint + "', '" + method + "', " + statusCode + ", " + responseSize + ", '" + userAgent + "');";
    }

}
