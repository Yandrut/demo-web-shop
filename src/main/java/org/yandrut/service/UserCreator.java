package org.yandrut.service;

import org.yandrut.data.UserData;

public class UserCreator {

    private static final String email = DataReader.getTestData("");
    private static final String password = DataReader.getTestData("user.password");

    private UserCreator() {}

    public static UserData createUser() {
        return new UserData(email, password);
    }
}
