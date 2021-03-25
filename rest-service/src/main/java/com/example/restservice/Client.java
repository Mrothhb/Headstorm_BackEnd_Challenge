package com.example.restservice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * simulate the input of JSON formatted as list of numbers.
 *
 */
public class Client {
    public static void main(String[] args) throws IOException {
        StringBuffer nums = new StringBuffer();
        Random rand = new Random();

        // Generate a String that would represent a list of random numbers
        for( int i = 0; i < 500; i++){
            String str = Integer.toString(rand.nextInt(1000));
            nums.append(str + ",");
        }

        URL url = new URL("http://localhost:8080/data?nums=" + nums );
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        System.out.println(content);    // If we made it this far, display the JSON with "list" of random numbers sorted
    }
}
