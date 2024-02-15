package com.alaeldin;
import com.alaeldin.scratchgame.logic.CameLogic;
import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

            CameLogic cameLogic = new CameLogic("config.json");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter Betting Amount");
            int bettingAmount = scanner.nextInt();
            cameLogic.playGame(bettingAmount);
    }
}