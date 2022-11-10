package com.open.QLExpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @author liuxiaowei
 * @date 2022年11月10日 12:59
 * @Description
 */
public class QLETest {

    public static void main(String[] args) throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("city", "重庆");
        context.put("age", "字典的比较");
        context.put("sex", 1);
        String express1 = "if (city=='重庆' and age>70 and sex==1) then {return '重庆老头';} ";
        String express2 = "if (city=='上海' and age>70 and sex==0) then {return '上海老太太';}";
        ExpressRunner runner = new ExpressRunner(true, true);
        Object result1 = runner.execute(express1, context, null, false, true, null);
        Object result2 = runner.execute(express2, context, null, false, true, null);
        System.out.println("执行结果1：" + result1);
        System.out.println("执行结果2：" + result2);
    }
}
