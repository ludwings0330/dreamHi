package com.elephant.dreamhi.security.oauth.provider;

import java.util.Map;

public class GoogleOAuth2UserInfo  implements OAuth2UserInfo{

    // OAuth2User 의 getAttributes
    private Map<String, Object> attributes;

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getPhone() {
        return (String) attributes.get("phone_number");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

}
