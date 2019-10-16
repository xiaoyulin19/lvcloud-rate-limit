package com.lv.cloud.ratelimiter.client.config;

public enum RATE_LIMTER_MODE {

    /**
     *只支持本地限流（默认）
     */
    LOCAL(0),
    /**
     * 只支持分布式限流
     */
    DISTRIBUTED(1),
    /**
     * 支持本地&分布式限流
     */
    ALL(2);

    private int mode;

    RATE_LIMTER_MODE(int mode){
       this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
}
