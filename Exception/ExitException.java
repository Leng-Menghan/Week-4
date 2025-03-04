package Exception;
public class ExitException extends IllegalArgumentException{
    public ExitException(String message) {
        super(message);
    }
    public ExitException(String input, String formaString) throws ExitException {
        if(!input.matches(formaString))
        {
            throw new ExitException("Only characters Y/y allowed");
        }
    }
}