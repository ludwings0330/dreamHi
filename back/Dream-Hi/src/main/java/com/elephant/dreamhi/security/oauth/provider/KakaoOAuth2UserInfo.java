package com.elephant.dreamhi.security.oauth.provider;

import java.util.Map;

public class KakaoOAuth2UserInfo implements OAuth2UserInfo{

    // OAuth2User 의 getAttributes
    private Map<String, Object> attributes;

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getEmail() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");

        if (account == null || account.get("email") == null) {
            return null;
        }
        return (String) account.get("email");
    }

    @Override
    public String getPhone() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");

        if (account == null || account.get("phone_number") == null) {
            return null;
        }
        String originPhone =  (String) account.get("phone_number");
        String[] numbers = originPhone.split("-");
        return numbers[0].substring(4,7) + numbers[1] + numbers[2];
    }

    @Override
    public String getName() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");
        if (profile == null || profile.get("nickname") == null) {
            return null;
        }
        return (String) profile.get("nickname");
    }

}
