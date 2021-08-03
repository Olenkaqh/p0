import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Helper {

    private static FileWriter file;
    Logger log = LoggerFactory.getLogger(MortgageCalculator.class);

    public void addToRecord(Loan loan){
        Gson gson = new Gson();

        //converts java objects to JSON strings
        String json = gson.toJson(loan);

        //writes string into record.txt
        try {
            file = new FileWriter("/Users/olenka/Desktop/p0/Record.txt", true);
            file.write('\n');
            file.write(json); // writes json to file
            log.info("String has been successfully copied to file...");
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
