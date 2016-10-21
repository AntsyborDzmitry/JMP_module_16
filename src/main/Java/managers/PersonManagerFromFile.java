package managers;

import beans.Person;

import java.io.*;

public class PersonManagerFromFile implements PersonManager {
    private final String FILE_PATH = "src\\main\\java\\resources\\CSVFile\\person.csv";
    private static final String COMMA_DELIMITER = ",";
     private static final String NEW_LINE_SEPARATOR = "\n";


    public PersonManagerFromFile() {
    }

    public void writePerson(Person person) {

File f = new File(FILE_PATH);
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true)){
            fileWriter.append(person.getName());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(String.valueOf(person.getAge()));
            fileWriter.append(NEW_LINE_SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Person readPerson() {
        Person p = new Person();
        String line = "";
        String cvsSplitBy = ",";
        File csvFile = new File(FILE_PATH);
        if (csvFile.exists()) {

            try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

                while ((line = br.readLine()) != null) {

                    String[] person = line.split(cvsSplitBy);
                    p.setName(person[0]);
                    p.setAge(Integer.parseInt( person[1]));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return p;
    }

    public Person readPerson(String name) {
        Person p = new Person();
        String line = "";
        String cvsSplitBy = ",";
        File csvFile = new File(FILE_PATH);
        if (csvFile.exists()) {

           try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

               while ((line = br.readLine()) != null) {

                   String[] person = line.split(cvsSplitBy);

                   if (person[0].equals(name)){
                       p.setName(person[0]);
                       p.setAge(Integer.parseInt( person[1]));
                       return p;
                   }
               }
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
        }
        return p;
    }
}
