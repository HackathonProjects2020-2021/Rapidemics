package app.patientocity;

public class patient
{
    String patienId;
    String patientName;
    String patientSymptom;
    String patientDiag;
    String patientRoom;
    String  datetime;
    String dis1;
    String dis2;
    public patient(){}

    public patient(String patienId, String patientName, String patientSymptom, String patientDiag, String patientRoom, String datetime ) {
        this.patienId = patienId;
        this.patientName = patientName;
        this.patientSymptom = patientSymptom;
        this.patientDiag = patientDiag;
        this.patientRoom = patientRoom;
        this.datetime =datetime;
//        this.dis1= dis1;
//        this.dis2= dis2;


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

//    public String getDis1() {
//        return dis1;
//    }
//
//    public String getDis2() {
//        return dis2;
//    }
}
