public class LimitOptionUserException extends IllegalArgumentException {
    public LimitOptionUserException(String message) {
        super(message);
    }

    public LimitOptionUserException(double input) throws LimitOptionUserException {
        if(input<=0){
            throw new LimitOptionUserException("Number can not smaller than Zero");
        }
    }
}
