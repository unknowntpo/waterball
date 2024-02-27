package matching;

public class Individual {
    private int id;
    private Gender gender;
    private int age;
    // char[0,200]
    private String intro;
    private String habits;
    private String Coor;

    private enum Gender {
        MALE, FEMAIL
    }
}