package managers;

import beans.Person;

public interface PersonManager {
    void writePerson (Person person);
    Person readPerson();
    Person readPerson (String name);

}
