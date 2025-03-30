

import java.util.List;

public class AuhinnaValjastaja {

    public static double arvutatasu(double panus, String tulemus) {

        return switch (tulemus) {
            // Lisa vastav väärtus või loogika
            case "Royal Flush" -> 250 * panus;
            case "Straight Flush" -> 50 * panus;
            case "Four of a Kind" -> 25 * panus;
            case "Full House" -> 9 * panus;
            case "Flush" -> 6 * panus;
            case "Straight" -> 4 * panus;
            case "Three of a Kind" -> 3 * panus;
            case "Two Pair" -> 2 * panus;
            case "One Pair" -> panus;
            case "High Card" -> 0;
            default -> 0; // Kui pole vastet, tagasta 0
        };
}}






