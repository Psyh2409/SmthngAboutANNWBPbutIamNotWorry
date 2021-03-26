package com.gmail.psyh2409;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vacancy {
    private Long id;
    private String companyName;
    private List<URL> urls;
    private String aboutCompany;
    private String contactPerson;
    private String cpName;
    private LocalDate publication;
    private String vacancyName;
    private String textOfVacancy;

    public Vacancy() {super();this.urls = new ArrayList<>();}

    public Vacancy(
            String companyName,
            URL url,
            String aboutCompany,
            String contactPerson,
            String cpName,
            LocalDate publication,
            String vacancyName,
            String textOfVacancy) {
        this.companyName = companyName;
        this.urls = new ArrayList<>();
        urls.add(url);
        this.aboutCompany = aboutCompany;
        this.contactPerson = contactPerson;
        this.cpName = cpName;
        this.publication = publication;
        this.vacancyName = vacancyName;
        this.textOfVacancy = textOfVacancy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<URL> getUrls() {
        return urls;
    }

    public void setUrls(List<URL> urls) {
        this.urls = urls;
    }

    public String getAboutCompany() {
        return aboutCompany;
    }

    public void setAboutCompany(String aboutCompany) {
        this.aboutCompany = aboutCompany;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public LocalDate getPublication() {
        return publication;
    }

    public void setPublication(LocalDate publication) {
        this.publication = publication;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public String getTextOfVacancy() {
        return textOfVacancy;
    }

    public void setTextOfVacancy(String textOfVacancy) {
        this.textOfVacancy = textOfVacancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(id, vacancy.id) &&
                Objects.equals(companyName, vacancy.companyName) &&
                Objects.equals(urls, vacancy.urls) &&
                Objects.equals(aboutCompany, vacancy.aboutCompany) &&
                Objects.equals(contactPerson, vacancy.contactPerson) &&
                Objects.equals(cpName, vacancy.cpName) &&
                Objects.equals(publication, vacancy.publication) &&
                Objects.equals(vacancyName, vacancy.vacancyName) &&
                Objects.equals(textOfVacancy, vacancy.textOfVacancy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, urls, aboutCompany, contactPerson, cpName, publication, vacancyName, textOfVacancy);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", urls=" + urls +
                ", aboutCompany='" + aboutCompany + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", cpName='" + cpName + '\'' +
                ", publication=" + publication +
                ", vacancyName='" + vacancyName + '\'' +
                ", textOfVacancy='" + textOfVacancy + '\'' +
                '}';
    }
}
