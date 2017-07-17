package com.huotu.scrm.web.controller.mall;


import com.huotu.scrm.common.utils.ApiResult;
import com.huotu.scrm.common.utils.ResultCodeEnum;
import com.huotu.scrm.common.utils.InformationSearch;
import com.huotu.scrm.service.entity.info.Info;
import com.huotu.scrm.service.service.InfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * 资讯管理控制器
 * Created by luohaibo on 2017/7/10.
 */
@Controller
public class InfoController extends MallBaseController {

    private Log logger = LogFactory.getLog(InfoController.class);
    @Autowired
    InfoService infoService;

    /***
     * 展示资讯首页内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/infoLists")
    public String infoHomeLists(InformationSearch informationSearch, @ModelAttribute("customerId") Long customerId , Model model){

        logger.info(informationSearch);
        informationSearch.setCustomerId(customerId);
        logger.info(informationSearch);
        Page<Info> page = infoService.infoSList(informationSearch);
        model.addAttribute("infoListsPage",page);
        long account = infoService.infoListsCount(false);
        logger.info(account);
        model.addAttribute("totalAccount",account);
        return "info/info_list";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit")
    public String infoEditPage(Model model){
        model.addAttribute("info",new Info());
        return "info/infoEdit";
    }

    /**
     * 获取行数记录总数
     * @param disable
     * @return
     */
    @RequestMapping("/count")
    @ResponseBody
    public ApiResult getInfoListsAccount(boolean disable,int page){

        logger.info(infoService.infoListsCount(disable)+"+++"+page);
        Map<String,Long> map = new HashMap<>();
        map.put("amount", infoService.infoListsCount(disable));
        ApiResult apiResult = ApiResult.resultWith(ResultCodeEnum.SUCCESS,"成功",map);
        logger.info(apiResult);
        return  apiResult;

        return null;

    }

    /**
     * 保存修改资讯
     * @param info
     * @return
     */
    @RequestMapping("saveInfo")
    public String saveInfo(Info info){
        infoService.infoSave(info);
        return "index";
    }


}
