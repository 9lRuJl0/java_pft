package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id =  Integer.MAX_VALUE;
    private String firstname;
    private String lastname;
    private String nickname;
    private String company;
    private String telephone;
    private String email;
    private String group;


    public int getId() {
        return id;
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



    public ContactData withId(int id) {

        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }


    public void setId(int id) {
        this.id = id;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

}
