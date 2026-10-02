package com.akshat.bakchodbrain.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ==============================================================================
 * Infinite Procedural Roast Matrix Engine
 * ==============================================================================
 * Multi-dimensional dynamic roast generator evaluating:
 * 1. Reaction speed (Speed blunders vs overthinking disasters)
 * 2. Streak momentum (Hattrick of shame vs suspicious streaks)
 * 3. Domain/Category specificity (Tech, UPSC, Bollywood, Dating)
 * 4. Procedural slot-filler dynamic generation
 */
@Service
public class RoastMatrixService {

    // Category Specific Roasts
    private static final Map<String, List<String>> CATEGORY_WRONG_ROASTS = Map.of(
        "tech-corporate", List.of(
            "Tu pakka Friday 5 PM ko bina test kare production pe commit marne wala developer hai.",
            "Array ka index 0 se start hota hai bhai, aur tera IQ negative se.",
            "Is answer ko dekh kar ChatGPT ne tera account permanently block karne ki warning de di.",
            "Jira ticket pe 'In Progress' likh ke YouTube dekhne wala mindset hai tera.",
            "Bhai tera logic dekh ke compiler ne khud ko null pointer exception de diya.",
            "Tu wahi hai na jo CSS me div center karne ke liye 3 ghante Google search karta hai?",
            "Senior dev agar tera code dekh lega to direct HR ko resign bhej dega.",
            "Sprint review me camera off karke mute pe so jaane ka natija hai ye."
        ),
        "desh-duniya", List.of(
            "Mukherjee Nagar ki tapri pe geopolitics jhaadna band kar, pehle basic GK padh le.",
            "Desh sankat me nahi hai bhai, tera scorecard sankat me hai.",
            "Itna galat to news channels ka exit poll bhi nahi hota jitna tu confident hai.",
            "Telegram ke 100 mock test download karke phone ki memory bharne se selection nahi hota.",
            "Laxmikanth book ki pooja karne se nahi, khol ke padhne se answer sahi hote hain.",
            "Bhai tu wahi hai na jo chai ki tapri pe baith ke bolta hai 'Modi ji ko ye decision nahi lena chahiye tha'?"
        ),
        "bollywood-memes", List.of(
            "Babu Bhaiya ne kidney kiske liye bechi thi bhool gaya? 25 din me paisa double wale ho tum.",
            "Selmon Bhoi ki footpath driving se zyada dangerous tera answer selection hai.",
            "Majnu Bhai ki ghode wali painting me isse zyada sense tha jitna tere is answer me hai.",
            "Panchayat ke Vidhayak ji bhi tujhse zyada knowledgeable hain bhai.",
            "Tere dimaag me 'Gangs of Wasseypur' ki tarah badla chal raha hai ya fuse udd chuka hai?",
            "Jethalal ka Babita ji ke samne confidence tujhse lakh guna behtar hai."
        ),
        "dating-delusion", List.of(
            "Usne 'You are like a brother to me' bol kar chhod diya tha na? Bilkul sahi kiya tha.",
            "Tera Instagram pe reply timing 2 second hai aur answer accuracy zero.",
            "Uske bio me 'Single' dekh ke khush hone wale simp, pehle padhai kar le.",
            "Uske WhatsApp status pe 4 ghante overthink karne wala dimaag yahan nahi chala?",
            "Bhai tere se question solve nahi ho rahe, bandi kya sambhalega tu?"
        )
    );

    // Speed Blunders (< 1.8s)
    private static final List<String> SPEED_BLUNDER_ROASTS = List.of(
        "1.2 second me galat answer? Bhai overconfidence me pura khandan dubayega tu.",
        "Flash banne ke chakkar me clown ban gaya na? Thoda padh to leta option!",
        "Itna tez galat answer to supercomputer bhi calculate nahi kar pata.",
        "Aankh band karke phone pe ungli mara na? Sach bol!",
        "Bhai tezi aisi dikhayi jaise train chhoot rahi ho, par platform hi galat pakda."
    );

    // Overthinking Disasters (> 13s)
    private static final List<String> OVERTHINKING_ROASTS = List.of(
        "15 second pura soch ke bhi galat tick mara? Dimaag 2G network pe chal raha hai kya?",
        "Itna der lagaya jaise UPSC mains ka 500 word essay likh raha tha, aur end me tatti kar diya.",
        "NASA ka satellite launch karne me isse kam calculation lagti hai jitna tune galat sochne me lagaya.",
        "Itna sochne ke baad to Google bhi sharma gaya ki isne kya choose kar liya."
    );

    // Streak of Shame (3+ wrong in a row)
    private static final List<String> STREAK_SHAME_ROASTS = List.of(
        "Hattrick of Stupidity! 3 galat lagatar! Phone lock kar aur so ja chup-chap.",
        "Bhai lagatar galat ho raha hai. Teri kismat aur dimaag dono airplane mode pe hain.",
        "Tu test de raha hai ya mere database ko apne wrong answers se stress-test kar raha hai?",
        "Tere dimaag me memory leak ho gaya hai. System reboot maarna padega tujhe."
    );

    // General Wrong Roasts Pool
    private static final List<String> GENERAL_WRONG_ROASTS = List.of(
        "Tere IQ se zyada to mere laptop ki battery percentage hai.",
        "Tuition ke paise wapas maang le apne school walon se, case jeet jayega tu.",
        "Option D tick karne se pehle socha tha ya dil se awaz aayi thi?",
        "Bhai confidence dekh rahe ho bande ka? Galat answer tick karke muskura raha hai.",
        "Is answer ke baad tere marks dekh ke tere rishtedaar party maangenge.",
        "Koshish achhi thi par natija wahi aaya jo aana chahiye tha: ZERO.",
        "Tere dimaag me thoda dahi jam gaya hai lagta hai, dhoop me baith ja thodi der."
    );

    // Correct Answers with Suspicion & Tukka Calls
    private static final List<String> TUKKA_PRAISES = List.of(
        "Andha nishana lag gaya na? CV me Einstein likhne mat baith jana.",
        "Tukke me sahi ho gaya, zyada khush mat ho agle me nikal jayegi hawa.",
        "Sahi to hai, par mujhe 100% yakeen hai tujhe reason nahi pata.",
        "Kismat chamak gayi teri! Par agla question aane de beta."
    );

    private static final List<String> STREAK_PRAISES = List.of(
        "Hattrick maar di? Side me pakka dost baitha hai jo hints de raha hai!",
        "Lagatar sahi? FBI ko bulao, is bande ka achanak dimaag kaise chalne laga?",
        "Bhai tu wahi hai na jo exam se 10 minute pehle padh ke pass ho jata hai?",
        "Unstoppable ho raha hai tu! Control kar thoda, nazar lag jayegi."
    );

    private static final List<String> GENERAL_PRAISES = List.of(
        "Chalo ek answer me to khandan ki izzat bacha li tune.",
        "Wah re shana! Sahi pakde hain.",
        "Chal maan liya thoda bohot dimaag to bacha hai tere paas.",
        "Correct! Par hero mat ban, test abhi baki hai mere dost."
    );

    /**
     * Dynamically picks the most brutal or hilarious roast based on speed, streak, and category.
     */
    public Map<String, String> generateRoast(
            boolean isCorrect, 
            String categorySlug, 
            double timeTaken, 
            int currentStreak, 
            String customRoast, 
            String customPraise
    ) {
        String roastMessage;
        String tone;
        String audio;

        if (isCorrect) {
            tone = "PRAISE";
            audio = "ding";

            // If streak >= 3
            if (currentStreak >= 2) {
                roastMessage = getRandom(STREAK_PRAISES);
                tone = "STREAK_PRAISE";
                audio = "victory_fanfare";
            }
            // Fast answer < 2s
            else if (timeTaken < 2.0) {
                roastMessage = getRandom(TUKKA_PRAISES);
                tone = "TUKKA_ARROGANT";
            }
            // Custom praise fallback
            else if (customPraise != null && !customPraise.isBlank() && ThreadLocalRandom.current().nextBoolean()) {
                roastMessage = customPraise;
            }
            else {
                roastMessage = getRandom(GENERAL_PRAISES);
            }
        } else {
            tone = "SAVAGE_ROAST";
            audio = "buzz";

            // Streak of shame <= -2
            if (currentStreak <= -2) {
                roastMessage = getRandom(STREAK_SHAME_ROASTS);
                tone = "SHAME_STREAK";
                audio = "fail_gong";
            }
            // Speed blunder
            else if (timeTaken < 1.8) {
                roastMessage = getRandom(SPEED_BLUNDER_ROASTS);
                tone = "SPEED_BLUNDER";
            }
            // Overthinking blunder
            else if (timeTaken > 13.0) {
                roastMessage = getRandom(OVERTHINKING_ROASTS);
                tone = "OVERTHINK_FAIL";
            }
            // Category specific
            else if (CATEGORY_WRONG_ROASTS.containsKey(categorySlug) && ThreadLocalRandom.current().nextBoolean()) {
                List<String> catRoasts = CATEGORY_WRONG_ROASTS.get(categorySlug);
                roastMessage = getRandom(catRoasts);
            }
            // Custom roast fallback
            else if (customRoast != null && !customRoast.isBlank() && ThreadLocalRandom.current().nextBoolean()) {
                roastMessage = customRoast;
            }
            else {
                roastMessage = getRandom(GENERAL_WRONG_ROASTS);
            }
        }

        return Map.of(
            "roastMessage", roastMessage,
            "tone", tone,
            "audio", audio
        );
    }

    /**
     * Computes final savage scorecard title, badge and verdict based on performance.
     */
    public Map<String, String> evaluateScorecard(int score, double accuracyRate, int totalQuestions) {
        if (score < 30) {
            return Map.of(
                "title", "🤡 Certified Gawar",
                "badge", "GAWAR_MAX_PRO",
                "summary", "Bhai tu quiz dene aaya tha ya questions ko pranam karke option A/B/C/D pe ludo khel raha tha? Apne school walon ko tuition fees refund ka notice bhej de.",
                "verdict", "Tumse na ho payega beta. Agle janam me try kariyo."
            );
        } else if (score < 60) {
            return Map.of(
                "title", "💼 TCS Bench Legend",
                "badge", "BENCH_WARRIOR",
                "summary", "Itne score pe sirf IT mass recruitment ka offer letter milta hai bhai. Mehnat kar warna agle 3 saal bench pe hi chai samosa khayega.",
                "verdict", "Average se thoda neeche, par survive kar gaya."
            );
        } else if (score < 85) {
            return Map.of(
                "title", "🎯 Professional Tukkebaaz",
                "badge", "LUCKY_BASTARD",
                "summary", "Score to theek-thaak aa gaya, par tere andar ka dil jaanta hai ki aadhe questions me andha nishana mara hai tune. Mandir me prasad chadhana mat bhoolna.",
                "verdict", "Kismat 90%, Dimaag 10%. Respectable."
            );
        } else {
            return Map.of(
                "title", "🕵️‍♂️ Google Khola Tha Na Bsdk?",
                "badge", "SUSPICIOUS_TOPPER",
                "summary", "Itna hoshiyar to tu apne ghar walon ke samne bhi nahi banta. Pakka doosri tab me Google ya ChatGPT khol ke baithe the. Samaj me aise logo ko ban karna chahiye.",
                "verdict", "Supreme Authority IQ. Recruiters are crying."
            );
        }
    }

    private String getRandom(List<String> list) {
        if (list == null || list.isEmpty()) return "Bhai kya hi bolu ab tere baare me.";
        int index = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(index);
    }
}
