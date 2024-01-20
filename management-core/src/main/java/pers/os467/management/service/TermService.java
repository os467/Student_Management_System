package pers.os467.management.service;

import pers.os467.management.base.response.ResponseEntity;

public interface TermService {
    /**
     * 获取数据库表近四年学期值
     * @return 返回给前端的实体数据
     */
    ResponseEntity getTermWithinFourYears();
}
