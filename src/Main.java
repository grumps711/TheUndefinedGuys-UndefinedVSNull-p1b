import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello world!");




        ArrayList<Character> characterArrayList=new ArrayList<Character>( List.of(
                new Warrior(),
                new Warrior(),
                new Warrior(),
                new Wizard(),
                new Wizard(),
                new Warrior("Pepe",1000,1000,1000),
                new Warrior("Pepe",-1000,1000,1000)
        ));


        characterArrayList.get(0).setName("Federico");
        System.out.println(characterArrayList);
    }
}