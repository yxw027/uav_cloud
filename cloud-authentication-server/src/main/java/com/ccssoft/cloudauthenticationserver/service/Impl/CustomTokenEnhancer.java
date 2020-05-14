package com.ccssoft.cloudauthenticationserver.service.Impl;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author moriarty
 * @date 2020/5/11 15:28
 */
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        // 这个UserTokenVo就是之前UserDetial返回的对象
        //从那获取要增强携带的字段
        UserTokenVo user = (UserTokenVo) authentication.getPrincipal();

        final Map<String, Object> additionalInfo = new HashMap<>();

        //添加token携带的字段
        additionalInfo.put("id", user.getId());
        additionalInfo.put("nickname", user.getNickname());
        additionalInfo.put("avatar", user.getAvatar());
        additionalInfo.put("description", user.getDescription());
        additionalInfo.put("blog_address", user.getBlog_address());

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation(additionalInfo);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }
}
