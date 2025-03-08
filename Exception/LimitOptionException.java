package Exception;
public class LimitOptionException extends IllegalArgumentException {
    public LimitOptionException(String message) {
        super(message);
    }

    public LimitOptionException(int input, int max, int min) throws LimitOptionException {
        if(input>max || input<min){
            throw new LimitOptionException("Number must be between " + min + " and " + max + ".");
        }
    }
}
