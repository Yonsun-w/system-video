package com.neusoft.java.hars.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;

public class DynamicSpecifications {

	public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters,
			final Class<T> entityClazz) {
		return new Specification<T>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3727427519581673746L;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (Collections3.isNotEmpty(filters)) {

					List<Predicate> predicates = Lists.newArrayList();
					List<Predicate> orNames = Lists.newArrayList();
					for (SearchFilter filter : filters) {
						// nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}
						// logic operator
						switch (filter.operator) {
						case EQ:
							if (expression.getJavaType() == Date.class) {
								predicates.add(builder.equal(expression.as(String.class), filter.value.toString()));
							} else {
								predicates.add(builder.equal(expression, filter.value));
							}
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%" + filter.value + "%"));
							break;
						case GT:
							if (expression.getJavaType() == Date.class) {
								predicates
										.add(builder.greaterThan(expression.as(String.class), filter.value.toString()));
							} else {
								predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
							}
							break;
						case LT:
							if (expression.getJavaType() == Date.class) {
								predicates.add(builder.lessThan(expression.as(String.class), filter.value.toString()));
							} else {
								predicates.add(builder.lessThan(expression, (Comparable) filter.value));
							}
							break;
						case GTE:
							if (expression.getJavaType() == Date.class) {
								predicates.add(builder.greaterThanOrEqualTo(expression.as(String.class),
										filter.value.toString()));
							} else {
								predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
							}
							break;
						case LTE:
							if (expression.getJavaType() == Date.class) {
								predicates.add(builder.lessThanOrEqualTo(expression.as(String.class),
										filter.value.toString()));
							} else {
								predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
							}
							break;
						case NOTEQ:
							predicates.add(builder.notEqual(expression, (Comparable) filter.value));
							break;
						case ISNULL:
							predicates.add(builder.isNull(expression));
							break;
						case ISNOTNULL:
							predicates.add(builder.isNotNull(expression));
							break;
						case ORLIKE:
							orNames.add(builder.or(builder.like(expression, "%" + filter.value + "%")));
							break;
						case IN:
							List<String> keys = Arrays.asList(filter.value.toString()
									.substring(1, filter.value.toString().length() - 1).split(", "));
							predicates.add(expression.in(keys));
							break;
						case NOTIN:
							List<String> notkeys = Arrays.asList(filter.value.toString()
									.substring(1, filter.value.toString().length() - 1).split(", "));
							predicates.add(builder.not(expression.in(notkeys)));
							break;
						}
					}
					if (!orNames.isEmpty()) {
						predicates.add(builder.or(orNames.toArray(new Predicate[orNames.size()])));
					}

					// 将所有条件用 and 联合起来
					if (!predicates.isEmpty()) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}
				//TODO 39服务器上的 mysql 版本 不支持
				//				query.where(builder.conjunction());
				//				query.groupBy(root.get("name"));
				//				return query.getRestriction();
				return builder.conjunction();
			}
		};
	}
}
