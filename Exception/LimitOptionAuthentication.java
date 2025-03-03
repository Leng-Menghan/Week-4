package Exception;
public class LimitOptionAuthentication extends IllegalArgumentException {
    public LimitOptionAuthentication(String message) {
        super(message);
    }

    public LimitOptionAuthentication(int input) throws LimitOptionAuthentication {
        if(input>2 || input<1){
            throw new LimitOptionAuthentication("Number must be between 1 and 2");
        }
    }
}
