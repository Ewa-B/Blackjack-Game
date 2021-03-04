package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;

    public Deck(){

        this.cards = new ArrayList<>();
    }
    public void createDeck(){
        for(Suit s : Suit.values()){
            for(Rank r : Rank.values()){
                cards.add(new Card(s,r));
            }
        }
    }
    //Shuffle 52 cards in the random position
    public void shuffle(){
        ArrayList<Card> shuffled = new ArrayList<>();
        Random random = new Random();
        int bound = 52;
        for (int i = 0; i < 52; i++){
            int x = random.nextInt(bound);
            shuffled.add(cards.get(x));
            this.cards.remove(x);
            bound--;
        }
        this.cards = shuffled;
    }
    public int countCards(ArrayList<Card> arr){
        int sum = 0;
        for (Card c : arr){
            String s = c.getRank().toString();
            switch (s) {
                case "TWO" -> sum += 2;
                case "THREE" -> sum += 3;
                case "FOUR" -> sum += 4;
                case "FIVE" -> sum += 5;
                case "SIX" -> sum += 6;
                case "SEVEN" -> sum += 7;
                case "EIGHT" -> sum += 8;
                case "NINE" -> sum += 9;
                case "TEN", "JACK", "QUEEN", "KING" -> sum += 10;
                case "AS" -> {
                    if (sum > 11) {
                        sum += 1;
                        break;
                    }
                    sum += 11;
                }
            }
        }
        return sum;

    }
    // First draw from the end of the deck
    public ArrayList<Card> draw(){
        ArrayList<Card> list = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            Card card = cards.get(cards.size()-1);
            list.add(i, card);
            cards.remove(card);
        }
        return list;

    }
    // Another hit draw
    public Card drawAnother(){
        Card card = cards.get(cards.size()-1);
        cards.remove(card);
        return card;
    }
    // Adds cards in play back to beginning of the deck
    public void addAllCards(ArrayList <Card> list){
        while (list.size() > 0){
            this.cards.add(0, list.get(0));
            list.remove(0);
        }
    }

}
