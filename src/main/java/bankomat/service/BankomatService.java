package bankomat.service;

import card.entity.Card;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BankomatService {

    public Card addCashInCard(Card card, long sum) {
        card.setCardBalance(card.getCardBalance() + sum);
        return card;
    }

    public Card takeCash(Card card, long sum) {
        card.setCardBalance(card.getCardBalance() - sum);
        return card;
    }

    public void saveInFile(List<Card> cardList) {
        try (FileWriter writer = new FileWriter("cards.txt", false)) {
            for(Card card: cardList){
                String text = card.getCardNumber() + " " + card.getCardBalance() + " " + card.getPassword() + " " + card.getAttempts() + " " + card.getLastAttempt() + " ";
                writer.write(text);
            }

            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}