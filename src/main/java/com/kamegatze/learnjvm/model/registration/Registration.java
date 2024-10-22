package com.kamegatze.learnjvm.model.registration;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Registration {
    @NotNull(message = "required field")
    @Size(min = 2, max = 50, message = "This field should be more 1 character and less 51 character")
    private String lastName;
    @NotNull(message = "required field")
    @Size(min = 2, max = 50, message = "This field should be more 1 character and less 51 character")
    private String firstName;
    @NotNull(message = "required field")
    @Size(min = 2, max = 50, message = "This field should be more 1 character and less 51 character")
    private String login;
    @NotNull(message = "required field")
    @Size(min = 8, max = 50, message = "This field should be more 8 character and less 51 character")
    private String password;
    @NotNull(message = "required field")
    @Size(min = 8, max = 50, message = "This field should be more 8 character and less 51 character")
    private String retryPassword;

    public Registration() {
        this.lastName = "";
        this.firstName = "";
        this.login = "";
        this.password = "";
        this.retryPassword = "";
    }

    public Registration(String lastName, String firstName, String login, String password, String retryPassword) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
        this.retryPassword = retryPassword;
    }

    public @NotNull(message = "required field") @Size(min = 2, max = 50, message = "This field should be more 1 character and less 51 character") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = "required field") @Size(min = 2, max = 50, message = "This field should be more 1 character and less 51 character") String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = "required field") @Size(min = 2, max = 50, message = "This field should be more 1 character and less 51 character") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "required field") @Size(min = 2, max = 50, message = "This field should be more 1 character and less 51 character") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "required field") @Size(min = 2, max = 50, message = "This field should be more 1 character and less 51 character") String getLogin() {
        return login;
    }

    public void setLogin(@NotNull(message = "required field") @Size(min = 2, max = 50, message = "This field should be more 1 character and less 51 character") String login) {
        this.login = login;
    }

    public @NotNull(message = "required field") @Size(min = 8, max = 50, message = "This field should be more 8 character and less 51 character") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "required field") @Size(min = 8, max = 50, message = "This field should be more 8 character and less 51 character") String password) {
        this.password = password;
    }

    public @NotNull(message = "required field") @Size(min = 8, max = 50, message = "This field should be more 8 character and less 51 character") String getRetryPassword() {
        return retryPassword;
    }

    public void setRetryPassword(@NotNull(message = "required field") @Size(min = 8, max = 50, message = "This field should be more 8 character and less 51 character") String retryPassword) {
        this.retryPassword = retryPassword;
    }
}
