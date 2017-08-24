package wad.valid;

import wad.domain.Person;

public class PersonValidator extends Validator {
    
    public boolean validateRegistration(Person person) {
        System.out.println(person.getPassword());
        if (person.getUsername().length() < 4 || person.getUsername().length() > 20
            || person.getPassword().length() < 1) {
            return false;
        }
        return true;
    }

}
