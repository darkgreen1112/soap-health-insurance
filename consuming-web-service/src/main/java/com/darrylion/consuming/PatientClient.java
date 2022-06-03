package com.darrylion.consuming;

import com.darrylion.consumingwebservice.wsdl.CreatePatientRequest;
import com.darrylion.consumingwebservice.wsdl.DeletePatientRequest;
import com.darrylion.consumingwebservice.wsdl.GetPatientRequest;
import com.darrylion.consumingwebservice.wsdl.GetPatientResponse;
import com.darrylion.consumingwebservice.wsdl.Patient;
import com.darrylion.consumingwebservice.wsdl.UpdatePatientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class PatientClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(PatientClient.class);

    public GetPatientResponse getPatient(String id) {

        GetPatientRequest request = new GetPatientRequest();
        request.setId(id);

        log.info("getPatient");

        GetPatientResponse response = (GetPatientResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/patients", request,
                        new SoapActionCallback(
                                "http://darrylion.org/producing-web-service/GetPatientRequest"));

        return response;
    }

    public GetPatientResponse createPatient(Patient patient) {

        CreatePatientRequest request = new CreatePatientRequest();
        request.setPatient(patient);

        log.info("createPatient");

        GetPatientResponse response = (GetPatientResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/patients", request,
                        new SoapActionCallback(
                                "http://darrylion.org/producing-web-service/CreatePatientRequest"));

        return response;
    }

    public GetPatientResponse updatePatient(Patient patient) {

        UpdatePatientRequest request = new UpdatePatientRequest();
        request.setPatient(patient);

        log.info("updatePatient");

        GetPatientResponse response = (GetPatientResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/patients", request,
                        new SoapActionCallback(
                                "http://darrylion.org/producing-web-service/GetPatientRequest"));

        return response;
    }

    public GetPatientResponse deletePatient(String id) {

        DeletePatientRequest request = new DeletePatientRequest();
        request.setId(id);

        log.info("deletePatient");

        GetPatientResponse response = (GetPatientResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/patients", request,
                        new SoapActionCallback(
                                "http://darrylion.org/producing-web-service/GetPatientRequest"));

        return response;
    }

}
