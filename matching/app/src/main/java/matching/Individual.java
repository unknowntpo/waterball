package matching;

public class Individual {
    private int id;
    private Gender gender;
    private int age;
    // char[0,200]
    private String intro;
    private String habits;
    private String coor;

    public enum Gender {
        MALE, FEMALE
    }

    // Constructor
    public Individual(int id, Gender gender, int age, String intro, String habits, String coor) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.intro = intro;
        this.habits = habits;
        this.coor = coor;
    }
}