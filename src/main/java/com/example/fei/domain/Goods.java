package com.example.fei.domain;

import java.math.BigDecimal;

public class Goods {

    // private static final long serialVersionUID = 1L;

    // 非数据库字段 ****************************************************************************************************
    private String startTime;
    private String endTime;
    private BigDecimal allCount;

    // 数据库字段 *******************************************************************************************************
    /** 商品ID */
    private long id;

    /** 商品名称 */
    private String goodsName;

    /** 单价 */
    private BigDecimal unitPrice;

    /** 数量 */
    private Long goodsNumber;

    /** 总价格 */
    private BigDecimal countPrice;

    /** 消费途径[线下;线上] */
    private String consumeWay;

    /** 商品单号分组 */
    private String goodsNumType;

    /** 备注 */
    private String goodsComment;

    /** 购买时间 */
    private String consumeTime;

    /** 入库时间 */
    private String createdTime;

    /** 删除次数(0:正常) */
    private String delNum;

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

    public BigDecimal getAllCount() {
        return allCount;
    }

    public void setAllCount(BigDecimal allCount) {
        this.allCount = allCount;
    }

    // 数据库字段 *******************************************************************************************************
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Long goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public BigDecimal getCountPrice() {
        return countPrice;
    }

    public void setCountPrice(BigDecimal countPrice) {
        this.countPrice = countPrice;
    }

    public String getConsumeWay() {
        return consumeWay;
    }

    public void setConsumeWay(String consumeWay) {
        this.consumeWay = consumeWay;
    }

    public String getGoodsNumType() {
        return goodsNumType;
    }

    public void setGoodsNumType(String goodsNumType) {
        this.goodsNumType = goodsNumType;
    }

    public String getGoodsComment() {
        return goodsComment;
    }

    public void setGoodsComment(String goodsComment) {
        this.goodsComment = goodsComment;
    }

    public String getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(String consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getDelNum() {
        return delNum;
    }

    public void setDelNum(String delNum) {
        this.delNum = delNum;
    }



}
