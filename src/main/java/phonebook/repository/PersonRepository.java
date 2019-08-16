package phonebook.repository;

import phonebook.model.Person;

import java.util.ArrayList;

public class PersonRepository {
    private H2DBManager h2DBManager = new H2DBManager();

    public void addPerson(Person person) {

        StringBuilder sb = new StringBuilder();

        if (person.getPhones() != null) {
            sb.append("SELECT @personID := SCOPE_IDENTITY();");
            for (String phone : person.getPhones()) {
                sb.append("INSERT INTO phones (phone, person_id) values ('").append(phone).append("', @personID); ");
            }
        }

        String query = "BEGIN TRANSACTION; " +
                "INSERT INTO person (name) VALUES ('" + person.getName() + "');" +
                sb.toString() +
                "COMMIT;";

        h2DBManager.executeUpdate(query);
    }

    public ArrayList<Person> findPersonByName(String name) {
        String query = "SELECT * FROM person WHERE name LIKE " + "'%" + name + "%';";

        return h2DBManager.executeSelectPerson(query);
    }

    public ArrayList<Person> findPhonesByPersonName(String name) {
        ArrayList<Person> persons = findPersonByName(name);

        for (Person person : persons) {
            String query = "SELECT * FROM phones WHERE person_id = " + person.getId() + ";";
            person.addPhones(h2DBManager.executeSelectPhones(query).toArray(new String[0]));
        }

        return persons;
    }

    public void addPhone(Person person, String phone) {

    }
}
