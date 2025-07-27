package com.db.mock.handler.rule;

import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public interface PlaceholderHandler {
    String resolve(String param, RuleContext ctx);
}

// 日期处理器
class DateHandler implements PlaceholderHandler {
    public String resolve(String format, RuleContext ctx) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return ctx.now.format(formatter);
    }
}

// 自增序列处理器
class SeqHandler implements PlaceholderHandler {
    private static final ConcurrentHashMap<String, AtomicLong> counterMap = new ConcurrentHashMap<>();

    public String resolve(String lengthStr, RuleContext ctx) {
        int length = Integer.parseInt(lengthStr);
        String key = ctx.bizKey;
        AtomicLong counter = counterMap.computeIfAbsent(key, k -> new AtomicLong(1));
        long val = counter.getAndIncrement();
        return String.format("%0" + length + "d", val);
    }
}

// UUID 处理器：默认8位，可传参数控制长度
class UuidHandler implements PlaceholderHandler {
    public String resolve(String param, RuleContext ctx) {
        int len = 8;
        if (param != null && !param.isEmpty()) {
            len = Integer.parseInt(param);
        }
        return UUID.randomUUID().toString().replace("-", "").substring(0, len);
    }
}

// 随机整数处理器：格式为 100-999
class RandHandler implements PlaceholderHandler {
    private final Random random = new Random();

    public String resolve(String param, RuleContext ctx) {
        if (param == null || !param.contains("-")) return "0";
        String[] parts = param.split("-");
        int min = Integer.parseInt(parts[0].trim());
        int max = Integer.parseInt(parts[1].trim());
        return String.valueOf(random.nextInt(max - min + 1) + min);
    }
}

// 枚举值选择器：格式为 男,女,未知
class EnumHandler implements PlaceholderHandler {
    private final Random random = new Random();

    public String resolve(String param, RuleContext ctx) {
        String[] options = param.split(",");
        return options[random.nextInt(options.length)].trim();
    }
}