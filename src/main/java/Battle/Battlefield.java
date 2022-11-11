package Battle;

import Characters.Character;
import Menu.Lobby;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Battlefield {


    private Lobby lobby;

    //constructor
    public Battlefield(Lobby lobby) {
        this.lobby = lobby;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public void battle(String menu_one_input){

        int userChoice = 0;
        Character defender = null;
        Character attacker = null;
        ArrayList<Character> graveyard = new ArrayList<>();

        System.out.println("entro en la battle!!");

        System.out.println("Team 1");
        System.out.println(lobby.getTeamOne().getTeamCharacters());

        System.out.println("Team 2");
        System.out.println(lobby.getTeamTwo().getTeamCharacters());


        while(checkTeamOneAlive()==true && checkTeamTwoAlive()==true){  //Duel team1 vs team2
            System.out.println("entro en el while!");

            if(menu_one_input.equals("1")){
                onePlayerBattle(userChoice,defender,attacker,graveyard);

            } else if (menu_one_input.equals("2")) {
                System.out.println("entro en el if!");
                twoPlayerBattle(userChoice,defender,attacker,graveyard);

            }else if(menu_one_input.equals("3")){
                noPlayerBattle();

            }
        }

        if(!checkTeamOneAlive() && checkTeamTwoAlive()){
            System.out.println("Team Two wins!!!");
        }

        if(checkTeamOneAlive() && !checkTeamTwoAlive()){
            System.out.println("Team One wins!!!");
        }

        if(!checkTeamOneAlive() && !checkTeamTwoAlive()){
            System.out.println("It's a tie!!!");
        }
    }




    public void onePlayerBattle (int userChoice,Character defender,Character attacker,ArrayList<Character> graveyard){


        for(int i= 0 ; i< lobby.getTeamOne().getTeamCharacters().size(); i++){       //1player mode

            if(!lobby.getTeamOne().getTeamCharacters().get(i).isAlive()){
                System.out.println("Player number "+(i+1)+" can't play, he's dead! \n");
                continue;
            }

            System.out.println("It's Team one's turn! Player "+ (i+1)+" "+lobby.getTeamOne().getTeamCharacters().get(i).getName()+" attacking first!");
            System.out.println("Which character is " + lobby.getTeamOne().getTeamCharacters().get(i).getName()+" going to attack? Choose number:");

            for (int j = 0; j < lobby.getTeamTwo().getTeamCharacters().size(); j++) {
                if(lobby.getTeamTwo().getTeamCharacters().get(j).isAlive()){
                    System.out.println((j+1)+" "+ lobby.getTeamTwo().getTeamCharacters().get(j).toString());
                }
            }
            Scanner sc = new Scanner(System.in);
            userChoice = sc.nextInt();
            try{
                defender = lobby.getTeamTwo().getTeamCharacters().get(userChoice-1);
            }
            catch (Exception e){
                System.out.println("Error of choice? there is no-one there?");
            }

            while(lobby.getTeamOne().getTeamCharacters().get(i).isAlive() && !lobby.getTeamTwo().checkAllDead()){ //Duel 1v1

                System.out.println("**********************************");
                System.out.println("It's "+lobby.getTeamOne().getTeamCharacters().get(i).getName()+"'s turn!");
                System.out.println("********************************** \n");
                lobby.getTeamOne().getTeamCharacters().get(i).attack(defender);

                if(!lobby.getTeamTwo().getTeamCharacters().get(userChoice - 1).isAlive()){
                    System.out.println("//////////////////////////////////");
                    System.out.println("Player "+lobby.getTeamOne().getTeamCharacters().get(i).getName()+ " wins the duel!");
                    System.out.println("////////////////////////////////// \n");
                    graveyard.add(getLobby().getTeamTwo().getTeamCharacters().get(userChoice-1));
                    System.out.println("+ + + + Graveyard + + + +\n");
                    System.out.println(graveyard.toString());
                    System.out.println("+ + + + + + + + + + + + +\n");
                    break;
                }

                attacker = lobby.getTeamOne().getTeamCharacters().get(i);
                System.out.println("**********************************");
                System.out.println(lobby.getTeamTwo().getTeamCharacters().get(userChoice-1).getName() + " is now CounterAttacking! Watch out!");
                System.out.println("********************************** \n");
                lobby.getTeamTwo().getTeamCharacters().get(userChoice-1).attack(attacker);

                if(!lobby.getTeamOne().getTeamCharacters().get(i).isAlive()){
                    System.out.println("//////////////////////////////////");
                    System.out.println("Player "+lobby.getTeamTwo().getTeamCharacters().get(userChoice-1).getName()+ " wins the duel!");
                    System.out.println("////////////////////////////////// \n");
                    graveyard.add(getLobby().getTeamOne().getTeamCharacters().get(i));
                    System.out.println("+ + + + Graveyard + + + +\n");
                    System.out.println(graveyard.toString());
                    System.out.println("+ + + + + + + + + + + + +\n");
                    break;
                }
            }
        } //1 player mode
    }

    private void twoPlayerBattle(int userChoice,Character defender,Character attacker,ArrayList<Character> graveyard) {

        System.out.println("entro en el metodo!");
        for(int i= 0 ; i< lobby.getTeamOne().getTeamCharacters().size(); i++){
            System.out.println("entro en el for!");
            if(!lobby.getTeamOne().getTeamCharacters().get(i).isAlive()){                                       //team1 choice
                System.out.println("Player number "+(i+1)+" can't play, he's dead! \n");
                continue; //?
            }

            System.out.println("It's Team one's turn! Player "+ (i+1)+" "+lobby.getTeamOne().getTeamCharacters().get(i).getName()+" attacking first!");
            System.out.println("Which character is " + lobby.getTeamOne().getTeamCharacters().get(i).getName()+" going to attack? Choose number:");

            for (int j = 0; j < lobby.getTeamTwo().getTeamCharacters().size(); j++) {
                if(lobby.getTeamTwo().getTeamCharacters().get(j).isAlive()){
                    System.out.println((j+1)+" "+ lobby.getTeamTwo().getTeamCharacters().get(j).toString());
                }
            }
            Scanner sc = new Scanner(System.in);
            userChoice = sc.nextInt();
            try{
                defender = lobby.getTeamTwo().getTeamCharacters().get(userChoice-1);
            }
            catch (Exception e){
                System.out.println("Error of choice? there is no-one there?");
            }

            while(lobby.getTeamOne().getTeamCharacters().get(i).isAlive() && !lobby.getTeamTwo().checkAllDead()){ //Duel 1v1

                System.out.println("**********************************");
                System.out.println("It's "+lobby.getTeamOne().getTeamCharacters().get(i).getName()+"'s turn!");
                System.out.println("********************************** \n");
                lobby.getTeamOne().getTeamCharacters().get(i).attack(defender);

                if(!lobby.getTeamTwo().getTeamCharacters().get(userChoice - 1).isAlive()){
                    System.out.println("//////////////////////////////////");
                    System.out.println("Player "+lobby.getTeamOne().getTeamCharacters().get(i).getName()+ " wins the duel!");
                    System.out.println("////////////////////////////////// \n");
                    graveyard.add(getLobby().getTeamTwo().getTeamCharacters().get(userChoice-1));
                    System.out.println("+ + + + Graveyard + + + +\n");
                    System.out.println(graveyard.toString());
                    System.out.println("+ + + + + + + + + + + + +\n");
                    break;
                }

                attacker = lobby.getTeamOne().getTeamCharacters().get(i);
                System.out.println("**********************************");
                System.out.println(lobby.getTeamTwo().getTeamCharacters().get(userChoice-1).getName() + " is now CounterAttacking! Watch out!");
                System.out.println("********************************** \n");
                lobby.getTeamTwo().getTeamCharacters().get(userChoice-1).attack(attacker);

                if(!lobby.getTeamOne().getTeamCharacters().get(i).isAlive()){
                    System.out.println("//////////////////////////////////");
                    System.out.println("Player "+lobby.getTeamTwo().getTeamCharacters().get(userChoice-1).getName()+ " wins the duel!");
                    System.out.println("////////////////////////////////// \n");
                    graveyard.add(getLobby().getTeamOne().getTeamCharacters().get(i));
                    System.out.println("+ + + + Graveyard + + + +\n");
                    System.out.println(graveyard.toString());
                    System.out.println("+ + + + + + + + + + + + +\n");
                    break;
                }


            }        //team 1 choice 1v1



            if(!lobby.getTeamTwo().getTeamCharacters().get(i).isAlive()){                   //team 2 choice
                System.out.println("Player number "+(i+1)+" can't play, he's dead! \n");
                continue; //?
            }

            System.out.println("It's Team Two's turn! Player "+ (i+1)+" "+lobby.getTeamTwo().getTeamCharacters().get(i).getName()+" attacking first!");
            System.out.println("Which character is " + lobby.getTeamTwo().getTeamCharacters().get(i).getName()+" going to attack? Choose number:");

            for (int j = 0; j < lobby.getTeamOne().getTeamCharacters().size(); j++) {
                if(lobby.getTeamOne().getTeamCharacters().get(j).isAlive()){
                    System.out.println((j+1)+" "+ lobby.getTeamOne().getTeamCharacters().get(j).toString());
                }
            }

            userChoice = sc.nextInt();
            try{
                defender = lobby.getTeamOne().getTeamCharacters().get(userChoice-1);
            }
            catch (Exception e){
                System.out.println("Error of choice? there is no-one there?");
            }

            while(lobby.getTeamTwo().getTeamCharacters().get(i).isAlive() && !lobby.getTeamOne().checkAllDead()){ //Duel 1v1

                System.out.println("**********************************");
                System.out.println("It's "+lobby.getTeamTwo().getTeamCharacters().get(i).getName()+"'s turn!");
                System.out.println("********************************** \n");
                lobby.getTeamTwo().getTeamCharacters().get(i).attack(defender);

                if(!lobby.getTeamOne().getTeamCharacters().get(userChoice - 1).isAlive()){
                    System.out.println("//////////////////////////////////");
                    System.out.println("Player "+lobby.getTeamTwo().getTeamCharacters().get(i).getName()+ " wins the duel!");
                    System.out.println("////////////////////////////////// \n");
                    graveyard.add(getLobby().getTeamOne().getTeamCharacters().get(userChoice-1));
                    System.out.println("+ + + + Graveyard + + + +\n");
                    System.out.println(graveyard.toString());
                    System.out.println("+ + + + + + + + + + + + +\n");
                    break;
                }

                attacker = lobby.getTeamTwo().getTeamCharacters().get(i);
                System.out.println("**********************************");
                System.out.println(lobby.getTeamOne().getTeamCharacters().get(userChoice-1).getName() + " is now CounterAttacking! Watch out!");
                System.out.println("********************************** \n");
                lobby.getTeamOne().getTeamCharacters().get(userChoice-1).attack(attacker);

                if(!lobby.getTeamTwo().getTeamCharacters().get(i).isAlive()){
                    System.out.println("//////////////////////////////////");
                    System.out.println("Player "+lobby.getTeamOne().getTeamCharacters().get(userChoice-1).getName()+ " wins the duel!");
                    System.out.println("////////////////////////////////// \n");
                    graveyard.add(getLobby().getTeamTwo().getTeamCharacters().get(i));
                    System.out.println("+ + + + Graveyard + + + +\n");
                    System.out.println(graveyard.toString());
                    System.out.println("+ + + + + + + + + + + + +\n");
                    break;
                }
            }
        } //2 player mode
    }

    private void noPlayerBattle() {
    }



    public boolean checkTeamOneAlive(){
        return !lobby.getTeamOne().checkAllDead();
    }

    public boolean checkTeamTwoAlive(){
        return !lobby.getTeamTwo().checkAllDead();
    }
}


