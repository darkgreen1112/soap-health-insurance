package com.darrylion.producing;

import org.darrylion.producing_web_service.CreatePatientRequest;
import org.darrylion.producing_web_service.DeletePatientRequest;
import org.darrylion.producing_web_service.GetPatientRequest;
import org.darrylion.producing_web_service.GetPatientResponse;
import org.darrylion.producing_web_service.UpdatePatientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PatientEndpoint {
    private static final String NAMESPACE_URI = "http://darrylion.org/producing-web-service";

    private final PatientRepository patientRepository;

    @Autowired
    public PatientEndpoint(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPatientRequest")
    @ResponsePayload
    public GetPatientResponse getPatient(@RequestPayload GetPatientRequest request) {
        GetPatientResponse response = new GetPatientResponse();
        response.setPatient(patientRepository.findPatient(request.getId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPatientRequest")
    @ResponsePayload
    public GetPatientResponse createPatient(@RequestPayload CreatePatientRequest request) {
        GetPatientResponse response = new GetPatientResponse();
        response.setPatient(patientRepository.createPatient(request.getPatient()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePatientRequest")
    @ResponsePayload
    public GetPatientResponse updatePatient(@RequestPayload UpdatePatientRequest request) {
        GetPatientResponse response = new GetPatientResponse();
        response.setPatient(patientRepository.updatePatient(request.getPatient()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePatientRequest")
    @ResponsePayload
    public GetPatientResponse deletePatient(@RequestPayload DeletePatientRequest request) {
        GetPatientResponse response = new GetPatientResponse();
        response.setPatient(patientRepository.deletePatient(request.getId()));

        return response;
    }
}