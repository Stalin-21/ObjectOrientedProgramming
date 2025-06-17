package com.oops;

import java.util.Random;

public class DeckOfCards {
    // Suits and Ranks arrays
    static String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
    static String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };

    public static void main(String[] args) {
        // 1. Create Deck of 52 cards
        String[] deck = new String[52];
        int index = 0;
        for (String suit : suits) {
            for (String rank : ranks) {
                deck[index++] = rank + " of " + suit;
            }
        }

        // 2. Shuffle the deck using Random
        shuffleDeck(deck);

        // 3. Distribute 9 cards to 4 players using 2D array
        String[][] players = new String[4][9];
        int cardIndex = 0;

        for (int i = 0; i < 4; i++) {       // 4 players
            for (int j = 0; j < 9; j++) {   // 9 cards each
                players[i][j] = deck[cardIndex++];
            }
        }

        // 4. Print the cards of each player
        for (int i = 0; i < 4; i++) {
            System.out.println("Player " + (i + 1) + " cards:");
            for (int j = 0; j < 9; j++) {
                System.out.println("  " + players[i][j]);
            }
            System.out.println();
        }
    }

    // Shuffle method
    public static void shuffleDeck(String[] deck) {
        Random rand = new Random();
        for (int i = 0; i < deck.length; i++) {
            int randomIndex = rand.nextInt(deck.length);
            // Swap cards
            String temp = deck[i];
            deck[i] = deck[randomIndex];
            deck[randomIndex] = temp;
        }
    }
}
