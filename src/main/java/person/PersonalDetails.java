package person;

public class PersonalDetails {
    private final String firstname;
    private String lastName;

    PersonalDetails(String firstName, String lastname){
        this.firstname = firstName;
        this.lastName = lastname;
    }

    PersonalDetails(String firstName){
        this.firstname = firstName;
    }
}
