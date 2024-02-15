package com.alaeldin.scratchgame.logic;

import com.alaeldin.scratchgame.config.Config;
import com.alaeldin.scratchgame.config.ConfigParser;
import com.alaeldin.scratchgame.model.Probability;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class CameLogic{
  private  Config config;
    public CameLogic(String pathFile) throws IOException {
     this.config = ConfigParser.parseConfig(pathFile);
    }

// public String[][] generateMatrix(){
  //      String[][] matrix = new String[config.getRows()][config.getColumns()];
    //    Random random = new Random();

//}

private String selectSymbol(Map<String,Integer> symbolProbabilities
                                                 , int randomNumber){
    int cumulativeProbability = 0;

    for (Map.Entry<String,Integer> entry: symbolProbabilities.entrySet()){
        cumulativeProbability += entry.getValue();
        if (randomNumber < cumulativeProbability){

            return entry.getKey();
        }
    }
    throw new IllegalArgumentException("Invalid symbol probabilities");
}

}
