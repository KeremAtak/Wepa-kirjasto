package wad.valid;

public class PersonValidator extends Validator {
    
    //validoi täyttävätkö käyttäjätunnus ja salasana pituuden ehdot
    public boolean validateRegistration(String username, String password) {
        if (username.length() < 4 || username.length() > 20
            || password.length() < 8 || password.length() > 20)  {
            return false;
        }
        return true;
    }

}
