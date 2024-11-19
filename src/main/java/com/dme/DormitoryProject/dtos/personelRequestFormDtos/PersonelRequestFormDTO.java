package com.dme.DormitoryProject.dtos.personelRequestFormDtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PersonelRequestFormDTO {
    @NotNull(message = "Bağlı olacağı departman boş bıraklamaz")
    private Long departmentId;
    private String departmentName;
    @NotNull(message = "Bağlı olacağı ünvan boş bıraklamaz")
    private Long titleId;
    private String titleName;
    @NotNull(message = "Bağlı olacağı yönetici boş bıraklamaz")
    private Long managerId;
    private String managerMail;
    private String managerName;
    private String managerPhoneNumber;
    private int managerSalary;
    private String managerSurName;
    private String managerTitle;
    @NotNull(message = "Açıklama alanı boş bıraklamaz")
    @NotEmpty(message = "Açıklama alanı boş bıraklamaz")
    private String description;
    private String status;
    private Long requestorManagerId;
    private String requestorManagerMail;
    private String requestorManagerName;
    private String requestorManagerPhoneNumber;
    private int requestorManagerSalary;
    private String requestorManagerSurName;
    private String requestorManagerTitle;

    public PersonelRequestFormDTO(){

    }

    public PersonelRequestFormDTO(Long departmentId, String departmentName, Long titleId,
                                  String titleName, Long managerId, String managerMail,
                                  String managerName, String managerPhoneNumber, int managerSalary,
                                  String managerSurName, String managerTitle, String description,
                                  Long requestorManagerId, String requestorManagerMail,
                                  String requestorManagerName, String requestorManagerPhoneNumber,
                                  int requestorManagerSalary, String requestorManagerSurName,
                                  String requestorManagerTitle, String status) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.titleId = titleId;
        this.titleName = titleName;
        this.managerId = managerId;
        this.managerMail = managerMail;
        this.managerName = managerName;
        this.managerPhoneNumber = managerPhoneNumber;
        this.managerSalary = managerSalary;
        this.managerSurName = managerSurName;
        this.managerTitle = managerTitle;
        this.description = description;
        this.requestorManagerId = requestorManagerId;
        this.requestorManagerMail = requestorManagerMail;
        this.requestorManagerName = requestorManagerName;
        this.requestorManagerPhoneNumber = requestorManagerPhoneNumber;
        this.requestorManagerSalary = requestorManagerSalary;
        this.requestorManagerSurName = requestorManagerSurName;
        this.requestorManagerTitle = requestorManagerTitle;
        this.status=status;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getManagerMail() {
        return managerMail;
    }

    public void setManagerMail(String managerMail) {
        this.managerMail = managerMail;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public void setManagerPhoneNumber(String managerPhoneNumber) {
        this.managerPhoneNumber = managerPhoneNumber;
    }

    public int getManagerSalary() {
        return managerSalary;
    }

    public void setManagerSalary(int managerSalary) {
        this.managerSalary = managerSalary;
    }

    public String getManagerSurName() {
        return managerSurName;
    }

    public void setManagerSurName(String managerSurName) {
        this.managerSurName = managerSurName;
    }

    public String getManagerTitle() {
        return managerTitle;
    }

    public void setManagerTitle(String managerTitle) {
        this.managerTitle = managerTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRequestorManagerId() {
        return requestorManagerId;
    }

    public void setRequestorManagerId(Long requestorManagerId) {
        this.requestorManagerId = requestorManagerId;
    }

    public String getRequestorManagerMail() {
        return requestorManagerMail;
    }

    public void setRequestorManagerMail(String requestorManagerMail) {
        this.requestorManagerMail = requestorManagerMail;
    }

    public String getRequestorManagerName() {
        return requestorManagerName;
    }

    public void setRequestorManagerName(String requestorManagerName) {
        this.requestorManagerName = requestorManagerName;
    }

    public String getRequestorManagerPhoneNumber() {
        return requestorManagerPhoneNumber;
    }

    public void setRequestorManagerPhoneNumber(String requestorManagerPhoneNumber) {
        this.requestorManagerPhoneNumber = requestorManagerPhoneNumber;
    }

    public int getRequestorManagerSalary() {
        return requestorManagerSalary;
    }

    public void setRequestorManagerSalary(int requestorManagerSalary) {
        this.requestorManagerSalary = requestorManagerSalary;
    }

    public String getRequestorManagerSurName() {
        return requestorManagerSurName;
    }

    public void setRequestorManagerSurName(String requestorManagerSurName) {
        this.requestorManagerSurName = requestorManagerSurName;
    }

    public String getRequestorManagerTitle() {
        return requestorManagerTitle;
    }

    public void setRequestorManagerTitle(String requestorManagerTitle) {
        this.requestorManagerTitle = requestorManagerTitle;
    }
}
