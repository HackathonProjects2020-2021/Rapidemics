package app.patientocity;

public class patientData {
    private String name;
    private String symptom;
    private String diag;
    private String room;

    public patientData(String name, String symptom, String diag, String room){
        this.name = name;
        this.symptom =symptom;
        this.diag = diag;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public String getSymptom() {
        return symptom;
    }

    public String getDiag() {
        return diag;
    }

    public String getRoom() {
        return room;
    }
}
