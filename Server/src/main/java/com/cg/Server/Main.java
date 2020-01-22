package com.cg.Server;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        serve();
    }

    public static void serve() {
        port(8008);
        post("/serve", (req, res) -> {
                switch(req.body()) {
                    case "Hello":
                        return hello();
                    case "url":
                        Class.forName("com.mysql.jdbc.Driver");
                         con = DriverManager.getConnection(
                      "jdbc:mysql://localhost:3306/contactdb","root","secret"); 
                        prepareStatement statement = con.prepareStatement("SELECT * FROM person WHERE BasicWeight=?");
                        statement.setInt(1, BasicWeight);
                        ResultSet rs = statement.executeQuery();
                        while(rs.next()){
                            this.BasicWeight = rs.getInt(1);
                            this.PercentageChance = rs.getString(2); 
                        }
                        if(rs!=null)
                            return rs;
                        else
                            return "No values in the table";
                        
                    //other scenarios could go here ;)
                    default:
                        return "Error! No or invalid request name specified! (" + req.body() + ")";
                }
            }
        );
    }

    public static String hello() {
        return "Hello stranger!";
    }
}
