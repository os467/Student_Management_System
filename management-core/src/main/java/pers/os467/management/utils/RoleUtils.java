package pers.os467.management.utils;

import pers.os467.management.common.RoleEnum;

public class RoleUtils {

    public static boolean isStudent(JwtUser jwtUser) {
        return jwtUser.getRid().equals(RoleEnum.STUDENT.getRid());
    }

}
