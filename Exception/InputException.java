package Exception;
public class InputException extends IllegalArgumentException{
    public InputException(String message) {
        super(message);
    }
    public InputException(String input, String formaString) throws InputException {
        if(!input.matches(formaString))
        {
            throw new InputException("Characters only");
        }
    }
}