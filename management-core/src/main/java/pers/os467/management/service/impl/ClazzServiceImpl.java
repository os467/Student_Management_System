package pers.os467.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.os467.management.common.Constant;
import pers.os467.management.entity.Clazz;
import pers.os467.management.mapper.ClazzMapper;
import pers.os467.management.service.ClazzService;
import pers.os467.management.vo.ClazzVo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public String getClazzNameByCid(Integer cid) {
        return clazzMapper.getClazzNameByCid(cid);
    }

    @Override
    public List<Clazz> getClazzList() {
        return clazzMapper.getClazzList();
    }

    @Override
    public List<ClazzVo> getClazzPageList(int startIndex, int pageSize) {
        List<Clazz> clazzList = clazzMapper.getClazzPageList(startIndex, pageSize);

        List<ClazzVo> clazzVoList = toClazzVoList(clazzList);

        return clazzVoList;
    }

    @Override
    public int addClazzMemberNumByCid(int i, Integer cid) {
        Clazz clazz = getClazzByCid(cid);
        clazz.setNum(clazz.getNum() + i);
        return updateClazz(clazz);
    }

    @Override
    public int updateClazzByEditPage(Clazz clazz) {
        Clazz clazzByCid = getClazzByCid(clazz.getCid());
        clazz.setNum(clazzByCid.getNum());
        return updateClazz(clazz);
    }

    @Override
    public int getClazzTotal() {
        return clazzMapper.getClazzTotal();
    }

    @Override
    public int deleteClazzByCid(Integer cid) {
        Clazz clazzByCid = getClazzByCid(cid);
        if (clazzByCid.getNum() > 0){
            //不能删除人数大于0的班级,直接返回
            return 0;
        }
        int i = 0;
        if (cid != null){
            i = clazzMapper.deleteClazzByCid(cid);
        }
        return i;
    }

    @Override
    public int addClazz(Clazz clazz) {
        //新班级人数为0
        clazz.setNum(0);
        return clazzMapper.addClazz(clazz);
    }

    public Clazz getClazzByCid(Integer cid) {
        Clazz clazz = clazzMapper.getClazzByCid(cid);
        return clazz;
    }

    @Override
    public int updateClazz(Clazz clazz) {
        int i = 0;
        if (clazz.getCid() != null){
            i = clazzMapper.updateClazz(clazz);
        }
        return i;
    }

    @Override
    public int getClazzListByLikeNameTotal(String likeName, String flag, Boolean precise) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return 0;
        }else {
            return clazzMapper.getClazzListByLikeNameTotal(likeName,col,precise);
        }
    }

    @Override
    public List<ClazzVo> getClazzListByLikeName(int startIndex, int pageSize, String likeName, String flag, Boolean precise) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return null;
        }else {
            List<Clazz> clazzListByLikeName = clazzMapper.getClazzListByLikeName(startIndex, pageSize, likeName,col,precise);
            List<ClazzVo> clazzVoList = toClazzVoList(clazzListByLikeName);
            return clazzVoList;
        }
    }


    private List<ClazzVo> toClazzVoList(List<Clazz> clazzList) {

        List<ClazzVo> clazzVoList = clazzList.stream().map((clazz) -> {
            ClazzVo clazzVo = new ClazzVo(clazz.getCid(),
                    clazz.getNum(),
                    clazz.getGrade(),
                    clazz.getClazzName()
            );
            return clazzVo;
        }).collect(Collectors.toList());
        return clazzVoList;
    }
}
