package com.amar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

@Entity
public class Loanuserdetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullname;
    private String loantype;
    private String email;
    private long phonenumber;
    private String status;

    @JsonIgnore
    @Lob
    private Blob loanCertificate;


    @Transient // This field will not be persisted to the database
    private String loanCertificateBase64; // To store Base64-encoded image

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLoantype() {
        return loantype;
    }

    public void setLoantype(String loantype) {
        this.loantype = loantype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Blob getLoanCertificate() {
        return loanCertificate;
    }

    public void setLoanCertificate(Blob loanCertificate) {
        this.loanCertificate = loanCertificate;
    }

    public String getLoanCertificateBase64() {
        if (loanCertificate != null) {
            try {
                byte[] bytes = loanCertificate.getBytes(1, (int) loanCertificate.length());
                return Base64.getEncoder().encodeToString(bytes);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setLoanCertificateBase64(String loanCertificateBase64) {
        this.loanCertificateBase64 = loanCertificateBase64;
    }


    // Convert Blob to Base64 (to be used in the service layer)
    public void convertBlobToBase64() throws SQLException, IOException {
        if (this.loanCertificate != null) {
            // Convert Blob to byte array
            byte[] blobBytes = loanCertificate.getBytes(1, (int) loanCertificate.length());
            // Convert byte array to Base64 string
            this.loanCertificateBase64 = Base64.getEncoder().encodeToString(blobBytes);
        }
    }
}
