package com.oops;

// Node for Linked List
class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// Simple Queue implementation using Linked List
class Queue<T> {
    Node<T> front, rear;

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public T dequeue() {
        if (front == null) return null;
        T data = front.data;
        front = front.next;
        if (front == null) rear = null;
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void display() {
        Node<T> temp = front;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public Node<T> getFront() {
        return front;
    }
}

// Player class with card queue
class Player {
    String name;
    Queue<String> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new Queue<>();
    }

    public void addCard(String card) {
        cards.enqueue(card);
    }

    public void sortCards() {
        // Convert queue to array, sort, re-add to queue
        String[] cardArray = new String[9];
        Node<String> current = cards.getFront();
        int i = 0;
        while (current != null && i < 9) {
            cardArray[i++] = current.data;
            current = current.next;
        }

        // Simple bubble sort based on RANK
        for (int j = 0; j < cardArray.length - 1; j++) {
            for (int k = j + 1; k < cardArray.length; k++) {
                if (DeckOfCardsWithQueue.getCardRank(cardArray[j]) > DeckOfCardsWithQueue.getCardRank(cardArray[k])) {
                    String temp = cardArray[j];
                    cardArray[j] = cardArray[k];
                    cardArray[k] = temp;
                }
            }
        }

        cards = new Queue<>();
        for (String card : cardArray) {
            cards.enqueue(card);
        }
    }

    public void showCards() {
        System.out.println(name + "'s cards:");
        cards.display();
        System.out.println();
    }
}

public class DeckOfCardsWithQueue {
    static String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
    static String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };

    public static void main(String[] args) {
        // Step 1: Create 52-card deck
        String[] deck = new String[52];
        int index = 0;
        for (String suit : suits) {
            for (String rank : ranks) {
                deck[index++] = rank + " of " + suit;
            }
        }

        // Step 2: Shuffle
        shuffleDeck(deck);

        // Step 3: Create Player Queue
        Queue<Player> players = new Queue<>();
        for (int i = 1; i <= 4; i++) {
            players.enqueue(new Player("Player " + i));
        }

        // Step 4: Distribute 9 cards to each player
        Node<Player> playerNode = players.getFront();
        index = 0;
        while (playerNode != null) {
            for (int j = 0; j < 9; j++) {
                playerNode.data.addCard(deck[index++]);
            }
            playerNode = playerNode.next;
        }

        // Step 5: Sort cards of each player and print
        playerNode = players.getFront();
        while (playerNode != null) {
            playerNode.data.sortCards();
            playerNode.data.showCards();
            playerNode = playerNode.next;
        }
    }

    // Utility: Shuffle deck
    public static void shuffleDeck(String[] deck) {
        for (int i = 0; i < deck.length; i++) {
            int r = (int) (Math.random() * deck.length);
            String temp = deck[i];
            deck[i] = deck[r];
            deck[r] = temp;
        }
    }

    // Utility: Get rank index
    public static int getCardRank(String card) {
        String rank = card.split(" ")[0];  // Split "Jack of Hearts"
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i].equals(rank)) return i;
        }
        return -1;
    }
}
