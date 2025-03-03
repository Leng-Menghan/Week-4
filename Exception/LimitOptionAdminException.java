package Exception;
public class LimitOptionAdminException extends IllegalArgumentException {
    public LimitOptionAdminException(String message) {
        super(message);
    }

    public LimitOptionAdminException(int input) throws LimitOptionAdminException {
        if(input>11 || input<1){
            throw new LimitOptionAdminException("Number must be between 1 and 4");
        }
    }
}
