package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.UserController;
import com.joysistvi.recording.model.User;

import java.util.List;
import java.util.Scanner;

public class UserView {

    private final UserController userController;
    private final Scanner scanner = new Scanner(System.in);

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void showMenu() {

        int choice;

        do {
            System.out.println("\n===== USER MENU =====");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Archive User");
            System.out.println("6. Restore User");
            System.out.println("7. View Archived Users");
            System.out.println("8. Search User");
            System.out.println("9. Back to Main Menu");
            System.out.print("Choose option: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addUser();
                case 2 -> viewUsers();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 5 -> archiveUser();
                case 6 -> restoreUser();
                case 7 -> viewArchivedUsers();
                case 8 -> searchUser();
                case 9 -> System.out.println("Returning...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 9);
    }

    private void addUser() {

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Role: ");
        String role = scanner.nextLine();

        boolean success = userController.addUser(
                firstName,
                lastName,
                username,
                email,
                password,
                role
        );

        System.out.println(success ? "User added successfully." : "Failed to add user.");
    }

    private void viewUsers() {

        List<User> users = userController.listUsers();

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        users.forEach(System.out::println);
    }

    private void updateUser() {

        System.out.print("User ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Role: ");
        String role = scanner.nextLine();

        boolean success = userController.updateUser(
                firstName,
                lastName,
                username,
                email,
                password,
                role,
                id
        );

        System.out.println(success ? "User updated successfully." : "Update failed.");
    }

    private void deleteUser() {

        System.out.print("User ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean success = userController.deleteUser(id);

        System.out.println(success ? "User deleted successfully." : "Delete failed.");
    }

    private void archiveUser() {

        System.out.print("User ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean success = userController.archiveUser(id);

        System.out.println(success ? "User archived successfully." : "Archive failed.");
    }

    private void restoreUser() {

        System.out.print("User ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        boolean success = userController.restoreUser(id);

        System.out.println(success ? "User restored successfully." : "Restore failed.");
    }

    private void viewArchivedUsers() {

        List<User> users = userController.listArchivedUsers();

        if (users.isEmpty()) {
            System.out.println("No archived users found.");
            return;
        }

        users.forEach(System.out::println);
    }

    private void searchUser() {

        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        List<User> users = userController.searchUsers(keyword);

        if (users.isEmpty()) {
            System.out.println("No matching users found.");
            return;
        }

        users.forEach(System.out::println);
    }
}