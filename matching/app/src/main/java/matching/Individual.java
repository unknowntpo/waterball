package matching;

public class Individual {
    private int id;

    public int getId() {
        return id;
    }

    private Gender gender;
    private int age;
    // char[0,200]
    private String intro;
    private String habits;
    private String coor;

    public enum Gender {
        MALE, FEMALE
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + age;
        result = prime * result + ((intro == null) ? 0 : intro.hashCode());
        result = prime * result + ((habits == null) ? 0 : habits.hashCode());
        result = prime * result + ((coor == null) ? 0 : coor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Individual other = (Individual) obj;
        if (id != other.id)
            return false;
        if (gender != other.gender)
            return false;
        if (age != other.age)
            return false;
        if (intro == null) {
            if (other.intro != null)
                return false;
        } else if (!intro.equals(other.intro))
            return false;
        if (habits == null) {
            if (other.habits != null)
                return false;
        } else if (!habits.equals(other.habits))
            return false;
        if (coor == null) {
            if (other.coor != null)
                return false;
        } else if (!coor.equals(other.coor))
            return false;
        return true;
    }

    public class Coordinate {
        float x;
        float y;

        public Coordinate(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float distance(Coordinate coor) {
            return (float) Math.sqrt(Math.pow((coor.x - this.x), 2) + Math.pow((coor.y - this.y), 2));
        }
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

    public Coordinate getCoordinate() {
        String[] coorStr = this.coor.replace("(", "").replace(")", "").split(",");
        return new Coordinate(Float.parseFloat(coorStr[0]), Float.parseFloat(coorStr[1]));
    }

    @Override
    public String toString() {
        return "Individual [id=" + id + ", gender=" + gender + ", age=" + age + ", intro=" + intro + ", habits="
                + habits + ", coor=" + coor + "]";
    }
}