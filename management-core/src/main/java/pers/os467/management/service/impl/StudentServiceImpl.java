package pers.os467.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.DigestUtils;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.common.RoleEnum;
import pers.os467.management.entity.Student;
import pers.os467.management.entity.User;
import pers.os467.management.mapper.StudentMapper;
import pers.os467.management.service.ClazzService;
import pers.os467.management.service.StudentService;
import pers.os467.management.service.UserService;
import pers.os467.management.service.XKService;
import pers.os467.management.utils.JwtUser;
import pers.os467.management.utils.RoleUtils;
import pers.os467.management.vo.GetStudentBySidVo;
import pers.os467.management.vo.StudentVo;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private UserService userService;

    @Autowired
    private XKService xkService;


    private List<StudentVo> checkStudentExist(JwtUser jwtUser) {
        //学生只能查询到本学生的记录条目
        //默认学生账户名为学号
        String username = jwtUser.getUsername();
        try {
            //检查用户名是否为整数
            int sid = Integer.parseInt(username);
            Student studentBySid = studentMapper.getStudentBySid(sid);
            if (studentBySid != null){
                //查询到记录
                List<Student> studentList = new ArrayList<>();
                studentList.add(studentBySid);
                List<StudentVo> studentVoList = toStudentVoList(studentList);
                return studentVoList;
            }
        }catch (NumberFormatException e){
            //该账户没有绑定任何学生
            return new ArrayList<>();
        }
        //没有查询到记录
        return new ArrayList<>();
    }


    private int checkStudentExistNum(JwtUser jwtUser) {
        //学生只能查询到本学生的记录条目
        //默认学生账户名为学号
        String username = jwtUser.getUsername();
        try {
            //检查用户名是否为整数
            int sid = Integer.parseInt(username);
            Student studentBySid = studentMapper.getStudentBySid(sid);
            if (studentBySid != null){
                //查询到一条记录
                return 1;
            }
        }catch (NumberFormatException e){
            //该账户没有绑定任何学生
            return 0;
        }
        //没有查询到记录
        return 0;
    }

    @Override
    public int deleteStudentBySid(Integer sid) {
        int i = 0,i1 = 0;
        if (sid != null){
            Student studentBySid = studentMapper.getStudentBySid(sid);
            Integer cid = studentBySid.getCid();
            //所在班级学生数-1
            i = clazzService.addClazzMemberNumByCid(-1, cid);

            //检查是否有关联的用户账号
            User userByUserName = userService.getUserByUserName(sid.toString());
            if (userByUserName != null){
                //存在相关用户账号
                //删除关联用户
                userService.deleteUserByUid(userByUserName.getUid());
            }

            //删除相关选课记录
            xkService.deleteXKWithSid(sid);

            i1 = studentMapper.deleteStudentBySid(sid);
        }
        if (i > 0 && i1 > 0){
            return 1;
        }
        return 0;
    }

    @Override
    public ResponseEntity addStudent(Student student, Boolean createUser) {
        //查询班级目前最高位次学生序号
        Integer highestSid = studentMapper.getStudentHighestSidByCid(student.getCid());
        String sid;
        //若存在学生学号
        if (highestSid != null){
            //班级最高位次学生序号末两位若为99则不可继续增加学生
            String s = highestSid.toString();
            if (Integer.parseInt(s.substring(s.length()-2)) == 99){
                return ResponseUtils.getFailResult("操作失败,该班级学生学号超过99");
            }

            //在最高位学号基础上新增学号
            sid = highestSid + 1 + "";
        }else {
            //班级一号学生
            sid = "100" + student.getCid() + "01";
        }

        //创建用户
        if (createUser){
            String password = DigestUtils.md5DigestAsHex(Constant.REST_PASSWORD.getBytes(StandardCharsets.UTF_8));
            User user = new User(null,sid, password, RoleEnum.STUDENT.getRid(),null);
            User userByUserName = userService.getUserByUserName(sid);
            if (userByUserName != null){
                //说明存在同名用户
                return ResponseUtils.getFailResult("操作失败,存在同名用户");
            }
            //否则增加用户
            userService.addUser(user);
        }

        student.setSid(Integer.parseInt(sid));

        //更新班级表班级人数 +1
        clazzService.addClazzMemberNumByCid(1, student.getCid());
        int i = studentMapper.addStudent(student);
        if (i > 0){
            return ResponseUtils.getSuccessResult();
        }

        return ResponseUtils.getFailResult("操作失败");
    }

    @Override
    public GetStudentBySidVo getStudentBySid(Integer sid) {
        Student student = studentMapper.getStudentBySid(sid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        GetStudentBySidVo getStudentBySidVo = null;
        if (student != null){
             getStudentBySidVo = new GetStudentBySidVo(student.getSid(),
                    student.getSname(),
                    student.getCid(),
                    student.getSex(),
                    simpleDateFormat.format(student.getBirthday())
            );
        }
        return getStudentBySidVo;
    }

    @Override
    public int updateStudent(Student student) {
        int i = 0;
        if (student.getSid() != null){
            //获取到原先学生所在班级数据
            Integer sid = student.getSid();
            GetStudentBySidVo studentBySid = getStudentBySid(sid);
            Integer cid = studentBySid.getCid();

            //更新学生原来所在班级数据 人数-1
            clazzService.addClazzMemberNumByCid(-1, cid);

            //更新学生目前所在班级数据 人数+1
            clazzService.addClazzMemberNumByCid(1, student.getCid());

            i = studentMapper.updateStudent(student);
        }
        return i;
    }

    @Override
    public int getStudentListByLikeNameTotal(String likeName, String flag, Boolean precise, JwtUser jwtUser) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return 0;
        }else {
            if (RoleUtils.isStudent(jwtUser)){
                //如果是学生则检查是否存在记录条目
                return checkStudentExistNum(jwtUser);
            }
            return studentMapper.getStudentListByLikeNameTotal(likeName,col,precise);
        }
    }

    @Override
    public List<StudentVo> getStudentListByLikeName(Integer startIndex, Integer pageSize,String likeName,String flag,Boolean precise,JwtUser jwtUser) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return null;
        }else {
            if (RoleUtils.isStudent(jwtUser)){
                //如果是学生则检查是否存在记录条目
                return checkStudentExist(jwtUser);
            }
            List<Student> studentListByLikeName = studentMapper.getStudentListByLikeName(startIndex, pageSize, likeName,col,precise);
            List<StudentVo> studentVoList = toStudentVoList(studentListByLikeName);
            return studentVoList;
        }
    }

    private List<StudentVo> toStudentVoList(List<Student> studentList) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<StudentVo> studentVoList = studentList.stream().map((student) -> {
            //拿出学生表中的班级号去查询班级名称
            String clazz = clazzService.getClazzNameByCid(student.getCid());
            StudentVo studentVo = new StudentVo(student.getSid(),
                    student.getSname(),
                    student.getCid(),
                    clazz,
                    student.getSex().equals(1) ? "男" : "女",
                    simpleDateFormat.format(student.getBirthday())
            );
            return studentVo;
        }).collect(Collectors.toList());
        return studentVoList;
    }
}
