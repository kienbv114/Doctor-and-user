package controller;

import common.Algorithm;
import common.Validation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Doctor;
import view.Menu;

public class DoctorProgramming {

    private final String[] MAIN_MENU_ITEMS = {
        " Add Doctor",
        " Update Doctor",
        " Delete Doctor",
        " Search Doctor",
        " Exit",};
    Validation validation = new Validation();
    Algorithm algorithm = new Algorithm();
    ArrayList<Doctor> dt = new ArrayList<>();

    Menu mainMenu = new Menu("========= Doctor Management ==========", MAIN_MENU_ITEMS) {
        @Override
        public void execute(int choice) {
            try {
            switch (choice) {
                case 1:
                    Doctor newDoctor = inputDoctorDetails();
                    if (algorithm.addDoctor(newDoctor)) {
                        System.out.println("Doctor added successfully.");
                    } else {
                        System.out.println("Failed to add doctor.");
                    }
                    break;
                case 2:
                    Doctor updatedDoctor = inputDoctorDetails();
                    if (algorithm.updateDoctor(updatedDoctor)) {
                        System.out.println("Doctor updated successfully.");
                    } else {
                        System.out.println("Failed to update doctor.");
                    }
                    break;
                case 3:
                    System.out.print("Enter doctor code to delete: ");
                    String code = validation.checkInputString();
                    if (algorithm.deleteDoctor(code)) {
                        System.out.println("Doctor deleted successfully.");
                    } else {
                        System.out.println("Failed to delete doctor.");
                    }
                    break;
                case 4:
                    System.out.print("Enter name to search: ");
                    String name = validation.checkInputString();
                    HashMap<String, Doctor> foundDoctors = algorithm.searchDoctor(name);
                    displaySearchResults(foundDoctors);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
            }
     } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    public Doctor inputDoctorDetails() {
        System.out.print("Enter code: ");
        String code = validation.checkInputString();
        System.out.print("Enter name: ");
        String name = validation.checkInputString();
        System.out.print("Enter specialization: ");
        String specialization = validation.checkInputString();
        System.out.print("Enter availability: ");
        int availability = validation.checkInputIntLimit(0, 100);
        return new Doctor(code, name, specialization, availability);
    }

    public void displaySearchResults(HashMap<String, Doctor> foundDoctors) {
        if (foundDoctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            for (Map.Entry<String, Doctor> entry : foundDoctors.entrySet()) {
                Doctor doctor = entry.getValue();
                System.out.println("Code: " + doctor.getCode());
                System.out.println("Name: " + doctor.getName());
                System.out.println("Specialization: " + doctor.getSpecialization());
                System.out.println("Availability: " + doctor.getAvailability());
                System.out.println("-------------------");
            }
        }
    }  
    };
    public void run() {
        mainMenu.run();
    }
}
