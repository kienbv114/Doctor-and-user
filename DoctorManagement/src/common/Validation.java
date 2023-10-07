package common;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import model.Doctor;

public class Validation {

    Scanner sc = new Scanner(System.in);

    public int checkInputIntLimit(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    System.err.println("Please input a number in the range [" + min + ", " + max + "]");
                    System.out.print("Enter again: ");
                } else {
                    return result;
                }
            } catch (NumberFormatException e) {
                System.err.println("Please input a valid integer.");
                System.out.print("Enter again: ");
            }
        }
    }

    public String checkInputString() {
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Input cannot be empty.");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public boolean checkInputYN() {
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input Y or N.");
            System.out.print("Enter again: ");
        }
    }

    public boolean checkCodeExist(Map<String, Doctor> doctorMap, String code) {
        if (doctorMap.containsKey(code)) {
            System.err.println("Doctor with this code already exists.");
            return false;
        }
        return true;
    }

    public boolean checkDuplicate(Map<String, Doctor> doctorMap, String code, String name, String specialization, int availability) {
        for (Map.Entry<String, Doctor> entry : doctorMap.entrySet()) {
            Doctor doctor = entry.getValue();
            if (code.equalsIgnoreCase(doctor.getCode())
                    && name.equalsIgnoreCase(doctor.getName())
                    && specialization.equalsIgnoreCase(doctor.getSpecialization())
                    && availability == doctor.getAvailability()) {
                System.err.println("Duplicate entry found.");
                return false;
            }
        }
        return true;
    }

    public boolean checkChangeInfo(Doctor doctor, String code, String name, String specialization, int availability) {
        if (doctor.getCode().equalsIgnoreCase(code)
                && doctor.getName().equalsIgnoreCase(name)
                && doctor.getSpecialization().equalsIgnoreCase(specialization)
                && doctor.getAvailability() == availability) {
            System.err.println("No changes detected.");
            return false;
        }
        return true;
    }
}