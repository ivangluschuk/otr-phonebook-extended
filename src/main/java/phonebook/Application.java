package phonebook;

import phonebook.model.Person;
import phonebook.repository.PersonRepository;

public class Application {
    public static void main(String[] arg) {
        PersonRepository personRepository = new PersonRepository();
        personRepository.addPerson(new Person("IVAN", "2523423425"));
        personRepository.addPerson(new Person("IVNAN", "3414124", "21352134"));
        personRepository.addPerson(new Person("IVNAN"));
        personRepository.findPersonByName("IV");
        var a = personRepository.findPhonesByPersonName("IV");
        System.out.println(a);
    }
}
