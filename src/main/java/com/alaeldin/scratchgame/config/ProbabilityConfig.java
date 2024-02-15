package com.alaeldin.scratchgame.config;

import com.alaeldin.scratchgame.model.Probability;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ProbabilityConfig {

    @JsonProperty("standard_symbols")
    private List<Probability> standardSymbols;
    @JsonProperty("bonus_symbols")
    private Probability bonusSymbols;
    ProbabilityConfig(){

    }

    public List<Probability> getStandardSymbols() {

        return standardSymbols;
    }

    public void setStandardSymbols(List<Probability> standardSymbols) {

        this.standardSymbols = standardSymbols;
    }

    public Probability getBonusSymbols() {

        return bonusSymbols;
    }

    public void setBonusSymbols(Probability bonusSymbols) {

        this.bonusSymbols = bonusSymbols;
    }
}
