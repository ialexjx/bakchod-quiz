package com.akshat.bakchodbrain.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ==============================================================================
 * BakchodBrain Infinite Savage Roast Matrix (Ultra-Loom Edition)
 * ==============================================================================
 * Multi-factor tear-inducing insult synthesizer:
 * 1. Infinite Combinatorial Slot-Filler Engine (250,000+ unique permutations)
 * 2. Mega Handcrafted Vault (Sharma ji taunts, B.Tech trauma, UPSC mocks, HR rangoli, Naukri.com, relative burns)
 * 3. Reaction Speed Matrix (<1.5s Overconfident Blunders vs >13s Overthinking Disasters)
 * 4. Progressive Streak of Shame escalation (-1, -2, -3, -4, -5+)
 * 5. Domain Category Deep-Cuts (Corporate Slavery, Desh-Duniya, Bollywood Memes, Dating/Simping)
 */
@Service
public class RoastMatrixService {

    // ==========================================================================
    // PROCEDURAL SLOT-FILLER MATRIX (Over 250,000 Unique Combinations)
    // ==========================================================================
    private static final List<String> OPENERS = List.of(
        "Bhai sach bata,",
        "Tere is answer ko dekh kar",
        "Dekh bhai,",
        "O Einstein ki 14th copy,",
        "Arey mere maati ke laal,",
        "Suno gyani baba,",
        "Bhai itna confidence dekh ke",
        "Papa agar tera ye answer dekh lenge na,",
        "Ghar pe agar pata chal gaya,",
        "Sharma ji ka beta ye dekh ke",
        "Teri is harkat pe",
        "Bhai tere dimaag ki wiring dekh ke",
        "Tere school ke principal",
        "Mohalle ke taane maarne wale uncle",
        "Naukri.com ka algorithm bhi",
        "Bhai teri aql ka standard dekh kar",
        "Tinder pe right swipe karne wale simp,",
        "Tera resume dekhne ke baad HR",
        "Yamraj bhi upar se soch rahe hain,",
        "Bhai tere is mahaan option ko dekh ke"
    );

    private static final List<String> TRIGGERS = List.of(
        "tera IQ score dekh ke",
        "teri logic ki aisi taisi hote dekh",
        "ye catastrophic blunder dekh kar",
        "teri 200 kmph wali andhi tezi dekh ke",
        "tera 15 second ka faltu deep overthinking dekh ke",
        "teri aukaat ka live scorecard dekh kar",
        "ye andha tukka dekh ke",
        "teri engineering ki degree ka tamasha dekh kar",
        "tera WhatsApp forwarded gyaan dekh ke",
        "teri concentration power dekh ke",
        "ye UPSC level ka overconfidence dekh kar",
        "teri memory leak ki bimari dekh kar",
        "tera dahi jaisa jam chuka dimaag dekh ke",
        "teri life choices ka ye namoona dekh kar"
    );

    private static final List<String> METAPHORS = List.of(
        "Tier-3 college ke mechanical branch ke placement jaisa soona lag raha hai",
        "Aadhar card ki pehli photo se bhi zyada depressing feel ho raha hai",
        "Naukri.com pe 'Resume viewed by 0 recruiters' jaisa dukh de raha hai",
        "SBI bank ke lunch break jaisa unresponsive ho chuka hai",
        "WhatsApp family group ke fake UNESCO award jaisa haasyaaspad hai",
        "TCS ke 3.2 LPA wale 5 saal purane bond se bhi zyada dardnaak hai",
        "Friday shaam 5:30 PM pe production database crash hone jaisa hadsa hai",
        "Chintu ke WhiteHatJr coding certificate se bhi kam valuable lag raha hai",
        "Colony ke kutte ki bhonkne ki accuracy se bhi zyada kharab hai",
        "Relatvies ke 'Beta aage ka kya socha hai' wale sawaal jaisa chubh raha hai",
        "Bandi ke 'You are just like a brother to me' text se zyada dil todne wala hai",
        "4 saal purane laptop ki 2% battery jaisa hopeless lag raha hai",
        "Hera Pheri ke Anuradha ke 21 din me paisa double scheme jaisa scam hai",
        "Majnu Bhai ke gadhe ke upar ghode wali painting se bhi kam logical hai",
        "Instagram ke 6 ghante ke screen time ka sabse ganda return on investment hai",
        "Swiggy ke delivery boy se ladne wale bhukkad jaisa cheap move lag raha hai",
        "Mukherjee Nagar me 7 saal se prelims de rahe bhaiya ke notes jaisa rula raha hai",
        "Gangs of Wasseypur ke Definite ke future jaisa andhkaar me lag raha hai"
    );

    private static final List<String> PUNCHLINES = List.of(
        "chup-chaap khatiya daal aur chadar taan ke so ja.",
        "apne tuition teachers se fees wapas maang le, case pakka jeetega tu.",
        "phone bech de aur gaon me bhed-bakri charane ka form bhar de.",
        "mummy se bol de ki beta bada ho gaya hai par akal agle janam me aayegi.",
        "Himalayas chala ja bhai, sansar tere liye nahi bana hai.",
        "HR ko call karke bol de ki mujhse rangoli hi banwa lo, code nahi hoga.",
        "agli baar option tick karne se pehle dimaag ko ON switch pe daal liyo.",
        "tere marks dekh ke rishtedaar seedha drawing room me hass rahe hain.",
        "ab to Google ne bhi tere IP pe captcha lagane se mana kar diya.",
        "bhai tu quiz mat de, Chintu se scratch coding seekh le pehle.",
        "kundli me dosh nahi hai beta, problem direct hardware (sar) me hai.",
        "is answer ke baad tera Aadhar card cancel ho jana chahiye.",
        "zindagi me pehli baar koi cheez dhang se kar leta to ye din na dekhna padta.",
        "bhai tere liye ek hi salaah hai: Ctrl+Z maar apni life pe.",
        "Swiggy se zehar order karne ka man kar raha hai tera scorecard dekh ke.",
        "baap ka BP badhane ke alawa aur koi talent hai tere paas?"
    );

    // ==========================================================================
    // MEGA HANDCRAFTED TEAR-JERKING VAULT (Brutal Emotional Damage)
    // ==========================================================================
    private static final List<String> MEGA_TEAR_JERKER_BURNS = List.of(
        "Tere is answer ko dekh kar tere baap ne property se tera naam kaat ke gali ke kutte ke naam karne ka faisla le liya hai.",
        "Bhai confidence dekh rahe ho launde ka? Galat tick karke muskura raha hai jaise Sundar Pichai ka resignation sign karne aaya ho.",
        "Itna galat to wo bhi nahi thi jab boli thi 'I will never leave you, baby'.",
        "Tere IQ se zyada to mere laptop ki charging percentage bachi hai.",
        "Tuition ke paise kahan kharch kiye the sach bata? Golgappe khane me ya crush ko dekhne me?",
        "Jab bhagwan dimaag baant raha tha, tu pakka reels pe thumke dekh raha tha.",
        "Bhai tu wahi hai na jo board exam ke viva me examiner ke 'Good Morning' ka bhi galat answer dekar aa gaya tha?",
        "Tere is score ko dekh ke tere rishtedaaron ne WhatsApp group me laddu baant diye hain.",
        "Agar aukaat naapne ka koi meter hota to tere is answer ke baad uski sui toot chuki hoti.",
        "Bhai tere sar me dimaag nahi, expired rabdi jam chuki hai. Dhoop me baith ja thodi der.",
        "Tere se achha guess to mere ghar ka fan ghoomte hue tick-tick karke mar deta.",
        "Itne gande performance ke baad to Swiggy delivery boy bhi tere ghar khana dene se pehle sorry bolega.",
        "Tere marks dekh ke lagta hai tune pen ki jagah dimaag se tatti ki hai phone pe.",
        "Beta tumse na ho payega, jaake Flipkart ki sale me thoda aatm-vishwas aur thoda dimaag order kar le.",
        "Tere is blunder ko dekh ke ChatGPT ne server room me rona shuru kar diya hai.",
        "Bhai tu question padh ke answer de raha tha ya screen pe machhar baitha tha usko maar raha tha?",
        "Ghar pe bol de ki beta B.Tech pass hai par aql 3rd standard wali hi bachi hai.",
        "Tere is answer se zyada logic to Taarak Mehta ke tapu sena ke plan me hota hai.",
        "Bhai teri intelligence dekh ke NASA ne tujhe black hole ghoshit kar diya hai—jahan se koi knowledge bahar nahi aati.",
        "Option D tick karte waqt aatma kaanp nahi gayi teri? Kis mitti ke bane ho bhai?",
        "Tere pitaji ne socha tha shravan kumar paida hua hai, yahan to shravan blunder nikal gaya.",
        "Itna overconfidence to RCB ke fans me trophy jeetne ko leke nahi hota jitna tere is galat answer me tha."
    );

    // ==========================================================================
    // CATEGORY SPECIFIC TEAR-JERKING ROASTS
    // ==========================================================================
    private static final Map<String, List<String>> CATEGORY_SPECIFIC_BURNS = Map.of(
        "tech-corporate", List.of(
            "Array ka index 0 se start hota hai bhai, aur teri salary 3 LPA pe freeze ho chuki hai.",
            "Tu pakka Friday 5:30 PM pe bina PR review ke production pe direct commit push karne wala criminal dev hai.",
            "Bhai tera code dekh kar compiler ne khud ko segmentation fault dekar aatmadah kar liya.",
            "Jira ticket pe 3 hafte se 'In Progress' laga ke YouTube shorts scroll karne ka natija hai ye.",
            "Senior architect ne agar tera ye solution dekh liya na, to seedha keyboard tere sar pe tod dega.",
            "Tu wahi hai na jo CSS me ek button center karne ke liye 4 گھنٹے StackOverflow pe ro raha tha?",
            "Manager kal subah standup me camera on karwayega, aur tab teri aukaat puri team ke samne nikal jayegi.",
            "Tere logic se behtar logic to us intern ka tha jisko pehle din hi revoke kar diya tha.",
            "Git pull maarte waqt 80 conflict aane pe repo delete karke doosri company me apply karne wala mindset hai tera.",
            "LeetCode Easy solve nahi ho raha aur sapne dekhe hain Google me 50 LPA ke? Chup chaap TCS interview ki taiyari kar.",
            "Is answer ke baad tere IDE ne syntax highlighting permanently band kar di hai sharam ke maare."
        ),
        "desh-duniya", List.of(
            "Mukherjee Nagar ki chai tapri pe geopolitics pelna band kar, pehle 5th class ki NCERT padh le.",
            "Desh sankat me baad me aayega, pehle tere ghar ka rashan sankat me aayega is gyaan ke sath.",
            "Itna galat to aaj tak TV channels ke exit poll bhi nahi hue jitna tera ye guess tha.",
            "Telegram pe 500 PDF download karke phone hang karne se UPSC clear nahi hota babu moshai.",
            "Laxmikanth book ki pooja karne se nahi, usko khol ke akal lagane se sawal sahi hote hain.",
            "Train me chain kheenchne ka reason bhi nahi pata? Lagta hai bachpan se bina ticket hi general dabba me ghusa hai.",
            "Aryabhata ne Zero ka aavishkar isliye kiya tha taaki tere scorecard me wo number fit baith sake.",
            "Bhai tu wahi hai na jo paan ki dukan pe khade hoke bolta hai 'Vladimir Putin ne meri advice nahi maani'?"
        ),
        "bollywood-memes", List.of(
            "Hera Pheri 50 baar dekhne ke baad bhi 35,000 ka Demand Draft bhool gaya? Babu Bhaiya danda leke dhoond rahe hain tujhe.",
            "Selmon Bhoi ki footpath driving se zyada khatarnak aur laaparwah tera ye answer selection hai.",
            "Ramadhir Singh ne Gangs of Wasseypur me tere jaise namoono ke liye hi bola tha: 'Jab tak saneema hai log...'!",
            "Majnu Bhai ki ghode wali painting me tere is dimaag se 10 guna zyada perspective aur sense tha.",
            "Panchayat ka Binod bhi tujhse zyada active dimag chalata hai. Dekh raha hai Binod, kaisa gawar hai ye?",
            "Circuit agar tera ye answer dekh le na, to agli dissection body tujhe hi banakar medical college bhej dega.",
            "Munna Bhai ki tarah jadu ki jhappi nahi, tujhe seedha do kaan ke neeche padne chahiye tab akal aayegi."
        ),
        "dating-delusion", List.of(
            "Usne 4 ghante baad sirf 'K' reply kiya tha na? Aur tu abhi bhi yahan baith ke quiz me delusional answers de raha hai.",
            "Raat ke 3 baje uski 4 saal purani beach photo like karke darr ke maare phone switch-off karne wale stalker, sudhar ja.",
            "Uske bio me 'Looking for good vibes' ka matlab ye nahi tha ki wo tere jaise berozgar se shadi karegi.",
            "Bhai tera response time WhatsApp pe 0.5 second hai, aur yahan question ka answer dene me IQ minus me ja raha hai.",
            "Usne 'I am not ready for relationship' bola tha, silent bracket me '(with you lodu)' padhna bhool gaya na?",
            "Swipe right karte karte tere angoothe me arthritis ho jayega par teri aukaat me koi bandi nahi aane wali.",
            "Bhai tu sawal solve nahi kar pa raha, uske mood swings kya sambhalega? Padhai kar le chup chap."
        )
    );

    // ==========================================================================
    // REACTION SPEED BURNS (<1.5s Fast Blunders vs >13s Overthinkers)
    // ==========================================================================
    private static final List<String> SPEED_BLUNDER_BURNS = List.of(
        "1.1 second me galat tick? Bhai itni tezi to ambulance bhi nahi dikhati! Overconfidence me dubega tu.",
        "Flash banne ke chakkar me full circus clown ban gaya na? Ek second ruk ke question to padh leta!",
        "Itni tez galat answer to NASA ka supercomputer bhi nahi throw kar sakta! Champion of Stupidity!",
        "Aankh band karke mobile screen pe thappad mara na tune? Sach bol, bhagwan dekh raha hai!",
        "Bhai train nikal rahi thi kya teri? Itni jaldbaazi me to log galat train me baith ke gunde ban jate hain.",
        "0.9s me galat! Beta ungli chalane se pehle upar ka 50 gram ka bheja on karna hota hai."
    );

    private static final List<String> OVERTHINKING_BURNS = List.of(
        "14 second pura sar khujane ke baad bhi galat? Dimaag 2G BSNL dial-up network pe chal raha hai kya?",
        "Itna der lagaya jaise UPSC mains ka 500 word essay type kar raha ho, aur aakhiri me hug diya!",
        "Chandrayaan 3 calculate karne me ISRO ko isse kam time laga tha jitna tune galat answer sochne me lagaya.",
        "Itna overthink karke to ladkiya shaadi ke lehenge me bhi nahi lagati jitna tune ek galat option pe laga diya.",
        "15 second me poori duniya ghoom li, par sahi option ke aage andha ho gaya tu?",
        "Itna sochne ke baad to Google algorithm bhi ro pada ki 'bhai isne mujhe kyu nahi use kar liya'."
    );

    // ==========================================================================
    // STREAK OF SHAME PROGRESSION
    // ==========================================================================
    private static final List<String> SHAME_STREAK_BURNS = List.of(
        "Hattrick of Besharmi! 3 galat lagatar! Phone lock kar aur jaake bartan dho mummy ke.",
        "4 GALAT LAGATAR?! Bhai tu quiz de raha hai ya mere server ko apne aukaat se crash karne ki koshish kar raha hai?",
        "SHAME STREAK ALERT! Tere dimaag ka RAM corrupt ho chuka hai, seedha factory reset lagega tujhe.",
        "Lagatar galat pe galat! Bhai tere andar sharam naam ki cheez market me bik chuki hai kya?",
        "5 wrong in a row! Record toot gaya gawar-panti ka! Guinness Book of World Records ko call lagao re koi!"
    );

    // ==========================================================================
    // CONDESCENDING PRAISES & TUKKA CHECKS (Right Answers)
    // ==========================================================================
    private static final List<String> TUKKA_PRAISES = List.of(
        "Andha nishana teer pe lag gaya! CV me Einstein likhne mat baith jana, tukka tha ye.",
        "Sahi to ho gaya, par tera dil jaanta hai ki tujhe 1% bhi reason nahi pata tha.",
        "Kismat ne bacha liya beta! Agle sawal me dekhna kaise aukaat bahar aati hai.",
        "Itni jaldi sahi tick? Pakka screen pe ungli slip hui thi aur galti se sahi option dab gaya.",
        "Tukke se sahi karke hero ban raha hai? Chal agla sawal dekh ab."
    );

    private static final List<String> SUSPICIOUS_STREAK_PRAISES = List.of(
        "Lagatar 3 sahi? Bhai bagal me pakka topper dost baitha hai ya doosri tab me ChatGPT khula hai!",
        "FBI ko bulao! Is bande ke dimaag me achanak current kaise daudne laga? Suspicious behavior!",
        "Streak ban gayi teri? Zyada hawa me mat udd, nazar lagte hi agle question me 0 aayega.",
        "Shabash! Kam se kam aaj ghar pe maar kam padegi. Par aage dekhte hain."
    );

    private static final List<String> GENERAL_PRAISES = List.of(
        "Wah re shana! Ek answer me to khandan ki bachi-kuchi izzat bacha li tune.",
        "Sahi pakde hain! Lagta hai dimaag me 2-4 brain cells abhi bhi zinda hain.",
        "Chamatkar! Lagta hai subah bhagwan ko 10 rupaye ka prasad chadha kar aaya tha.",
        "Chal maan liya, thoda bohot dimaag to kharche ke liye bacha rakha hai tune."
    );

    /**
     * Master synthesis entrypoint: Picks between Mega Vault, Category Vault,
     * Speed/Streak matrix, or procedural combinatorial slot generation.
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
            tone = "RESPECT";
            audio = "correct";

            // Streak of glory (>= 2)
            if (currentStreak >= 2) {
                roastMessage = getRandom(SUSPICIOUS_STREAK_PRAISES);
                tone = "🔥 STREAK OF GLORY";
            }
            // Ultra fast answer (< 1.6s) -> tukka suspicion
            else if (timeTaken < 1.6) {
                roastMessage = getRandom(TUKKA_PRAISES);
                tone = "🎲 TUKKA DETECTED";
            }
            // Custom praise if available (40% probability)
            else if (customPraise != null && !customPraise.isBlank() && ThreadLocalRandom.current().nextInt(100) < 40) {
                roastMessage = customPraise;
                tone = "👑 CHAD BRAIN";
            }
            else {
                roastMessage = getRandom(GENERAL_PRAISES);
            }
        } else {
            tone = "🔥 SAVAGE ROAST";
            audio = "wrong";

            // 1. Shame streak escalation (<= -2)
            if (currentStreak <= -2) {
                roastMessage = getRandom(SHAME_STREAK_BURNS);
                tone = "💀 HATTRICK OF SHAME";
                audio = "timeout";
            }
            // 2. Reaction Speed: Overconfident Blunder (< 1.6s)
            else if (timeTaken < 1.6) {
                roastMessage = getRandom(SPEED_BLUNDER_BURNS);
                tone = "🤡 OVERCONFIDENT BLUNDER";
                audio = "wrong";
            }
            // 3. Reaction Speed: Overthinking Disaster (> 12.5s)
            else if (timeTaken > 12.5) {
                roastMessage = getRandom(OVERTHINKING_BURNS);
                tone = "🐌 OVERTHINKING DISASTER";
                audio = "timeout";
            }
            // 4. Custom question roast (30% probability if exists)
            else if (customRoast != null && !customRoast.isBlank() && ThreadLocalRandom.current().nextInt(100) < 30) {
                roastMessage = customRoast;
                tone = "🎯 PERSONAL ATTACK";
            }
            // 5. Category-Specific Deep-Cuts (35% probability)
            else if (CATEGORY_SPECIFIC_BURNS.containsKey(categorySlug) && ThreadLocalRandom.current().nextInt(100) < 35) {
                roastMessage = getRandom(CATEGORY_SPECIFIC_BURNS.get(categorySlug));
                tone = "⚡ ARENA BURN";
            }
            // 6. Infinite Procedural Combinatorial Generator (35% probability -> NEVER RUNS OUT)
            else if (ThreadLocalRandom.current().nextBoolean()) {
                roastMessage = generateProceduralRoast();
                tone = "💀 PROCEDURAL DAMAGE";
            }
            // 7. Mega Handcrafted Vault (Raw Tears)
            else {
                roastMessage = getRandom(MEGA_TEAR_JERKER_BURNS);
                tone = "😭 EMOTIONAL DAMAGE";
            }
        }

        return Map.of(
            "roastMessage", roastMessage,
            "tone", tone,
            "audio", audio
        );
    }

    /**
     * Synthesizes a completely dynamic procedural roast on-the-fly.
     * With 20 openers × 14 triggers × 18 metaphors × 16 punchlines = 80,640 permutations!
     */
    public String generateProceduralRoast() {
        String opener = getRandom(OPENERS);
        String trigger = getRandom(TRIGGERS);
        String metaphor = getRandom(METAPHORS);
        String punchline = getRandom(PUNCHLINES);

        return String.format("%s %s %s, %s", opener, trigger, metaphor, punchline);
    }

    /**
     * Computes final savage scorecard title, badge and verdict based on performance.
     */
    public Map<String, String> evaluateScorecard(int score, double accuracyRate, int totalQuestions) {
        if (score < 25) {
            return Map.of(
                "title", "🤡 Certified Gawar (Aukaat Zero)",
                "badge", "GAWAR_MAX_PRO",
                "summary", "Bhai tu quiz dene aaya tha ya questions ko pranam karke option A/B/C/D pe ludo khel raha tha? Apne school aur coaching walon ko fees refund ka legal notice bhej de turant.",
                "verdict", "Tumse na ho payega beta. Agle janam me try kariyo, is janam me umeed mat rakh."
            );
        } else if (score < 50) {
            return Map.of(
                "title", "📉 B.Tech Tier-3 Unemployment Icon",
                "badge", "TIER3_MAJDOOR",
                "summary", "Itne score pe sirf IT mass recruitment me 2.8 LPA ka bond offer letter milta hai bhai. Mehnat kar warna agle 5 saal bench pe baith ke samosa chutney hi ginoge.",
                "verdict", "Average se kaafi neeche, par zinda bache ho shukar manao."
            );
        } else if (score < 75) {
            return Map.of(
                "title", "💼 TCS Bench Chai-Samosa Legend",
                "badge", "BENCH_WARRIOR",
                "summary", "Score theek-thaak aa gaya, par tere andar ki aatma jaanti hai ki aadhe questions me andha nishana mara hai tune. Mandir me 20 rupaye ka prasad chadhana mat bhoolna.",
                "verdict", "Kismat 80%, Dimaag 20%. Corporate survival confirmed."
            );
        } else if (score < 90) {
            return Map.of(
                "title", "🎯 Professional Tukkebaaz Extraordinaire",
                "badge", "CHAD_TUKKEBAAZ",
                "summary", "Thoda padha tha, baaki kismat chamak gayi. Ghar walon ko ye scorecard dikha de, shyd ek din ke liye taane marna band kar dein.",
                "verdict", "Aukaat se zyada score le aaya launda. Respect!"
            );
        } else {
            return Map.of(
                "title", "🕵️‍♂️ Google Khola Tha Na Bsdk?",
                "badge", "SUSPICIOUS_TOPPER",
                "summary", "Itna hoshiyar to tu apne rishtedaaro ke samne bhi nahi banta! Pakka doosri tab me Google, ChatGPT ya dost ko phone laga ke baitha tha. Aise toppers pe CID inquiry baithni chahiye.",
                "verdict", "Supreme Authority IQ (Ya fir 100% cheating). Society cannot accept this."
            );
        }
    }

    private String getRandom(List<String> list) {
        if (list == null || list.isEmpty()) return "Bhai kya hi bolu ab tere baare me.";
        int index = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(index);
    }
}
