package com.mrkt.admin.service;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.Writer;

@Service
@Slf4j
public class EhCacheService {

    public void clearCache()
    {
        for(CacheManager cm:CacheManager.ALL_CACHE_MANAGERS)
        {
            if(cm.getStatus().equals(Status.STATUS_ALIVE))
            {
                for(String name:cm.getCacheNames())
                {
                    Ehcache ehcache = cm.getEhcache(name);
                    if (ehcache.getStatus().equals(Status.STATUS_ALIVE))
                    {
                        if(log.isTraceEnabled())
                        {
                            log.trace("Cache "+name + " contains "+ehcache.getSize() +" objects");
                        }
                        ehcache.removeAll();
                        if(log.isTraceEnabled())
                        {
                            log.trace(" Clearing Cache "+name + " now contains "+ehcache.getSize() +" objects");
                        }
                    }
                }
            }
        }

    }

    public void inspectCache(Writer writer)
    {
        PrintWriter out=new PrintWriter(writer);
        for(CacheManager cm:CacheManager.ALL_CACHE_MANAGERS)
        {
            if(cm.getStatus().equals(Status.STATUS_ALIVE))
            {
                for(String name:cm.getCacheNames())
                {
                    Ehcache ehcache = cm.getEhcache(name);
                    if (ehcache.getStatus().equals(Status.STATUS_ALIVE))
                    {
                            out.println("=================="
                                    +"Cache Manager : ["+cm.getCacheNames() +"],"
                                    + " Region "+ehcache.getName() +" objects");

                    }
                }
            }
        }

    }

}
