package com.generated.app;

public class Quote {
    private String text;
    private String source;
    private String category;
    private String type;
    private boolean isExact;

    public Quote(String text, String source, String category, String type, boolean isExact) {
        this.text = text;
        this.source = source;
        this.category = category;
        this.type = type;
        this.isExact = isExact;
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public boolean isExact() {
        return isExact;
    }
}
