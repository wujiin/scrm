package com.huotu.scrm.service.entity.activity;

import com.huotu.scrm.service.model.PrizeType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 活动中奖记录
 * <p>
 * Created by montage on 2017/7/11.
 */

@Entity
@Getter
@Setter
@ToString
@Table(name = "SCRM_WinDetail")
public class ActWinDetail {

    /**
     * 中奖记录Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long winDetailId;

    /**
     * 用户Id
     */
    @Column(name = "User_Id")
    private Long userId;

    /**
     * 中奖人电话
     */
    @Column(name = "Winner_Tel")
    private String winnerTel;

    /**
     * 中奖人姓名
     */
    @Column(name = "Winner_Name")
    private String winnerName;

    /**
     * 中奖日期
     */
    @Column(name = "Win_Time")
    private LocalDateTime winTime;

    /**
     * 中奖Ip地址
     */
    @Column(name = "Ip_Address")
    private String ipAddress;

    /**
     * 奖品名称冗余字段
     */
    @Column(name = "Prize_Name")
    private String prizeName;

    /**
     * 奖品类型冗余字段
     */
    @Column(name = "Prize_Type")
    private PrizeType prizeType;
    /**
     * 奖品图片冗余字段
     */
    @Column(name = "Prize_Image_Url")
    private String prizeImageUrl;
    /**
     * 奖品地址
     */
    @Transient
    private String mallPrizeImageUrl;

    /**
     * 奖品Id
     */
    @Column(name = "Prize_Id")
    private Long prizeId;

    /**
     * 活动Id
     */
    @Column(name = "Act_Id")
    private Long actId;

    /**
     * 活动Id  0表示未领取  1表示已领取
     */
    @Column(name = "Get_Reward")
    private boolean isGetReward;

}
