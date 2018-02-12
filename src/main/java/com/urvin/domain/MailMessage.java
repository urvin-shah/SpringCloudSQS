package com.urvin.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MailMessage {
    private String userList;
    private String toField;
    private String fromField;
    private String subjectField;
    private String messageField;
    private String optionalSenderName;

    @JsonCreator
    public MailMessage(@JsonProperty("userList") String userList, @JsonProperty("toField") String toField,@JsonProperty("fromField") String fromField, @JsonProperty("subjectField") String subjectField,@JsonProperty("messageField") String messageField,@JsonProperty("optionalSenderName") String optionalSenderName) {
        this.userList = userList;
        this.toField = toField;
        this.fromField = fromField;
        this.subjectField = subjectField;
        this.messageField = messageField;
        this.optionalSenderName = optionalSenderName;
    }
   /* public MailMessage( String userList, String toField,String fromField,String subjectField,String messageField,String optionalSenderName) {
        this.userList = userList;
        this.toField = toField;
        this.fromField = fromField;
        this.subjectField = subjectField;
        this.messageField = messageField;
        this.optionalSenderName = optionalSenderName;
    }*/

    public String getUserList() {
        return userList;
    }

    public void setUserList(String userList) {
        this.userList = userList;
    }

    public String getToField() {
        return toField;
    }

    public void setToField(String toField) {
        this.toField = toField;
    }

    public String getFromField() {
        return fromField;
    }

    public void setFromField(String fromField) {
        this.fromField = fromField;
    }

    public String getSubjectField() {
        return subjectField;
    }

    public void setSubjectField(String subjectField) {
        this.subjectField = subjectField;
    }

    public String getMessageField() {
        return messageField;
    }

    public void setMessageField(String messageField) {
        this.messageField = messageField;
    }

    public String getOptionalSenderName() {
        return optionalSenderName;
    }

    public void setOptionalSenderName(String optionalSenderName) {
        this.optionalSenderName = optionalSenderName;
    }

    @Override
    public String toString() {
        return "MailMessage{" +
                "userList='" + userList + '\'' +
                ", toField='" + toField + '\'' +
                ", fromField='" + fromField + '\'' +
                ", subjectField='" + subjectField + '\'' +
                ", messageField='" + messageField + '\'' +
                ", optionalSenderName='" + optionalSenderName + '\'' +
                '}';
    }
}
