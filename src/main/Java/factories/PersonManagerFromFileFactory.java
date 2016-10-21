package factories;

import managers.PersonManager;
import managers.PersonManagerFromFile;

public class PersonManagerFromFileFactory extends AbstractManagerFactory {
    public PersonManagerFromFileFactory() {
    }

    public PersonManager createPersonManager() {
        return new PersonManagerFromFile();
    }
}
