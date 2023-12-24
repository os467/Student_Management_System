package pers.os467.management.common;


import java.util.HashMap;
import java.util.Map;

public class Constant {

    public static final String NO_SCORE = "暂无成绩";

    public static final String TOKEN_COOKIE_NAME = "token";

    //session中存放jwtUser的信息的键值
    public static final String JWT_USER = "loginUser";

    public static final String REST_PASSWORD = "123456";

    //默认的头像地址
    public static final String DEFAULT_IMG_URL = "https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png";

    public static final Map<String,String> FLAG_MAP = new HashMap<>();

    public static final Integer SYSTEM_MSG_READ_STATUS_UNREAD = 0;
    public static final Integer SYSTEM_MSG_READ_STATUS_READ = 1;
    public static final Integer SYSTEM_MSG_READ_STATUS_DELETE = 2;


    static {
        //学生
        FLAG_MAP.put("snameFlag","sname");
        FLAG_MAP.put("sidFlag","sid");
        FLAG_MAP.put("clazzFlag","cid");
        //教师
        FLAG_MAP.put("tnameFlag","tname");
        FLAG_MAP.put("tidFlag","tid");
        //课程
        FLAG_MAP.put("lnameFlag","lname");
        FLAG_MAP.put("lidFlag","lid");
        //授课
        //lidFlag -> lid
        //tidFlag -> tid
        FLAG_MAP.put("skIdFlag","sk_id");

        //班级
        FLAG_MAP.put("cidFlag","cid");
        FLAG_MAP.put("clazzNameFlag","clazz_name");
        FLAG_MAP.put("gradeFlag","grade");

        //选课
        //sidFlag -> sid
        //skIdFlag -> sk_id
        FLAG_MAP.put("xkIdFlag","xk_id");

        //用户
        FLAG_MAP.put("uidFlag","uid");
        FLAG_MAP.put("usernameFlag","username");
    }

}
