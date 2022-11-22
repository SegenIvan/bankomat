import bankomat.entity.Bankomat;
import bankomat.service.BankomatService;
import card.entity.Card;
import card.service.CardService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private final CardService cardService;
    private final BankomatService bankomatService;
    private final Bankomat bankomat;

    public Ui(CardService cardService, BankomatService bankomatService, Bankomat bankomat) {
        this.cardService = cardService;
        this.bankomatService = bankomatService;
        this.bankomat = bankomat;
    }

    public void start() {
        List<Card> cards = cardService.getCardsFromFile();
        do {
            Card activeCard = null;
            Scanner inputCardScanner = new Scanner(System.in);
            System.out.println("Введите номер карты");
            String cardNumber = inputCardScanner.nextLine();

            for (Card card : cards) {
                if (card.getCardNumber().equals(cardNumber)) {
                    activeCard = card;
                }
            }

            if (activeCard == null) {
                System.err.println("Неправильно введен номер карты!");
                start();
            }

            if (!activeCard.getLastAttempt().plusHours(24).isAfter(LocalDateTime.now())) {
                activeCard.setAttempts(0);
                bankomatService.saveInFile(cards);
            }

            if (activeCard.getAttempts() >= 3) {
                System.out.println("Карта заблокирована, попробуйте через 24 часа!");
                start();
            }

            System.out.println("Введите пинкод");
            String pinCode = inputCardScanner.nextLine();

            if (!activeCard.getPassword().equals(pinCode)) {
                System.err.println("Неправильно введен пинкод!");
                activeCard.setAttempts(activeCard.getAttempts() + 1);
                activeCard.setLastAttempt(LocalDateTime.now());
                bankomatService.saveInFile(cards);
                start();
            } else {
                while (true) {
                    try {
                        activeCard.setAttempts(0);
                        System.out.println("Выберите действие: 1 - пополнить счет, 2 - снять деньги, 3 - проерить баланс, 0 - выход");
                        int code = Integer.parseInt(inputCardScanner.nextLine());
                        if (code == 0) {
                            bankomatService.saveInFile(cards);
                            break;
                        } else if (code == 1) {
                            System.out.println("Введите сумму");
                            long sum = Long.parseLong(inputCardScanner.nextLine());
                            if (sum > bankomat.getMaxCashAdd()) {
                                throw new Exception("Сумма пополнения превышает лимит");
                            }
                            activeCard = bankomatService.addCashInCard(activeCard, sum);
                        } else if (code == 2) {
                            System.out.println("Введите сумму");
                            long sum = Long.parseLong(inputCardScanner.nextLine());
                            if (sum > activeCard.getCardBalance()) {
                                throw new Exception("Недостаточно средств");
                            }
                            if (sum > bankomat.getLimOfCashInBankomat()) {
                                throw new Exception("Недостаточно средств в банкомате");
                            }
                            activeCard = bankomatService.takeCash(activeCard, sum);
                        } else if (code == 3) {
                            System.out.println("Баланс: " + activeCard.getCardBalance());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Неизвестная операция");
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        } while (true);
    }
}
