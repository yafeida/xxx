package com.monkey.wx.domain;

import java.util.ArrayList;
import java.util.List;

public class WxConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WxConfigExample() {
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

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(String value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(String value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(String value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(String value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(String value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLike(String value) {
            addCriterion("app_id like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotLike(String value) {
            addCriterion("app_id not like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<String> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<String> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(String value1, String value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(String value1, String value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNull() {
            addCriterion("app_secret is null");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNotNull() {
            addCriterion("app_secret is not null");
            return (Criteria) this;
        }

        public Criteria andAppSecretEqualTo(String value) {
            addCriterion("app_secret =", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotEqualTo(String value) {
            addCriterion("app_secret <>", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThan(String value) {
            addCriterion("app_secret >", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThanOrEqualTo(String value) {
            addCriterion("app_secret >=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThan(String value) {
            addCriterion("app_secret <", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThanOrEqualTo(String value) {
            addCriterion("app_secret <=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLike(String value) {
            addCriterion("app_secret like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotLike(String value) {
            addCriterion("app_secret not like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretIn(List<String> values) {
            addCriterion("app_secret in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotIn(List<String> values) {
            addCriterion("app_secret not in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretBetween(String value1, String value2) {
            addCriterion("app_secret between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotBetween(String value1, String value2) {
            addCriterion("app_secret not between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andTokenIsNull() {
            addCriterion("token is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("token is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(String value) {
            addCriterion("token =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(String value) {
            addCriterion("token <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(String value) {
            addCriterion("token >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(String value) {
            addCriterion("token >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(String value) {
            addCriterion("token <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(String value) {
            addCriterion("token <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLike(String value) {
            addCriterion("token like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotLike(String value) {
            addCriterion("token not like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<String> values) {
            addCriterion("token in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<String> values) {
            addCriterion("token not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(String value1, String value2) {
            addCriterion("token between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(String value1, String value2) {
            addCriterion("token not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andAeskeyIsNull() {
            addCriterion("aesKey is null");
            return (Criteria) this;
        }

        public Criteria andAeskeyIsNotNull() {
            addCriterion("aesKey is not null");
            return (Criteria) this;
        }

        public Criteria andAeskeyEqualTo(String value) {
            addCriterion("aesKey =", value, "aeskey");
            return (Criteria) this;
        }

        public Criteria andAeskeyNotEqualTo(String value) {
            addCriterion("aesKey <>", value, "aeskey");
            return (Criteria) this;
        }

        public Criteria andAeskeyGreaterThan(String value) {
            addCriterion("aesKey >", value, "aeskey");
            return (Criteria) this;
        }

        public Criteria andAeskeyGreaterThanOrEqualTo(String value) {
            addCriterion("aesKey >=", value, "aeskey");
            return (Criteria) this;
        }

        public Criteria andAeskeyLessThan(String value) {
            addCriterion("aesKey <", value, "aeskey");
            return (Criteria) this;
        }

        public Criteria andAeskeyLessThanOrEqualTo(String value) {
            addCriterion("aesKey <=", value, "aeskey");
            return (Criteria) this;
        }

        public Criteria andAeskeyLike(String value) {
            addCriterion("aesKey like", value, "aeskey");
            return (Criteria) this;
        }

        public Criteria andAeskeyNotLike(String value) {
            addCriterion("aesKey not like", value, "aeskey");
            return (Criteria) this;
        }

        public Criteria andAeskeyIn(List<String> values) {
            addCriterion("aesKey in", values, "aeskey");
            return (Criteria) this;
        }

        public Criteria andAeskeyNotIn(List<String> values) {
            addCriterion("aesKey not in", values, "aeskey");
            return (Criteria) this;
        }

        public Criteria andAeskeyBetween(String value1, String value2) {
            addCriterion("aesKey between", value1, value2, "aeskey");
            return (Criteria) this;
        }

        public Criteria andAeskeyNotBetween(String value1, String value2) {
            addCriterion("aesKey not between", value1, value2, "aeskey");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNull() {
            addCriterion("mch_id is null");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNotNull() {
            addCriterion("mch_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchIdEqualTo(String value) {
            addCriterion("mch_id =", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotEqualTo(String value) {
            addCriterion("mch_id <>", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThan(String value) {
            addCriterion("mch_id >", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThanOrEqualTo(String value) {
            addCriterion("mch_id >=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThan(String value) {
            addCriterion("mch_id <", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThanOrEqualTo(String value) {
            addCriterion("mch_id <=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLike(String value) {
            addCriterion("mch_id like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotLike(String value) {
            addCriterion("mch_id not like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdIn(List<String> values) {
            addCriterion("mch_id in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotIn(List<String> values) {
            addCriterion("mch_id not in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdBetween(String value1, String value2) {
            addCriterion("mch_id between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotBetween(String value1, String value2) {
            addCriterion("mch_id not between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchKeyIsNull() {
            addCriterion("mch_key is null");
            return (Criteria) this;
        }

        public Criteria andMchKeyIsNotNull() {
            addCriterion("mch_key is not null");
            return (Criteria) this;
        }

        public Criteria andMchKeyEqualTo(String value) {
            addCriterion("mch_key =", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyNotEqualTo(String value) {
            addCriterion("mch_key <>", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyGreaterThan(String value) {
            addCriterion("mch_key >", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyGreaterThanOrEqualTo(String value) {
            addCriterion("mch_key >=", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyLessThan(String value) {
            addCriterion("mch_key <", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyLessThanOrEqualTo(String value) {
            addCriterion("mch_key <=", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyLike(String value) {
            addCriterion("mch_key like", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyNotLike(String value) {
            addCriterion("mch_key not like", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyIn(List<String> values) {
            addCriterion("mch_key in", values, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyNotIn(List<String> values) {
            addCriterion("mch_key not in", values, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyBetween(String value1, String value2) {
            addCriterion("mch_key between", value1, value2, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyNotBetween(String value1, String value2) {
            addCriterion("mch_key not between", value1, value2, "mchKey");
            return (Criteria) this;
        }

        public Criteria andCertPathIsNull() {
            addCriterion("cert_path is null");
            return (Criteria) this;
        }

        public Criteria andCertPathIsNotNull() {
            addCriterion("cert_path is not null");
            return (Criteria) this;
        }

        public Criteria andCertPathEqualTo(String value) {
            addCriterion("cert_path =", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathNotEqualTo(String value) {
            addCriterion("cert_path <>", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathGreaterThan(String value) {
            addCriterion("cert_path >", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathGreaterThanOrEqualTo(String value) {
            addCriterion("cert_path >=", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathLessThan(String value) {
            addCriterion("cert_path <", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathLessThanOrEqualTo(String value) {
            addCriterion("cert_path <=", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathLike(String value) {
            addCriterion("cert_path like", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathNotLike(String value) {
            addCriterion("cert_path not like", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathIn(List<String> values) {
            addCriterion("cert_path in", values, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathNotIn(List<String> values) {
            addCriterion("cert_path not in", values, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathBetween(String value1, String value2) {
            addCriterion("cert_path between", value1, value2, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathNotBetween(String value1, String value2) {
            addCriterion("cert_path not between", value1, value2, "certPath");
            return (Criteria) this;
        }

        public Criteria andZfTypeIsNull() {
            addCriterion("zf_type is null");
            return (Criteria) this;
        }

        public Criteria andZfTypeIsNotNull() {
            addCriterion("zf_type is not null");
            return (Criteria) this;
        }

        public Criteria andZfTypeEqualTo(String value) {
            addCriterion("zf_type =", value, "zfType");
            return (Criteria) this;
        }

        public Criteria andZfTypeNotEqualTo(String value) {
            addCriterion("zf_type <>", value, "zfType");
            return (Criteria) this;
        }

        public Criteria andZfTypeGreaterThan(String value) {
            addCriterion("zf_type >", value, "zfType");
            return (Criteria) this;
        }

        public Criteria andZfTypeGreaterThanOrEqualTo(String value) {
            addCriterion("zf_type >=", value, "zfType");
            return (Criteria) this;
        }

        public Criteria andZfTypeLessThan(String value) {
            addCriterion("zf_type <", value, "zfType");
            return (Criteria) this;
        }

        public Criteria andZfTypeLessThanOrEqualTo(String value) {
            addCriterion("zf_type <=", value, "zfType");
            return (Criteria) this;
        }

        public Criteria andZfTypeLike(String value) {
            addCriterion("zf_type like", value, "zfType");
            return (Criteria) this;
        }

        public Criteria andZfTypeNotLike(String value) {
            addCriterion("zf_type not like", value, "zfType");
            return (Criteria) this;
        }

        public Criteria andZfTypeIn(List<String> values) {
            addCriterion("zf_type in", values, "zfType");
            return (Criteria) this;
        }

        public Criteria andZfTypeNotIn(List<String> values) {
            addCriterion("zf_type not in", values, "zfType");
            return (Criteria) this;
        }

        public Criteria andZfTypeBetween(String value1, String value2) {
            addCriterion("zf_type between", value1, value2, "zfType");
            return (Criteria) this;
        }

        public Criteria andZfTypeNotBetween(String value1, String value2) {
            addCriterion("zf_type not between", value1, value2, "zfType");
            return (Criteria) this;
        }

        public Criteria andZfInfoIsNull() {
            addCriterion("zf_info is null");
            return (Criteria) this;
        }

        public Criteria andZfInfoIsNotNull() {
            addCriterion("zf_info is not null");
            return (Criteria) this;
        }

        public Criteria andZfInfoEqualTo(String value) {
            addCriterion("zf_info =", value, "zfInfo");
            return (Criteria) this;
        }

        public Criteria andZfInfoNotEqualTo(String value) {
            addCriterion("zf_info <>", value, "zfInfo");
            return (Criteria) this;
        }

        public Criteria andZfInfoGreaterThan(String value) {
            addCriterion("zf_info >", value, "zfInfo");
            return (Criteria) this;
        }

        public Criteria andZfInfoGreaterThanOrEqualTo(String value) {
            addCriterion("zf_info >=", value, "zfInfo");
            return (Criteria) this;
        }

        public Criteria andZfInfoLessThan(String value) {
            addCriterion("zf_info <", value, "zfInfo");
            return (Criteria) this;
        }

        public Criteria andZfInfoLessThanOrEqualTo(String value) {
            addCriterion("zf_info <=", value, "zfInfo");
            return (Criteria) this;
        }

        public Criteria andZfInfoLike(String value) {
            addCriterion("zf_info like", value, "zfInfo");
            return (Criteria) this;
        }

        public Criteria andZfInfoNotLike(String value) {
            addCriterion("zf_info not like", value, "zfInfo");
            return (Criteria) this;
        }

        public Criteria andZfInfoIn(List<String> values) {
            addCriterion("zf_info in", values, "zfInfo");
            return (Criteria) this;
        }

        public Criteria andZfInfoNotIn(List<String> values) {
            addCriterion("zf_info not in", values, "zfInfo");
            return (Criteria) this;
        }

        public Criteria andZfInfoBetween(String value1, String value2) {
            addCriterion("zf_info between", value1, value2, "zfInfo");
            return (Criteria) this;
        }

        public Criteria andZfInfoNotBetween(String value1, String value2) {
            addCriterion("zf_info not between", value1, value2, "zfInfo");
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