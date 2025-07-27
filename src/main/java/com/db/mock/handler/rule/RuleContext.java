package com.db.mock.handler.rule;

import java.time.LocalDateTime;

public class RuleContext {
    public final String bizKey;         // 用于隔离不同表/字段的序列
    public final LocalDateTime now;     // 固定当前时间，防止多次调用时间不一致

    public RuleContext(String bizKey) {
        this.bizKey = bizKey;
        this.now = LocalDateTime.now();
    }
}
