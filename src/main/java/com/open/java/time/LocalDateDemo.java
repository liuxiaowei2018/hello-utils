package com.open.java.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author liuxiaowei
 * @date 2022年08月18日 16:41
 * @Description Java8-LocalDate
 */
public class LocalDateDemo {

    public static void main(String[] args) {

        // Java 8 中获取今天的日期
        LocalDate now = LocalDate.now();
        System.out.println("当前日期=" + now);

        // 获取当前时间
        LocalTime time = LocalTime.now();
        System.out.println("获取当前的时间,不含有日期:" + time);

        // Java 8 中获取年、月、日信息
        System.out.println(now.getYear());
        Month month = now.getMonth();
        System.out.println(month.getValue());
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getDayOfYear());

        // 创建特定日期
        LocalDate date = LocalDate.of(2018, 2, 6);
        System.out.println("自定义日期:" + date);

        // Java 8 中判断两个日期是否相等
        if (now.equals(date)) {
            System.out.println("时间相等");
        } else {
            System.out.println("时间不等");
        }

        // 检查像生日这种周期性事件
        LocalDate date2 = LocalDate.of(2019, 2, 6);
        MonthDay birthday = MonthDay.of(date2.getMonth(), date2.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(date);
        if (currentMonthDay.equals(birthday)) {
            System.out.println("是你的生日");
        } else {
            System.out.println("你的生日还没有到");
        }

        // 增加小时、分、秒来计算将来的时间
        LocalTime newTime = time.plusHours(1);
        System.out.println(newTime);

        // 计算一周后的日期(将来时间)
        LocalDate nextWeek = now.plus(1, ChronoUnit.WEEKS);
        System.out.println("一周后的日期为:" + nextWeek);

        // 计算一年前或一年后的日期
        LocalDate pviousYear = now.minus(1, ChronoUnit.YEARS);
        System.out.println("一年前的日期 : " + pviousYear);
        LocalDate nextYear = now.plus(1, ChronoUnit.YEARS);
        System.out.println("一年后的日期:" + nextYear);

        // Clock 时钟类用于获取当时的时间戳，或当前时区下的日期时间信息。
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock.millis());
        // Returns time based on system clock zone
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + defaultClock.millis());

        // 判断日期早于还是晚于另一个日期
        LocalDate tomorrow = LocalDate.of(2022, 8, 19);
        if (tomorrow.isAfter(now)) {
            System.out.println("之后的日期:" + tomorrow);
        }
        LocalDate yesterday = now.minus(1, ChronoUnit.DAYS);
        if (yesterday.isBefore(now)) {
            System.out.println("之前的日期:" + yesterday);
        }

        // 处理时区
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);

        // 表示信用卡到期这类固定日期
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2019, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);

        // 检查闰年
        if (now.isLeapYear()) {
            System.out.println("This year is Leap year");
        } else {
            System.out.println("2022 is not a Leap year");
        }

        // 计算两个日期之间的天数和月数
        LocalDate java8Release = LocalDate.of(2018, 12, 14);
        Period periodToNextJavaRelease = Period.between(java8Release, now);
        System.out.println("Months left between today and Java 8 release : "
                + periodToNextJavaRelease.getMonths());

        // 获取当前的时间戳
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp.toEpochMilli());

        // 使用预定义的格式化工具去解析或格式化日期
        String dayAfterTommorrow = "20180205";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow,
                DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(dayAfterTommorrow+"  格式化后的日期为:  "+formatted);

        // 日期转字符串
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String str = now.format(format1);
        System.out.println("日期转换为字符串:"+str);
        // 字符串转日期
        LocalDate dateN = LocalDate.parse(str,format1);
        System.out.println("日期类型:"+dateN);
    }
}
