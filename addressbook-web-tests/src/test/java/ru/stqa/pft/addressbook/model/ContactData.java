package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.*;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "addressbook")

@XStreamAlias("contacts")

public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname", columnDefinition = "Text")
    private String firstname;
    @Expose
    @Column(name = "lastname", columnDefinition = "Text")
    private String lastname;
    @Expose
    @Column(name = "nickname", columnDefinition = "Text")
    private String nickname;
    @Expose
    @Column(name = "company", columnDefinition = "Text")
    private String company;
    @Expose
    @Transient
    private String telephone;
    @Expose
    @Column(name = "email", columnDefinition = "Text")
    private String email;
    @Column(name = "home", columnDefinition = "Text")
    private String homePhone;
    @Column(name = "mobile", columnDefinition = "Text")
    private String mobilePhone;
    @Column(name = "work", columnDefinition = "Text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Transient
    private String email2;
    @Transient
    private String email3;
    @Transient
    private String allEmail;
    @Transient
    private String address;
    @Transient
    private String phone2;
    @Column(name = "photo", columnDefinition = "Text")
    private String photo;
    @Column(name = "deprecated", columnDefinition = "Datetime")
    private String deprecated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))

    private Set<GroupData> groups = new HashSet<GroupData>();


    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getNickname() {return nickname;}
    public String getCompany() {
        return company;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getEmail() {
        return email;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public String getWorkPhone() {
        return workPhone;
    }
    public String getAllPhones() {
        return allPhones;
    }
    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }
    public String getAllemail() {return allEmail;}
    public String getAddress() {return address;}
    public String getPhone2() {
        return phone2;
    }

    public File getPhoto() {return new File(photo);}

    public String getDeprecated() {return deprecated;}

    public Groups getGroups() {return new Groups(groups);}







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

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactData withDeprecated(String deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }



    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
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


}




