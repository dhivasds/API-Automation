package Utils;

import java.util.Random;
public class General {
    Random rand = new Random();

    public String randomEmail(){
        return "dhivas" + rand.nextInt(1000) + "@g4.com";
    }

}
