public class person {
    String name, surName, eMail;

    public person(String name, String surName, String eMail) {
        //creating constructor person

        this.name = name;
        this.surName = surName;
        this.eMail = eMail;
    }


    //getters
    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getEmail() {
        return eMail;

    }
}
