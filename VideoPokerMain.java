import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VideoPokerMain {
    public static void main(String[] args) {
        KaartideGeneraator generaator = new KaartideGeneraator();
        Scanner scanner = new Scanner(System.in);

        List<Kaart> algneKaardiPakk = generaator.genereerimeKaardid(5);
        System.out.println(algneKaardiPakk);
        System.out.println("See on sinu algne käsi: milliseid kaarte tahad alles jätta?, sisesta nt 1,2,3 (esimesel positsioonil olev kaart jne): ");
        String input = scanner.nextLine();

        if (input.isEmpty() || input.equals("0")) {
            List<Kaart> viimaneKaardiPakk = generaator.genereerimeKaardid(5);
            System.out.println("Sinu viimane käsi: " + viimaneKaardiPakk);

            String lõplikTulemus = Kontrollija.kontrolliPakki(viimaneKaardiPakk);
            System.out.println("Teie käsi on: " + lõplikTulemus);
        } else {
            String[] indeksid = input.split(",");
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

            int genereeritudKaartideArv  = 5 - viimaneKaardiPakk.size();
            List<Kaart> viimasedGenereeritudKaardid = new ArrayList<>();
            while (viimasedGenereeritudKaardid.size() < genereeritudKaartideArv) {
                List<Kaart> uuedKaardid = generaator.genereerimeKaardid(1);
                Kaart uusKaart = uuedKaardid.getFirst();

                if (!viimaneKaardiPakk.contains(uusKaart)) {
                    viimaneKaardiPakk.add(uusKaart);
                    viimasedGenereeritudKaardid.add(uusKaart);
                }

            }

            System.out.println("Sinu viimane käsi: " + viimaneKaardiPakk);

            String lõplikTulemus = Kontrollija.kontrolliPakki(viimaneKaardiPakk);
            System.out.println("Teie käsi on: " + lõplikTulemus);
        }}

}

        System.out.println("Sinu viimane käsi: " + viimaneKaardiPakk);

        String lõplikTulemus = Kontrollija.kontrolliPakki(viimaneKaardiPakk);
        System.out.println("Teie käsi on: " + lõplikTulemus);
    }
}
