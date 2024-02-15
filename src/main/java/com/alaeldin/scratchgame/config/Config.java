/**
 * Developed By Alaeldin Musa
 * 10/2/2024
 */
package com.alaeldin.scratchgame.config;

import com.alaeldin.scratchgame.model.Symbol;
import com.alaeldin.scratchgame.model.WinCombination;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Config {
    @JsonProperty("columns")
    private int columns;

    @JsonProperty("rows")
    private int rows;

    @JsonProperty("symbols")
    private Map<String, Symbol> symbols;
    @JsonProperty("probabilities")
    private ProbabilityConfig probabilities; // Using Object for simplicity
    @JsonProperty("win_combinations")
    private Map<String, WinCombination> winCombinations;

    public Config() {

    }

    /**
     * function Get Reward Multiplier
     * @param symbol String
     * @param count  Int
     * @return Double
     */
    public double getRewardMultiplier(String symbol, int count) {

        Map<String, Symbol> symbols = getSymbols();
        Map<String, WinCombination> winCombinations = getWinCombinations();

        if (symbols.containsKey(symbol) && winCombinations.containsKey("same_symbol_" + count + "_times")) {

            double symbolMultiplier = symbols.get(symbol).getRewardMultiplier();
            double winCombinationMultiplier = winCombinations.get("same_symbol_" + count + "_times").getRewardMultiplier();
            return symbolMultiplier * winCombinationMultiplier;
        }

        throw new IllegalArgumentException("Invalid symbol or count: " + symbol + ", " + count);
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {

        this.rows = rows;
    }

    public Map<String, Symbol> getSymbols() {

        return symbols;
    }

    public void setSymbols(Map<String, Symbol> symbols) {

        this.symbols = symbols;
    }

    public ProbabilityConfig getProbabilities() {

        return probabilities;
    }

    public void setProbabilities(ProbabilityConfig probabilities) {

        this.probabilities = probabilities;
    }

    public Map<String, WinCombination> getWinCombinations() {

        return winCombinations;
    }

    public void setWinCombinations(Map<String, WinCombination> winCombinations) {

        this.winCombinations = winCombinations;
    }
}


