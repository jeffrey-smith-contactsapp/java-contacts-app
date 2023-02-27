import java.util.*;


public class ContactApp {
  protected static final Scanner scanner = new Scanner(System.in);
  protected static final HashMap<String, String> contacts = new HashMap<>();
    public static void main(String[] args) {
        boolean running = true;
        loadContacts();
        while (running) {
            System.out.printf("1. View contacts.\n2. Add a new contact.\n3. Search a contact by name.\n4. Delete an existing contact.\n5. Exit.\nEnter an option (1, 2, 3, 4 or 5):\n");

            int ui = scanner.nextInt();
            scanner.nextLine();
            switch (ui) {
                case 1:
//                System.out.println(ui);
                    displayContacts();
                    break;
                case 2:
//                System.out.println(ui);
                    addContacts();
                    break;
                case 3:
                    System.out.println(ui);
                    break;
                case 4:
                    System.out.println(ui);
                    break;
                case 5:
//                    System.out.println(ui);
                    running=false;
                    break;
                default:
                    System.out.println(ui);
                    break;
            }
        }
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

            fnumber ="+" + num.substring(0,num.length()-10) + " " + num.substring(num.length()-10).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        } else if (num.length() > 13){
            fnumber = "sorry this number input is incorrect";
        }
        return fnumber;
    };

    public static void displayContacts(){
        System.out.println(contacts);
    }

    public static void loadContacts(){

    }



    public static void addContacts(){
        String uName = null;
        String uNumber = null;
        boolean isNameGood = false;
        boolean isNumberGood = false;
        while (!isNameGood){
            System.out.println("Enter Name...");
            uName = scanner.nextLine();
            if(uName.length() == 0){
                System.out.println("Please enter a name!");
            }else {
                isNameGood = true;
            }
        }

        while (!isNumberGood){
            System.out.println("Enter Number...");
            uNumber = scanner.nextLine();
            if(uNumber.length() == 0){
                System.out.println("Please enter a number!");
            } else if (uNumber.length() > 13) {
                System.out.println("Number too long!");
                /**
                 * TODO:
                 * Check for numbers only
                 */
            } else {
                isNumberGood = true;
            }

        }

        contacts.put(uName,uNumber);

        System.out.println("Contact added!");



    }
    public static void saveContacts(){

    }


}
