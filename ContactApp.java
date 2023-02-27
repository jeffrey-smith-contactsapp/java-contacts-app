import java.util.HashMap;
import java.util.Scanner;

public class ContactApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("1. View contacts.\n2. Add a new contact.\n3. Search a contact by name.\n4. Delete an existing contact.\n5. Exit.\nEnter an option (1, 2, 3, 4 or 5):\n"
);
        HashMap<String, String> contacts = new HashMap<>();
//        contacts.put(get,"7028888888");
//        System.out.println(contacts);
    }
    public String formatNumber(String num){
        String fnumber = num;
        if (num.length()== 7){
            fnumber = num.replaceFirst("(\\d{3})(\\d+)", "$1-$2");
        } else if (num.length()==10){
            fnumber = num.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        } else if (num.length() > 10){
//
            fnumber ="+" + num.substring(0,num.length()-10) + " " + num.substring(num.length()-10).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        } else if (num.length() > 13){
            fnumber = "sorry this number input is incorrect";
        }
        return fnumber;
    };
//    1. View contacts.
//            2. Add a new contact.
//3. Search a contact by name.
//4. Delete an existing contact.
//            5. Exit.
//    Enter an option (1, 2, 3, 4 or 5):
//

}
