package Exception;
public class LimitOptionUserException extends IllegalArgumentException {
    public LimitOptionUserException(String message) {
        super(message);
    }

    public LimitOptionUserException(int input) throws LimitOptionUserException {
        if(input>3 || input<1){
            throw new LimitOptionUserException("Number must be between 1 and 3");
        }
    }
}
