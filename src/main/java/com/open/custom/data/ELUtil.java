package com.open.custom.data;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Collections;
import java.util.Map;

/**
 * @author liuxiaowei
 * @date 2022年10月27日 13:49
 * @Description el表达式
 */
public class ELUtil {

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    private static final TemplateParserContext TEMPLATE_PARSER_CONTEXT = new TemplateParserContext("${", "}");

    /**
     * @param express：el表达式
     * @param map：el表达式动态参数
     * @return
     */
    public static <T> T parse(String express, Map<String, Object> map, Class<T> clazz) {
        if (StrUtil.isBlank(express) || CollectionUtil.isEmpty(map)) {
            return null;
        }
        //创建一个EL解析器
        SpelExpression expression = (SpelExpression) PARSER.parseExpression(StrUtil.trim(express), TEMPLATE_PARSER_CONTEXT);
        //设置动态参数
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariables(map);
        context.setPropertyAccessors(Collections.singletonList(new MapAccessor()));
        expression.setEvaluationContext(context);
        return expression.getValue(map, clazz);
    }

    /**
     * @param express：el表达式
     * @return
     */
    public static <T, R> R parse(String express, T target, Class<R> clazz) {
        StandardEvaluationContext context = new StandardEvaluationContext(target);
        Expression exp5 = PARSER.parseExpression(StrUtil.trim(express), TEMPLATE_PARSER_CONTEXT);
        return exp5.getValue(context, clazz);
    }

    /**
     * @param express：el表达式
     * @param map：el表达式动态参数
     * @return
     */
    public static <T> T parse(String express, Map<String, Object> map) {
        return parse(express, map, null);
    }

}
