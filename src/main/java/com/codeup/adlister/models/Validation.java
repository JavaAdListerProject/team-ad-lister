package com.codeup.adlister.models;

import com.codeup.adlister.util.Password;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Provide validation functions for for different classes of user Input*/
public class Validation {
    List<Response> results = new ArrayList<>();

    public static final Pattern EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public Validation() { }

    public  void checkString(String inputName, String string, Boolean canBeNull) {
        if(!canBeNull) {
            if (string.isEmpty()) results.add(new ResponseError(inputName , inputName + " should not be empty"));
        }

    }

    public  void checkString(String inputName, String string, Boolean canBeNull, Integer minLength, Integer maxLength) {
        checkString(inputName, string, canBeNull);
        if(string.length() < minLength || string.length() > maxLength) {
            results.add(new ResponseError(inputName , inputName + "length should be between " + minLength +  " and  " + maxLength + "."));
        }

    }

    public  void checkEmail(String inputName, String email) {
        checkString(inputName, email, false);
        Matcher matcher = EMAIL_ADDRESS_REGEX.matcher(email);
        if(!matcher.find())  results.add(new ResponseError(inputName ,"Email is not valid."));
    }

    public  void comparePassword(String inputName, String pw, String pwCompare) {
        if(!pw.equals(pwCompare))  results.add(new ResponseError(inputName ,"Passwords do not match"));
    }

    public  void checkAndComparePassword(String inputName, String pw, String pwCompare) {
        comparePassword(inputName, pw, pwCompare);
        checkString(inputName, pw, false);
    }

    public  void checkAndComparePassword(String inputName, String pw, String pwCompare, Integer minLength, Integer maxLength) {
        comparePassword(inputName, pw, pwCompare);
        checkString(inputName, pw, false, minLength, maxLength);

    }

    public void checkEncryptedPassword(String password, String storedHash) {

        boolean validAttempt = Password.check(password,storedHash);

        if(!validAttempt) results.add(new ResponseError("Password","Error authenticating user."));

    }

    public void checkExists(String inputName, boolean shouldExist, boolean returnedExistenceCheck) {
        // Should exist but does not
        if(shouldExist && !returnedExistenceCheck) {
            results.add(new ResponseError(inputName , inputName + " does not exist"));
        }

        if(!shouldExist && returnedExistenceCheck) {
            results.add(new ResponseError(inputName , inputName + " already exists"));
        }
    }


    public void checkValueExists(String inputName, String string, boolean shouldExist, boolean returnedExistenceCheck) {

        // Should exist but does not
        if(shouldExist && !returnedExistenceCheck) {
            results.add(new ResponseError(inputName , inputName + " does not exist"));
        }

        if(!shouldExist && returnedExistenceCheck) {
            results.add(new ResponseError(inputName , inputName + " already exists"));
        }

    }

    public List<Response> getResults() {
        return results;
    }

    public void setResults(List<Response> result) {
        this.results = results;
    }


    public Integer errorCount() {
        return results.size();
    }

    public boolean passed() {

        if(results.isEmpty()) return true;

            for(Response response : results) {
                 if (!response.isSuccess()) return false;
            }

        return true;
    }


}
