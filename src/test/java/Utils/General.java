package Utils;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;
public class General {
    Random rand = new Random();
    Faker faker = new Faker(new Locale("in-ID"));

//    public String randomEmail(){ return "dhivas" + rand.nextInt(1000) + "@g4.com"; }
    public String randomEmail(){ return faker.internet().emailAddress(); }
    public String randomName(String name) { return faker.name().fullName(); }
    public String randomPassword(String password) { return faker.internet().password(10,20);}

}
