package pers.os467.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.entity.XK;
import pers.os467.management.mapper.XKMapper;
import pers.os467.management.service.*;
import pers.os467.management.utils.JwtUser;
import pers.os467.management.utils.RoleUtils;
import pers.os467.management.vo.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class XKServiceImpl implements XKService {
    @Autowired
    private XKMapper xkMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SKService skService;

    @Override
    public Integer xkCountBySKId(Integer skId) {
        return xkMapper.xkCountBySKId(skId);
    }

    @Override
    public void deleteXKWithSid(Integer sid) {
        xkMapper.deleteXKWithSid(sid);
    }

    @Override
    public ScoreVo getScoreResult(String likeName, String flag, String term, String filterFlag) {
        String col = Constant.FLAG_MAP.get(flag);

        ScoreVo scoreVo = new ScoreVo();
        if (!insertStudent(likeName, scoreVo)) return null;
        if (!insertSK(likeName, scoreVo)) return null;

        //按照表中存在字段查询
        if (filterFlag.equals("averageFlag")){
            //平均分
            String score = xkMapper.averageScoreFilterByCol(likeName,col,term);
            if (score != null){
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                scoreVo.setScore(decimalFormat.format(Double.valueOf(score)));
                scoreVo.setTerm(term);
                return scoreVo;
            }
        }else if (filterFlag.equals("highestFlag")){
            //最高分
            XK xk = xkMapper.highestScoreFilterByCol(likeName, col, term);
            if (xk != null){
                scoreVo.setScore(xk.getScore());
                scoreVo.setSkId(xk.getSkId().toString());
                scoreVo.setTerm(term);
                GetStudentBySidVo studentBySid = studentService.getStudentBySid(xk.getSid());
                scoreVo.setSname(studentBySid.getSname());
                return scoreVo;
            }
        }else if (filterFlag.equals("lowestFlag")){
            //最低分
            XK xk = xkMapper.lowestScoreFilterByCol(likeName, col, term);
            if (xk != null){
                scoreVo.setScore(xk.getScore());
                scoreVo.setSkId(xk.getSkId().toString());
                scoreVo.setTerm(term);
                GetStudentBySidVo studentBySid = studentService.getStudentBySid(xk.getSid());
                scoreVo.setSname(studentBySid.getSname());
                return scoreVo;
            }
        }else if (filterFlag.equals("passRateFlag") && !col.equals("sid")){
            //学生及格率不存在 课程及格率可以计算
            //及格率
            DecimalFormat decimalFormat = new DecimalFormat("0.00%");
            String passRate = xkMapper.passRate(likeName, col, term);
            if (passRate != null){
                scoreVo.setSkId(likeName);
                scoreVo.setPassRate(decimalFormat.format(Double.valueOf(passRate)));
                scoreVo.setTerm(term);
                return scoreVo;
            }
        }
        //无查询结果
        return null;
    }

    @Override
    public ScoreVo getScoreByClazzResult(String likeName, String skId, String term, String filterFlag) {

        ScoreVo scoreVo = new ScoreVo();

        //按照表中存在字段查询
        if (filterFlag.equals("averageFlag")){
            //平均分
            String score = xkMapper.averageScoreByClazzFilterByCol(likeName,skId,term);
            if (score != null){
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                scoreVo.setSkId(skId);
                scoreVo.setCid(likeName);
                scoreVo.setScore(decimalFormat.format(Double.valueOf(score)));
                scoreVo.setTerm(term);
                return scoreVo;
            }
        }else if (filterFlag.equals("highestFlag")){
            //最高分
            XK xk = xkMapper.highestScoreByClazzFilterByCol(likeName, skId, term);
            if (xk != null){
                scoreVo.setCid(likeName);
                scoreVo.setScore(xk.getScore());
                scoreVo.setSkId(xk.getSkId().toString());
                scoreVo.setTerm(term);
                GetStudentBySidVo studentBySid = studentService.getStudentBySid(xk.getSid());
                scoreVo.setSname(studentBySid.getSname());
                return scoreVo;
            }
        }else if (filterFlag.equals("lowestFlag")){
            //最低分
            XK xk = xkMapper.lowestScoreByClazzFilterByCol(likeName, skId, term);
            if (xk != null){
                scoreVo.setCid(likeName);
                scoreVo.setScore(xk.getScore());
                scoreVo.setSkId(xk.getSkId().toString());
                scoreVo.setTerm(term);
                GetStudentBySidVo studentBySid = studentService.getStudentBySid(xk.getSid());
                scoreVo.setSname(studentBySid.getSname());
                return scoreVo;
            }
        }else if (filterFlag.equals("passRateFlag")){
            String passRate = xkMapper.passRateByClazz(likeName,skId, term);
            if (passRate != null){
                DecimalFormat decimalFormat = new DecimalFormat("0.00%");
                scoreVo.setCid(likeName);
                scoreVo.setSkId(skId);
                scoreVo.setPassRate(decimalFormat.format(Double.valueOf(passRate)));
                scoreVo.setTerm(term);
                return scoreVo;
            }
        }
        //无查询结果
        return null;
    }

    private boolean insertSK(String likeName, ScoreVo scoreVo) {
        Integer skId;
        try {
            //如果有授课信息则插入
            skId = Integer.parseInt(likeName);
            GetSKBySKIdVo skBySKID = null;
            if (skId != null){
                skBySKID = skService.getSKBySKId(skId);
            }
            //如果有授课信息则插入
            if (skBySKID != null){
                scoreVo.setSkId(skId.toString());
            }
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private boolean insertStudent(String likeName, ScoreVo scoreVo) {
        Integer sid;
        try {
            //尝试查询学生信息
            sid = Integer.parseInt(likeName);
            GetStudentBySidVo studentBySid = null;
            if (sid != null){
                studentBySid = studentService.getStudentBySid(sid);
            }
            //如果有学生信息则插入
            if (studentBySid != null){
                scoreVo.setSname(studentBySid.getSname());
                scoreVo.setCid(studentBySid.getCid().toString());
            }
        }catch (NumberFormatException e){
            //数据格式异常
            return false;
        }
        return true;
    }


    @Override
    public int deleteXKByXKId(Integer xkId) {
        int i = 0;
        if (xkId != null){
            i = xkMapper.deleteXKByXKId(xkId);
        }
        return i;
    }

    @Override
    public ResponseEntity addXK(XK xk) {
        //检查是否有重复选课记录
        XK xkBySidAndSKId = xkMapper.getXKBySidAndSKId(xk.getSid(),xk.getSkId());
        if (xkBySidAndSKId != null){
            //存在重复选课
            return ResponseUtils.getFailResult("存在重复选课");
        }else {
            //可以选课
            //新增选课暂无成绩
            xk.setScore(Constant.NO_SCORE);
            int i = xkMapper.addXK(xk);
            if (i > 0){
                return ResponseUtils.getSuccessResult();
            }
            return ResponseUtils.getFailResult("操作失败");
        }
    }

    @Override
    public GetXKByXKIdVo getXKByXKId(Integer xkId) {
        XK xk = xkMapper.getXKByXKId(xkId);

        GetStudentBySidVo studentBySid = studentService.getStudentBySid(xk.getSid());
        GetSKBySKIdVo skBySKId = skService.getSKBySKId(xk.getSkId());

        GetXKByXKIdVo getXKByXKIdVo = new GetXKByXKIdVo(xk.getXkId(),
                studentBySid.getSname(),
                skBySKId.getLname(),
                skBySKId.getTname(),
                xk.getTerm(),
                xk.getScore(),
                xk.getSid(),
                xk.getSkId()
        );
        return getXKByXKIdVo;
    }

    @Override
    public int updateXK(XK xk) {
        int i = 0;
        if (xk.getSkId() != null){
            i = xkMapper.updateXK(xk);
        }
        return i;
    }

    @Override
    public int getXKListByLikeNameTotal(String likeName, String flag, Boolean precise, JwtUser jwtUser) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return 0;
        }else {
            if (RoleUtils.isStudent(jwtUser)){
                //如果是学生则检查是否存在记录条目
                return xkMapper.getXKListByLikeNameTotalWithSid(likeName,col,precise,jwtUser.getUsername());
            }
            return xkMapper.getXKListByLikeNameTotal(likeName,precise,col);
        }
    }

    @Override
    public List<XKVo> getXKListByLikeName(int startIndex, int pageSize, String likeName, String flag, Boolean precise, JwtUser jwtUser) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return null;
        }else {
            if (RoleUtils.isStudent(jwtUser)){
                List<XK> xkListByLikeName = xkMapper.getXKListByLikeNameWithSid(startIndex, pageSize, likeName,col,precise,jwtUser.getUsername());
                List<XKVo> xkVoList = toXKVoList(xkListByLikeName);
                return xkVoList;
            }
            List<XK> xkListByLikeName = xkMapper.getXKListByLikeName(startIndex, pageSize, likeName,col,precise);
            List<XKVo> xkVoList = toXKVoList(xkListByLikeName);
            return xkVoList;
        }
    }


    private List<XKVo> toXKVoList(List<XK> xkList) {
        List<XKVo> xkVoList = xkList.stream().map((xk) -> {
            GetStudentBySidVo studentBySid = studentService.getStudentBySid(xk.getSid());
            GetSKBySKIdVo skBySKId = skService.getSKBySKId(xk.getSkId());
            XKVo xkVo = new XKVo(
                    xk.getXkId(),
                    studentBySid.getSname(),
                    xk.getSkId(),
                    skBySKId.getLname(),
                    skBySKId.getTname(),
                    xk.getTerm(),
                    xk.getScore()
            );
            return xkVo;
        }).collect(Collectors.toList());
        return xkVoList;
    }

}
