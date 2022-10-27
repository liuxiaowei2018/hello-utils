package com.open.custom;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuxiaowei
 * @date 2022年10月27日 13:42
 * @Description 异常信息工具类
 */
@Slf4j
public class ThrowableUtil {

    private static final int DEFAULT_LINE_NUMBER = 10;

    /**
     * 获取所有堆栈信息
     *
     * @param e 异常
     * @return 堆栈信息
     */
    public static String getStackTrace(Throwable e) {
        StringBuilder s = new StringBuilder().append(e);
        for (StackTraceElement traceElement : e.getStackTrace()) {
            s.append("\r\n    at ").append(traceElement);
        }
        return s.toString();
    }

    /**
     * 获取最后lineNumber条堆栈信息
     *
     * @param e 异常对象
     * @param lineNumber 最后lineNumber条
     * @return 错误信息
     */
    public static String getLastStackTrace(Throwable e, Integer lineNumber) {
        Integer tmpLineNumber = lineNumber;
        if (tmpLineNumber == null) {
            tmpLineNumber = DEFAULT_LINE_NUMBER;
        }

        StringBuilder s = new StringBuilder().append(e);
        int i = 0;
        for (StackTraceElement traceElement : e.getStackTrace()) {
            s.append("\r\n    at ").append(traceElement);
            if (i >= tmpLineNumber) {
                break;
            }
            i++;
        }
        return s.toString();
    }

    /**
     * 获取以指定包名为前缀的所有堆栈信息
     *
     * @param e             异常
     * @param packagePrefix 包前缀
     * @return 堆栈信息
     */
    public static String getStackTraceByPackage(Throwable e, String packagePrefix) {
        StringBuilder s = new StringBuilder().append(e);

        for (StackTraceElement traceElement : e.getStackTrace()) {
            // 如果是指定的包
            if (traceElement.getClassName().startsWith(packagePrefix)) {
                s.append("\r\n    at ").append(traceElement);
            }
        }
        return s.toString();
    }

    /**
     * 获取以指定包名为前缀的最后lineNumber条堆栈信息
     *
     * @param e             异常
     * @param packagePrefix 包前缀
     * @param lineNumber    最后lineNumber条
     * @return 堆栈信息
     */
    public static String getLastStackTraceByPackage(Throwable e, String packagePrefix, Integer lineNumber) {
        Integer tmpLineNumber = lineNumber;
        if (tmpLineNumber == null) {
            tmpLineNumber = DEFAULT_LINE_NUMBER;
        }

        StringBuilder s = new StringBuilder().append(e);
        int i = 0;
        for (StackTraceElement traceElement : e.getStackTrace()) {
            // 如果是指定的包
            if (traceElement.getClassName().startsWith(packagePrefix)) {
                s.append("\r\n    at ").append(traceElement);
            }
            if (i >= tmpLineNumber) {
                break;
            }
            i++;
        }
        return s.toString();
    }

    /**
     * 获取最后lineNumber行简略堆栈信息（方法名加行号）
     *
     * @param e 异常对象
     * @param lineNumber 最后lineNumber行
     * @return 错误信息
     */
    public static String getBriefLastStackTrace(Throwable e, Integer lineNumber) {
        Integer tmpLineNumber = lineNumber;
        if (tmpLineNumber == null) {
            tmpLineNumber = DEFAULT_LINE_NUMBER;
        }

        StringBuilder s = new StringBuilder().append(e);
        int i = 0;
        for (StackTraceElement traceElement : e.getStackTrace()) {
            String info = traceElement.getMethodName() + ":" + traceElement.getLineNumber();

            s.append("\r\n    at ").append(info);
            if (i >= tmpLineNumber) {
                break;
            }
            i++;
        }
        return s.toString();
    }

    /**
     * 获取以指定包名为前缀的所有简略堆栈信息（方法名+包名）
     *
     * @param e             异常
     * @param packagePrefix 包前缀
     * @return 堆栈信息
     */
    public static String getBriefStackTraceByPackage(Throwable e, String packagePrefix) {
        StringBuilder s = new StringBuilder().append(e);

        for (StackTraceElement traceElement : e.getStackTrace()) {
            // 如果是指定的包
            if (traceElement.getClassName().startsWith(packagePrefix)) {
                String info = traceElement.getMethodName() + ":" + traceElement.getLineNumber();
                s.append("\r\n    at ").append(info);
            }
        }
        return s.toString();
    }

    /**
     * 获取以指定包名为前缀的最后lineNumber行简略堆栈信息（方法名+包名）
     *
     * @param e             异常
     * @param packagePrefix 包前缀
     * @param lineNumber    获取前lineNumber行输出
     * @return 堆栈信息
     */
    public static String getLastBriefStackTraceByPackage(Throwable e, String packagePrefix, Integer lineNumber) {
        Integer tmpLineNumber = lineNumber;
        if (tmpLineNumber == null) {
            tmpLineNumber = DEFAULT_LINE_NUMBER;
        }

        StringBuilder s = new StringBuilder().append(e);
        int i = 0;
        for (StackTraceElement traceElement : e.getStackTrace()) {
            // 如果是指定的包
            if (traceElement.getClassName().startsWith(packagePrefix)) {
                String info = traceElement.getMethodName() + ":" + traceElement.getLineNumber();
                s.append("\r\n    at ").append(info);
            }
            if (i >= tmpLineNumber) {
                break;
            }
            i++;
        }
        return s.toString();
    }

    public static void main(String[] args) {
        try{
            int ex = 1 / 0;
        } catch(Exception e) {
            e.printStackTrace();
            String errorMessage = ThrowableUtil.getLastStackTrace(e, 10);
            log.info(errorMessage);
            // 这里可以将errorMessage返回给前端
        }
    }

}
