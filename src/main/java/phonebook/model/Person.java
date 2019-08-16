package phonebook.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class Person {

    private long id;
    private String name;
    private Set<String> phones;

    public Person(String name) {
        this.name = name;
    }

    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(String name, String... phones) {
        this.name = name;
        this.phones = new HashSet<>(Arrays.asList(phones));
    }

    public Person(long id, String name, String... phones) {
        this.id = id;
        this.name = name;
        this.phones = new HashSet<>(Arrays.asList(phones));
    }

    public void addPhones(String... phones) {

        if (this.phones == null) {
           this.phones = new HashSet<>();
        }

        this.phones.addAll(Arrays.asList(phones));
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public Set<String> getPhones() {
        return phones;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + phones.toString();
    }
}
