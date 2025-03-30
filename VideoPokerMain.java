import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class VideoPokerMain {

    private static void väljastaJuhised(Scanner scanner) throws InterruptedException {
        while(true){
            System.out.print("Tahad näha juhiseid? Sisesta y või n: ");
            String valik = scanner.nextLine();
            if (valik.equalsIgnoreCase("y")){
                System.out.println("\n📌 Ülevaade:");
                System.out.println("VideoPoker X on tavalise pokkeri põhjal loodud mäng, kus mängija saab kaardid ning valib, milliseid hoida,");
                System.out.println("ja proovib saada parimat võimalikku pokkerikäe kombinatsiooni.\n");

                System.out.println("🎲 Kuidas mängu mängida?");
                System.out.println("1. Mängu alustamine:");
                System.out.println("   - Panusta mingisugune summa eurodes ning siis genereetiakse sulle 5 erinevat kaarti.\n");

                System.out.println("2. Vali hoitavad kaardid:");
                System.out.println("   - Sisesta käsk \"1,3\", et hoida kaarte 1 ja 3 näiteks.");
                System.out.println("   - Kindlasti ei pea kaarte hoidma, selleks sisesta mitte midagi või \"0\".\n");

                System.out.println("3. Lõpliku käe kuvamine:");
                System.out.println("   - Kui valikud on tehtud, siis kuvatakse lõplik käsi koos väljamaksega.\n");

                System.out.println("🃏 Pokkerikäed ja väljamaksed:");
                System.out.println("| Käsi                | Kirjeldus                                      | Koefitsient   |");
                System.out.println("|---------------------|------------------------------------------------|-------------- |");
                System.out.println("| Royal Flush         | Äss, Kuningas, Emand, Poiss, 10 samast mastist | 800x          |");
                System.out.println("| Straight Flush      | Viis järjestikust kaarti samast mastist        | 50x           |");
                System.out.println("| Four of a Kind      | Neli sama väärtusega kaarti                    | 30x           |");
                System.out.println("| Full House          | Kolm ühesugust ja kaks teist ühesugust kaarti  | 10x           |");
                System.out.println("| Flush               | Viis kaarti samast mastist                     | 8x            |");
                System.out.println("| Straight            | Viis järjestikust kaarti (eri mastid)          | 5x            |");
                System.out.println("| Three of a Kind     | Kolm sama väärtusega kaarti                    | 3x            |");
                System.out.println("| Two Pair            | Kaks erinevat paari                            | 1.5x          |");
                System.out.println("| Jacks or Better     | Üks paar poistega või tugevamate kaartidega    | 1x            |");
                System.out.println("| One Pair            | Üks paar                                       | 0.5x          |");
                System.out.println("| High Card           | Kõrge kaart                                    | 0x            |\n");

                System.out.println("❌ - Kuidas mängust väljuda?");
                System.out.println("- Kirjutage panusesse \"q\", et väljuda, või lase balansil nulli joosta.");
                break;
            } else if (valik.equalsIgnoreCase("n")){
                break;
            } else {
                System.err.println("Tundmatu sisend \"" + valik + "\", proovi uuesti!");
                Thread.sleep(100); // Kindlustada, et veateade jõuaks enne ekraanile
            }

        }
    }

    private static void väljastaTiitel(Scanner scanner) throws InterruptedException {
        System.out.println(" __     ___     _            ____       _              __  __ ");
        System.out.println(" \\ \\   / (_) __| | ___  ___ |  _ \\ ___ | | _____ _ __  \\ \\/ / ");
        System.out.println("  \\ \\ / /| |/ _` |/ _ \\/ _ \\| |_) / _ \\| |/ / _ \\ '__|  \\  /  ");
        System.out.println("   \\ V / | | (_| |  __/ (_) |  __/ (_) |   <  __/ |     /  \\  ");
        System.out.println("    \\_/  |_|\\__,_|\\___|\\___/|_|   \\___/|_|\\_\\___|_|    /_/\\_\\ ");
        System.out.println();
        System.out.println("            ==================================");
        System.out.println("            |        OOP RÜHMATOO nr I       |");
        System.out.println("            |                                |");
        System.out.println("            |             AUTORID            |");
        System.out.println("            |           Markus Muts          |");
        System.out.println("            |        Georg Klettenberg       |");
        System.out.println("            |          Martin Rahuorg        |");
        System.out.println("            ==================================");
        System.out.println();
        väljastaJuhised(scanner);
    }


    public static void main(String[] args) throws InterruptedException {
        KaartideGeneraator generaator = new KaartideGeneraator();
        Scanner scanner = new Scanner(System.in);
        double balanss = 500;
        double panus = 0;

        väljastaTiitel(scanner);

        while (balanss > 0) {
            System.out.println("\nBalanss: " + balanss + "€");
            System.out.print("Sisesta oma panus eurodes: ");
            String sisend = scanner.nextLine();

            if (sisend.equalsIgnoreCase("q")){
                System.out.println("Välja võetud summa: " + balanss);
                break;
            } else {
                try{
                    panus = Double.parseDouble(sisend);
                }catch (Exception e){
                    System.err.println("Tundmatu sisend \"" + sisend + "\", proovi uuesti!");
                    Thread.sleep(100); // Kindlustada, et veateade jõuaks enne ekraanile
                    continue;
                }
            }

            if ((balanss-panus) < 0){
                System.err.println("Panus on liiga suur, proovi uuesti!");
                Thread.sleep(100); // Kindlustada, et veateade jõuaks enne ekraanile
                continue;
            }

            balanss -= panus;

            List<Kaart> algneKaardiPakk = generaator.genereerimeKaardid(5);
            System.out.println(algneKaardiPakk);
            System.out.print("See on sinu algne käsi. Milliseid kaarte tahad alles jätta?, sisesta nt \"1,2,3\": ");
            String input = scanner.nextLine();

            if (input.isEmpty() || input.equals("0")) {
                List<Kaart> viimaneKaardiPakk = generaator.genereerimeKaardid(5);
                System.out.println("Sinu viimane käsi: " + viimaneKaardiPakk);

                String lõplikTulemus = Kontrollija.kontrolliPakki(viimaneKaardiPakk);
                System.out.println("Teie käsi on: " + lõplikTulemus);

                double tasu = AuhinnaValjastaja.arvutatasu(panus, lõplikTulemus);
                balanss += tasu;
                if (lõplikTulemus.equals("High Card")) {
                    System.out.println("Kahjuks ei võitnud seekord, proovi uuesti!");
                } else {
                    System.out.println("Võitsite " + tasu + "€! Palju õnne!");
                }
            } else {
                String[] indeksid = input.split(",");
                List<Integer> indeksidList = new ArrayList<>();
                for (int i = 0; i < indeksid.length; i++) {
                    indeksid[i] = indeksid[i].trim();
                }

                try{
                    for (String s : indeksid) {
                        indeksidList.add(Integer.parseInt(s));
                    }
                } catch (Exception e){
                    System.err.println("Viga sisendiga \"" + input + "\". Proovi uuesti!");
                    Thread.sleep(100); // Kindlustada, et veateade jõuaks enne ekraanile
                    balanss += panus;
                    continue;
                }
                Collections.sort(indeksidList);

                List<Kaart> viimaneKaardiPakk = new ArrayList<>();
                try {
                    for (int i = 0; i < indeksidList.size(); i++) {
                        viimaneKaardiPakk.add(algneKaardiPakk.get(indeksidList.get(i) - 1));
                    }
                } catch (Exception e) {
                    System.err.println("Viga sisendiga \"" + input + "\". Proovi uuesti!");
                    Thread.sleep(100); // Kindlustada, et veateade jõuaks enne ekraanile
                    balanss += panus;
                    continue;
                }

                int genereeritudKaartideArv = 5 - viimaneKaardiPakk.size();
                List<Kaart> viimasedGenereeritudKaardid = new ArrayList<>();
                while (viimasedGenereeritudKaardid.size() < genereeritudKaartideArv) {
                    List<Kaart> uuedKaardid = generaator.genereerimeKaardid(1);
                    Kaart uusKaart = uuedKaardid.getFirst();

                    if (!viimaneKaardiPakk.contains(uusKaart)) {
                        viimaneKaardiPakk.add(uusKaart);
                        viimasedGenereeritudKaardid.add(uusKaart);
                    }

                }

                for (int i = 0; i < indeksidList.size(); i++) {
                    viimaneKaardiPakk.remove(0); // Eemaldame esimese elemendi mitu korda
                }


                for (int i = 0; i < indeksidList.size(); i++) {
                    viimaneKaardiPakk.add(indeksidList.get(i) - 1, algneKaardiPakk.get(indeksidList.get(i) - 1));
                }

                System.out.println("Teie viimane käsi: " + viimaneKaardiPakk);

                String lõplikTulemus = Kontrollija.kontrolliPakki(viimaneKaardiPakk);
                System.out.println("Teie käsi on: " + lõplikTulemus);

                double tasu = AuhinnaValjastaja.arvutatasu(panus, lõplikTulemus);
                balanss += tasu;
                if (lõplikTulemus.equals("High Card")) {
                    System.out.println("Kahjuks ei võitnud seekord, proovi uuesti!");
                } else {
                    System.out.println("Võitsite " + tasu + "€! Palju õnne!");
                }
            }
        }
        if (balanss == 0){
            System.out.println("\nTäna sa ei võtnud. Järgmine kord läheb paremini!");
        }
    }
}
