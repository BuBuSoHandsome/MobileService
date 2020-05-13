package com.ruoyi.web.controller.system;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 落地页
 * Datetime:    2020/4/26
 */

@Controller
@RequestMapping("/system/landingpage")
public class LandingPageController {

    private String prefix = "system/landingpage";

    @GetMapping()
    public String landingpage()
    {
        return prefix + "/landingpage";
    }

}
