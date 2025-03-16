package ProjektVideoPoker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VideoPokerMain {
    public static void main(String[] args) {
        KaartideGeneraator generaatorBaas = new KaartideGeneraator();
        List<Kaart> algneKaardiPakk = generaatorBaas.genereerimeKaardid(5);
        System.out.println(algneKaardiPakk);
        Scanner scanner = new Scanner(System.in);
        System.out.println("See on sinu algne käsi: milliseid kaarte tahad alles jätta?, sisesta nt 1,2,3 (esimesel positsioonil olev kaart jne): ");
        String[] indeksid = scanner.nextLine().split(",");
        for (int i = 0; i < indeksid.length; i++) {
            indeksid[i] = indeksid[i].trim();
        }
        List<Integer> indeksid1 = new ArrayList<>();
        for (String s : indeksid) {
            indeksid1.add(Integer.parseInt(s));
        }

        List<Kaart> viimaneKaardiPakk = new ArrayList<>();
        for (int i = 0; i < indeksid1.size(); i++) {
            viimaneKaardiPakk.add(algneKaardiPakk.get(indeksid1.get(i)-1));
        }
        KaartideGeneraator viimaneGenereerimine = new KaartideGeneraator();
        int genereeritudKaartideArv  = 5 - viimaneKaardiPakk.size();
        List<Kaart> viimasedGenereeritudKaardid = new ArrayList<>();
        while (viimasedGenereeritudKaardid.size() < genereeritudKaartideArv) {
            List<Kaart> uuedKaardid = viimaneGenereerimine.genereerimeKaardid(1);
            Kaart uusKaart = uuedKaardid.get(0);

            if (!viimaneKaardiPakk.contains(uusKaart)) {
                viimaneKaardiPakk.add(uusKaart);
                viimasedGenereeritudKaardid.add(uusKaart);
            }

        }
        System.out.println("Sinu viimane käsi: " + viimaneKaardiPakk);


    }
}
