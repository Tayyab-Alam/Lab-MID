/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitytransportsystem;

import java.util.Scanner;

public class UniversityTransportSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create Filters
        Business.ChallanFilter challanFilter = new Business.ChallanFilter();
        Business.TimeFilter timeFilter = new Business.TimeFilter(new Data.Time(8, 0), new Data.Time(18, 0)); // 8:00 AM to 6:00 PM

        // Set Up EntryValidator and Observer
        Business.EntryValidator validator = new Business.EntryValidator();
        validator.setObserver(passenger ->
            System.out.println("Observer: " + (passenger.getName().isEmpty() ? "Faculty member" : passenger.getName()) + " has entered the transport system."));

        // Ask User for Identity
        System.out.println("Welcome to the University Transport System!");
        String identity;
        while (true) {
            System.out.print("Are you a Student or Faculty? Enter 'Student' or 'Faculty': ");
            identity = scanner.nextLine();
            if (identity.equalsIgnoreCase("Student") || identity.equalsIgnoreCase("Faculty")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'Student' or 'Faculty'.");
            }
        }

        // Initialize name and challan details
        String name = "";
        boolean isChallanPaid = false;

        if (identity.equalsIgnoreCase("Faculty")) {
            // For faculty, they don't need to enter their name, and they're automatically free
            System.out.println("Welcome, Faculty member! Your identity will remain hidden.");
            name = ""; // Faculty doesn't need to input their name
            isChallanPaid = true; // Faculty is considered free, so no challan needed
        } else if (identity.equalsIgnoreCase("Student")) {
            // For students, ask for details
            System.out.print("Enter your name: ");
            name = scanner.nextLine();

            System.out.print("Is your challan paid? Enter 'yes' or 'no': ");
            isChallanPaid = scanner.nextLine().equalsIgnoreCase("yes");
        }

        // Ask for entry time (common for both faculty and students)
        System.out.print("Enter the time of entry (hours): ");
        int hours = scanner.nextInt();

        System.out.print("Enter the time of entry (minutes): ");
        int minutes = scanner.nextInt();

        // Create Passenger (Name is empty for Faculty)
        Data.Passenger passenger = new Data.Passenger(name, new Data.Challan(isChallanPaid), new Data.Time(hours, minutes));

        // For Faculty, bypass challan and time validation as they are considered free
        if (identity.equalsIgnoreCase("Faculty")) {
            System.out.println("Faculty member validated successfully. No challan or time validation needed.");
            validator.validate(passenger);  // Faculty bypasses filters
        } else {
            // Validate the Passenger for Students
            validator.validate(passenger, challanFilter, timeFilter);
        }

        // Close Scanner
        scanner.close();
    }
}
