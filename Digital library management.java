import java.util.*;

public class DigitalLibrarySystem {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, String> users = new HashMap<>();
    static Map<String, String> books = new HashMap<>();
    static Map<String, String> issuedBooks = new HashMap<>();
    static String currentUser = null;

    public static void main(String[] args) {
        users.put("admin", "admin123");
        users.put("user1", "user123");
        books.put("Java Programming", "Available");
        books.put("Data Structures", "Available");

        System.out.println("Welcome to the Digital Library System");
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                login();
                if (currentUser.equals("admin")) {
                    adminMenu();
                } else {
                    userMenu();
                }
            } else {
                System.out.println("Exiting...");
                break;
            }
        }
    }

    public static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (users.containsKey(username) && users.get(username).equals(password)) {
            currentUser = username;
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public static void adminMenu() {
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View Books");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                addBook();
            } else if (choice == 2) {
                removeBook();
            } else if (choice == 3) {
                viewBooks();
            } else if (choice == 4) {
                currentUser = null;
                System.out.println("Logged out.");
                break;
            }
        }
    }

    public static void userMenu() {
        while (true) {
            System.out.println("User Menu");
            System.out.println("1. View Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                viewBooks();
            } else if (choice == 2) {
                issueBook();
            } else if (choice == 3) {
                returnBook();
            } else if (choice == 4) {
                currentUser = null;
                System.out.println("Logged out.");
                break;
            }
        }
    }

    public static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        books.put(title, "Available");
        System.out.println("Book added.");
    }

    public static void removeBook() {
        System.out.print("Enter book title to remove: ");
        String title = scanner.nextLine();
        if (books.containsKey(title)) {
            books.remove(title);
            System.out.println("Book removed.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public static void viewBooks() {
        System.out.println("Books in Library:");
        for (Map.Entry<String, String> entry : books.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public static void issueBook() {
        System.out.print("Enter book title to issue: ");
        String title = scanner.nextLine();
        if (books.containsKey(title) && books.get(title).equals("Available")) {
            issuedBooks.put(title, currentUser);
            books.put(title, "Issued");
            System.out.println("Book issued.");
        } else {
            System.out.println("Book not available or already issued.");
        }
    }

    public static void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
        if (issuedBooks.containsKey(title) && issuedBooks.get(title).equals(currentUser)) {
            books.put(title, "Available");
            issuedBooks.remove(title);
            System.out.println("Book returned.");
        } else {
            System.out.println("You have not issued this book.");
        }
    }
}
