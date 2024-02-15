package com.alaeldin.scratchgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StandardSymbol {
    private String name;
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;
    private String type;
}
