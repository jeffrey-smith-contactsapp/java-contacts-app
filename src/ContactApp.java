package src;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class ContactApp implements Colors {
    private static final Scanner scanner = new Scanner(System.in);
    protected static final List<Contact> contacts = new ArrayList<>();
    private static final Path filePath = Paths.get("src", "contacts.txt");
    public static void main(String[] args) {
        validateFile();
        loadContacts();
        runApp();
    }

    public static void runApp(){
        boolean running = true;

        while (running) {
            System.out.printf("%s1. View contacts.\n2. Add a new contact.\n3. Search a contact by name.\n4. Delete an existing contact.\n5. Exit.%s\nEnter an option (1, 2, 3, 4 or 5):\n",WHITE_BRIGHT,RESET);

            int ui = scanner.nextInt();
            scanner.nextLine();
            switch (ui) {
                case 1 -> displayContacts();
                case 2 -> addContacts();
                case 3 -> searchContact();
                case 4 -> deleteContact();
                case 5 -> running = false;
                default -> System.out.println("Please enter a valid menu option");
            }
            if (running) {
                System.out.printf("%scontinue...? (%sy%s/%sn%s)%s%n",WHITE_BRIGHT,GREEN_BRIGHT,WHITE_BRIGHT,RED_BRIGHT,WHITE_BRIGHT,RESET);
                if (scanner.nextLine().equalsIgnoreCase("n")) {
                    running = false;
                }
            }
            if (!running) System.out.printf("%s%sHave a nice day! %s%n",ITALICS,CYAN_BOLD_BRIGHT,SHRIMP);
        }
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

    public static void deleteContact(){
        Contact toRemove = null;
        System.out.printf("%sEnter name of contact to delete%s%n",RED_BRIGHT,RESET);
        String isName = scanner.nextLine();
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(isName)){
             toRemove = c;
            }
        }
        if (toRemove == null){
            System.out.println("invalid name");
        } else {
            contacts.remove(toRemove);
            System.out.println("contact deleted");
            saveContacts();
        }
    }

    public static void searchContact(){
        boolean isSuccessful = false;
        System.out.println("Enter name of contact to search");
        String theName = scanner.nextLine();
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(theName)){
                System.out.printf("%s%s%s - %s%s%s%n",PURPLE_BRIGHT,c.getName(),WHITE_BRIGHT,PURPLE_BRIGHT,  formatNumber(c.getNumber()),RESET);
                isSuccessful = true;
            }
        }
        if (!isSuccessful) System.out.println("Invalid name");
    }

    public static void displayContacts() {
        if(contacts.size() >= 1)contactFormatter();
        else System.out.println("No contacts found...");
    }

    private static void validateFile() {
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void loadContacts() {
        contacts.clear();
        List<String> newList = new ArrayList<>();
        try {
            newList = Files.readAllLines(filePath);
        } catch (IOException iox) {
            iox.printStackTrace();
        }
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
    public static boolean validatePhoneNumber(String str)
    {
        if(str.length() < 7 || str.length() > 13)
            return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }
    public static boolean validateName(String str)
    {
        if (str.length() == 0 || str.length() > 20)
            return false;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '◈')
                return false;
        }
        return true;
    }
    public static void addContacts() {
        String outName = null;
        String outNumber = null;
        while (outName == null) {
            System.out.println("Enter Name...");
            String uName = scanner.nextLine();
            if (!validateName(uName)) {
                System.out.println("Please enter a valid name");
            } else {
                outName = uName;
            }
        }
        while (outNumber == null){
            System.out.println("Enter Number...");
            String uNumber = scanner.nextLine();
            if (!validatePhoneNumber(uNumber)) {
                System.out.println("Please enter a valid phone number");
            }else{
                outNumber = uNumber;
            }
        }
        boolean passable = true;
        for(Contact c : contacts){
            if(c.getName().equalsIgnoreCase(outName)){
                passable = false;
                System.out.println("Name already exists in contacts! please try again!");
            }
            if(c.getNumber().equals(outNumber)){
                passable = false;
                System.out.println("Number already exists in contacts! please try again!");
            }
        }
        if(passable) {
            Contact c = new Contact(outName, outNumber);
            System.out.printf("%sContact for %s added!%s%n", GREEN_BRIGHT, outName, RESET);
            contacts.add(c);
            saveContacts();
        }
    }

    public static void contactFormatter() {
        System.out.println("┌───────────────────┬───────────────────┐");
        System.out.printf("│%-19s│%-19s│%n", "Name","Phone Number");
        System.out.println("┝━━━━━━━━━━━━━━━━━━━┿━━━━━━━━━━━━━━━━━━━┥");
        for (Contact c : contacts) {
            System.out.printf("│%s%-19s%s│%s%-19s%s│%n", YELLOW,c.getName(),RESET,YELLOW,formatNumber(c.getNumber()),RESET);
        }
        System.out.println("└───────────────────┴───────────────────┘");
    }

}
