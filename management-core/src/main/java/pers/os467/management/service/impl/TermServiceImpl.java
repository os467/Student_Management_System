package pers.os467.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.entity.Term;
import pers.os467.management.mapper.TermMapper;
import pers.os467.management.service.TermService;
import pers.os467.management.vo.TermVo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    private TermMapper termMapper;

    @Override
    public ResponseEntity getTermWithinFourYears() {
        long limit = System.currentTimeMillis()
                //四年
                - 1000L * 60L * 60L * 24L * 365L * 4L;
        Date date = new Date(limit);
        List<Term> termList = termMapper.getTermWithinFourYears(date);

        return ResponseUtils.getSuccessResult(toTermVoList(termList));
    }

    private List<TermVo> toTermVoList(List<Term> termList) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM");
        List<TermVo> termVoList = termList.stream().map((term -> {
            String start = simpleDateFormat.format(term.getTermStart());
            String end = simpleDateFormat.format(term.getTermEnd());
            return new TermVo(term.getTid(), start + "-" + end);
        })).collect(Collectors.toList());

        return termVoList;
    }
}
