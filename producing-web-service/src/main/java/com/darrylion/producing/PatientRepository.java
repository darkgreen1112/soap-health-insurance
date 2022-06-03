package com.darrylion.producing;

import org.darrylion.producing_web_service.Patient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PatientRepository {
    private static final Map<String, Patient> patients = new HashMap<>();

    public Patient findPatient(String id) {
        return patients.get(id);
    }

    public Patient createPatient(Patient patient) {
        patients.put(patient.getId(), patient);
        return findPatient(patient.getId());
    }

    public Patient updatePatient(Patient patient) {
        patients.replace(patient.getId(), patient);
        return findPatient(patient.getId());
    }

    public Patient deletePatient(String id) {
        return patients.remove(id);
    }
}
