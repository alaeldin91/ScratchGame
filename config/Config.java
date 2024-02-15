/**
 * Developed By Alaeldin Musa
 * 10/2/2024
 */
package com.alaeldin.scratchgame.config;


import com.alaeldin.scratchgame.model.Symbol;
import com.alaeldin.scratchgame.model.WinCombination;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Config{
      @JsonProperty("columns")
        private int columns;

        @JsonProperty("rows")
        private int rows;

        @JsonProperty("symbols")
        private Map<String, Symbol> symbols;

        @JsonProperty("probabilities")
        private Map<String, Object> probabilities;  // Using Object for simplicity
        @JsonProperty("win_combinations")
        private Map<String, WinCombination> winCombinations;

        Config(){

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

  public Map<String, Object> getProbabilities() {
    return probabilities;
  }

  public void setProbabilities(Map<String, Object> probabilities) {
    this.probabilities = probabilities;
  }

  public Map<String, WinCombination> getWinCombinations() {
    return winCombinations;
  }

  public void setWinCombinations(Map<String, WinCombination> winCombinations) {
    this.winCombinations = winCombinations;
  }
}


