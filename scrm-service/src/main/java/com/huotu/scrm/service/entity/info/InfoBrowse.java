package com.huotu.scrm.service.entity.info;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by luohaibo on 2017/7/11.
 */
@Entity
@Getter
@Setter
@Table(name = "SCRM_InfoBrowseLog")
//@Cacheable(false)
public class InfoBrowse {

    /**
     * 资讯主键
     */
    @Id
    @Column(name = "Info_Id")
    private Long infoId;

    /**
     * 资讯转发来源用户
     */
    @Id
    @Column(name = "Info_Source_UserId")
    private Long sourceUserId;


    /**
     * 资讯查看用户
     */
    @Id
    @Column(name = "Info_Read_UserId")
    private Long readUserId;


    /**
     * 商户Id
     */
    @Column(name = "Customer_Id")
    private Long customerId;


    /**
     * 资讯查看时间
     */
    @Column(name = "Brose_Time", columnDefinition = "datetime")
    private LocalDateTime browseTime;

    /**
     * 资讯转发
     */
    @Column(name = "Turn_Time", columnDefinition = "datetime")
    private LocalDateTime turnTime;


    /**
     * 转发记录是否删除   0 未  1 删除
     */
    @Column(name = "Turn_Disable")
    private boolean turnDisable;


    /**
     * 浏览记录是否删除   0 未  1 删除
     */
    @Column(name = "Browse_Disable")
    private boolean browseDisable;


    /**
     * 头像地址
     */
    @Transient
    private String imgUrl;

    /**
     * 昵称
     */
    @Transient
    private String nickName;


    /**
     * 用户上线的昵称
     */
    @Transient
    private String belongNickName;


    /**
     * 用户上线的level
     */
    @Transient
    private String belongLevel;


    @Override
    public String toString() {
        return "InfoBrowse{" +
                ", infoId=" + infoId +
                ", sourceUserId=" + sourceUserId +
                ", readUserId=" + readUserId +
                ", customerId=" + customerId +
                ", browseTime=" + browseTime +
                ", turnTime=" + turnTime +
                ", imgUrl='" + imgUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public InfoBrowse(Long infoId, Long sourceUserId) {
        this.infoId = infoId;
        this.sourceUserId = sourceUserId;
    }
    public InfoBrowse(Long infoId,Long sourceUserId, LocalDateTime browseTime) {
        this.infoId = infoId;
        this.sourceUserId = sourceUserId;
        this.browseTime = browseTime;
    }

    public InfoBrowse(Long infoId, Long sourceUserId, LocalDateTime turnTime, String imgUrl, String wxNickName,String nickName) {
        this.infoId = infoId;
        this.sourceUserId = sourceUserId;
        this.turnTime = turnTime;
        this.imgUrl = imgUrl;
        this.nickName = StringUtils.isEmpty(wxNickName) ? nickName : wxNickName;
    }


    public InfoBrowse(Long infoId, Long sourceUserId, Long readUserId, LocalDateTime browseTime, String imgUrl, String wxNickName,String nickName, Long customerId
                       ) {
        this.infoId = infoId;
        this.sourceUserId = sourceUserId;
        this.readUserId = readUserId;
        this.browseTime = browseTime;
        this.imgUrl = imgUrl;
        this.nickName = StringUtils.isEmpty(wxNickName) ? nickName : wxNickName;
        this.customerId = customerId;

    }


    public InfoBrowse(Long infoId, Long sourceUserId, Long readUserId, LocalDateTime browseTime, String imgUrl, String wxNickName,String nickName, Long customerId,
                  String belongNickName, String belongLevel) {
        this.infoId = infoId;
        this.sourceUserId = sourceUserId;
        this.readUserId = readUserId;
        this.browseTime = browseTime;
        this.imgUrl = imgUrl;
        this.nickName = StringUtils.isEmpty(wxNickName) ? nickName : wxNickName;
        this.customerId = customerId;
        this.belongNickName = belongNickName;
        this.belongLevel = belongLevel;

    }


    public InfoBrowse() {

    }

    public InfoBrowse(Long infoId, String imgUrl, String nickName, Long customerId) {
        this.imgUrl = imgUrl;
        this.nickName = nickName;
        this.infoId = infoId;
        this.customerId = customerId;
    }
}
