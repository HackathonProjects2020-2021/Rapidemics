package app.patientocity;

public class patient
{
    String patienId;
    String patientName;
    String patientSymptom;
    String patientDiag;
    String patientRoom;

    public patient(){}

    public patient(String patienId, String patientName, String patientSymptom, String patientDiag, String patientRoom) {
        this.patienId = patienId;
        this.patientName = patientName;
        this.patientSymptom = patientSymptom;
        this.patientDiag = patientDiag;
        this.patientRoom = patientRoom;
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
}
