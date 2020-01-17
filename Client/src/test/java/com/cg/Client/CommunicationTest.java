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

    //add more tests here to validate your work
    
    @Test
    public void testTableRequestInputs() throws Exception {
        String res = Client.postRequest("BasicWeight");
        Scanner keyboard = new Scanner(in);
        out.println("Enter a number");
        int input = keyboard.nextInt();

        while (input != res) {
        out.println("Wrong input, try again.");
        input = keyboard.nextInt();
    }

    return input;
    }

}
