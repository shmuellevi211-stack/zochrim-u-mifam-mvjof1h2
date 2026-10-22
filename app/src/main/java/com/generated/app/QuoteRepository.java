package com.generated.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuoteRepository {

    public static List<Quote> getAllQuotes() {
        List<Quote> quotes = new ArrayList<Quote>();
        
        // Category: longing
        quotes.add(new Quote(
            "אִם אֶשְׁכָּחֵךְ יְרוּשָׁלָ‍ִם תִּשְׁכַּח יְמִינִי",
            "תהילים קל״ז, ה׳",
            "longing",
            "תנ״ך",
            true
        ));
        quotes.add(new Quote(
            "כָּל הַמִּתְאַבֵּל עַל יְרוּשָׁלַיִם זוֹכֶה וְרוֹאֶה בְּשִׂמְחָתָהּ",
            "תלמוד בבלי, תענית ל ע״ב",
            "longing",
            "תלמוד",
            true
        ));

        // Category: prayer
        quotes.add(new Quote(
            "וְתֶחֱזֶינָה עֵינֵינוּ בְּשׁוּבְךָ לְצִיּוֹן בְּרַחֲמִים",
            "תפילת העמידה",
            "prayer",
            "תפילה",
            true
        ));
        quotes.add(new Quote(
            "וְהָשֵׁב אֶת הָעֲבוֹדָה לִדְבִיר בֵּיתֶךָ",
            "תפילת העמידה, ברכת העבודה",
            "prayer",
            "תפילה",
            true
        ));

        // Category: building
        quotes.add(new Quote(
            "בּוֹנֵה יְרוּשָׁלָ‍ִם ה׳, נִדְחֵי יִשְׂרָאֵל יְכַנֵּס",
            "תהילים קמ״ז, ב׳",
            "building",
            "תנ״ך",
            true
        ));
        quotes.add(new Quote(
            "וְנִבְנְתָה עִיר עַל תִּלָּהּ",
            "ירמיהו ל׳, י״ח",
            "building",
            "תנ״ך",
            true
        ));
        quotes.add(new Quote(
            "גָּדוֹל יִהְיֶה כְּבוֹד הַבַּיִת הַזֶּה הָאַחֲרוֹן מִן הָרִאשׁוֹן",
            "חגי ב׳, ט׳",
            "building",
            "תנ״ך",
            true
        ));

        // Category: destruction
        quotes.add(new Quote(
            "מִשֶּׁנִּכְנַס אָב, מְמַעֲטִין בְּשִׂמְחָה",
            "משנה, תענית ד׳, ו׳",
            "destruction",
            "משנה",
            true
        ));
        quotes.add(new Quote(
            "כָּל דּוֹר שֶׁלֹּא נִבְנֶה בֵּית הַמִּקְדָּשׁ בְּיָמָיו, כְּאִלּוּ נֶחֱרַב בְּיָמָיו",
            "תלמוד ירושלמי, יומא א׳, א׳",
            "destruction",
            "תלמוד",
            true
        ));

        return quotes;
    }

    public static Quote getRandomQuote() {
        List<Quote> list = getAllQuotes();
        if (list.isEmpty()) return null;
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}
