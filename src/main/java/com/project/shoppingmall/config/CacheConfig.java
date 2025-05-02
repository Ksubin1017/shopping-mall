package com.project.shoppingmall.config;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@EnableCaching
@Configuration
public class CacheConfig {

    private net.sf.ehcache.CacheManager createCacheManager() {
        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
        configuration.diskStore(new DiskStoreConfiguration().path("java.io.tmpdir"));
        return net.sf.ehcache.CacheManager.create(configuration);
    }


    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        net.sf.ehcache.CacheManager manager = this.createCacheManager();
        Cache getMenuCache = new Cache(new CacheConfiguration()
                .name("bestNew")
                .maxEntriesLocalHeap(1000)
                .maxEntriesLocalDisk(0)
                .eternal(false).timeToLiveSeconds(86400)
                .timeToLiveSeconds(20)
                .memoryStoreEvictionPolicy("LFU")
                .transactionalMode(CacheConfiguration.TransactionalMode.OFF)
                .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP)));

        manager.addCache(getMenuCache);
        return new EhCacheCacheManager(manager);
    }
}
