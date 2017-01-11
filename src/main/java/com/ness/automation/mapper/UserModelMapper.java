package com.ness.automation.mapper;

import com.ness.automation.dto.UserModel;
import com.ness.automation.model.User;

public class UserModelMapper {

    public static void mergeModel(final UserModel model, User user) {
        user.setUsername(model.getUsername());
    }

    public static UserModel toModel(final User user) {
        if (user == null) return null;
        UserModel userModel = new UserModel();
        userModel.setUsername(user.getUsername());
        userModel.setUuid(String.valueOf(user.getId()));
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setPermissions(user.getPermissions());
        return userModel;
    }

}
