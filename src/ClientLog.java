import java.io.File;

public class ClientLog {

    int productLog;
    int amountLog;


    public void log(int productNum, int amount) {
        productLog +=productNum;
        amountLog +=amount;
    }

    public void exportAsCSV(File txtFile) {

    }
}