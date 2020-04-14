package com.example.uposhonghar;

public class savedata {

    String name;
    String username;
    String age;
    String favwriter;
    String favbook;
    String country;
    String propic;
    String feedback;


    public savedata(String name, String username, String age, String favwriter, String favbook, String country) {
        this.name = name;
        this.username = username;
        this.age = age;
        this.favwriter = favwriter;
        this.favbook = favbook;
        this.country = country;
        this.propic=propic;

    }

    public savedata(){

    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getPropic() {
        return propic;
    }

    public void setPropic(String propic) {
        this.propic = propic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFavwriter() {
        return favwriter;
    }

    public void setFavwriter(String favwriter) {
        this.favwriter = favwriter;
    }

    public String getFavbook() {
        return favbook;
    }

    public void setFavbook(String favbook) {
        this.favbook = favbook;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
