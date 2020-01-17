package com.cg.Client;

import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.io.IOException;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.net.URL;

public class Client {

    public static String postRequest(String body) throws MalformedURLException, IOException, ProtocolException {
        URL url = new URL("http://localhost:8008/serve");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        byte[] outputInBytes = body.getBytes("UTF-8")
        String url = "jdbc:mysql://localhost:3306/contactdb";
        String user = "root";
        String password = "secret";    

        OutputStream os = con.getOutputStream();
        os.write( outputInBytes );    
        os.close();

        InputStream in = new BufferedInputStream(con.getInputStream());
        String result = null;
        try (Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name())) {
            result = scanner.useDelimiter("\\A").next();
            
            Connection conn = DriverManager.getConnection(url, user, password);
 
            String sql = "INSERT INTO person (BasicWeight, PercentageChance) values (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "85");
            statement.setString(2, "65");
 
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("A value is insered in the row");
            }
            conn.close();  
        }catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        in.close();
        con.disconnect();

        return result;
    }
}
