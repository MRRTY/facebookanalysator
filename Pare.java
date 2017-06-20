/**
 * Created by Greg on 03.06.2017.
 */
public class Pare {
    private String param1;
    private String param2;

    public Pare(String param1, String param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    @Override
    public String toString() {
        return "Pare{" +
                "param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pare pare = (Pare) o;

        if (!param1.equals(pare.param1)) return false;
        return param2.equals(pare.param2);
    }

    @Override
    public int hashCode() {
        int result = param1.hashCode();
        result = 31 * result + param2.hashCode();
        return result;
    }
}
