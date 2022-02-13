package com.example.fei.domain;

import java.math.BigDecimal;

public class BillCount {

    // private static final long serialVersionUID = 1L;

    /** 统计ID */
    private long id;

    /** 开始时间00:00:00 */
    private String  startTime;

    /** 结束时间23:59:59 */
    private String endTime;

    /** 消费方式 */
    private String consumeWay;

    /** 所有消费共计 */
    private BigDecimal allCount;

    /** 删除次数(0:正常) */
    private String delNum;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getConsumeWay() {
        return consumeWay;
    }

    public void setConsumeWay(String consumeWay) {
        this.consumeWay = consumeWay;
    }

    public BigDecimal getAllCount() {
        return allCount;
    }

    public void setAllCount(BigDecimal allCount) {
        this.allCount = allCount;
    }

    public String getDelNum() {
        return delNum;
    }

    public void setDelNum(String delNum) {
        this.delNum = delNum;
    }
}
