package com.elephant.dreamhi.security.oauth.provider;

import com.elephant.dreamhi.model.statics.ProviderType;
import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("["+OAuth2UserInfoFactory.class.getName() + "] : Invalid Provider Type - " + providerType + "에서 로그인은 지원하지 않습니다.");
        }
    }
}
