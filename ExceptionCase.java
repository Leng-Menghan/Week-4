import java.util.InputMismatchException;

public class ExceptionCase extends InputMismatchException {
    public ExceptionCase(String msg) {
        super(msg);
    }
    public static void isValidBookQty(int qty){
        if (qty <= 0) {
            throw new ExceptionCase("Quantity should be greater than zero");
        }
    }
}
