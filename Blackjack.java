package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.createDeck();
        deck.shuffle();
        int money = 100;
        int bet;
        int total;
        int dealerTotal;
        ArrayList<Card> playerList;
        ArrayList<Card> dealerList;
        Scanner scanner = new Scanner(System.in);

        while (money > 0) {
            do{
                System.out.println("You have £" + money + ". How much would you like to bet? (0 -> zero to quit)");
                bet = scanner.nextInt();
                scanner.nextLine();
            }while (bet > money);
            if (bet == 0){
                break;
            }
            playerList = deck.draw();
            dealerList = deck.draw();
            total = deck.countCards(playerList);
            dealerTotal =deck.countCards(dealerList);
            System.out.println("Player cards: " + playerList.toString() + " Total: " + total);
            System.out.println("Dealer cards: " + dealerList.get(0).toString() + " + 1 covered card");
            while (true) {
                if (total == 21){
                    break;
                }
                System.out.println("Hit or stand?");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("hit")){
                    playerList.add(deck.drawAnother());
                    total = deck.countCards(playerList);
                    System.out.println("Yours cards : " + playerList.toString() + " Total: " + total);
                    if(total >= 21) {
                        break;
                    }
                }else{
                    break;
                }
            }

            System.out.println("Dealer's cards: " + dealerList.toString() + " Total: " + dealerTotal);

            while (dealerTotal < 17 && total < 21) {
                dealerList.add(deck.drawAnother());
                dealerTotal = deck.countCards(dealerList);
                System.out.println("Dealers cards: " + dealerList.toString() + " Total: " + dealerTotal);
            }

            System.out.println("Dealer total: " + dealerTotal + " |||" + " Yours total was: " + total);
            if (total == 21){
                System.out.println("Blackjack! You win £"+ bet);
                money += bet;
            }
            if (total > 21){
                System.out.println("Bust!!! You lost £" + bet);
                money -= bet;
            }
            if ((total < 21 && total > dealerTotal )|| dealerTotal > 21){
                System.out.println("You win!!! ");
                money += bet;
                System.out.println("You have now £" + money);
            }
            if (total < dealerTotal && dealerTotal < 21){
                System.out.println("You lose :(");
                money -= bet;
                System.out.println("You have now £" + money + " left.");
            }
            if (dealerTotal == total) {
                System.out.println("Even. Nobody wins.");
            }
            if (money == 0) {
                System.out.println("Game over! You have no money left :(");
            }
            deck.addAllCards(playerList);
            deck.addAllCards(dealerList);
        }
    }
}
