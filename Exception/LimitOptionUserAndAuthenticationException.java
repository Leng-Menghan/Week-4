package Exception;
public class LimitOptionUserAndAuthenticationException extends IllegalArgumentException {
    public LimitOptionUserAndAuthenticationException(String message) {
        super(message);
    }

    public LimitOptionUserAndAuthenticationException(int input) throws LimitOptionUserAndAuthenticationException {
        if(input>3 || input<1){
            throw new LimitOptionUserAndAuthenticationException("Number must be between 1 and 3");
        }
    }
}
