package com.joysistvi.recording.view;

import com.joysistvi.recording.controller.LoginController;
import com.joysistvi.recording.model.User;

import java.util.Scanner;

public class LoginView {

    private final LoginController loginController;
    private final AdminDashboardView adminDashboardView;
    private final UserDashboardView userDashboardView;
    private final Scanner scanner = new Scanner(System.in);

    public LoginView(LoginController loginController,
                     AdminDashboardView adminDashboardView,
                     UserDashboardView userDashboardView) {

        this.loginController = loginController;
        this.adminDashboardView = adminDashboardView;
        this.userDashboardView = userDashboardView;
    }

    public void showMenu() {

        System.out.println("\n===== LOGIN =====");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = loginController.login(username, password);

        if (user == null) {
            System.out.println("Invalid username or password.");
            return;
        }

        System.out.println("\nLogin successful!");
        System.out.println("Welcome, " + user.getFirstName() + "!");

        if (user.getRole().equalsIgnoreCase("admin")) {

            adminDashboardView.showMenu();

        } else {

            userDashboardView.showMenu();

        }
    }
}