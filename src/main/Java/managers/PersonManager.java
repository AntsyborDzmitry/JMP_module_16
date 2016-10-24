package managers;

import beans.Person;

public interface PersonManager {
    void writePerson (Person person);
    Person readLastPerson();
    Person readPerson (String name);

}
