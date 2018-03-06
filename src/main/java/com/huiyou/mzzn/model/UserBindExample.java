package com.huiyou.mzzn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import me.fishlord.common.pageable.PageExample;

public class UserBindExample extends PageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserBindExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
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

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andBindUserIdIsNull() {
            addCriterion("bind_user_id is null");
            return (Criteria) this;
        }

        public Criteria andBindUserIdIsNotNull() {
            addCriterion("bind_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andBindUserIdEqualTo(Long value) {
            addCriterion("bind_user_id =", value, "bindUserId");
            return (Criteria) this;
        }

        public Criteria andBindUserIdNotEqualTo(Long value) {
            addCriterion("bind_user_id <>", value, "bindUserId");
            return (Criteria) this;
        }

        public Criteria andBindUserIdGreaterThan(Long value) {
            addCriterion("bind_user_id >", value, "bindUserId");
            return (Criteria) this;
        }

        public Criteria andBindUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bind_user_id >=", value, "bindUserId");
            return (Criteria) this;
        }

        public Criteria andBindUserIdLessThan(Long value) {
            addCriterion("bind_user_id <", value, "bindUserId");
            return (Criteria) this;
        }

        public Criteria andBindUserIdLessThanOrEqualTo(Long value) {
            addCriterion("bind_user_id <=", value, "bindUserId");
            return (Criteria) this;
        }

        public Criteria andBindUserIdIn(List<Long> values) {
            addCriterion("bind_user_id in", values, "bindUserId");
            return (Criteria) this;
        }

        public Criteria andBindUserIdNotIn(List<Long> values) {
            addCriterion("bind_user_id not in", values, "bindUserId");
            return (Criteria) this;
        }

        public Criteria andBindUserIdBetween(Long value1, Long value2) {
            addCriterion("bind_user_id between", value1, value2, "bindUserId");
            return (Criteria) this;
        }

        public Criteria andBindUserIdNotBetween(Long value1, Long value2) {
            addCriterion("bind_user_id not between", value1, value2, "bindUserId");
            return (Criteria) this;
        }

        public Criteria andNowLatitudeIsNull() {
            addCriterion("now_latitude is null");
            return (Criteria) this;
        }

        public Criteria andNowLatitudeIsNotNull() {
            addCriterion("now_latitude is not null");
            return (Criteria) this;
        }

        public Criteria andNowLatitudeEqualTo(Double value) {
            addCriterion("now_latitude =", value, "nowLatitude");
            return (Criteria) this;
        }

        public Criteria andNowLatitudeNotEqualTo(Double value) {
            addCriterion("now_latitude <>", value, "nowLatitude");
            return (Criteria) this;
        }

        public Criteria andNowLatitudeGreaterThan(Double value) {
            addCriterion("now_latitude >", value, "nowLatitude");
            return (Criteria) this;
        }

        public Criteria andNowLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("now_latitude >=", value, "nowLatitude");
            return (Criteria) this;
        }

        public Criteria andNowLatitudeLessThan(Double value) {
            addCriterion("now_latitude <", value, "nowLatitude");
            return (Criteria) this;
        }

        public Criteria andNowLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("now_latitude <=", value, "nowLatitude");
            return (Criteria) this;
        }

        public Criteria andNowLatitudeIn(List<Double> values) {
            addCriterion("now_latitude in", values, "nowLatitude");
            return (Criteria) this;
        }

        public Criteria andNowLatitudeNotIn(List<Double> values) {
            addCriterion("now_latitude not in", values, "nowLatitude");
            return (Criteria) this;
        }

        public Criteria andNowLatitudeBetween(Double value1, Double value2) {
            addCriterion("now_latitude between", value1, value2, "nowLatitude");
            return (Criteria) this;
        }

        public Criteria andNowLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("now_latitude not between", value1, value2, "nowLatitude");
            return (Criteria) this;
        }

        public Criteria andNowLongitudeIsNull() {
            addCriterion("now_longitude is null");
            return (Criteria) this;
        }

        public Criteria andNowLongitudeIsNotNull() {
            addCriterion("now_longitude is not null");
            return (Criteria) this;
        }

        public Criteria andNowLongitudeEqualTo(Double value) {
            addCriterion("now_longitude =", value, "nowLongitude");
            return (Criteria) this;
        }

        public Criteria andNowLongitudeNotEqualTo(Double value) {
            addCriterion("now_longitude <>", value, "nowLongitude");
            return (Criteria) this;
        }

        public Criteria andNowLongitudeGreaterThan(Double value) {
            addCriterion("now_longitude >", value, "nowLongitude");
            return (Criteria) this;
        }

        public Criteria andNowLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("now_longitude >=", value, "nowLongitude");
            return (Criteria) this;
        }

        public Criteria andNowLongitudeLessThan(Double value) {
            addCriterion("now_longitude <", value, "nowLongitude");
            return (Criteria) this;
        }

        public Criteria andNowLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("now_longitude <=", value, "nowLongitude");
            return (Criteria) this;
        }

        public Criteria andNowLongitudeIn(List<Double> values) {
            addCriterion("now_longitude in", values, "nowLongitude");
            return (Criteria) this;
        }

        public Criteria andNowLongitudeNotIn(List<Double> values) {
            addCriterion("now_longitude not in", values, "nowLongitude");
            return (Criteria) this;
        }

        public Criteria andNowLongitudeBetween(Double value1, Double value2) {
            addCriterion("now_longitude between", value1, value2, "nowLongitude");
            return (Criteria) this;
        }

        public Criteria andNowLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("now_longitude not between", value1, value2, "nowLongitude");
            return (Criteria) this;
        }

        public Criteria andIsSetIsNull() {
            addCriterion("is_set is null");
            return (Criteria) this;
        }

        public Criteria andIsSetIsNotNull() {
            addCriterion("is_set is not null");
            return (Criteria) this;
        }

        public Criteria andIsSetEqualTo(Integer value) {
            addCriterion("is_set =", value, "isSet");
            return (Criteria) this;
        }

        public Criteria andIsSetNotEqualTo(Integer value) {
            addCriterion("is_set <>", value, "isSet");
            return (Criteria) this;
        }

        public Criteria andIsSetGreaterThan(Integer value) {
            addCriterion("is_set >", value, "isSet");
            return (Criteria) this;
        }

        public Criteria andIsSetGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_set >=", value, "isSet");
            return (Criteria) this;
        }

        public Criteria andIsSetLessThan(Integer value) {
            addCriterion("is_set <", value, "isSet");
            return (Criteria) this;
        }

        public Criteria andIsSetLessThanOrEqualTo(Integer value) {
            addCriterion("is_set <=", value, "isSet");
            return (Criteria) this;
        }

        public Criteria andIsSetIn(List<Integer> values) {
            addCriterion("is_set in", values, "isSet");
            return (Criteria) this;
        }

        public Criteria andIsSetNotIn(List<Integer> values) {
            addCriterion("is_set not in", values, "isSet");
            return (Criteria) this;
        }

        public Criteria andIsSetBetween(Integer value1, Integer value2) {
            addCriterion("is_set between", value1, value2, "isSet");
            return (Criteria) this;
        }

        public Criteria andIsSetNotBetween(Integer value1, Integer value2) {
            addCriterion("is_set not between", value1, value2, "isSet");
            return (Criteria) this;
        }
        
        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Double value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Double value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Double value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Double value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Double> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Double> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Double value1, Double value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Double value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Double value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Double value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Double value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Double> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Double> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Double value1, Double value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andRailRadiusIsNull() {
            addCriterion("rail_radius is null");
            return (Criteria) this;
        }

        public Criteria andRailRadiusIsNotNull() {
            addCriterion("rail_radius is not null");
            return (Criteria) this;
        }

        public Criteria andRailRadiusEqualTo(Integer value) {
            addCriterion("rail_radius =", value, "railRadius");
            return (Criteria) this;
        }

        public Criteria andRailRadiusNotEqualTo(Integer value) {
            addCriterion("rail_radius <>", value, "railRadius");
            return (Criteria) this;
        }

        public Criteria andRailRadiusGreaterThan(Integer value) {
            addCriterion("rail_radius >", value, "railRadius");
            return (Criteria) this;
        }

        public Criteria andRailRadiusGreaterThanOrEqualTo(Integer value) {
            addCriterion("rail_radius >=", value, "railRadius");
            return (Criteria) this;
        }

        public Criteria andRailRadiusLessThan(Integer value) {
            addCriterion("rail_radius <", value, "railRadius");
            return (Criteria) this;
        }

        public Criteria andRailRadiusLessThanOrEqualTo(Integer value) {
            addCriterion("rail_radius <=", value, "railRadius");
            return (Criteria) this;
        }

        public Criteria andRailRadiusIn(List<Integer> values) {
            addCriterion("rail_radius in", values, "railRadius");
            return (Criteria) this;
        }

        public Criteria andRailRadiusNotIn(List<Integer> values) {
            addCriterion("rail_radius not in", values, "railRadius");
            return (Criteria) this;
        }

        public Criteria andRailRadiusBetween(Integer value1, Integer value2) {
            addCriterion("rail_radius between", value1, value2, "railRadius");
            return (Criteria) this;
        }

        public Criteria andRailRadiusNotBetween(Integer value1, Integer value2) {
            addCriterion("rail_radius not between", value1, value2, "railRadius");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
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

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andstatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
        public Criteria andBindIsNull() {
            addCriterion("bind is null");
            return (Criteria) this;
        }

        public Criteria andBindIsNotNull() {
            addCriterion("bind is not null");
            return (Criteria) this;
        }

        public Criteria andBindEqualTo(Integer value) {
            addCriterion("bind =", value, "bind");
            return (Criteria) this;
        }

        public Criteria andBindNotEqualTo(Integer value) {
            addCriterion("bind <>", value, "bind");
            return (Criteria) this;
        }

        public Criteria andBindGreaterThan(Integer value) {
            addCriterion("bind >", value, "bind");
            return (Criteria) this;
        }

        public Criteria andBindGreaterThanOrEqualTo(Integer value) {
            addCriterion("bind >=", value, "bind");
            return (Criteria) this;
        }

        public Criteria andBindLessThan(Integer value) {
            addCriterion("bind <", value, "bind");
            return (Criteria) this;
        }

        public Criteria andBindLessThanOrEqualTo(Integer value) {
            addCriterion("bind <=", value, "bind");
            return (Criteria) this;
        }

        public Criteria andBindIn(List<Integer> values) {
            addCriterion("bind in", values, "bind");
            return (Criteria) this;
        }

        public Criteria andBindNotIn(List<Integer> values) {
            addCriterion("bind not in", values, "bind");
            return (Criteria) this;
        }

        public Criteria andBindBetween(Integer value1, Integer value2) {
            addCriterion("bind between", value1, value2, "bind");
            return (Criteria) this;
        }

        public Criteria andBindNotBetween(Integer value1, Integer value2) {
            addCriterion("bind not between", value1, value2, "bind");
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