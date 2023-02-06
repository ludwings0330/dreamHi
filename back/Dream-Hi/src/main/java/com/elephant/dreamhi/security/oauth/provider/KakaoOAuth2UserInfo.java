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
        String regex = originPhone.contains("-") ? "-" : " ";
        String[] numbers = originPhone.split(regex);
        StringBuilder phone = new StringBuilder("010");
        return phone.append(numbers[1]).append(numbers[2]).toString();
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
