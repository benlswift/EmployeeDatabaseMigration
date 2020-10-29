package org.sparta.ben.start;

public class DataVerification {
    public boolean verifyEmail(String email){
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");
    }
}
