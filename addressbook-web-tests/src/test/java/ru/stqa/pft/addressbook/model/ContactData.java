package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String nickname;
    private final String company;
    private final String telephone;
    private final String email;
    private String group;

    public ContactData(String firstname, String lastname, String nickname, String company, String telephone, String email, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.telephone = telephone;
        this.email = email;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
