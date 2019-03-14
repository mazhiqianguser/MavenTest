package cn.com.test.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysPermExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysPermExample() {
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

        public Criteria andPerm_idIsNull() {
            addCriterion("perm_id is null");
            return (Criteria) this;
        }

        public Criteria andPerm_idIsNotNull() {
            addCriterion("perm_id is not null");
            return (Criteria) this;
        }

        public Criteria andPerm_idEqualTo(Integer value) {
            addCriterion("perm_id =", value, "perm_id");
            return (Criteria) this;
        }

        public Criteria andPerm_idNotEqualTo(Integer value) {
            addCriterion("perm_id <>", value, "perm_id");
            return (Criteria) this;
        }

        public Criteria andPerm_idGreaterThan(Integer value) {
            addCriterion("perm_id >", value, "perm_id");
            return (Criteria) this;
        }

        public Criteria andPerm_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("perm_id >=", value, "perm_id");
            return (Criteria) this;
        }

        public Criteria andPerm_idLessThan(Integer value) {
            addCriterion("perm_id <", value, "perm_id");
            return (Criteria) this;
        }

        public Criteria andPerm_idLessThanOrEqualTo(Integer value) {
            addCriterion("perm_id <=", value, "perm_id");
            return (Criteria) this;
        }

        public Criteria andPerm_idIn(List<Integer> values) {
            addCriterion("perm_id in", values, "perm_id");
            return (Criteria) this;
        }

        public Criteria andPerm_idNotIn(List<Integer> values) {
            addCriterion("perm_id not in", values, "perm_id");
            return (Criteria) this;
        }

        public Criteria andPerm_idBetween(Integer value1, Integer value2) {
            addCriterion("perm_id between", value1, value2, "perm_id");
            return (Criteria) this;
        }

        public Criteria andPerm_idNotBetween(Integer value1, Integer value2) {
            addCriterion("perm_id not between", value1, value2, "perm_id");
            return (Criteria) this;
        }

        public Criteria andPerm_nameIsNull() {
            addCriterion("perm_name is null");
            return (Criteria) this;
        }

        public Criteria andPerm_nameIsNotNull() {
            addCriterion("perm_name is not null");
            return (Criteria) this;
        }

        public Criteria andPerm_nameEqualTo(String value) {
            addCriterion("perm_name =", value, "perm_name");
            return (Criteria) this;
        }

        public Criteria andPerm_nameNotEqualTo(String value) {
            addCriterion("perm_name <>", value, "perm_name");
            return (Criteria) this;
        }

        public Criteria andPerm_nameGreaterThan(String value) {
            addCriterion("perm_name >", value, "perm_name");
            return (Criteria) this;
        }

        public Criteria andPerm_nameGreaterThanOrEqualTo(String value) {
            addCriterion("perm_name >=", value, "perm_name");
            return (Criteria) this;
        }

        public Criteria andPerm_nameLessThan(String value) {
            addCriterion("perm_name <", value, "perm_name");
            return (Criteria) this;
        }

        public Criteria andPerm_nameLessThanOrEqualTo(String value) {
            addCriterion("perm_name <=", value, "perm_name");
            return (Criteria) this;
        }

        public Criteria andPerm_nameLike(String value) {
            addCriterion("perm_name like", value, "perm_name");
            return (Criteria) this;
        }

        public Criteria andPerm_nameNotLike(String value) {
            addCriterion("perm_name not like", value, "perm_name");
            return (Criteria) this;
        }

        public Criteria andPerm_nameIn(List<String> values) {
            addCriterion("perm_name in", values, "perm_name");
            return (Criteria) this;
        }

        public Criteria andPerm_nameNotIn(List<String> values) {
            addCriterion("perm_name not in", values, "perm_name");
            return (Criteria) this;
        }

        public Criteria andPerm_nameBetween(String value1, String value2) {
            addCriterion("perm_name between", value1, value2, "perm_name");
            return (Criteria) this;
        }

        public Criteria andPerm_nameNotBetween(String value1, String value2) {
            addCriterion("perm_name not between", value1, value2, "perm_name");
            return (Criteria) this;
        }

        public Criteria andPerm_urlIsNull() {
            addCriterion("perm_url is null");
            return (Criteria) this;
        }

        public Criteria andPerm_urlIsNotNull() {
            addCriterion("perm_url is not null");
            return (Criteria) this;
        }

        public Criteria andPerm_urlEqualTo(String value) {
            addCriterion("perm_url =", value, "perm_url");
            return (Criteria) this;
        }

        public Criteria andPerm_urlNotEqualTo(String value) {
            addCriterion("perm_url <>", value, "perm_url");
            return (Criteria) this;
        }

        public Criteria andPerm_urlGreaterThan(String value) {
            addCriterion("perm_url >", value, "perm_url");
            return (Criteria) this;
        }

        public Criteria andPerm_urlGreaterThanOrEqualTo(String value) {
            addCriterion("perm_url >=", value, "perm_url");
            return (Criteria) this;
        }

        public Criteria andPerm_urlLessThan(String value) {
            addCriterion("perm_url <", value, "perm_url");
            return (Criteria) this;
        }

        public Criteria andPerm_urlLessThanOrEqualTo(String value) {
            addCriterion("perm_url <=", value, "perm_url");
            return (Criteria) this;
        }

        public Criteria andPerm_urlLike(String value) {
            addCriterion("perm_url like", value, "perm_url");
            return (Criteria) this;
        }

        public Criteria andPerm_urlNotLike(String value) {
            addCriterion("perm_url not like", value, "perm_url");
            return (Criteria) this;
        }

        public Criteria andPerm_urlIn(List<String> values) {
            addCriterion("perm_url in", values, "perm_url");
            return (Criteria) this;
        }

        public Criteria andPerm_urlNotIn(List<String> values) {
            addCriterion("perm_url not in", values, "perm_url");
            return (Criteria) this;
        }

        public Criteria andPerm_urlBetween(String value1, String value2) {
            addCriterion("perm_url between", value1, value2, "perm_url");
            return (Criteria) this;
        }

        public Criteria andPerm_urlNotBetween(String value1, String value2) {
            addCriterion("perm_url not between", value1, value2, "perm_url");
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

        public Criteria andPerm_iconIsNull() {
            addCriterion("perm_icon is null");
            return (Criteria) this;
        }

        public Criteria andPerm_iconIsNotNull() {
            addCriterion("perm_icon is not null");
            return (Criteria) this;
        }

        public Criteria andPerm_iconEqualTo(String value) {
            addCriterion("perm_icon =", value, "perm_icon");
            return (Criteria) this;
        }

        public Criteria andPerm_iconNotEqualTo(String value) {
            addCriterion("perm_icon <>", value, "perm_icon");
            return (Criteria) this;
        }

        public Criteria andPerm_iconGreaterThan(String value) {
            addCriterion("perm_icon >", value, "perm_icon");
            return (Criteria) this;
        }

        public Criteria andPerm_iconGreaterThanOrEqualTo(String value) {
            addCriterion("perm_icon >=", value, "perm_icon");
            return (Criteria) this;
        }

        public Criteria andPerm_iconLessThan(String value) {
            addCriterion("perm_icon <", value, "perm_icon");
            return (Criteria) this;
        }

        public Criteria andPerm_iconLessThanOrEqualTo(String value) {
            addCriterion("perm_icon <=", value, "perm_icon");
            return (Criteria) this;
        }

        public Criteria andPerm_iconLike(String value) {
            addCriterion("perm_icon like", value, "perm_icon");
            return (Criteria) this;
        }

        public Criteria andPerm_iconNotLike(String value) {
            addCriterion("perm_icon not like", value, "perm_icon");
            return (Criteria) this;
        }

        public Criteria andPerm_iconIn(List<String> values) {
            addCriterion("perm_icon in", values, "perm_icon");
            return (Criteria) this;
        }

        public Criteria andPerm_iconNotIn(List<String> values) {
            addCriterion("perm_icon not in", values, "perm_icon");
            return (Criteria) this;
        }

        public Criteria andPerm_iconBetween(String value1, String value2) {
            addCriterion("perm_icon between", value1, value2, "perm_icon");
            return (Criteria) this;
        }

        public Criteria andPerm_iconNotBetween(String value1, String value2) {
            addCriterion("perm_icon not between", value1, value2, "perm_icon");
            return (Criteria) this;
        }

        public Criteria andPerm_typeIsNull() {
            addCriterion("perm_type is null");
            return (Criteria) this;
        }

        public Criteria andPerm_typeIsNotNull() {
            addCriterion("perm_type is not null");
            return (Criteria) this;
        }

        public Criteria andPerm_typeEqualTo(Integer value) {
            addCriterion("perm_type =", value, "perm_type");
            return (Criteria) this;
        }

        public Criteria andPerm_typeNotEqualTo(Integer value) {
            addCriterion("perm_type <>", value, "perm_type");
            return (Criteria) this;
        }

        public Criteria andPerm_typeGreaterThan(Integer value) {
            addCriterion("perm_type >", value, "perm_type");
            return (Criteria) this;
        }

        public Criteria andPerm_typeGreaterThanOrEqualTo(Integer value) {
            addCriterion("perm_type >=", value, "perm_type");
            return (Criteria) this;
        }

        public Criteria andPerm_typeLessThan(Integer value) {
            addCriterion("perm_type <", value, "perm_type");
            return (Criteria) this;
        }

        public Criteria andPerm_typeLessThanOrEqualTo(Integer value) {
            addCriterion("perm_type <=", value, "perm_type");
            return (Criteria) this;
        }

        public Criteria andPerm_typeIn(List<Integer> values) {
            addCriterion("perm_type in", values, "perm_type");
            return (Criteria) this;
        }

        public Criteria andPerm_typeNotIn(List<Integer> values) {
            addCriterion("perm_type not in", values, "perm_type");
            return (Criteria) this;
        }

        public Criteria andPerm_typeBetween(Integer value1, Integer value2) {
            addCriterion("perm_type between", value1, value2, "perm_type");
            return (Criteria) this;
        }

        public Criteria andPerm_typeNotBetween(Integer value1, Integer value2) {
            addCriterion("perm_type not between", value1, value2, "perm_type");
            return (Criteria) this;
        }

        public Criteria andPerm_stateIsNull() {
            addCriterion("perm_state is null");
            return (Criteria) this;
        }

        public Criteria andPerm_stateIsNotNull() {
            addCriterion("perm_state is not null");
            return (Criteria) this;
        }

        public Criteria andPerm_stateEqualTo(Integer value) {
            addCriterion("perm_state =", value, "perm_state");
            return (Criteria) this;
        }

        public Criteria andPerm_stateNotEqualTo(Integer value) {
            addCriterion("perm_state <>", value, "perm_state");
            return (Criteria) this;
        }

        public Criteria andPerm_stateGreaterThan(Integer value) {
            addCriterion("perm_state >", value, "perm_state");
            return (Criteria) this;
        }

        public Criteria andPerm_stateGreaterThanOrEqualTo(Integer value) {
            addCriterion("perm_state >=", value, "perm_state");
            return (Criteria) this;
        }

        public Criteria andPerm_stateLessThan(Integer value) {
            addCriterion("perm_state <", value, "perm_state");
            return (Criteria) this;
        }

        public Criteria andPerm_stateLessThanOrEqualTo(Integer value) {
            addCriterion("perm_state <=", value, "perm_state");
            return (Criteria) this;
        }

        public Criteria andPerm_stateIn(List<Integer> values) {
            addCriterion("perm_state in", values, "perm_state");
            return (Criteria) this;
        }

        public Criteria andPerm_stateNotIn(List<Integer> values) {
            addCriterion("perm_state not in", values, "perm_state");
            return (Criteria) this;
        }

        public Criteria andPerm_stateBetween(Integer value1, Integer value2) {
            addCriterion("perm_state between", value1, value2, "perm_state");
            return (Criteria) this;
        }

        public Criteria andPerm_stateNotBetween(Integer value1, Integer value2) {
            addCriterion("perm_state not between", value1, value2, "perm_state");
            return (Criteria) this;
        }

        public Criteria andPerm_codeIsNull() {
            addCriterion("perm_code is null");
            return (Criteria) this;
        }

        public Criteria andPerm_codeIsNotNull() {
            addCriterion("perm_code is not null");
            return (Criteria) this;
        }

        public Criteria andPerm_codeEqualTo(String value) {
            addCriterion("perm_code =", value, "perm_code");
            return (Criteria) this;
        }

        public Criteria andPerm_codeNotEqualTo(String value) {
            addCriterion("perm_code <>", value, "perm_code");
            return (Criteria) this;
        }

        public Criteria andPerm_codeGreaterThan(String value) {
            addCriterion("perm_code >", value, "perm_code");
            return (Criteria) this;
        }

        public Criteria andPerm_codeGreaterThanOrEqualTo(String value) {
            addCriterion("perm_code >=", value, "perm_code");
            return (Criteria) this;
        }

        public Criteria andPerm_codeLessThan(String value) {
            addCriterion("perm_code <", value, "perm_code");
            return (Criteria) this;
        }

        public Criteria andPerm_codeLessThanOrEqualTo(String value) {
            addCriterion("perm_code <=", value, "perm_code");
            return (Criteria) this;
        }

        public Criteria andPerm_codeLike(String value) {
            addCriterion("perm_code like", value, "perm_code");
            return (Criteria) this;
        }

        public Criteria andPerm_codeNotLike(String value) {
            addCriterion("perm_code not like", value, "perm_code");
            return (Criteria) this;
        }

        public Criteria andPerm_codeIn(List<String> values) {
            addCriterion("perm_code in", values, "perm_code");
            return (Criteria) this;
        }

        public Criteria andPerm_codeNotIn(List<String> values) {
            addCriterion("perm_code not in", values, "perm_code");
            return (Criteria) this;
        }

        public Criteria andPerm_codeBetween(String value1, String value2) {
            addCriterion("perm_code between", value1, value2, "perm_code");
            return (Criteria) this;
        }

        public Criteria andPerm_codeNotBetween(String value1, String value2) {
            addCriterion("perm_code not between", value1, value2, "perm_code");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
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