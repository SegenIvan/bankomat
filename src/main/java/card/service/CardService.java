package card.service;

import card.entity.Card;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CardService {
    public List<Card> getCardsFromFile(){
        File file = new File("cards.txt");
        List<Card> cards = new ArrayList<>();
        String cardNumber;
        long cardBalance;
        String cardPassword;
        int attempts;
        LocalDateTime lastAttempt;
        String[] cardInfo;
        try(FileReader reader = new FileReader(file))
        {
            BufferedReader bufferedReader = new BufferedReader(reader);
            cardInfo = bufferedReader.readLine().split(" ");
            for (int i = 0; i < cardInfo.length;  i++){
                cardNumber = cardInfo[i];
                i++;
                cardBalance = Long.parseLong(cardInfo[i]);
                i++;
                cardPassword = cardInfo[i];
                i++;
                attempts = Integer.parseInt(cardInfo[i]);
                i++;
                lastAttempt = LocalDateTime.parse(cardInfo[i]);
                cards.add(new Card(cardNumber, cardBalance, cardPassword, attempts, lastAttempt));
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("загрузка карт закончена");
        return cards;
    }
}
