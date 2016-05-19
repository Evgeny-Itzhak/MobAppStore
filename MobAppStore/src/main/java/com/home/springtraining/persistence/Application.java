package com.home.springtraining.persistence;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Proxy(lazy=false)
@Table(name = "T_APPLICATION")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "APPLICATION_ID")
    private Long applicationId;

    @Column(name = "APPLICATION_NAME", nullable = false)
    @NotEmpty(message = "Please enter your Application name.")
    private String applicationName;

    @Column(name = "APPLICATION_DESCRIPTION", nullable = false)
    @NotEmpty(message = "Please enter your Application description.")
    private String applicationDescription;

    @Column(name = "APPLICATION_PACKAGE", unique = true, nullable = false)
    private String applicationPackage;

    @Column(name = "APPLICATION_IMAGE_128", nullable = true)
    private byte[] applicationImage128;

    @Column(name = "APPLICATION_IMAGE_512", nullable = true)
    private byte[] applicationImage512;

    @Column(name = "APPLICATION_ARCHIVE", nullable = false)
    private byte[] applicationArchive;

    @Column(name = "APPLICATION_DOWNLOADS_COUNT", nullable = false)
    private int applicationDownloadsCount;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    Category category;

    public Application() {
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public String getApplicationPackage() {
        return applicationPackage;
    }

    public void setApplicationPackage(String applicationPackage) {
        this.applicationPackage = applicationPackage;
    }

    public byte[] getApplicationImage128() {
        return applicationImage128;
    }

    public void setApplicationImage128(byte[] applicationImage128) {
        this.applicationImage128 = applicationImage128;
    }

    public byte[] getApplicationImage512() {
        return applicationImage512;
    }

    public void setApplicationImage512(byte[] applicationImage512) {
        this.applicationImage512 = applicationImage512;
    }

    public byte[] getApplicationArchive() {
        return applicationArchive;
    }

    public void setApplicationArchive(byte[] applicationArchive) {
        this.applicationArchive = applicationArchive;
    }

    public int getApplicationDownloadsCount() {
        return applicationDownloadsCount;
    }

    public void setApplicationDownloadsCount(int applicationDownloadsCount) {
        this.applicationDownloadsCount = applicationDownloadsCount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", applicationName='" + applicationName +
                '}';
    }
}
