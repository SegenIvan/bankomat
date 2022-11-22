package card.entity;

import java.time.LocalDateTime;

public class Card {
    private String cardNumber;
    private Long cardBalance;
    private String password;
    private int attempts;
    private LocalDateTime lastAttempt;

    public Card(String cardNumber, Long cardBalance, String password, int attempts, LocalDateTime lastAttempt) {
        this.cardNumber = cardNumber;
        this.cardBalance = cardBalance;
        this.password = password;
        this.attempts = attempts;
        this.lastAttempt = lastAttempt;
    }

    public Long getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(Long cardBalance) {
        this.cardBalance = cardBalance;
    }

    public String getPassword() {
        return password;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public LocalDateTime getLastAttempt() {
        return lastAttempt;
    }

    public void setLastAttempt(LocalDateTime lastAttempt) {
        this.lastAttempt = lastAttempt;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}