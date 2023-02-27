package src;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;


public class ContactApp {

    /**
     * TODO:
     * add checks to user input ;; disallow diamonds
     */
    protected static final Scanner scanner = new Scanner(System.in);
    //  protected static final HashMap<String, String> contacts = new HashMap<>();
    protected static final List<Contact> contacts = new ArrayList<>();


    private static final String fileName = "contacts.txt";
    private static final Path filePath = Paths.get("src", fileName);

    public static void main(String[] args) {
        validateFile();
        loadContacts();
        boolean running = true;

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
                    searchContact();
                    break;
                case 4:
                    System.out.println(ui);
                    break;
                case 5:
//                    System.out.println(ui);
                    running = false;
                    break;
                default:
                    System.out.println("System Error");
                    break;
            }
            if (running) {
                System.out.println("continue...? (y/n)");
                if (scanner.nextLine().equalsIgnoreCase("n")) {
                    running = false;
                }
            }
            if (!running) System.out.println("Have a nice day");
        }


//        contacts.put(get,"7028888888");
//        System.out.println(contacts);
    }

    public  static String formatNumber(String num) {
        String fnumber = num;
        if (num.length() == 7) {
            fnumber = num.replaceFirst("(\\d{3})(\\d+)", "$1-$2");
        } else if (num.length() == 10) {
            fnumber = num.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        } else if (num.length() > 10) {

            fnumber = "+" + num.substring(0, num.length() - 10) + " " + num.substring(num.length() - 10).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        }
        return fnumber;
    }

    public static void searchContact(){
        boolean isSuccessful = false;
        System.out.println("Enter name of contact");
        String theName = scanner.nextLine();
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(theName)){
                System.out.println(c.getName() + " " + formatNumber(c.getNumber()));
                isSuccessful = true;
            }
        }
        if (!isSuccessful) System.out.println("Invalid name");

    };

    public static void displayContacts() {
        contactFormatter();
    }

    private static void validateFile() {
//        System.out.println("ddd");
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void loadContacts() {
//        System.out.println();
        contacts.clear();
        List<String> newList = new ArrayList<>();
        try {
            newList = Files.readAllLines(filePath);
        } catch (IOException iox) {
            iox.printStackTrace();
        }
//        System.out.println(newList);
        if (newList.size() >= 1) {
            for (String s : newList) {
                String[] arr = s.split("◈");
                Contact c = new Contact(arr[0], arr[1]);
                contacts.add(c);
            }
        }
    }


    public static void saveContacts() {
        List<String> contactString = new ArrayList<>();
        for (Contact c : contacts) {
            String s = c.getName() + "◈" + c.getNumber();
            contactString.add(s);
        }
        try {
            Files.write(filePath, contactString);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addContacts() {
        System.out.println("Enter Name...");
        String uName = scanner.nextLine();
        System.out.println("Enter Number...");
        String uNumber = scanner.nextLine();
        if (uName.length() > 0 && uNumber.length() > 6 && uNumber.length() <= 14){
            Contact c = new Contact(uName, uNumber);
            contacts.add(c);

            saveContacts();
        } else {
            System.out.println("Please enter a valid input");
        }


    }

    public static void contactFormatter() {
        System.out.println("------------------------------------");
        System.out.printf("|%-15s|%-18s|%n", "Name","Phone Number");
        System.out.println("------------------------------------");
        for (Contact c : contacts) {
            System.out.printf("|%-15s|%-18s|%n", c.getName(),formatNumber(c.getNumber()));
        }
        ;
        System.out.println("------------------------------------");


    }

}
