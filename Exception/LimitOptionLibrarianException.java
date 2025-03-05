package Exception;
public class LimitOptionLibrarianException extends IllegalArgumentException {
    public LimitOptionLibrarianException(String message) {
        super(message);
    }

    public LimitOptionLibrarianException(int input) throws LimitOptionLibrarianException {
        if(input>8 || input<1){
            throw new LimitOptionLibrarianException("Number must be between 1 and 8");
        }
    }
}