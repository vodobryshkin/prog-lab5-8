package com.example.auth.classes.enter_account;

public class AuthorizationManager {
    private boolean authorized;

    public AuthorizationManager() {
        authorized = false;
    }

    public boolean getAuthorizationStatus() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}
