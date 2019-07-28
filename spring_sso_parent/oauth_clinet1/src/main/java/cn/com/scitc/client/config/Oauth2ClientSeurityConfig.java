/**
 * @program spring_sso_parent
 * @description: oauth2 客户端配置
 * @author: zhoubiao
 * @create: 2019/07/27 15:08
 */

package cn.com.scitc.client.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class Oauth2ClientSeurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //关闭csrf保护
            .antMatcher("/**") //使用以任意开头的url
            .authorizeRequests() // 配置路径拦截，表明路径访问所对应的权限，角色，认证信息
            .antMatchers("/", "/login**") //控制不同的url接受不同权限的用户访问
            .permitAll()//　允许所有人访问
            .anyRequest()
            .authenticated(); //除了以上请求都需要身份认证
    }
}
