import java.util.HashMap;

public class contact {
    private String name;
    private int number;
//    private HashMap<String, Integer> list;

    public contact(String name, int number){
        this.name = name;
        this.number = number;
    }



    public HashMap<String, Integer> getList() {
        HashMap<String, Integer> list = new HashMap<String, Integer>() {
            {
                put(name, number);
            }

        };
        return list;
    }
    public static void main(String[] args) {

    }

}

