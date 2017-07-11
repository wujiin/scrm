package com.huotu.scrm.service.entity;

import com.huotu.scrm.common.ienum.UserType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Description;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by helloztt on 2017-07-10.
 */
@Entity
@Getter
@Setter
@Table(name = "Hot_UserBaseInfo")
@Description("用户")
@Cacheable(value = false)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UB_UserID")
    private Long id;

    /**
     * 商户ID
     */
    @Column(name = "UB_CustomerID")
    private Long customerId;

    /**
     * 登录名
     */
    @Column(name = "UB_UserLoginName", nullable = false, length = 50)
    private String loginName;

    /**
     * 昵称
     */
    @Column(name = "UB_UserNickName", length = 100)
    private String nickName;

    /**
     * 用户类型
     * 0：普通会员
     * 1：小伙伴
     */
    @Column(name = "UB_UserType")
    private UserType userType;

    /**
     * 微信头像
     */
    @Column(name = "UB_WxHeadImg")
    private String weixinImageUrl;

    /**
     * 微信昵称
     */
    @Column(name = "UB_WxNickName")
    private String wxNickName;

    /**
     * 注册时间
     */
    @Column(name = "UB_UserRegTime")
    private Date regTime;

}