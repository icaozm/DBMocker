package com.db.mock.handler.rule;

import java.util.Map;

public interface TemplateToken {
    String evaluate(Map<String, PlaceholderHandler> handlerMap, RuleContext ctx);
}

 class FixedToken implements TemplateToken {
    private final String text;
    public FixedToken(String text) { this.text = text; }
    public String evaluate(Map<String, PlaceholderHandler> map, RuleContext ctx) { return text; }
}

 class PlaceholderToken implements TemplateToken {
    private final String type;
    private final String param;
    public PlaceholderToken(String type, String param) {
        this.type = type;
        this.param = param;
    }
    public String evaluate(Map<String, PlaceholderHandler> map, RuleContext ctx) {
        PlaceholderHandler handler = map.get(type);
        if (handler == null) throw new IllegalArgumentException("不支持的类型: " + type);
        return handler.resolve(param, ctx);
    }
}