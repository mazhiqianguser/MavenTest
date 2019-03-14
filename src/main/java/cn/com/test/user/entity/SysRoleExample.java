package cn.com.test.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysRoleExample() {
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

        public Criteria andRole_idIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRole_idIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRole_idEqualTo(Integer value) {
            addCriterion("role_id =", value, "role_id");
            return (Criteria) this;
        }

        public Criteria andRole_idNotEqualTo(Integer value) {
            addCriterion("role_id <>", value, "role_id");
            return (Criteria) this;
        }

        public Criteria andRole_idGreaterThan(Integer value) {
            addCriterion("role_id >", value, "role_id");
            return (Criteria) this;
        }

        public Criteria andRole_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_id >=", value, "role_id");
            return (Criteria) this;
        }

        public Criteria andRole_idLessThan(Integer value) {
            addCriterion("role_id <", value, "role_id");
            return (Criteria) this;
        }

        public Criteria andRole_idLessThanOrEqualTo(Integer value) {
            addCriterion("role_id <=", value, "role_id");
            return (Criteria) this;
        }

        public Criteria andRole_idIn(List<Integer> values) {
            addCriterion("role_id in", values, "role_id");
            return (Criteria) this;
        }

        public Criteria andRole_idNotIn(List<Integer> values) {
            addCriterion("role_id not in", values, "role_id");
            return (Criteria) this;
        }

        public Criteria andRole_idBetween(Integer value1, Integer value2) {
            addCriterion("role_id between", value1, value2, "role_id");
            return (Criteria) this;
        }

        public Criteria andRole_idNotBetween(Integer value1, Integer value2) {
            addCriterion("role_id not between", value1, value2, "role_id");
            return (Criteria) this;
        }

        public Criteria andRole_nameIsNull() {
            addCriterion("role_name is null");
            return (Criteria) this;
        }

        public Criteria andRole_nameIsNotNull() {
            addCriterion("role_name is not null");
            return (Criteria) this;
        }

        public Criteria andRole_nameEqualTo(String value) {
            addCriterion("role_name =", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameNotEqualTo(String value) {
            addCriterion("role_name <>", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameGreaterThan(String value) {
            addCriterion("role_name >", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameGreaterThanOrEqualTo(String value) {
            addCriterion("role_name >=", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameLessThan(String value) {
            addCriterion("role_name <", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameLessThanOrEqualTo(String value) {
            addCriterion("role_name <=", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameLike(String value) {
            addCriterion("role_name like", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameNotLike(String value) {
            addCriterion("role_name not like", value, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameIn(List<String> values) {
            addCriterion("role_name in", values, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameNotIn(List<String> values) {
            addCriterion("role_name not in", values, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameBetween(String value1, String value2) {
            addCriterion("role_name between", value1, value2, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_nameNotBetween(String value1, String value2) {
            addCriterion("role_name not between", value1, value2, "role_name");
            return (Criteria) this;
        }

        public Criteria andRole_stateIsNull() {
            addCriterion("role_state is null");
            return (Criteria) this;
        }

        public Criteria andRole_stateIsNotNull() {
            addCriterion("role_state is not null");
            return (Criteria) this;
        }

        public Criteria andRole_stateEqualTo(Integer value) {
            addCriterion("role_state =", value, "role_state");
            return (Criteria) this;
        }

        public Criteria andRole_stateNotEqualTo(Integer value) {
            addCriterion("role_state <>", value, "role_state");
            return (Criteria) this;
        }

        public Criteria andRole_stateGreaterThan(Integer value) {
            addCriterion("role_state >", value, "role_state");
            return (Criteria) this;
        }

        public Criteria andRole_stateGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_state >=", value, "role_state");
            return (Criteria) this;
        }

        public Criteria andRole_stateLessThan(Integer value) {
            addCriterion("role_state <", value, "role_state");
            return (Criteria) this;
        }

        public Criteria andRole_stateLessThanOrEqualTo(Integer value) {
            addCriterion("role_state <=", value, "role_state");
            return (Criteria) this;
        }

        public Criteria andRole_stateIn(List<Integer> values) {
            addCriterion("role_state in", values, "role_state");
            return (Criteria) this;
        }

        public Criteria andRole_stateNotIn(List<Integer> values) {
            addCriterion("role_state not in", values, "role_state");
            return (Criteria) this;
        }

        public Criteria andRole_stateBetween(Integer value1, Integer value2) {
            addCriterion("role_state between", value1, value2, "role_state");
            return (Criteria) this;
        }

        public Criteria andRole_stateNotBetween(Integer value1, Integer value2) {
            addCriterion("role_state not between", value1, value2, "role_state");
            return (Criteria) this;
        }

        public Criteria andRole_codeIsNull() {
            addCriterion("role_code is null");
            return (Criteria) this;
        }

        public Criteria andRole_codeIsNotNull() {
            addCriterion("role_code is not null");
            return (Criteria) this;
        }

        public Criteria andRole_codeEqualTo(String value) {
            addCriterion("role_code =", value, "role_code");
            return (Criteria) this;
        }

        public Criteria andRole_codeNotEqualTo(String value) {
            addCriterion("role_code <>", value, "role_code");
            return (Criteria) this;
        }

        public Criteria andRole_codeGreaterThan(String value) {
            addCriterion("role_code >", value, "role_code");
            return (Criteria) this;
        }

        public Criteria andRole_codeGreaterThanOrEqualTo(String value) {
            addCriterion("role_code >=", value, "role_code");
            return (Criteria) this;
        }

        public Criteria andRole_codeLessThan(String value) {
            addCriterion("role_code <", value, "role_code");
            return (Criteria) this;
        }

        public Criteria andRole_codeLessThanOrEqualTo(String value) {
            addCriterion("role_code <=", value, "role_code");
            return (Criteria) this;
        }

        public Criteria andRole_codeLike(String value) {
            addCriterion("role_code like", value, "role_code");
            return (Criteria) this;
        }

        public Criteria andRole_codeNotLike(String value) {
            addCriterion("role_code not like", value, "role_code");
            return (Criteria) this;
        }

        public Criteria andRole_codeIn(List<String> values) {
            addCriterion("role_code in", values, "role_code");
            return (Criteria) this;
        }

        public Criteria andRole_codeNotIn(List<String> values) {
            addCriterion("role_code not in", values, "role_code");
            return (Criteria) this;
        }

        public Criteria andRole_codeBetween(String value1, String value2) {
            addCriterion("role_code between", value1, value2, "role_code");
            return (Criteria) this;
        }

        public Criteria andRole_codeNotBetween(String value1, String value2) {
            addCriterion("role_code not between", value1, value2, "role_code");
            return (Criteria) this;
        }

        public Criteria andRole_describeIsNull() {
            addCriterion("role_describe is null");
            return (Criteria) this;
        }

        public Criteria andRole_describeIsNotNull() {
            addCriterion("role_describe is not null");
            return (Criteria) this;
        }

        public Criteria andRole_describeEqualTo(String value) {
            addCriterion("role_describe =", value, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRole_describeNotEqualTo(String value) {
            addCriterion("role_describe <>", value, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRole_describeGreaterThan(String value) {
            addCriterion("role_describe >", value, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRole_describeGreaterThanOrEqualTo(String value) {
            addCriterion("role_describe >=", value, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRole_describeLessThan(String value) {
            addCriterion("role_describe <", value, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRole_describeLessThanOrEqualTo(String value) {
            addCriterion("role_describe <=", value, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRole_describeLike(String value) {
            addCriterion("role_describe like", value, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRole_describeNotLike(String value) {
            addCriterion("role_describe not like", value, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRole_describeIn(List<String> values) {
            addCriterion("role_describe in", values, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRole_describeNotIn(List<String> values) {
            addCriterion("role_describe not in", values, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRole_describeBetween(String value1, String value2) {
            addCriterion("role_describe between", value1, value2, "role_describe");
            return (Criteria) this;
        }

        public Criteria andRole_describeNotBetween(String value1, String value2) {
            addCriterion("role_describe not between", value1, value2, "role_describe");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeEqualTo(Date value) {
            addCriterion("create_time =", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThan(Date value) {
            addCriterion("create_time >", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThan(Date value) {
            addCriterion("create_time <", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIn(List<Date> values) {
            addCriterion("create_time in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeEqualTo(Date value) {
            addCriterion("update_time =", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeGreaterThan(Date value) {
            addCriterion("update_time >", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeLessThan(Date value) {
            addCriterion("update_time <", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIn(List<Date> values) {
            addCriterion("update_time in", values, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "update_time");
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