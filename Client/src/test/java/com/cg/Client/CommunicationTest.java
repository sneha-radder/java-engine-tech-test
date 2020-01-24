package com.cg.Client;

import com.cg.Client.Client;
import org.junit.Test;

public class CommunicationTest { 

    @Test
    public void testHelloRequestCorrect() throws Exception {
        String res = Client.postRequest("Hello");
        assert (res.equals("Hello stranger!"));
    }

    @Test
    public void testHelloRequestIncorrect() throws Exception {
        String res = Client.postRequest("hello");
        assert (res.equals("Error! No or invalid request name specified! (hello)"));
    }
    
    @Test
    public void testTableAggregateResponses() throws Exception {
        String url = "jdbc:mysql://localhost:3306/contactdb";
        String user = "root";
        String password = "secret";
        Connection conn = DriverManager.getConnection(url, user, password);
        prepareStatement statement = conn.prepareStatement("SELECT * FROM persons WHERE BasicWeight=?");
        statement.setInt(1, BasicWeight);
        statement.setInt(1, PercentageChance);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            assert (res.equals("There is a value in the table with value percentage chance));
        }
        if(rs== null){
           assert (res.equals("Table is empty));
        }
  }

    //add more tests here to validate your work
}
