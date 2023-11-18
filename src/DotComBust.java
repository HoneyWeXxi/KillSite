// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,

import java.io.*;
import java.util.*;

// then press Enter. You can now see whitespace characters in your code.
public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        DotCom one = new DotCom();
        one.setName("Google.com");
        DotCom two = new DotCom();
        two.setName("Yandex.ru");
        DotCom three = new DotCom();
        three.setName("Wikipedia.com");
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);

        System.out.println("Ваша цель потопить три сайта");
        System.out.println("Google.com , Yandex.ru, Wikipedia.com");
        System.out.println("Попытайтесь сделать это за минимальное количество ходов");

        for (DotCom dotComToSet : dotComsList) {
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dotComToSet.setLocationCells(newLocation);
        }
    }

    private void startPlaying() {
        while (!dotComsList.isEmpty()) {
            String userGuess = helper.getUserInput("Сделайте ход");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "Мимо";

        for (DotCom dotComToTest : dotComsList){
            result = dotComToTest.checkYourself(userGuess);
            if (result.equals("Попал")){
                break;
            }
            if (result.equals("Потопил")){
                dotComsList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame(){
        System.out.println("Все сайты потоплены! Вы круты!");
        if (numOfGuesses <= 18){
            System.out.println("Это заняло всего " + numOfGuesses + " попыток");
        }else {
            System.out.println("Это заняло у вас довольно много времени " + numOfGuesses + " попыток");
        }
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }

}

class DotCom {

    private String name;
    private ArrayList<String> locationCells;

    public String checkYourself(String userInput) {
        String result = "Мимо";
        int index = locationCells.indexOf(userInput);
        if (index >= 0) {
            locationCells.remove((index));

            if (locationCells.isEmpty()) {
                result = "Потопил";
                System.out.println("Вы потопили " + name);
            } else {
                result = "Попал";
            }
        }
        return result;
    }

    public void setName(String n){
        name = n;
    }


    public void setLocationCells(ArrayList<String> loc) {
        locationCells = loc;
    }

}


