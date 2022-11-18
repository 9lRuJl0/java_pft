package ru.stqa.pft.addressbook.tests;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String company;
    private final String nickname;
    private final String telephone;
    private final String email;

    public ContactData(String firstname, String lastname, String company, String nickname, String telephone, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.nickname = nickname;
        this.telephone = telephone;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompany() {
        return company;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }
}
