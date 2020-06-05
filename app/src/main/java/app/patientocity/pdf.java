package app.patientocity;

public class pdf {

    public String name;
    public String url;

    public pdf(){

    }

    public pdf(String name, String url){
        this.name=name;
        this.url=url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
