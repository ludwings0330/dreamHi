package com.elephant.dreamhi.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "token")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(name = "access_token", length = 256)
    private String accessToken;

    @Column(name = "refresh_token", length = 256)
    private String refreshToken;


    public Token(Long userId, String accessToken, String refreshToken) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    /**
     * access token 교체 메소드
     *
     * @param accessToken
     */
    public void changeAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 로그인 시 이미 토큰 정보가 존재 시 새로운 토큰으로 교체하기 위한 메소드
     *
     * @param accessToken  : Access Token
     * @param refreshToken : Refresh Token
     */
    public void regenerateToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
