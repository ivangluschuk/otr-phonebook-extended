package phonebook.repository;

import phonebook.model.Person;

import java.sql.*;
import java.util.ArrayList;

final class H2DBManager {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";

    private static final String USER = "sa";
    private static final String PASS = "";

    H2DBManager() {
        setUp();
    }

    private void setUp() {
        String dropTablePerson = "DROP TABLE IF EXISTS person;";
        String dropTablePhones = "DROP TABLE IF EXISTS phones;";
        String createTablePerson = "CREATE TABLE person" +
                        "(person_id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(255));";
        String createTablePhones = "CREATE TABLE phones " +
                    "(phone_id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "phone VARCHAR(255), " +
                    "person_id INTEGER, " +
                    "FOREIGN KEY (person_id) REFERENCES person(person_id));";

        executeUpdate(dropTablePerson);
        executeUpdate(dropTablePhones);
        executeUpdate(createTablePerson);
        executeUpdate(createTablePhones);
    }

    void executeUpdate(final String query) {

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {

            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {}

            try {
                if(conn!=null) conn.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    ArrayList<Person> executeSelectPerson(final String query) {

        ArrayList<Person> persons = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                long id = rs.getLong("person_id");
                String name = rs.getString("name");

                persons.add(new Person(id, name));
            }

            stmt.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {

            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {}

            try {
                if(conn!=null) conn.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return persons;
    }

    ArrayList<String> executeSelectPhones(final String query) {

        ArrayList<String> phones = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                String phone = rs.getString("phone");
                phones.add(phone);
            }

            stmt.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {

            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {}

            try {
                if(conn!=null) conn.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return phones;
    }
}