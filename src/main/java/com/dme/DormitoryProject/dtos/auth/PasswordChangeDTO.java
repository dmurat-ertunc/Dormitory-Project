package com.dme.DormitoryProject.dtos.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PasswordChangeDTO {
    @NotNull(message = "Eski şifre alanı boş geçilemez")
    @NotEmpty(message = "Eski şifre alanı boş geçilemez")
    private String oldPassword;
    @NotNull(message = "Yeni şifre alanı boş geçilemez")
    @NotEmpty(message = "Yeni şifre alanı boş geçilemez")
    private String newPassword;
    @NotNull(message = "Yeni şifre tekrar alanı boş geçilemez")
    @NotEmpty(message = "Yeni şifre tekrar alanı boş geçilemez")
    private String newPasswordAgain;

    public PasswordChangeDTO() {
    }

    public PasswordChangeDTO(PasswordChangeDTO passwordChangeDTO) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordAgain = newPasswordAgain;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordAgain() {
        return newPasswordAgain;
    }

    public void setNewPasswordAgain(String newPasswordAgain) {
        this.newPasswordAgain = newPasswordAgain;
    }
}
