/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼在地图中查看
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2017. All rights reserved.
 */

/**
 * Created by admin on 2017-07-28.
 */
<!--JSSDK分享接口BEGIN-->
var domain = [[${domain}]],customerId= [[${customerId}]],sourceUserId= [[${sourceUserId}]];
var info = eval([[${info}]]);
document.write('<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>');
document.write('<script type="text/javascript" src="http://m.' + domain + '.com/Weixin/JsSdk/RegConfig.aspx?customerid=' + customerId+ '&debug=0"></script>');
document.write('<script src="http://m.' + domain + '.com/Weixin/JsSdk/wxShare.js?20150112"></script>');
wxShare.InitShare({
    title: info.title,
    desc: info.introduce,
    link: window.location.href + '&sourceUserId=' + sourceUserId,//分享网址，一般在这里添加自己想要的参数
    img_url: info.mallImageUrl
});

