package com.alaeldin.scratchgame.config;

import com.alaeldin.scratchgame.model.BonusSymbol;
import com.alaeldin.scratchgame.model.StandardSymbol;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class SymbolsConfig {

    @JsonProperty("standard_symbols")
    private Map<String, StandardSymbol> standardSymbols;
    @JsonProperty("bonus_symbols")
    private Map<String, BonusSymbol> bonusSymbols;

    public SymbolsConfig(){

    }

    public Map<String, StandardSymbol> getStandardSymbols() {

        return standardSymbols;
    }

    public void setStandardSymbols(Map<String, StandardSymbol> standardSymbols) {

        this.standardSymbols = standardSymbols;
    }


    public void setBonusSymbols(Map<String, BonusSymbol> bonusSymbols) {

        this.bonusSymbols = bonusSymbols;
    }

    public Map<String, BonusSymbol> getBonusSymbols() {

        return bonusSymbols;
    }
}
