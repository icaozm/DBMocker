package com.db.mock.handler.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleTemplateEngine {

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\{(\\w+)(:(.+?))?\\}");

    private final Map<String, PlaceholderHandler> handlerMap = new HashMap<>();

    public RuleTemplateEngine() {
        // 注册内置 handler
        handlerMap.put("date", new DateHandler());
        handlerMap.put("seq", new SeqHandler());
        handlerMap.put("uuid", new UuidHandler());
        handlerMap.put("rand", new RandHandler());
        handlerMap.put("enum", new EnumHandler());
    }

    public String generate(String template, RuleContext ctx) {
        List<TemplateToken> tokens = parse(template);
        StringBuilder result = new StringBuilder();
        for (TemplateToken token : tokens) {
            result.append(token.evaluate(handlerMap, ctx));
        }
        return result.toString();
    }

    private List<TemplateToken> parse(String template) {
        List<TemplateToken> tokens = new ArrayList<>();
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(template);
        int last = 0;
        while (matcher.find()) {
            if (matcher.start() > last) {
                tokens.add(new FixedToken(template.substring(last, matcher.start())));
            }
            String type = matcher.group(1);
            String param = matcher.group(3);
            tokens.add(new PlaceholderToken(type, param));
            last = matcher.end();
        }
        if (last < template.length()) {
            tokens.add(new FixedToken(template.substring(last)));
        }
        return tokens;
    }


    // ===== 示例运行入口 =====
    public static void main(String[] args) {
        RuleTemplateEngine engine = new RuleTemplateEngine();
        RuleContext ctx = new RuleContext("order");

        String template = "USR{date:YYMMDD}_{seq:5}-{uuid:6}$#{rand:1000-9999}-{enum:男,女,未知}";
        for (int i = 0; i < 5; i++) {
            String result = engine.generate(template, ctx);
            System.out.println(result);
        }
    }
}