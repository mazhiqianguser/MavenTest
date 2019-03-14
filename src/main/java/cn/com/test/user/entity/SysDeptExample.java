package cn.com.test.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysDeptExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDeptExample() {
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

        public Criteria andDept_idIsNull() {
            addCriterion("dept_id is null");
            return (Criteria) this;
        }

        public Criteria andDept_idIsNotNull() {
            addCriterion("dept_id is not null");
            return (Criteria) this;
        }

        public Criteria andDept_idEqualTo(Integer value) {
            addCriterion("dept_id =", value, "dept_id");
            return (Criteria) this;
        }

        public Criteria andDept_idNotEqualTo(Integer value) {
            addCriterion("dept_id <>", value, "dept_id");
            return (Criteria) this;
        }

        public Criteria andDept_idGreaterThan(Integer value) {
            addCriterion("dept_id >", value, "dept_id");
            return (Criteria) this;
        }

        public Criteria andDept_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("dept_id >=", value, "dept_id");
            return (Criteria) this;
        }

        public Criteria andDept_idLessThan(Integer value) {
            addCriterion("dept_id <", value, "dept_id");
            return (Criteria) this;
        }

        public Criteria andDept_idLessThanOrEqualTo(Integer value) {
            addCriterion("dept_id <=", value, "dept_id");
            return (Criteria) this;
        }

        public Criteria andDept_idIn(List<Integer> values) {
            addCriterion("dept_id in", values, "dept_id");
            return (Criteria) this;
        }

        public Criteria andDept_idNotIn(List<Integer> values) {
            addCriterion("dept_id not in", values, "dept_id");
            return (Criteria) this;
        }

        public Criteria andDept_idBetween(Integer value1, Integer value2) {
            addCriterion("dept_id between", value1, value2, "dept_id");
            return (Criteria) this;
        }

        public Criteria andDept_idNotBetween(Integer value1, Integer value2) {
            addCriterion("dept_id not between", value1, value2, "dept_id");
            return (Criteria) this;
        }

        public Criteria andDept_nameIsNull() {
            addCriterion("dept_name is null");
            return (Criteria) this;
        }

        public Criteria andDept_nameIsNotNull() {
            addCriterion("dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andDept_nameEqualTo(String value) {
            addCriterion("dept_name =", value, "dept_name");
            return (Criteria) this;
        }

        public Criteria andDept_nameNotEqualTo(String value) {
            addCriterion("dept_name <>", value, "dept_name");
            return (Criteria) this;
        }

        public Criteria andDept_nameGreaterThan(String value) {
            addCriterion("dept_name >", value, "dept_name");
            return (Criteria) this;
        }

        public Criteria andDept_nameGreaterThanOrEqualTo(String value) {
            addCriterion("dept_name >=", value, "dept_name");
            return (Criteria) this;
        }

        public Criteria andDept_nameLessThan(String value) {
            addCriterion("dept_name <", value, "dept_name");
            return (Criteria) this;
        }

        public Criteria andDept_nameLessThanOrEqualTo(String value) {
            addCriterion("dept_name <=", value, "dept_name");
            return (Criteria) this;
        }

        public Criteria andDept_nameLike(String value) {
            addCriterion("dept_name like", value, "dept_name");
            return (Criteria) this;
        }

        public Criteria andDept_nameNotLike(String value) {
            addCriterion("dept_name not like", value, "dept_name");
            return (Criteria) this;
        }

        public Criteria andDept_nameIn(List<String> values) {
            addCriterion("dept_name in", values, "dept_name");
            return (Criteria) this;
        }

        public Criteria andDept_nameNotIn(List<String> values) {
            addCriterion("dept_name not in", values, "dept_name");
            return (Criteria) this;
        }

        public Criteria andDept_nameBetween(String value1, String value2) {
            addCriterion("dept_name between", value1, value2, "dept_name");
            return (Criteria) this;
        }

        public Criteria andDept_nameNotBetween(String value1, String value2) {
            addCriterion("dept_name not between", value1, value2, "dept_name");
            return (Criteria) this;
        }

        public Criteria andParent_idIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParent_idIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParent_idEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idLessThan(Integer value) {
            addCriterion("parent_id <", value, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parent_id");
            return (Criteria) this;
        }

        public Criteria andDept_describeIsNull() {
            addCriterion("dept_describe is null");
            return (Criteria) this;
        }

        public Criteria andDept_describeIsNotNull() {
            addCriterion("dept_describe is not null");
            return (Criteria) this;
        }

        public Criteria andDept_describeEqualTo(String value) {
            addCriterion("dept_describe =", value, "dept_describe");
            return (Criteria) this;
        }

        public Criteria andDept_describeNotEqualTo(String value) {
            addCriterion("dept_describe <>", value, "dept_describe");
            return (Criteria) this;
        }

        public Criteria andDept_describeGreaterThan(String value) {
            addCriterion("dept_describe >", value, "dept_describe");
            return (Criteria) this;
        }

        public Criteria andDept_describeGreaterThanOrEqualTo(String value) {
            addCriterion("dept_describe >=", value, "dept_describe");
            return (Criteria) this;
        }

        public Criteria andDept_describeLessThan(String value) {
            addCriterion("dept_describe <", value, "dept_describe");
            return (Criteria) this;
        }

        public Criteria andDept_describeLessThanOrEqualTo(String value) {
            addCriterion("dept_describe <=", value, "dept_describe");
            return (Criteria) this;
        }

        public Criteria andDept_describeLike(String value) {
            addCriterion("dept_describe like", value, "dept_describe");
            return (Criteria) this;
        }

        public Criteria andDept_describeNotLike(String value) {
            addCriterion("dept_describe not like", value, "dept_describe");
            return (Criteria) this;
        }

        public Criteria andDept_describeIn(List<String> values) {
            addCriterion("dept_describe in", values, "dept_describe");
            return (Criteria) this;
        }

        public Criteria andDept_describeNotIn(List<String> values) {
            addCriterion("dept_describe not in", values, "dept_describe");
            return (Criteria) this;
        }

        public Criteria andDept_describeBetween(String value1, String value2) {
            addCriterion("dept_describe between", value1, value2, "dept_describe");
            return (Criteria) this;
        }

        public Criteria andDept_describeNotBetween(String value1, String value2) {
            addCriterion("dept_describe not between", value1, value2, "dept_describe");
            return (Criteria) this;
        }

        public Criteria andDept_codeIsNull() {
            addCriterion("dept_code is null");
            return (Criteria) this;
        }

        public Criteria andDept_codeIsNotNull() {
            addCriterion("dept_code is not null");
            return (Criteria) this;
        }

        public Criteria andDept_codeEqualTo(String value) {
            addCriterion("dept_code =", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeNotEqualTo(String value) {
            addCriterion("dept_code <>", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeGreaterThan(String value) {
            addCriterion("dept_code >", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeGreaterThanOrEqualTo(String value) {
            addCriterion("dept_code >=", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeLessThan(String value) {
            addCriterion("dept_code <", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeLessThanOrEqualTo(String value) {
            addCriterion("dept_code <=", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeLike(String value) {
            addCriterion("dept_code like", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeNotLike(String value) {
            addCriterion("dept_code not like", value, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeIn(List<String> values) {
            addCriterion("dept_code in", values, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeNotIn(List<String> values) {
            addCriterion("dept_code not in", values, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeBetween(String value1, String value2) {
            addCriterion("dept_code between", value1, value2, "dept_code");
            return (Criteria) this;
        }

        public Criteria andDept_codeNotBetween(String value1, String value2) {
            addCriterion("dept_code not between", value1, value2, "dept_code");
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