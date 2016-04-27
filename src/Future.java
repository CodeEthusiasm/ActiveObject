/**
 * Created by lstrzalk on 27.04.16.
 */
public class Future {
    private Integer value;
    public boolean isAvailable(){return value!=null;}

    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}