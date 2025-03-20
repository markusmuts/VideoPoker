import java.util.*;

public class Kontrollija {
    /**
     * Teeb kindlaks, kas on flush.
     * @param pakk Kaardipakk listina.
     * @return Tõeväärtus, kas on või mitte.
     */
    private static boolean onFlush(List<Kaart> pakk){
        boolean flush = true;
        String esimene_mast = "";
        // Vaatame, kas kõik kaardid on sama masti
        for(int i = 0; i < pakk.size(); i++){
            if(i == 0) {
                esimene_mast = pakk.get(i).getMast();
            } else {
                if (!esimene_mast.equals(pakk.get(i).getMast())){
                    flush = false;
                    break;
                }
            }
        }
        return flush;
    }

    /**
     * Teeb kindlaks, kas on royal flush.
     * @param pakk Kaardipakk listina.
     * @return Tõeväärtus, kas on või mitte.
     */
    private static boolean onRoyalFlush(List<Kaart> pakk){
        boolean royalFlush = true;
        List<String> royal = new ArrayList<>(List.of("10", "J", "Q", "K", "A"));
        for (Kaart kaart : pakk) {
            if (royal.contains(kaart.getNumber())) {
                royal.remove(String.valueOf(kaart.getNumber()));
            } else {
                royalFlush= false;
            }
        }
        return royalFlush;
    }

    /**
     * Teeb kindlaks, kas on straight.
     * @param pakk Kaardipakk listina.
     * @return Tõeväärtus, kas on või mitte.
     */
    private static boolean onStraight(List<Kaart> pakk) {
        boolean onStraight = true;
        int[] kaardid = new int[pakk.size()];
        int kaardidIndeks = 0;
        for(Kaart kaart : pakk){
            // Teisendame kaardi numbrid ära, et saaks kontrollida,
            // kas on straight või mitte
            switch(kaart.getNumber()){
                case "2":
                    kaardid[kaardidIndeks] = 1;
                    break;
                case "3":
                    kaardid[kaardidIndeks] = 2;
                    break;
                case "4":
                    kaardid[kaardidIndeks] = 3;
                    break;
                case "5":
                    kaardid[kaardidIndeks] = 4;
                    break;
                case "6":
                    kaardid[kaardidIndeks] = 5;
                    break;
                case "7":
                    kaardid[kaardidIndeks] = 6;
                    break;
                case "8":
                    kaardid[kaardidIndeks] = 7;
                    break;
                case "9":
                    kaardid[kaardidIndeks] = 8;
                    break;
                case "10":
                    kaardid[kaardidIndeks] = 9;
                    break;
                case "J":
                    kaardid[kaardidIndeks] = 10;
                    break;
                case "Q":
                    kaardid[kaardidIndeks] = 11;
                    break;
                case "K":
                    kaardid[kaardidIndeks] = 12;
                    break;
                case "A":
                    kaardid[kaardidIndeks] = 13;
                    break;
            }
            kaardidIndeks++;
        }

        // Sorteerime ära
        Arrays.sort(kaardid);

        // Erijuht pokkeris, kus staright on ka 5-4-3-2-A
        int[] wheel = {1,2,3,4,13};
        if (Arrays.equals(kaardid, wheel)) {
            return onStraight;
        }

        // Kontrollime, kas mingi kaart ei ole järjekorras
        for (int i = 0; i < kaardid.length - 2; i++) {
            if (kaardid[i] != kaardid[i+1] - 1) {
                onStraight = false;
                break;
            }
        }

        return onStraight;
    }

    /**
     * Peameetod sellel klassil.
     * Teeb kindlaks, mis sorti käsi on mängijal.
     * @param pakk Kaardipakk listina.
     * @return Tagastab sõne, milles on kirjas, mis tüüpi käsi on.
     */
    public static String kontrolliPakki(List<Kaart> pakk){
        String tulemus = null;

        // Kontrollime erinevaid kombinatsioone
        boolean onFlush = onFlush(pakk);
        boolean onStraight = onStraight(pakk);

        if (onFlush) {
           boolean onRoyalFlush = onRoyalFlush(pakk);

           // Kas on royal flush
            if (onRoyalFlush) {
                tulemus = "Royal Flush";
                return tulemus;
            }

            // Kas on straight flush
            if (onStraight) {
                tulemus = "Straight Flush";
                return tulemus;
            }
        } else if (onStraight) { // Kas on straight
            tulemus = "Straight";
            return tulemus;
        }

        HashMap<String, Integer> kontrollitudKaardid = new HashMap<String, Integer>();

        for (Kaart kaart : pakk) {
            int mituSama = 0;
            if(!kontrollitudKaardid.containsKey(kaart.getNumber())) {
                for (Kaart pakisKaart : pakk) {
                    if(pakisKaart.getNumber().equals(kaart.getNumber())) {
                        mituSama++;
                    }
                }
                // Ei paneks üheseid kaarte
                if (mituSama >= 2) {
                    kontrollitudKaardid.put(kaart.getNumber(), mituSama);
                }
            }
        }

        // Kui pole eelmised käed
        switch (kontrollitudKaardid.size()) {
            case 0:
                if (onFlush) {
                    tulemus = "Flush";
                    return tulemus;
                } else {
                    tulemus = "High Card";
                    return tulemus;
                }

            case 1:
                // Võtame ainukese väärtuse
                int mituSamat = kontrollitudKaardid.values().iterator().next();

                switch (mituSamat) {
                    case 2:
                        tulemus = "One Pair";
                        return tulemus;
                    case 3:
                        tulemus = "Three of a Kind";
                        return tulemus;
                    case 4:
                        tulemus = "Four of a Kind";
                        return tulemus;
                }

            case 2:
                Iterator<Integer> iterator = kontrollitudKaardid.values().iterator();

                // Võtame 2 väärtust
                int mituSamat1 = iterator.next();
                int mituSamat2 = iterator.next();

                if (mituSamat1 == mituSamat2) {
                    tulemus = "Two Pair";
                    return tulemus;
                } else {
                    tulemus = "Full House";
                    return tulemus;
                }
        }
        return tulemus;
    }
}
