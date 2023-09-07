package com.example.service.id_generation;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "generating-id-num")
public class Config {
    private Integer start;
    private Integer end;
    private Integer increment;
    private Integer minimumThreshold;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getIncrement() {
        return increment;
    }

    public void setIncrement(Integer increment) {
        this.increment = increment;
    }

    public Integer getMinimumThreshold() {
        return minimumThreshold;
    }

    public void setMinimumThreshold(Integer minimumThreshold) {
        this.minimumThreshold = minimumThreshold;
    }

    public Config(Integer start, Integer end, Integer increment, Integer minimumThreshold) {
        this.start = start;
        this.end = end;
        this.increment = increment;
        this.minimumThreshold = minimumThreshold;
    }

    public Config() {
    }
}