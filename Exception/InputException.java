package Exception;

public class InputException extends IllegalArgumentException {
    public InputException(String message) {
        super(message);
    }

    public InputException(double input) throws InputException {
        if(input<=0){
            throw new InputException("Number can not smaller than Zero");
        }
    }

    public InputException(String input, String formaString) throws InputException {
        if(!input.matches(formaString))
        {
            throw new InputException("Must has at least one characters");
        }
    }
}