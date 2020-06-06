package app.patientocity;

public class patient
{
    String patienId;
    String patientName;
    String patientSymptom;
    String patientDiag;
    String patientRoom;
    String  datetime;

    public patient(){}

    public patient(String patienId, String patientName, String patientSymptom, String patientDiag, String patientRoom, String datetime) {
        this.patienId = patienId;
        this.patientName = patientName;
        this.patientSymptom = patientSymptom;
        this.patientDiag = patientDiag;
        this.patientRoom = patientRoom;
        this.datetime =datetime;
    }

    public String getPatienId() {
        return patienId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientSymptom() {
        return patientSymptom;
    }

    public String getPatientDiag() {
        return patientDiag;
    }

    public String getPatientRoom() {
        return patientRoom;
    }

    public String getDateTime() {
        return patientRoom;
    }


}
