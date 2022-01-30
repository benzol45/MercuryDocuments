package com.example.mercury.entitiy;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
public class Enterprise {
    @Id
    private UUID uuid;
    @Column
    private String name;

    @Column
    private String mercLogin;
    @Column
    private String mercuryName;
    @Column
    private String vetisApiLogin;
    @Column
    private String vetisApiPassword;
    @Column
    private String vetisApiKey;
    @Column
    private String vetisApiIssuerID;
    @Column
    private Boolean vetInspection;

    @OneToMany (mappedBy = "enterprise", fetch = FetchType.LAZY)
    private Set<Document> documents;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Enterprise() {}


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMercLogin() {
        return mercLogin;
    }

    public void setMercLogin(String mercLogin) {
        this.mercLogin = mercLogin;
    }

    public String getMercuryName() {
        return mercuryName;
    }

    public void setMercuryName(String mercuryName) {
        this.mercuryName = mercuryName;
    }

    public String getVetisApiLogin() {
        return vetisApiLogin;
    }

    public void setVetisApiLogin(String vetisApiLogin) {
        this.vetisApiLogin = vetisApiLogin;
    }

    public String getVetisApiPassword() {
        return vetisApiPassword;
    }

    public void setVetisApiPassword(String vetisApiPassword) {
        this.vetisApiPassword = vetisApiPassword;
    }

    public String getVetisApiKey() {
        return vetisApiKey;
    }

    public void setVetisApiKey(String vetisApiKey) {
        this.vetisApiKey = vetisApiKey;
    }

    public String getVetisApiIssuerID() {
        return vetisApiIssuerID;
    }

    public void setVetisApiIssuerID(String vetisApiIssuerID) {
        this.vetisApiIssuerID = vetisApiIssuerID;
    }

    public Boolean getVetInspection() {
        return vetInspection;
    }

    public void setVetInspection(Boolean vetInspection) {
        this.vetInspection = vetInspection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
