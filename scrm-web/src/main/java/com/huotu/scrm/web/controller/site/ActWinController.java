/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼在地图中查看
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2017. All rights reserved.
 */

package com.huotu.scrm.web.controller.site;

import com.huotu.scrm.common.utils.ApiResult;
import com.huotu.scrm.common.utils.Constant;
import com.huotu.scrm.common.utils.IpUtil;
import com.huotu.scrm.common.utils.ResultCodeEnum;
import com.huotu.scrm.service.entity.activity.ActPrize;
import com.huotu.scrm.service.entity.activity.ActWinDetail;
import com.huotu.scrm.service.entity.activity.Activity;
import com.huotu.scrm.service.entity.mall.User;
import com.huotu.scrm.service.service.activity.ActPrizeService;
import com.huotu.scrm.service.service.activity.ActWinDetailService;
import com.huotu.scrm.service.service.activity.ActivityService;
import com.huotu.scrm.service.service.mall.UserService;
import com.huotu.scrm.web.service.StaticResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by montage on 2017/7/17.
 */

@Controller
public class ActWinController extends SiteBaseController {


    @Autowired
    StaticResourceService staticResourceService;
    @Autowired
    private ActPrizeService actPrizeService;
    @Autowired
    private ActWinDetailService actWinDetailService;
    @Autowired
    private UserService userService;
    @Autowired
    ActivityService activityService;

    @RequestMapping("/activity/index")
    public String marketingActivity(@ModelAttribute("userId") Long userId, Long customerId, Long actId, Model model) {
        User user = userService.getByIdAndCustomerId(1058823L, customerId);
        double score = user.getUserIntegral() - user.getLockedIntegral();
        Activity activity = activityService.findByActId(actId);
        activity.getActPrizes().forEach(p -> {
            try {
                p.setPrizeImageUrl(staticResourceService.getResource(null, p.getPrizeImageUrl()).toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        boolean actStatus = activity.actItSelfStatus();
        int costScore = activity.getGameCostlyScore();
        if (actStatus && score > costScore) {
            model.addAttribute("times", (int) score / costScore);
        } else {
            model.addAttribute("times", 0);
        }
        model.addAttribute("active", activity);
        return "activity/game";
    }

    /**
     * 获取用户参与记录（中奖记录）
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/join/record")
    public String getJoinRecord(@ModelAttribute("userId") Long userId, @RequestParam(required = false, defaultValue = "1") int pageIndex, Model model) {
        Page<ActWinDetail> pageActWinDetail = actWinDetailService.getPageActWinDetail(userId, pageIndex, Constant.PAGE_SIZE);
        model.addAttribute("joinRecord", pageActWinDetail);
        model.addAttribute("totalPages", pageActWinDetail.getTotalPages());
        model.addAttribute("totalRecords", pageActWinDetail.getTotalElements());
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("pageSize", Constant.PAGE_SIZE);
        return "activity/winPrize_list";
    }

    /**
     * 参加抽奖活动
     *
     * @return
     */
    @RequestMapping(value = "/join/act")
    @ResponseBody
    public ApiResult joinAct(
            HttpServletRequest request, String userId, int userScore,
            @RequestParam(required = false, defaultValue = "null") String userName,
            @RequestParam(required = false, defaultValue = "null") String userTel) {
        //回归算法得到奖品
        int proCount = 100;
        String minRate = "min";
        String maxRate = "max";
        Integer tempInt = 0;
        //待中奖奖品数组
        Map<Long, Map<String, Integer>> prizesMap = new HashMap<>();
        List<ActPrize> actPrizeList = actPrizeService.findAll();
        for (ActPrize actPrize : actPrizeList) {
            Map<String, Integer> oddMap = new HashMap<>();
            oddMap.put(minRate, tempInt);
            tempInt += actPrize.getWinRate();
            oddMap.put(maxRate, tempInt);
            prizesMap.put(actPrize.getPrizeId(), oddMap);
        }
        //得到随机数
        int random = (int) Math.random() * proCount;
        ActPrize prize = null;
        String format = null;
        Set<Long> prizeIds = prizesMap.keySet();
        for (Long prizeId : prizeIds) {
            Map<String, Integer> oddsMap = prizesMap.get(prizeId);
            Integer minNum = oddsMap.get(minRate);
            Integer maxNum = oddsMap.get(maxRate);
            //验证random 在哪件奖品中间
            if (minNum <= random && maxNum > random) {
                prize = actPrizeService.findByPrizeId(prizeId);
                if (prize.getRemainCount() == 0) {
                    prize = actPrizeService.findByPrizeType(false);
                }
                break;
            }
        }
        //TODO 用户积分没有得到 无法计算游戏消耗积分
        ActWinDetail actWinDetail = new ActWinDetail();
        actWinDetail.setPrize(prize);
        actWinDetail.setIpAddress(IpUtil.IpAddress(request));
        actWinDetail.setUserId(Long.valueOf(userId));
        actWinDetail.setWinTime(new Date());
        actWinDetail.setWinnerName(userName);
        actWinDetail.setWinnerTel(userTel);
        actWinDetail = actWinDetailService.saveActWinDetail(actWinDetail);
        if (actWinDetail != null) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        }
        return ApiResult.resultWith(ResultCodeEnum.SAVE_DATA_ERROR);
    }

}
