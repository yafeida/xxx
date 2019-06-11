package com.monkey.wx.domain;

import java.util.ArrayList;
import java.util.List;

public class WxOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WxOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdIsNull() {
            addCriterion("up_order_id is null");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdIsNotNull() {
            addCriterion("up_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdEqualTo(String value) {
            addCriterion("up_order_id =", value, "upOrderId");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdNotEqualTo(String value) {
            addCriterion("up_order_id <>", value, "upOrderId");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdGreaterThan(String value) {
            addCriterion("up_order_id >", value, "upOrderId");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("up_order_id >=", value, "upOrderId");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdLessThan(String value) {
            addCriterion("up_order_id <", value, "upOrderId");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdLessThanOrEqualTo(String value) {
            addCriterion("up_order_id <=", value, "upOrderId");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdLike(String value) {
            addCriterion("up_order_id like", value, "upOrderId");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdNotLike(String value) {
            addCriterion("up_order_id not like", value, "upOrderId");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdIn(List<String> values) {
            addCriterion("up_order_id in", values, "upOrderId");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdNotIn(List<String> values) {
            addCriterion("up_order_id not in", values, "upOrderId");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdBetween(String value1, String value2) {
            addCriterion("up_order_id between", value1, value2, "upOrderId");
            return (Criteria) this;
        }

        public Criteria andUpOrderIdNotBetween(String value1, String value2) {
            addCriterion("up_order_id not between", value1, value2, "upOrderId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("open_id is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("open_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("open_id =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("open_id <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("open_id >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("open_id >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("open_id <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("open_id <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("open_id like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("open_id not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("open_id in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("open_id not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("open_id between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("open_id not between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(Double value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(Double value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(Double value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(Double value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(Double value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<Double> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<Double> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(Double value1, Double value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(Double value1, Double value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andDispriceIsNull() {
            addCriterion("disprice is null");
            return (Criteria) this;
        }

        public Criteria andDispriceIsNotNull() {
            addCriterion("disprice is not null");
            return (Criteria) this;
        }

        public Criteria andDispriceEqualTo(Double value) {
            addCriterion("disprice =", value, "disprice");
            return (Criteria) this;
        }

        public Criteria andDispriceNotEqualTo(Double value) {
            addCriterion("disprice <>", value, "disprice");
            return (Criteria) this;
        }

        public Criteria andDispriceGreaterThan(Double value) {
            addCriterion("disprice >", value, "disprice");
            return (Criteria) this;
        }

        public Criteria andDispriceGreaterThanOrEqualTo(Double value) {
            addCriterion("disprice >=", value, "disprice");
            return (Criteria) this;
        }

        public Criteria andDispriceLessThan(Double value) {
            addCriterion("disprice <", value, "disprice");
            return (Criteria) this;
        }

        public Criteria andDispriceLessThanOrEqualTo(Double value) {
            addCriterion("disprice <=", value, "disprice");
            return (Criteria) this;
        }

        public Criteria andDispriceIn(List<Double> values) {
            addCriterion("disprice in", values, "disprice");
            return (Criteria) this;
        }

        public Criteria andDispriceNotIn(List<Double> values) {
            addCriterion("disprice not in", values, "disprice");
            return (Criteria) this;
        }

        public Criteria andDispriceBetween(Double value1, Double value2) {
            addCriterion("disprice between", value1, value2, "disprice");
            return (Criteria) this;
        }

        public Criteria andDispriceNotBetween(Double value1, Double value2) {
            addCriterion("disprice not between", value1, value2, "disprice");
            return (Criteria) this;
        }

        public Criteria andZfStatusIsNull() {
            addCriterion("zf_status is null");
            return (Criteria) this;
        }

        public Criteria andZfStatusIsNotNull() {
            addCriterion("zf_status is not null");
            return (Criteria) this;
        }

        public Criteria andZfStatusEqualTo(Integer value) {
            addCriterion("zf_status =", value, "zfStatus");
            return (Criteria) this;
        }

        public Criteria andZfStatusNotEqualTo(Integer value) {
            addCriterion("zf_status <>", value, "zfStatus");
            return (Criteria) this;
        }

        public Criteria andZfStatusGreaterThan(Integer value) {
            addCriterion("zf_status >", value, "zfStatus");
            return (Criteria) this;
        }

        public Criteria andZfStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("zf_status >=", value, "zfStatus");
            return (Criteria) this;
        }

        public Criteria andZfStatusLessThan(Integer value) {
            addCriterion("zf_status <", value, "zfStatus");
            return (Criteria) this;
        }

        public Criteria andZfStatusLessThanOrEqualTo(Integer value) {
            addCriterion("zf_status <=", value, "zfStatus");
            return (Criteria) this;
        }

        public Criteria andZfStatusIn(List<Integer> values) {
            addCriterion("zf_status in", values, "zfStatus");
            return (Criteria) this;
        }

        public Criteria andZfStatusNotIn(List<Integer> values) {
            addCriterion("zf_status not in", values, "zfStatus");
            return (Criteria) this;
        }

        public Criteria andZfStatusBetween(Integer value1, Integer value2) {
            addCriterion("zf_status between", value1, value2, "zfStatus");
            return (Criteria) this;
        }

        public Criteria andZfStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("zf_status not between", value1, value2, "zfStatus");
            return (Criteria) this;
        }

        public Criteria andZfMsgIsNull() {
            addCriterion("zf_msg is null");
            return (Criteria) this;
        }

        public Criteria andZfMsgIsNotNull() {
            addCriterion("zf_msg is not null");
            return (Criteria) this;
        }

        public Criteria andZfMsgEqualTo(String value) {
            addCriterion("zf_msg =", value, "zfMsg");
            return (Criteria) this;
        }

        public Criteria andZfMsgNotEqualTo(String value) {
            addCriterion("zf_msg <>", value, "zfMsg");
            return (Criteria) this;
        }

        public Criteria andZfMsgGreaterThan(String value) {
            addCriterion("zf_msg >", value, "zfMsg");
            return (Criteria) this;
        }

        public Criteria andZfMsgGreaterThanOrEqualTo(String value) {
            addCriterion("zf_msg >=", value, "zfMsg");
            return (Criteria) this;
        }

        public Criteria andZfMsgLessThan(String value) {
            addCriterion("zf_msg <", value, "zfMsg");
            return (Criteria) this;
        }

        public Criteria andZfMsgLessThanOrEqualTo(String value) {
            addCriterion("zf_msg <=", value, "zfMsg");
            return (Criteria) this;
        }

        public Criteria andZfMsgLike(String value) {
            addCriterion("zf_msg like", value, "zfMsg");
            return (Criteria) this;
        }

        public Criteria andZfMsgNotLike(String value) {
            addCriterion("zf_msg not like", value, "zfMsg");
            return (Criteria) this;
        }

        public Criteria andZfMsgIn(List<String> values) {
            addCriterion("zf_msg in", values, "zfMsg");
            return (Criteria) this;
        }

        public Criteria andZfMsgNotIn(List<String> values) {
            addCriterion("zf_msg not in", values, "zfMsg");
            return (Criteria) this;
        }

        public Criteria andZfMsgBetween(String value1, String value2) {
            addCriterion("zf_msg between", value1, value2, "zfMsg");
            return (Criteria) this;
        }

        public Criteria andZfMsgNotBetween(String value1, String value2) {
            addCriterion("zf_msg not between", value1, value2, "zfMsg");
            return (Criteria) this;
        }

        public Criteria andZfTimeIsNull() {
            addCriterion("zf_time is null");
            return (Criteria) this;
        }

        public Criteria andZfTimeIsNotNull() {
            addCriterion("zf_time is not null");
            return (Criteria) this;
        }

        public Criteria andZfTimeEqualTo(String value) {
            addCriterion("zf_time =", value, "zfTime");
            return (Criteria) this;
        }

        public Criteria andZfTimeNotEqualTo(String value) {
            addCriterion("zf_time <>", value, "zfTime");
            return (Criteria) this;
        }

        public Criteria andZfTimeGreaterThan(String value) {
            addCriterion("zf_time >", value, "zfTime");
            return (Criteria) this;
        }

        public Criteria andZfTimeGreaterThanOrEqualTo(String value) {
            addCriterion("zf_time >=", value, "zfTime");
            return (Criteria) this;
        }

        public Criteria andZfTimeLessThan(String value) {
            addCriterion("zf_time <", value, "zfTime");
            return (Criteria) this;
        }

        public Criteria andZfTimeLessThanOrEqualTo(String value) {
            addCriterion("zf_time <=", value, "zfTime");
            return (Criteria) this;
        }

        public Criteria andZfTimeLike(String value) {
            addCriterion("zf_time like", value, "zfTime");
            return (Criteria) this;
        }

        public Criteria andZfTimeNotLike(String value) {
            addCriterion("zf_time not like", value, "zfTime");
            return (Criteria) this;
        }

        public Criteria andZfTimeIn(List<String> values) {
            addCriterion("zf_time in", values, "zfTime");
            return (Criteria) this;
        }

        public Criteria andZfTimeNotIn(List<String> values) {
            addCriterion("zf_time not in", values, "zfTime");
            return (Criteria) this;
        }

        public Criteria andZfTimeBetween(String value1, String value2) {
            addCriterion("zf_time between", value1, value2, "zfTime");
            return (Criteria) this;
        }

        public Criteria andZfTimeNotBetween(String value1, String value2) {
            addCriterion("zf_time not between", value1, value2, "zfTime");
            return (Criteria) this;
        }

        public Criteria andTransactionIdIsNull() {
            addCriterion("transaction_id is null");
            return (Criteria) this;
        }

        public Criteria andTransactionIdIsNotNull() {
            addCriterion("transaction_id is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionIdEqualTo(String value) {
            addCriterion("transaction_id =", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotEqualTo(String value) {
            addCriterion("transaction_id <>", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdGreaterThan(String value) {
            addCriterion("transaction_id >", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdGreaterThanOrEqualTo(String value) {
            addCriterion("transaction_id >=", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdLessThan(String value) {
            addCriterion("transaction_id <", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdLessThanOrEqualTo(String value) {
            addCriterion("transaction_id <=", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdLike(String value) {
            addCriterion("transaction_id like", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotLike(String value) {
            addCriterion("transaction_id not like", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdIn(List<String> values) {
            addCriterion("transaction_id in", values, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotIn(List<String> values) {
            addCriterion("transaction_id not in", values, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdBetween(String value1, String value2) {
            addCriterion("transaction_id between", value1, value2, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotBetween(String value1, String value2) {
            addCriterion("transaction_id not between", value1, value2, "transactionId");
            return (Criteria) this;
        }

        public Criteria andCzStatusIsNull() {
            addCriterion("cz_status is null");
            return (Criteria) this;
        }

        public Criteria andCzStatusIsNotNull() {
            addCriterion("cz_status is not null");
            return (Criteria) this;
        }

        public Criteria andCzStatusEqualTo(Integer value) {
            addCriterion("cz_status =", value, "czStatus");
            return (Criteria) this;
        }

        public Criteria andCzStatusNotEqualTo(Integer value) {
            addCriterion("cz_status <>", value, "czStatus");
            return (Criteria) this;
        }

        public Criteria andCzStatusGreaterThan(Integer value) {
            addCriterion("cz_status >", value, "czStatus");
            return (Criteria) this;
        }

        public Criteria andCzStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("cz_status >=", value, "czStatus");
            return (Criteria) this;
        }

        public Criteria andCzStatusLessThan(Integer value) {
            addCriterion("cz_status <", value, "czStatus");
            return (Criteria) this;
        }

        public Criteria andCzStatusLessThanOrEqualTo(Integer value) {
            addCriterion("cz_status <=", value, "czStatus");
            return (Criteria) this;
        }

        public Criteria andCzStatusIn(List<Integer> values) {
            addCriterion("cz_status in", values, "czStatus");
            return (Criteria) this;
        }

        public Criteria andCzStatusNotIn(List<Integer> values) {
            addCriterion("cz_status not in", values, "czStatus");
            return (Criteria) this;
        }

        public Criteria andCzStatusBetween(Integer value1, Integer value2) {
            addCriterion("cz_status between", value1, value2, "czStatus");
            return (Criteria) this;
        }

        public Criteria andCzStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("cz_status not between", value1, value2, "czStatus");
            return (Criteria) this;
        }

        public Criteria andCzMsgIsNull() {
            addCriterion("cz_msg is null");
            return (Criteria) this;
        }

        public Criteria andCzMsgIsNotNull() {
            addCriterion("cz_msg is not null");
            return (Criteria) this;
        }

        public Criteria andCzMsgEqualTo(String value) {
            addCriterion("cz_msg =", value, "czMsg");
            return (Criteria) this;
        }

        public Criteria andCzMsgNotEqualTo(String value) {
            addCriterion("cz_msg <>", value, "czMsg");
            return (Criteria) this;
        }

        public Criteria andCzMsgGreaterThan(String value) {
            addCriterion("cz_msg >", value, "czMsg");
            return (Criteria) this;
        }

        public Criteria andCzMsgGreaterThanOrEqualTo(String value) {
            addCriterion("cz_msg >=", value, "czMsg");
            return (Criteria) this;
        }

        public Criteria andCzMsgLessThan(String value) {
            addCriterion("cz_msg <", value, "czMsg");
            return (Criteria) this;
        }

        public Criteria andCzMsgLessThanOrEqualTo(String value) {
            addCriterion("cz_msg <=", value, "czMsg");
            return (Criteria) this;
        }

        public Criteria andCzMsgLike(String value) {
            addCriterion("cz_msg like", value, "czMsg");
            return (Criteria) this;
        }

        public Criteria andCzMsgNotLike(String value) {
            addCriterion("cz_msg not like", value, "czMsg");
            return (Criteria) this;
        }

        public Criteria andCzMsgIn(List<String> values) {
            addCriterion("cz_msg in", values, "czMsg");
            return (Criteria) this;
        }

        public Criteria andCzMsgNotIn(List<String> values) {
            addCriterion("cz_msg not in", values, "czMsg");
            return (Criteria) this;
        }

        public Criteria andCzMsgBetween(String value1, String value2) {
            addCriterion("cz_msg between", value1, value2, "czMsg");
            return (Criteria) this;
        }

        public Criteria andCzMsgNotBetween(String value1, String value2) {
            addCriterion("cz_msg not between", value1, value2, "czMsg");
            return (Criteria) this;
        }

        public Criteria andCzTimeIsNull() {
            addCriterion("cz_time is null");
            return (Criteria) this;
        }

        public Criteria andCzTimeIsNotNull() {
            addCriterion("cz_time is not null");
            return (Criteria) this;
        }

        public Criteria andCzTimeEqualTo(String value) {
            addCriterion("cz_time =", value, "czTime");
            return (Criteria) this;
        }

        public Criteria andCzTimeNotEqualTo(String value) {
            addCriterion("cz_time <>", value, "czTime");
            return (Criteria) this;
        }

        public Criteria andCzTimeGreaterThan(String value) {
            addCriterion("cz_time >", value, "czTime");
            return (Criteria) this;
        }

        public Criteria andCzTimeGreaterThanOrEqualTo(String value) {
            addCriterion("cz_time >=", value, "czTime");
            return (Criteria) this;
        }

        public Criteria andCzTimeLessThan(String value) {
            addCriterion("cz_time <", value, "czTime");
            return (Criteria) this;
        }

        public Criteria andCzTimeLessThanOrEqualTo(String value) {
            addCriterion("cz_time <=", value, "czTime");
            return (Criteria) this;
        }

        public Criteria andCzTimeLike(String value) {
            addCriterion("cz_time like", value, "czTime");
            return (Criteria) this;
        }

        public Criteria andCzTimeNotLike(String value) {
            addCriterion("cz_time not like", value, "czTime");
            return (Criteria) this;
        }

        public Criteria andCzTimeIn(List<String> values) {
            addCriterion("cz_time in", values, "czTime");
            return (Criteria) this;
        }

        public Criteria andCzTimeNotIn(List<String> values) {
            addCriterion("cz_time not in", values, "czTime");
            return (Criteria) this;
        }

        public Criteria andCzTimeBetween(String value1, String value2) {
            addCriterion("cz_time between", value1, value2, "czTime");
            return (Criteria) this;
        }

        public Criteria andCzTimeNotBetween(String value1, String value2) {
            addCriterion("cz_time not between", value1, value2, "czTime");
            return (Criteria) this;
        }

        public Criteria andAgentIsNull() {
            addCriterion("agent is null");
            return (Criteria) this;
        }

        public Criteria andAgentIsNotNull() {
            addCriterion("agent is not null");
            return (Criteria) this;
        }

        public Criteria andAgentEqualTo(String value) {
            addCriterion("agent =", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentNotEqualTo(String value) {
            addCriterion("agent <>", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentGreaterThan(String value) {
            addCriterion("agent >", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentGreaterThanOrEqualTo(String value) {
            addCriterion("agent >=", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentLessThan(String value) {
            addCriterion("agent <", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentLessThanOrEqualTo(String value) {
            addCriterion("agent <=", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentLike(String value) {
            addCriterion("agent like", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentNotLike(String value) {
            addCriterion("agent not like", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentIn(List<String> values) {
            addCriterion("agent in", values, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentNotIn(List<String> values) {
            addCriterion("agent not in", values, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentBetween(String value1, String value2) {
            addCriterion("agent between", value1, value2, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentNotBetween(String value1, String value2) {
            addCriterion("agent not between", value1, value2, "agent");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("create_time not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}