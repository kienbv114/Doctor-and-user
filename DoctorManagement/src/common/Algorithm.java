package common ;

import common.Validation;
import java.util.HashMap;
import model.Doctor;

public class Algorithm {

    HashMap<String, Doctor> doctorMap = new HashMap<>();
    Validation validation = new Validation();

    public boolean addDoctor(Doctor doctor) throws Exception {
        if (doctorMap == null) {
            throw new Exception("Database does not exist");
        }
        if (doctor == null) {
            throw new IllegalArgumentException("Data does not exist");
        }
        if (!validation.checkCodeExist(doctorMap, doctor.getCode())) {
            return false;
        }
        if (!validation.checkDuplicate(doctorMap, doctor.getCode(), doctor.getName(), doctor.getSpecialization(), doctor.getAvailability())) {
            return false;
        }
        doctorMap.put(doctor.getCode(), doctor);
        return true;
    }

    public boolean updateDoctor(Doctor doctor) throws Exception {
        if (doctorMap == null) {
            throw new Exception("Database does not exist");
        }
        if (doctor == null) {
            throw new IllegalArgumentException("Data does not exist");
        }
        if (!doctorMap.containsKey(doctor.getCode())) {
            throw new Exception("Doctor code [" + doctor.getCode() + "] doesn't exist");
        }
        if (!validation.checkDuplicate(doctorMap, doctor.getCode(), doctor.getName(), doctor.getSpecialization(), doctor.getAvailability())) {
            return false;
        }
        doctorMap.put(doctor.getCode(), doctor);
        return true;
    }
    
    public boolean deleteDoctor(String code) throws Exception {
        if (doctorMap == null) {
            throw new Exception("Database does not exist");
        }
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Data does not exist");
        }
        if (!doctorMap.containsKey(code)) {
            throw new Exception("Doctor code [" + code + "] doesn't exist");
        }
        doctorMap.remove(code);
        return true;
    }

    public HashMap<String, Doctor> searchDoctor(String input) throws Exception {
        if (doctorMap == null) {
            throw new Exception("Database does not exist");
        }
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Data does not exist");
        }
        HashMap<String, Doctor> foundDoctors = new HashMap<>();
        for (Doctor doctor : doctorMap.values()) {
            if (doctor.getName().contains(input)) {
                foundDoctors.put(doctor.getCode(), doctor);
            }
        }
        return foundDoctors;
    }
}
