package com.elephant.dreamhi.security.oauth.provider;

import java.util.Map;

public class NaverOAuth2UserInfo implements OAuth2UserInfo{

    // OAuth2User 의 getAttributes
    private Map<String, Object> attributes;

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getEmail() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if (response == null || response.get("email") == null) {
            return null;
        }

        return (String) response.get("email");
    }

    @Override
    public String getPhone() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if (response == null || response.get("mobile") == null) {
            return null;
        }

        return ((String) response.get("mobile")).replaceAll("-", "");
    }

    @Override
    public String getName() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        if (response == null || response.get("name") == null) {
            return null;
        }

        return (String) response.get("name");
    }

}
