package com.alaeldin.scratchgame.logic;

import com.alaeldin.scratchgame.config.Config;
import com.alaeldin.scratchgame.config.ConfigParser;
import com.alaeldin.scratchgame.model.Probability;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CameLogic {
    private final Config config;
    private String selectedBonus  = "";

    public CameLogic(String pathFile) throws IOException {
        this.config = ConfigParser.parseConfig(pathFile);
    }

    /** This function Play game
     *
     * @param betAmount
     * void
     */
    public void playGame(int betAmount) {
        System.out.println("Generated Matrix:");
        String[][] matrix = generateMatrix();

        for (String[] row : matrix) {

            for (String symbol : row) {

                System.out.print(symbol + "\t");
            }

            System.out.println();

        }
        System.out.println();
        double finalReward = calculateFinalReward(matrix,betAmount);
        System.out.println("Final Reward: " + finalReward);
    }

    /**
     * Method Generate Random Matrix
     *
     * @return String [][]
     */
    public String[][] generateMatrix() {
        String[][] matrix = new String[config.getRows()][config.getColumns()];
        Random random = new Random();
        selectedBonus = selectBonusSymbol(random.nextInt(10));
        // Ensure the matrix is at least 3x3
        if (config.getRows() >= 3 && config.getColumns() >= 3) {

            int column = random.nextInt(config.getColumns()); // Adjusted to prevent IndexOutOfBoundsException
            int row = random.nextInt(config.getRows()); // Adjusted to prevent IndexOutOfBoundsException
            matrix[row][column] = selectedBonus;
            List<Probability> probabilities = config.getProbabilities().getStandardSymbols();
            Probability standardProbability = probabilities.get(0); // You might need to adjust this based on your requirements

            for (int i = 0; i < config.getRows(); i++) {

                for (int j = 0; j < config.getColumns(); j++) {

                    if (matrix[i][j] == null) {

                        String selectSymbol = selectSymbol(standardProbability.getSymbols(), random.nextInt(10));
                        matrix[i][j] = selectSymbol;
                    }
                }
            }
        }

        else {
              // Handle the case where the matrix is too small
            System.out.println("Matrix is too small. Ensure it is at least 3x3.");
        }

        return matrix;
    }

    /**
     * function get Bonus Symbol
     * @param randomNumber int
     * @return  String
     */
    private String selectBonusSymbol(int randomNumber) {
        Probability bonusProbabilities = config.getProbabilities().getBonusSymbols();

        return selectSymbol(bonusProbabilities.getSymbols(), randomNumber);
    }

    /**
     * this Function Select Symbol
     * @param symbolProbabilities Map<String,Integer>
     * @param randomNumber integer
     * @return String
     */
    private String selectSymbol(Map<String, Integer> symbolProbabilities, int randomNumber) {
        int cumulativeProbability = 0;

        for (Map.Entry<String, Integer> entry : symbolProbabilities.entrySet()) {

            cumulativeProbability += entry.getValue();

            if (randomNumber < cumulativeProbability) {

                return entry.getKey();
            }
        }

        throw new IllegalArgumentException("Invalid symbol probabilities");
    }

    /**
     * this Function calculateFinalReward
     * @param matrix String[][]
     * @param betAmount Int
     * @return double
     */
    private double calculateFinalReward (String[][] matrix,int betAmount) {

        double rewardMultiplier = 0;
        int count;
        boolean sameSymbolsHorizontally = sameSymbolsHorizontally(matrix);
        boolean sameSymbolsDiagonallyRightToLeft = sameSymbolsDiagonallyRightToLeft(matrix);
        boolean sameSymbolsVertically = sameSymbolsVertically(matrix);
        boolean sameSymbolsDiagonallyLeftToRight = sameSymbolsDiagonallyLeftToRight(matrix);
        Map<String, Integer> duplicateSymbolMap = getDuplicateSymbolCounts(matrix);
        for (Map.Entry<String, Integer> entry : duplicateSymbolMap.entrySet()) {

               String symbol = entry.getKey();
               count = entry.getValue();

               if (count >= 3) {

                   double symbolReward  =betAmount * config.getRewardMultiplier(symbol, count);

                    if (sameSymbolsHorizontally) {

                        symbolReward *=2;

                      }

                   if (sameSymbolsVertically) {

                    symbolReward *=2;
                 }

                   if (sameSymbolsDiagonallyRightToLeft) {

                    symbolReward *=5;

                }
                if (sameSymbolsDiagonallyLeftToRight) {

                    symbolReward *=5;

                }

                    rewardMultiplier += symbolReward;

            }

        }

        double bonusReward = config.getSymbols().get(selectedBonus)
                                .getRewardMultiplier();
            rewardMultiplier += bonusReward;
            double extraBonus = config.getSymbols().get(selectedBonus)
                                .getExtra();
            rewardMultiplier += extraBonus;

            return  rewardMultiplier;
    }

    /**
     * this Function check sameSymbolsVertically
     * @param matrix String[][]
     * @return boolean
     */
        public static  boolean sameSymbolsVertically(String[][] matrix){

            for (int col =0; col < matrix[0].length;col++){

            String[] column = new String[matrix.length];

            for (int row =0; row < matrix.length;row++){

                column[row] = matrix[row][col];
            }

            if (areSymbolsSame(column)){

                return true;
            }
        }

        return false;
    }

    /**
     *function Check sameSymbolsHorizontally
     * @param matrix String[][]
     * @return boolean
     */
    public static boolean sameSymbolsHorizontally(String[][] matrix) {

        for (String[] symbol : matrix) {

            if (areSymbolsSame(symbol)) {

                return true;
            }
        }

        return false;
    }

    /**
     * function check sameSymbolsDiagonallyRightToLeft
     * @param matrix String[][]
     * @return boolean
     */
    public static boolean sameSymbolsDiagonallyRightToLeft(String[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int row = 0; row < rows - 2; row++) {

            for (int col = cols - 1; col >= 2; col--) {
                String[] symbols = {matrix[row][col], matrix[row + 1][col - 1]
                                   , matrix[row + 2][col - 2]};

                if (areSymbolsSame(symbols)) {

                    return true;
                }
            }
        }

        return false;
    }

    /**
     * function Get Duplicate SymbolCounts
     * @param matrix String[][]
     * @return Map<String,Integer>
     */
    private Map<String,Integer> getDuplicateSymbolCounts(String[][] matrix) {
        Map<String, Integer> symbolCount = new HashMap<>();

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {
                String symbol = matrix[row][col];

                if (!symbol.equals("MISS")) {

                    symbolCount.put(symbol, symbolCount.getOrDefault(symbol, 0) + 1);
                }

            }
        }

        return symbolCount
                        .entrySet()
                         .stream()
                                 .filter(entry->entry.getValue() > 1)
                                         .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

    }

    /**
     * function SameSymbols Diagonally Left To Right
     * @param matrix String[][]
     * @return boolean
     */
    public static boolean sameSymbolsDiagonallyLeftToRight(String[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int row = 0; row < rows - 2; row++) {

            for (int col = 0; col < cols - 2; col++) {
                String[] symbols = {matrix[row][col], matrix[row + 1][col + 1], matrix[row + 2][col + 2]};

                if (areSymbolsSame(symbols)) {

                    return true;
                }
            }
        }

        return false;
    }

    /**
     * function AreSymbols Same
     * @param symbols String[]
     * @return boolean
     */
    private static boolean areSymbolsSame(String[] symbols) {

        for (int i = 1; i < symbols.length; i++) {

            if (!Objects.equals(symbols[i], symbols[0])) {

                return false;
            }
        }

        return true;
    }

}


