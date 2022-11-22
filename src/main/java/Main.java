import bankomat.entity.Bankomat;
import bankomat.service.BankomatService;
import card.service.CardService;


public class Main {
    public static void main(String[] args) {
        Ui ui = new Ui(new CardService(), new BankomatService(), new Bankomat());
        ui.start();
    }
}

