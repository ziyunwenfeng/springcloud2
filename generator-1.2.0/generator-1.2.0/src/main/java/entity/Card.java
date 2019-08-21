package entity;

import java.io.Serializable;
import java.util.*;

/**
* Author wenfeng
* Date  2019-07-26
*/
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cardNo;
    private String physicalCardNo;
    private String operatorId;
    private Timestamp activeTime;
    private String password;
    private Timestamp lastChargeTime;
    private BigDecimal balance;
    private BigDecimal lastPayAmount;
    private BigDecimal payTotalAmount;
    private String cardType;
    private Timestamp createTime;
    private String cardState;
    private String phone;
    private Timestamp lostDate;
    private Timestamp offDate;
    private String allowNegative;
    private String customerId;
    private int rateGroupId;
    private String virtualId;
    private String cardCheckFlag;
    private BigDecimal discountAmount;


    public Card(){
    }

    public void setCardNo (String cardNo) {this.cardNo = cardNo;} 
    public String getCardNo(){ return cardNo;} 
    public void setPhysicalCardNo (String physicalCardNo) {this.physicalCardNo = physicalCardNo;} 
    public String getPhysicalCardNo(){ return physicalCardNo;} 
    public void setOperatorId (String operatorId) {this.operatorId = operatorId;} 
    public String getOperatorId(){ return operatorId;} 
    public void setActiveTime (Timestamp activeTime) {this.activeTime = activeTime;} 
    public Timestamp getActiveTime(){ return activeTime;} 
    public void setPassword (String password) {this.password = password;} 
    public String getPassword(){ return password;} 
    public void setLastChargeTime (Timestamp lastChargeTime) {this.lastChargeTime = lastChargeTime;} 
    public Timestamp getLastChargeTime(){ return lastChargeTime;} 
    public void setBalance (BigDecimal balance) {this.balance = balance;} 
    public BigDecimal getBalance(){ return balance;} 
    public void setLastPayAmount (BigDecimal lastPayAmount) {this.lastPayAmount = lastPayAmount;} 
    public BigDecimal getLastPayAmount(){ return lastPayAmount;} 
    public void setPayTotalAmount (BigDecimal payTotalAmount) {this.payTotalAmount = payTotalAmount;} 
    public BigDecimal getPayTotalAmount(){ return payTotalAmount;} 
    public void setCardType (String cardType) {this.cardType = cardType;} 
    public String getCardType(){ return cardType;} 
    public void setCreateTime (Timestamp createTime) {this.createTime = createTime;} 
    public Timestamp getCreateTime(){ return createTime;} 
    public void setCardState (String cardState) {this.cardState = cardState;} 
    public String getCardState(){ return cardState;} 
    public void setPhone (String phone) {this.phone = phone;} 
    public String getPhone(){ return phone;} 
    public void setLostDate (Timestamp lostDate) {this.lostDate = lostDate;} 
    public Timestamp getLostDate(){ return lostDate;} 
    public void setOffDate (Timestamp offDate) {this.offDate = offDate;} 
    public Timestamp getOffDate(){ return offDate;} 
    public void setAllowNegative (String allowNegative) {this.allowNegative = allowNegative;} 
    public String getAllowNegative(){ return allowNegative;} 
    public void setCustomerId (String customerId) {this.customerId = customerId;} 
    public String getCustomerId(){ return customerId;} 
    public void setRateGroupId (int rateGroupId) {this.rateGroupId = rateGroupId;} 
    public int getRateGroupId(){ return rateGroupId;} 
    public void setVirtualId (String virtualId) {this.virtualId = virtualId;} 
    public String getVirtualId(){ return virtualId;} 
    public void setCardCheckFlag (String cardCheckFlag) {this.cardCheckFlag = cardCheckFlag;} 
    public String getCardCheckFlag(){ return cardCheckFlag;} 
    public void setDiscountAmount (BigDecimal discountAmount) {this.discountAmount = discountAmount;} 
    public BigDecimal getDiscountAmount(){ return discountAmount;} 

}