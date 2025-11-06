package com.PulseLabs.carflow_user_service.model;

import java.time.LocalDate;

public class UserDTO {

    private int id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String drivingLicenseNumber;
    private LocalDate registrationDate;

    public UserDTO(int id, String name, String surname, LocalDate birthday, String drivingLicenseNumber, LocalDate registrationDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
