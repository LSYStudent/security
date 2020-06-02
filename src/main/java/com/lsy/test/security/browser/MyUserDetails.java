package com.lsy.test.security.browser;

import com.lsy.test.security.config.Constants;
import com.lsy.test.security.model.User;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户信息类
 * @author lsy
 */
@Data
@Accessors(chain = true)
public class MyUserDetails extends User implements UserDetails {

    public MyUserDetails() {}

    public MyUserDetails(User user) {
        BeanUtils.copyProperties(user, this);
    }

    /**
     * 用户权限集合
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
    }

    /**
     * 密码
     * @return
     */
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    /**
     * 用户名
     * @return
     */
    @Override
    public String getUsername() {
        return super.getUserName();
    }

    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return !getResult().equals(Constants.EXPIRED) && getStatus().equals(Constants.NORMAL);
    }

    /**
     * 账号是否冻结
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return !getResult().equals(Constants.LOCKED) && getStatus().equals(Constants.NORMAL);
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return getResult() >= Constants.NORMAL && getStatus().equals(Constants.NORMAL);
    }
}
