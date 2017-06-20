/**
 * Created by Greg on 01.06.2017.
 */
public class Action {
    private String name;
    private int year;

    public Action(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action = (Action) o;

        if (year != action.year) return false;
        return name.equals(action.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + year;
        return result;
    }
}
