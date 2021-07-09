// import java.util.ArrayList;
// import java.util.Map;
// import java.util.HashMap;
// import java.util.LinkedHashMap;
// import java.util.InputMismatchException;
// import java.util.Scanner;
import java.util.*;

class UserInfo {
    private String name;
    private int pin;

    public UserInfo() {
        this.name = "Jane";
        this.pin = 1234;
    }
    
    public String getName(){
        return name;
    }
    
    public int getPin(){
        return pin;
    }


    //note - delay method
    // console output was showing too fast. I wanted to give time to read.
    void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }



    //note - after login setup
    public int choice; // when choosing from things you can do
    public int num; // when going back with 0
    public int del; // when deleting from list / removing from hold
    public int hld; // when holding from list
    public String deletedBook; // placeholder for a deleted book title
    public int count; // when iterating search result (found (LinkedHashMap))
    public int list; // when adding to my list

    // creating initial book list
    void initiate() {
        fullListInit();
        borrowingInit();
        holdInit();
        mylistInit();
    }



    //note - login
    void login(String name, int pin) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Username:");
        String username = sc.nextLine();
        System.out.println("PIN:");
        int userpin = sc.nextInt();

        delay();
        System.out.println("-----------------------");

        if(!(name.equals(username)) && pin != userpin) {
            System.out.println("Wrong username and PIN.");
        } else if (!(name.equals(username))) {
            System.out.println("Wrong username.");
        } else if (pin != userpin) {
            System.out.println("Wrong PIN.");
        } else {
            System.out.println("Welcome, " + username + "!");
            System.out.println("\n");
            initiate();
            myPage();
        }
        sc.close();
    }




    //note - mypage
    void myPage() {
        Scanner sc = new Scanner(System.in);

        System.out.println("+++++++ My Page +++++++");
        System.out.println("[0] : Search");
        System.out.println("[1] : View borrowing");
        System.out.println("[2] : On Hold");
        System.out.println("[3] : My List");
        System.out.println("[4] : Log out");

        System.out.println("\n");
        System.out.println("-----------------------");
        System.out.println("What would you like to do?");
    
        try { 
            choice = sc.nextInt();
            selectOnMyPage(choice); // passing choice(int) to switch
        } catch (InputMismatchException ex) {// if input is string
            delay();
            System.out.println("\n");
            System.out.println("***!! Invalid input. Please select again. ***");
            System.out.println("\n");
            myPage();
        }

        sc.close();
    }

    void selectOnMyPage(int choice) {
        delay();
        System.out.println("\n");
        switch(choice) {
            case 0:
                search();
                break;
            case 1:
                borrowing();
                break;
            case 2:
                onHold();
                break;
            case 3:
                myList();
                break;
            case 4:
                logout();
                break;
            default: // if choice was not 0-4
                System.out.println("***!! Invalid input. Please select again. ***");
                System.out.println("\n");
                myPage();
                break;
        }
    }





    //note - search
    HashMap<String, String> fullList = new HashMap<>();
    HashMap<String, String> found = new LinkedHashMap<>(); // used LinkedHashMap because it needed to be able to take out by number(from first)
    void fullListInit() {
        fullList.put("Charlie and the Chocolate Factory", "Roald Dahl");
        fullList.put("The Body", "Stephen King");
        fullList.put("Looking for Alaska", "John Green");
        fullList.put("The Witches", "Roald Dahl");
    }

    void search() {
        Scanner sc = new Scanner(System.in);

        System.out.println("------- Search -------");
        System.out.println("[0] : Search by title");
        System.out.println("[1] : Search by author");
        System.out.println("[2] : Go back to my page");
        System.out.println("[99] : View full list");
        
        System.out.println("\n");
        System.out.println("-----------------------");
        System.out.println("What would you like to do?");

        try {
            choice = sc.nextInt();
            selectOnSearch(choice);
        } catch (InputMismatchException ex) {
            delay();
            System.out.println("\n");
            System.out.println("***!! Invalid input. Please select again. ***");
            System.out.println("\n");
            search();
        }
        sc.close();
    }

    void selectOnSearch(int choice) { // whether you want to search by title / by author / go back / look cheat sheet
        //* - search menu
        Scanner sc = new Scanner(System.in);

        delay();
        System.out.println("\n");

        switch(choice) {            
            //* - search by title
            case 0:
                System.out.println("------- search by title -------");
                System.out.println("Please type in keywords.");

                String title = sc.nextLine().toLowerCase();
        
                for (Map.Entry<String, String> entry : fullList.entrySet()) {
                    if (entry.getKey().toLowerCase().matches(".*"+ title + ".*")) {
                        found.put(entry.getKey(), entry.getValue());
                    }
                }
                
                delay();

                System.out.println("\n");
                System.out.println("-----------------------");

                if(found.size() == 0) {
                    System.out.println("Nothing is found.");
                    System.out.println("\n");
                    System.out.println("Type [0] to go back (without brackets).");

                    while(true) {
                        try {
                            num = sc.nextInt();
                            if(num == 0) {
                                System.out.println("\n");
                                search();
                                break;
                            } else {
                                throw new InputMismatchException();                        
                            }
                        } catch (InputMismatchException ex) {
                            delay();
                            System.out.println("\n");
                            System.out.println("***!! Invalid input. ***");
                            System.out.println("Type [0] to go back (without brackets).");
                            sc.nextLine();
                        }
                    }
                } else if(found.size() == 1) {
                    System.out.println("1 book found.");
                    for (Map.Entry<String, String> entry : found.entrySet()) {
                        System.out.println("-" + count + "- : " + entry.getKey() + "\n\tauthor: " + entry.getValue());
                        count++;
                    }
                    afterSearchChoice();
                    break;
                } else {
                    System.out.println(found.size() + " books found.");
                    for (Map.Entry<String, String> entry : found.entrySet()) {
                        System.out.println("-" + count + "- : " + entry.getKey() + "\n\tauthor: " + entry.getValue());
                        count++;
                    }
                    afterSearchChoice();
                    break;
                }                

                break;
            

            //* - search by author
            case 1:
                System.out.println("------- search by author -------");
                System.out.println("Please type in name.");

                String author = sc.nextLine().toLowerCase();
        
                for (Map.Entry<String, String> entry : fullList.entrySet()) {
                    if (entry.getValue().toLowerCase().matches(".*"+ author + ".*")) {
                        found.put(entry.getKey(), entry.getValue());
                    }
                }

                delay();
                System.out.println("\n");
                System.out.println("-----------------------");
                
                if(found.size() == 0) {
                    System.out.println("Nothing is found.");
                    System.out.println("\n");
                    System.out.println("Type [0] to go back (without brackets).");

                    while (true) {
                        try {
                            num = sc.nextInt();
                            if(num == 0) {
                                System.out.println("\n");
                                search();
                                break;
                            } else {
                                throw new InputMismatchException();
                            }
                        } catch (InputMismatchException ex) {
                            delay();
                            System.out.println("\n");
                            System.out.println("***!! Invalid input. ***");
                            System.out.println("Type [0] to go back (without brackets).");
                            sc.nextLine();
                        }
                    }
                } else if(found.size() == 1){
                    System.out.println("1 book found.");
                    for (Map.Entry<String, String> entry : found.entrySet()) {
                        System.out.println("-" + count + "- : " + entry.getKey() + "\n\tauthor: " + entry.getValue());
                        count++;
                    }
                    afterSearchChoice();
                    break;
                } else {
                    System.out.println(found.size() + " books found.");
                    for (Map.Entry<String, String> entry : found.entrySet()) {
                        System.out.println("-" + count + "- : " + entry.getKey() + "\n\tauthor: " + entry.getValue());
                        count++;
                    }
                    afterSearchChoice();
                    break;
                }

                break;


            //* - main page
            case 2:
                System.out.println("\n");
                myPage();
                break;


            //* - cheat list
            case 99:
                System.out.println("------- full list -------");
                for(Map.Entry<String, String> entry : fullList.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    System.out.println(key + "\n\tauthor: " + value);
                }
                
                System.out.println("\n");
                System.out.println("Type [0] to go back (without brackets).");

                while (true) {
                    try {
                        num = sc.nextInt();
                        if(num == 0) {
                            System.out.println("\n");
                            search();
                            break;
                        } else {
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException ex) {
                        delay();
                        System.out.println("\n");
                        System.out.println("***!! Invalid input. ***");
                        System.out.println("Type [0] to go back (without brackets).");
                        sc.nextLine();
                    }
                }
                break;



            default:
                System.out.println("***!! Invalid input. Please select again. ***");
                System.out.println("\n");
                search();
                break;
        }
        sc.close();
    }

    void afterSearchChoice() { // showing selection after search is done
        Scanner sc = new Scanner(System.in);
        count = 0;
                
        System.out.println("\n");
        System.out.println("Things you can do here.");
        System.out.println("[0] : Hold");
        System.out.println("[1] : Add to my list");
        System.out.println("[2] : Go back to search menu");
                
        System.out.println("\n");
        System.out.println("-----------------------");
        System.out.println("What would you like to do?");
                
        try {
            choice = sc.nextInt();
            selectAfterSearch(choice);
        } catch (InputMismatchException ex) {
            delay();
            System.out.println("\n");
            System.out.println("***!! Invalid input. Please select again. ***");
            System.out.println("\n");
            sc.nextLine();
        }
        sc.close();
    }

    void selectAfterSearch(int choice) { // after search is done, making choice of holding / adding to my list
        Scanner sc = new Scanner(System.in);
        delay();
        System.out.println("\n");

        //* - menu
        switch(choice) {
            //* - hold
            case 0:
                System.out.println("Choose the number of book you want to hold.");

                while (true) {
                    try { // when number to hold is int
                        hld = sc.nextInt();

                        if(hld < found.size()) { // if hld number is within found.size()
                            for(Map.Entry<String, String> entry : found.entrySet()) {
                                if(onHold.indexOf(entry.getKey()) >= 0) { // whether the book to hold is in onHold arraylist
                                    System.out.println("This item is already on hold.");
                                    System.out.println("\n");
                                    search();
                                    break;
                                }               
                                if(count == hld) { // while iterating, check if entry is the book to hold
                                    onHold.add(entry.getKey());
                                    System.out.println("\n");
                                    System.out.println("++++ \"" + entry.getKey() + "\" " + "is on hold. ++++");

                                    count = 0;

                                    System.out.println("\n");
                                    System.out.println("Type [0] to go back (without brackets).");

                                    while (true) { // if 0 is the input
                                        try {
                                            num = sc.nextInt();
                                            if(num == 0) {
                                                found.clear();
                                                System.out.println("\n");
                                                search();
                                                break;
                                            } else {
                                                throw new InputMismatchException();
                                            }
                                        } catch (InputMismatchException ex) {
                                            delay();
                                            System.out.println("\n");
                                            System.out.println("***!! Invalid input. ***");
                                            System.out.println("Type [0] to go back (without brackets).");
                                            sc.nextLine();
                                        }
                                    }

                                    break;
                                } else { // while iterating, if entry is NOT the book to hold, count++
                                    count++;
                                }
                            }
                            break;
                        } else { // if hld is over the found.size()
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException ex) {// when hld is string
                        delay();
                        System.out.println("\n");
                        System.out.println("***!! Invalid input. Please select again. ***");
                        sc.nextLine();
                    }
                }

                

                break;

            //* - add to my list
            case 1:
                System.out.println("Choose the number of book you want to add to my list.");
                
                while (true) {
                    try { // when number to add to list is int
                        list = sc.nextInt();

                        if(list < found.size()) { // if list number is within found.size()
                            for(Map.Entry<String, String> entry : found.entrySet()) {  
                                if(myList.indexOf(entry.getKey()) >= 0) { // whether the book to add to list is in myList arraylist
                                    System.out.println("This item is already in my list.");
                                    System.out.println("\n");
                                    search();
                                    break;
                                }                
                                if(count == list) { // while iterating, check if entry is the book to add to list
                                    myList.add(entry.getKey());
                                    System.out.println("\n");
                                    System.out.println("++++ \"" + entry.getKey() + "\" " + "is added to my list. ++++");

                                    count = 0;

                                    System.out.println("\n");
                                    System.out.println("Type [0] to go back (without brackets).");

                                    while (true) { // if 0 is the input to go back
                                        try {
                                            num = sc.nextInt();
                                            if(num == 0) {
                                                found.clear();
                                                System.out.println("\n");
                                                search();
                                                break;
                                            } else {
                                                throw new InputMismatchException();
                                            }
                                        } catch (InputMismatchException ex) {
                                            delay();
                                            System.out.println("\n");
                                            System.out.println("***!! Invalid input. ***");
                                            System.out.println("Type [0] to go back (without brackets).");
                                            sc.nextLine();
                                        }
                                    }

                                    break;
                                } else { // while iterating, if entry is NOT the book to add to list, count++
                                    count++;                                    
                                }                                
                            }
                            break;
                        } else { // if list is over the found.size()
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException ex) {// when number to hold is string
                        delay();
                        System.out.println("\n");
                        System.out.println("***!! Invalid input. Please select again. ***");
                        sc.nextLine();
                    }
                }                
                break;


            case 2:
                found.clear();
                System.out.println("\n");
                search();
                break;


            default:
                System.out.println("***!! Invalid input. Please select again. ***");
                System.out.println("\n");
                search();
                break;
        }
        sc.close();
    }




    //note - borrowing
    HashMap<String, String> borrowing = new HashMap<>();
    void borrowingInit() {
        borrowing.put("Before the Coffee Gets Cold", "21/7/20");
        borrowing.put("The Girl on the Train", "21/7/20");
        borrowing.put("Miss Peregrine’s Home for Peculiar Children", "18/7/20");
    }

    void borrowing() {
        Scanner sc = new Scanner(System.in);

        System.out.println("------- Borrowing -------");

        for(Map.Entry<String, String> entry : borrowing.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "\n\tdue: " + value);
        }

        delay();
        System.out.println("\n");
        System.out.println("-----------------------");
        
        System.out.println("Type [0] to go back (without brackets).");

        while (true) {
            try {
                num = sc.nextInt();
                if(num == 0) {
                    System.out.println("\n");
                    myPage();
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException ex) {
                delay();
                System.out.println("\n");
                System.out.println("***!! Invalid input. ***");
                System.out.println("Type [0] to go back (without brackets).");
                sc.nextLine();
            }
        }
        sc.close();
    }





    //note - onHold
    ArrayList<String> onHold = new ArrayList<>();
    void holdInit() {
        onHold.add("The Book Of Dust");
    }

    void onHold() {
        Scanner sc = new Scanner(System.in);

        System.out.println("------- On Hold -------");

        for(int i = 0; i < onHold.size(); i++) {
            System.out.println("-" + i + "- : " + onHold.get(i));
        }

        System.out.println("\n");
        System.out.println("Things you can do here.");
        System.out.println("[0] : Cancel hold");
        System.out.println("[1] : Go back to my page");
        
        System.out.println("\n");
        System.out.println("-----------------------");
        System.out.println("What would you like to do?");

        try {
            choice = sc.nextInt();
            selectOnHold(choice);
        } catch (InputMismatchException ex) {
            delay();
            System.out.println("\n");
            System.out.println("***!! Invalid input. Please select again. ***");
            System.out.println("\n");
            onHold();
        }
        
        sc.close();
    }

    void selectOnHold(int choice) {
        Scanner sc = new Scanner(System.in);
        delay();
        System.out.println("\n");

        //* - menu
        switch(choice) {
            //* - cancel hold
            case 0:
                if(onHold.size() == 0) {
                    System.out.println("There is currently no book on hold.");
                    System.out.println("Type [0] to go back (without brackets).");

                    while (true) {
                        try {
                            num = sc.nextInt();
                            if(num == 0) {
                                System.out.println("\n");
                                onHold();
                                break;
                            } else {
                                throw new InputMismatchException();
                            }
                        } catch (InputMismatchException ex) {
                            delay();
                            System.out.println("\n");
                            System.out.println("***!! Invalid input. ***");
                            System.out.println("Type [0] to go back (without brackets).");
                            sc.nextLine();
                        }
                    }
                } else {
                    System.out.println("Choose the number of book you want to cancel hold.");

                    while (true) {
                        try {
                            del = sc.nextInt();
                            if(del < onHold.size()) {
                                deletedBook = onHold.get(del);
                                onHold.remove(del);
                                System.out.println("\n");
                                System.out.println("++++ \"" + deletedBook + "\" " + "is canceled. ++++");
                                break;
                            } else {
                                throw new InputMismatchException();
                            }
                        } catch (InputMismatchException ex) {
                            delay();
                            System.out.println("\n");
                            System.out.println("***!! Invalid input. Please select again. ***");
                            sc.nextLine();
                        }
                    }

                    System.out.println("\n");
                    System.out.println("Type [0] to go back (without brackets).");

                    while (true) {
                        try {
                            num = sc.nextInt();
                            if(num == 0) {
                                System.out.println("\n");
                                onHold();
                                break;
                            } else {
                                throw new InputMismatchException();
                            }
                        } catch (InputMismatchException ex) {
                            delay();
                            System.out.println("\n");
                            System.out.println("***!! Invalid input. ***");
                            System.out.println("Type [0] to go back (without brackets).");
                            sc.nextLine();
                        }
                    }
                }

                break;


            case 1:
                System.out.println("\n");
                myPage();
                break;

            
            default:
                System.out.println("***!! Invalid input. Please select again. ***");
                System.out.println("\n");
                onHold();
                break;
        }
        sc.close();
    }




    //note - myList
    ArrayList<String> myList = new ArrayList<>();
    void mylistInit() {
        myList.add("Fragile Things");
        myList.add("Midnight Library");
    }

    void myList() {
        Scanner sc = new Scanner(System.in);

        System.out.println("------- My List -------");

        for(int i = 0; i < myList.size(); i++) {
            System.out.println("-" + i + "- : " + myList.get(i));
        }

        System.out.println("\n");
        System.out.println("Things you can do here.");
        System.out.println("[0] : Hold");
        System.out.println("[1] : Remove from my list");
        System.out.println("[2] : Go back to my page");
        
        System.out.println("\n");
        System.out.println("-----------------------");
        System.out.println("What would you like to do?");

        try {
            choice = sc.nextInt();
            selectOnMyList(choice);
        } catch (InputMismatchException ex) {
            delay();
            System.out.println("\n");
            System.out.println("***!! Invalid input. Please select again. ***");
            System.out.println("\n");
            myList();
        }
        
        sc.close();
    }

    void selectOnMyList(int choice) {
        Scanner sc = new Scanner(System.in);
        delay();
        System.out.println("\n");

        //* - menu
        switch(choice) {
            //* - hold
            case 0:
                System.out.println("Choose the number of book you want to hold.");

                while (true) {
                    try {
                        hld = sc.nextInt();
                        if(hld < myList.size()) {
                            if(onHold.indexOf(myList.get(hld)) == -1) {
                                onHold.add(myList.get(hld));
                                System.out.println("\n");
                                System.out.println("++++ \"" + myList.get(hld) + "\" " + "is on hold. ++++");
                                break;
                            } else {
                                System.out.println("This item is already on hold.");
                                break;
                            }
                        } else {
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException ex) {
                        delay();
                        System.out.println("\n");
                        System.out.println("***!! Invalid input. Please select again. ***");
                        sc.nextLine();
                    }
                }

                System.out.println("\n");
                System.out.println("Type [0] to go back (without brackets).");

                while (true) {
                    try {
                        num = sc.nextInt();
                        if(num == 0) {
                            System.out.println("\n");
                            myList();
                            break;
                        } else {
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException ex) {
                        delay();
                        System.out.println("\n");
                        System.out.println("***!! Invalid input. ***");
                        System.out.println("Type [0] to go back (without brackets).");
                        sc.nextLine();
                    }
                }

                break;

            //* - remove
            case 1:
                if(myList.size() == 0) { //if there's no book in my list.
                    System.out.println("There is currently no book in my list.");
                    System.out.println("Type [0] to go back (without brackets).");

                    while (true) {
                        try {
                            num = sc.nextInt();
                            if(num == 0) {
                                System.out.println("\n");
                                myList();
                                break;
                            } else {
                                throw new InputMismatchException();
                            }
                        } catch (InputMismatchException ex) {
                            delay();
                            System.out.println("\n");
                            System.out.println("***!! Invalid input. ***");
                            System.out.println("Type [0] to go back (without brackets).");
                            sc.nextLine();
                        }
                    }
                } else { // if there are books in my list.
                    System.out.println("Choose the number of book you want to remove.");
                    while (true) {
                        try {
                            del = sc.nextInt();
                            if(del < myList.size()) {
                                deletedBook = myList.get(del);
                                myList.remove(del);
                                System.out.println("\n");
                                System.out.println("++++ \"" + deletedBook + "\" " + "is removed. ++++");
                                break;
                            } else {
                                throw new InputMismatchException();
                            }
                        } catch (InputMismatchException ex) {
                            delay();
                            System.out.println("\n");
                            System.out.println("***!! Invalid input. Please select again. ***");
                            sc.nextLine();
                        }
                    }
    
                    System.out.println("\n");
                    System.out.println("Type [0] to go back (without brackets).");
    
                    while (true) {
                        try {
                            num = sc.nextInt();
                            if(num == 0) {
                                System.out.println("\n");
                                myList();
                                break;
                            } else {
                                throw new InputMismatchException();
                            }
                        } catch (InputMismatchException ex) {
                            delay();
                            System.out.println("\n");
                            System.out.println("***!! Invalid input. ***");
                            System.out.println("Type [0] to go back (without brackets).");
                            sc.nextLine();
                        }
                    }
                }                           
                
                break;


            case 2:
                System.out.println("\n");
                myPage();
                break;


            default:
                System.out.println("***!! Invalid input. Please select again. ***");
                System.out.println("\n");
                myList();
                break;
        }
        sc.close();
    }




    //note - logout
    void logout() {
        System.out.println("++++ You successfully logged out. ++++");
    }
    
}





//note - Main
public class Main {
    public static void main(String[] args) {

        //note - login
        UserInfo userinfo = new UserInfo();
        userinfo.login(userinfo.getName(),userinfo.getPin());
    }


}
