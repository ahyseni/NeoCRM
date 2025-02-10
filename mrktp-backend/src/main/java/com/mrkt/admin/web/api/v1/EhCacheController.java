package com.mrkt.admin.web.api.v1;


import com.mrkt.admin.service.EhCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.Writer;

@RestController
@RequestMapping(value = "/ehchache")
@Slf4j
public class EhCacheController {

    @Autowired
    private EhCacheService ehCacheService;

    @RequestMapping(value = "/clear",method = {RequestMethod.GET,RequestMethod.POST})
    public String clearCache(){

        log.info("Clearing L2 Cache");
        ehCacheService.clearCache();
        return "OK";
    }

    @GetMapping(value = "/inspect",produces = MediaType.TEXT_PLAIN_VALUE)
    public void inspectCache(Writer writer){
        ehCacheService.inspectCache(writer);
    }

}
