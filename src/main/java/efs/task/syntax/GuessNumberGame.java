package efs.task.syntax;

import java.util.Scanner;

public class GuessNumberGame {

    private final int numberToGuess ;
    private final int maxAttempts;
    private final int upperBound;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try {
            this.upperBound= Integer.parseInt(argument);

        } catch (NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException(UsefulConstants.WRONG_ARGUMENT);
        }

        if (this.upperBound < 1 || this.upperBound > UsefulConstants.MAX_UPPER_BOUND) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException(UsefulConstants.WRONG_ARGUMENT);
        }

        this.maxAttempts=(int)(Math.log(this.upperBound) / Math.log(2))+1;
        this.numberToGuess=(int)(Math.random()*upperBound)+1;
    }

    private void printProgressBar(int a, int b) {

        System.out.print("Twoje próby: [");

        for(int i=0;i<a;i++) {
            System.out.print("*");
        }

        for(int i=0;i<b;i++) {
            System.out.print(".");
        }

        System.out.print("]\n");
    }


    public void play() {
        // Proceed with the game logic here
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," +this.upperBound+ ">");

        Scanner scanner = new Scanner(System.in);

        int att=1;
        int number;

        while(true)
        {
            printProgressBar(att,maxAttempts-att);
            System.out.println(UsefulConstants.GIVE_ME);
            String stringValue = scanner.nextLine();

            try{
                number = Integer.parseInt(stringValue);
            }catch(NumberFormatException e) {
                System.out.println("'" +stringValue+ "' to " + UsefulConstants.NOT_A_NUMBER);
                att++;
                continue;
            }

            if(number==numberToGuess){
                System.out.println(UsefulConstants.YES);
                System.out.println(UsefulConstants.CONGRATULATIONS + ", " +att+ " - tyle prób zajęło Ci odgadnięcie liczby "+numberToGuess);
                break;

            }else if(number>numberToGuess){
                System.out.println(UsefulConstants.TO_MUCH);
            }else{
                System.out.println(UsefulConstants.TO_LESS);
            }

            if(att==maxAttempts) {
                System.out.println(UsefulConstants.UNFORTUNATELY + ", wyczerpałeś limit prób ("+maxAttempts+") do odgadnięcia liczby "+numberToGuess);
                break;
            }
            att++;
        }
    }
}
