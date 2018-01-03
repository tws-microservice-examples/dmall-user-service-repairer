package com.dmall.user.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

//TODO: 思考题Contact能不能跟Order下的共用？
@Table(name = "jx_contact")
@Entity
public class Contact  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;


    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setMore_details(String more_details) {
        this.more_details = more_details;
    }

    private String name;
    private String province;
    private String city;
    private String area;
    private String street;
    private String more_details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getStreet() {
        return street;
    }

    public String getMore_details() {
        return more_details;
    }
}
