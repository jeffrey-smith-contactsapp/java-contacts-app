package src;

public class Contact {
    private String name;
    private String number;
//    private HashMap<String, Integer> list;

    public Contact(String name, String number){
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }
}

