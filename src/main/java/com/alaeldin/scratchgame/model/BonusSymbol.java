package com.alaeldin.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BonusSymbol {
    private String name;
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;
    private String type;
    private int extra;
    private String impact;

    public BonusSymbol(){

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public double getRewardMultiplier() {

        return rewardMultiplier;
    }

    public void setRewardMultiplier(double rewardMultiplier) {

        this.rewardMultiplier = rewardMultiplier;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public int getExtra() {

        return extra;
    }

    public void setExtra(int extra) {

        this.extra = extra;
    }

    public String getImpact() {

        return impact;
    }

    public void setImpact(String impact) {

        this.impact = impact;
    }
}
