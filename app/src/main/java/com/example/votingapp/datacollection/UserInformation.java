package com.example.votingapp.datacollection;

public class UserInformation {

    private String fullName;
    private String idNumber;
    private String emailAddress;
    private String uid;
    private String profileImage;
    private String userType;
    private String userCode;

    private String voted;

    public UserInformation() {
    }

    public UserInformation(String fullName, String idNumber, String emailAddress, String uid, String profileImage, String userType, String userCode, String voted) {
        this.fullName = fullName;
        this.idNumber = idNumber;
        this.emailAddress = emailAddress;
        this.uid = uid;
        this.profileImage = profileImage;
        this.userType = userType;
        this.userCode = userCode;
        this.voted = voted;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getVoted() {
        return voted;
    }

    public void setVoted(String voted) {
        this.voted = voted;
    }
}
