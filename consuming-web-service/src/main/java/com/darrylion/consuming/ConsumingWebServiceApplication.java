package com.darrylion.consuming;

import com.darrylion.consumingwebservice.wsdl.GetPatientResponse;
import com.darrylion.consumingwebservice.wsdl.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.GregorianCalendar;

@SpringBootApplication
public class ConsumingWebServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(PatientClient.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsumingWebServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(PatientClient quoteClient) {
        return args -> {

            // The list is empty by default. There are no patients yet. Should return null

            GetPatientResponse response = quoteClient.getPatient("0");
            if (response.getPatient() == null) {
                log.info("OK");
            } else {
                throw new RuntimeException();
            }


            // Create a patient and save it on the server

            Patient patient = new Patient();
            patient.setId("0");
            patient.setFirstName("Луиза");
            patient.setLastName("Лебедева");
            patient.setPatronymic("Ефимовна");
            patient.setBirthday(new GregorianCalendar(2000, 9, 30).getTime().toString());
            patient.setTermPolicy(new GregorianCalendar(2050, 9, 30).getTime().toString());

            // We check that it was saved and returned

            response = quoteClient.createPatient(patient);
            if (response.getPatient() != null) {
                log.info(print(response.getPatient()));
            } else {
                throw new RuntimeException();
            }

            // We update the last name and replace it on the server

            patient.setLastName("Береслава");
            response = quoteClient.updatePatient(patient);
            if (response.getPatient() != null) {
                log.info(print(response.getPatient()));
            } else {
                throw new RuntimeException();
            }

            // Delete the patient and return it

            response = quoteClient.deletePatient("0");
            if (response.getPatient() != null) {
                log.info(print(response.getPatient()));
            } else {
                throw new RuntimeException();
            }

            // Checking that the patient is not on the server

            response = quoteClient.getPatient("0");
            if (response.getPatient() == null) {
                log.info("OK");
            } else {
                throw new RuntimeException();
            }
        };
    }

    public static String print(Patient patient) {
        return "PatientExtend{" +
                "id='" + patient.getId() + '\'' +
                ", lastName='" + patient.getLastName() + '\'' +
                ", firstName='" + patient.getFirstName() + '\'' +
                ", patronymic='" + patient.getPatronymic() + '\'' +
                ", birthday='" + patient.getBirthday() + '\'' +
                ", termPolicy='" + patient.getTermPolicy() + '\'' +
                '}';
    }

}
