package pers.os467.management.common;

public enum RoleEnum {
    ADMIN(1,"管理员"),
    TEACHER(2,"教师"),
    STUDENT(3,"学生")
    ;

    private final Integer rid;

    private final String rname;

    RoleEnum(Integer rid, String rname) {
        this.rid = rid;
        this.rname = rname;
    }

    public Integer getRid() {
        return rid;
    }

    public String getRname() {
        return rname;
    }
}
