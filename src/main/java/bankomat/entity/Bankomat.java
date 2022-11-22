package bankomat.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Bankomat {
    private Long cashInBankomat;

    public Bankomat() {
       setCash();
    }

    public Long getLimOfCashInBankomat() {
        return cashInBankomat;
    }

    public Long getMaxCashAdd() {
        return 1000000L;
    }

    private void setCash(){
        System.out.println("Внесение денег в банокмат");
        File file = new File("cashInBankomat.txt");
        this.cashInBankomat = 0L;
        try(FileReader reader = new FileReader(file))
        {
            BufferedReader bufferedReader = new BufferedReader(reader);
            this.cashInBankomat = Long.parseLong(bufferedReader.readLine());
        }
        catch(IOException ex){
            System.out.println("чет пошло не так" + ex.getMessage());
        }
        System.out.println("Внесение денег в банокмат закончено");
    }
}
