import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class KaartideGeneraator {
    private static final List<String> mastid = List.of("♦", "♣", "♥", "♠"); //Loome algsed default listid, kus on mastid ja numbrid kaartide jaoks
    private static final List<String> numbrid = List.of("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
    private final Random random = new Random();  //Klassi Random kasutamine.
    public KaartideGeneraator() {};

    public List<Kaart> genereerimeKaardid(int kogus) {
        List<Kaart> kaardid = new ArrayList<>();

        for (int i = 0; i < kogus; ) {
            int suvalinemast = random.nextInt(mastid.size());
            int suvalinenr = random.nextInt(numbrid.size());
            Kaart uuskaart = new Kaart(mastid.get(suvalinemast), numbrid.get(suvalinenr));
            if (!kaardid.contains(uuskaart)) { //Kontrollime, kas kaart, mis genereeriti on juba olemas, kui on, siis jätkub tsükkel kohe, kui kaarti ei ole
                kaardid.add(uuskaart);
                i++;                           //siis lisatakse kaartide listi genereeritud kaart, ja jätkatakse
                                                           //tsüklit i++ga.

                }
            }
            return kaardid;
        }

    }


