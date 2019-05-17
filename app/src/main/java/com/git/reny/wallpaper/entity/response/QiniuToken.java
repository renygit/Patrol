package com.git.reny.wallpaper.entity.response;

import java.util.List;

public class QiniuToken {

    private List<String> keys;
    private List<String> tokens;

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }
}
