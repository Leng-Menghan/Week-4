import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CompareDate {
    public boolean compareDate(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate d1 = LocalDate.parse(date1, formatter);
        LocalDate d2 = LocalDate.parse(date2, formatter);

        return d1.isBefore(d2);
    }


}
