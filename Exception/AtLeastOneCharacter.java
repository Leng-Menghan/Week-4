package Exception;

public class AtLeastOneCharacter extends IllegalArgumentException {
    public AtLeastOneCharacter(String message) {
        super(message);
    }

    public AtLeastOneCharacter(String input, String formaString) throws AtLeastOneCharacter {
        if(!input.matches(formaString))
        {
            throw new AtLeastOneCharacter("Must has at least one character");
        }
    }
}