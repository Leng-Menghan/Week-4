package Exception;
public class CharacterOnlyException extends IllegalArgumentException{
    public CharacterOnlyException(String message) {
        super(message);
    }
    public CharacterOnlyException(String input, String formaString) throws CharacterOnlyException {
        if(!input.matches(formaString))
        {
            throw new CharacterOnlyException("Only characters allowed");
        }
    }
}
