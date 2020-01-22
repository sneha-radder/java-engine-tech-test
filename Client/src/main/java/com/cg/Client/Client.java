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
            String symbolsData = "INSERT INTO symbols (SymbolId, SymbolStar, SymbolPercentage, SymbolDollar) values (?, ?,?,?)";
            PreparedStatement symbolStatement = conn.prepareStatement(symbolsData);
            String inputString = "Spin";
            if(inputString == "Spin"){
            symbolStatement.setString(1, "*", "%","$");
            symbolStatement.setString(2, "*", "%","$");
            symbolStatement.setString(3, "*", "%","$");
            int resultSymbol = symbolStatement.executeUpdate();
                if(row==3){
                    System.out.println("A Matrix is insered in the row");
                }
            }  
            String reelSymbols = "INSERT INTO reels (ReelId, ReelSymbol) values (?, ?)";
            PreparedStatement symbolStatement = conn.prepareStatement(reelSymbols);
            reelSymbols.setString(1, "&");
            reelSymbols.setString(2, "@");
            int resultReel = symbolStatement.executeUpdate();
            
            prepareStatement statement = con.prepareStatement("SELECT * FROM reels WHERE ReelId=?");
            statement.setInt(1, ReelId);
            ResultSet rs = statement.executeQuery();
            String ReelSymbol=null;
            while(rs.next()){
             this.ReelSymbol = rs.getInt(1);
             ReelSymbol=this.ReelSymbol;   
            }
            
            if(ReelSymbol !=null && ReelSymbol == "&"){
                prepareStatement statement = con.prepareStatement("SELECT SymbolStar,SymbolPercentage FROM symbols WHERE SymbolId=?");
                statement.setInt(1, SymbolId);
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                this.SymbolStar = rs.getString(1);
                this.SymbolPercentage=rs.SymbolPercentage(2);   
                }
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
