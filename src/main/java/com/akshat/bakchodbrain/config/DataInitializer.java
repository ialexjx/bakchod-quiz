package com.akshat.bakchodbrain.config;

import com.akshat.bakchodbrain.model.Category;
import com.akshat.bakchodbrain.model.Question;
import com.akshat.bakchodbrain.repository.CategoryRepository;
import com.akshat.bakchodbrain.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() > 0) {
            log.info("BakchodBrain: Database already initialized. Skipping question bank seeding.");
            return;
        }

        log.info("BakchodBrain: Seeding unhinged categories and savage question bank...");

        // ==========================================
        // 1. Tech & Corporate Slavery
        // ==========================================
        Category tech = Category.builder()
                .name("Broke Engineers & Corporate Majdoor")
                .slug("tech-corporate")
                .icon("terminal")
                .tagline("Jira tickets, Friday 5 PM prod deployments & LeetCode trauma")
                .colorAccent("sky")
                .description("For engineers who solve LeetCode Hard but can't center a div in CSS.")
                .build();
        categoryRepository.save(tech);

        questionRepository.saveAll(List.of(
            Question.builder()
                .category(tech)
                .questionText("Java me Array ka index kahan se start hota hai, O Computer Einstein?")
                .optionA("1 se (Human indexing)")
                .optionB("0 se (Standard zero-based indexing)")
                .optionC("-1 se (Python negative slicing)")
                .optionD("HR ki permission ke baad")
                .correctOption("B")
                .explanation("Zero-based indexing hoti hai bhai. Memory offset calculation ke liye.")
                .customRoast("Array index 1 bolne walon ko code editor me nahi, painting class me hona chahiye.")
                .customPraise("Shabash! Kam se kam 1st year engineering ka basic to yaad hai.")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(tech)
                .questionText("Production server Friday shaam 5:30 PM pe crash ho gaya. Standard Senior Dev response kya hona chahiye?")
                .optionA("Turant Slack pe announce karna aur hotfix deploy karna")
                .optionB("'Works on my local machine' bol kar laptop band karke bhaag jana")
                .optionC("Intern ko scapegoat banakar uska access revoke karna")
                .optionD("B aur C dono (Corporate Survival Rule #1)")
                .correctOption("D")
                .explanation("Friday shaam ko production touch karne wale ko direct Yamraj ka call aata hai.")
                .customRoast("Tu pakka wahi hai jo Friday ko bina PR review ke merge karta hai.")
                .customPraise("Corporate survivor detected! Senior Manager banega tu.")
                .difficulty("MEDIUM")
                .build(),
            Question.builder()
                .category(tech)
                .questionText("Git me agar achanak code fat jaye aur samajh na aaye, to sabse aakhiri hathiyar kya hota hai?")
                .optionA("git bisect")
                .optionB("git reflog")
                .optionC("Repo delete karke dobara git clone maarna")
                .optionD("System format karke fake medical certificate lagana")
                .correctOption("C")
                .explanation("Desi developer ka ram-baan: Delete folder, git clone again.")
                .customRoast("Tu to wahi hai na jo commit message me 'fix bug', 'fix again', 'final fix' likhta hai?")
                .customPraise("Pura experience bol raha hai tera! Real developer!")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(tech)
                .questionText("Daily Agile Standup me 15 minute bolne ke baad actual progress kya hoti hai?")
                .optionA("99% feature complete ho chuka hai")
                .optionB("Still looking into that issue, waiting for dependencies")
                .optionC("Jira status change kiya but code nahi likha")
                .optionD("B aur C dono")
                .correctOption("D")
                .explanation("Agile Standup is just an art of convincing your manager you worked yesterday.")
                .customRoast("Manager tera camera on karwayega kal standup me, dekh liyo.")
                .customPraise("Corporate majdoori ka full gyaan hai tujhe.")
                .difficulty("MEDIUM")
                .build(),
            Question.builder()
                .category(tech)
                .questionText("CSS me ek 'div' ko horizontally aur vertically perfect center kaise karte hain bina roye?")
                .optionA("display: flex; justify-content: center; align-items: center;")
                .optionB("margin: auto; aur thoda bhagwan ka naam lena")
                .optionC("position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);")
                .optionD("A ya C dono, baaki laptop tod do")
                .correctOption("D")
                .explanation("Flexbox ya Transform translate best tareeqe hain.")
                .customRoast("Div center nahi hua na tujhse kabhi? Margins badhate reh bas.")
                .customPraise("Frontend CSS ninja! Respect.")
                .difficulty("SAVAGE")
                .build(),
            Question.builder()
                .category(tech)
                .questionText("HR jab bole: 'We are like a family here', iska asli corporate translation kya hai?")
                .optionA("Sab milke Diwali manayenge")
                .optionB("Weekend pe bhi bina overtime ke kaam karwayenge aur hike me 2% denge")
                .optionC("Company CEO tumhara chacha hai")
                .optionD("Health insurance 10 crore ka milega")
                .correctOption("B")
                .explanation("'Like a family' means toxicity free milegi, paise nahi.")
                .customRoast("HR ke pizza party dekh ke hi bik gaya tha na tu? Sudhar ja.")
                .customPraise("Corporate trauma ne tujhe matured banaya hai, salute!")
                .difficulty("MEDIUM")
                .build(),
            Question.builder()
                .category(tech)
                .questionText("Senior Dev ka GitHub PR review comment: 'LGTM' (Looks Good To Me). Asliyat kya hai?")
                .optionA("Code line by line check kiya hai")
                .optionB("Code khol ke dekha bhi nahi, seedha merge mara hai")
                .optionC("Architecture benchmark run kiya")
                .optionD("Security vulnerability audit pass hui")
                .correctOption("B")
                .explanation("LGTM = 'Let's Get This Merged, prod fatega to dekhenge'.")
                .customRoast("Tere PR pe to 'LGTM' bhi nahi, seedha rejection stamp lagna chahiye.")
                .customPraise("Pro developer secrets jaanta hai tu!")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(tech)
                .questionText("Resume me 'Proficient in Java, C++, Go, Rust, React, Docker, Kubernetes, AWS' likhne wale fresher ko company me pehla kaam kya milta hai?")
                .optionA("Microservices architecture design karna")
                .optionB("AI neural network train karna")
                .optionC("Excel sheet me data manually copy-paste karna aur PDF rename karna")
                .optionD("Direct CTO ko shadow karna")
                .correctOption("C")
                .explanation("The grand tragedy of Indian IT: 8 languages in resume, copy-pasting Excel rows in reality.")
                .customRoast("Resume me NASA ka project daal ke aaya tha na tu? Excel khol chup chap.")
                .customPraise("Indian IT reality check passed with 100% marks!")
                .difficulty("EASY")
                .build()
        ));

        // ==========================================
        // 2. Desh-Duniya & GK Bakchodi
        // ==========================================
        Category gk = Category.builder()
                .name("Desh-Duniya & GK Bakchodi")
                .slug("desh-duniya")
                .icon("globe")
                .tagline("Mukherjee Nagar chai tapri geopolitics & UPSC trauma")
                .colorAccent("emerald")
                .description("Absurd real-world questions where confident people make catastrophic guesses.")
                .build();
        categoryRepository.save(gk);

        questionRepository.saveAll(List.of(
            Question.builder()
                .category(gk)
                .questionText("Duniya me sabse zyada shant aur peaceful desh kaunsa mana jata hai?")
                .optionA("Iceland")
                .optionB("Apna padosi Pakistan")
                .optionC("Switzerland")
                .optionD("Wasseypur, Jharkhand")
                .correctOption("A")
                .explanation("Global Peace Index ke mutabiq Iceland saalon se #1 pe hai.")
                .customRoast("Option B tick karne wale ko visa deke wahi bhej dena chahiye.")
                .customPraise("GK solid hai tera, ya bas TV pe news dekh raha tha?")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(gk)
                .questionText("Indian Constitution me total kitne fundamental rights hain?")
                .optionA("6")
                .optionB("7 (Right to Property abhi bhi hai)")
                .optionC("11 (Fundamental duties samajh liya)")
                .optionD("Rights? Wo kya hota hai, hum to corporate me hain")
                .correctOption("A")
                .explanation("Total 6 Fundamental Rights bache hain (Right to Property 44th Amendment me legal right ban gaya).")
                .customRoast("Laxmikanth book ki pooja karna band kar, page khol ke padh bhi le.")
                .customPraise("UPSC aspirant lagta hai bhai tu, clear karega!")
                .difficulty("MEDIUM")
                .build(),
            Question.builder()
                .category(gk)
                .questionText("Train me chain pull karne pe emergency brake lagne ka scientific reason kya hai?")
                .optionA("Loco pilot ka headphone bajta hai")
                .optionB("Brake pipe ka air pressure release ho jata hai")
                .optionC("Guard daudte hue brake lever kheenchta hai")
                .optionD("TTE ko seat bechne ka mauka milta hai")
                .correctOption("B")
                .explanation("Train pneumatic braking par chalti hai. Pressure drop hone par springs brake shoes ko wheel pe daba deti hain.")
                .customRoast("Lagta hai train me bina ticket travel karta hai tu, air brake bhi nahi pata.")
                .customPraise("Engineering plus Indian Railway knowledge! Solid!")
                .difficulty("SAVAGE")
                .build(),
            Question.builder()
                .category(gk)
                .questionText("Zero (0) ka aavishkar kis desh ke mahan ganitagya ne kiya tha?")
                .optionA("America (Silicon Valley me)")
                .optionB("Bharat (Aryabhata)")
                .optionC("England (East India Company)")
                .optionD("Elon Musk ne Twitter pe")
                .correctOption("B")
                .explanation("Bharat ke mahan Aryabhata ne shunya ka concept diya tha.")
                .customRoast("Ye bhi galat kiya? Bhai school ke headmaster se maafi maang jaake.")
                .customPraise("Deshbhakt topper! Aryabhata would be proud.")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(gk)
                .questionText("Subah 7 baje colony ke uncle log park me jor-jor se 'Ha-ha-ha-ha' karke kyun haste hain?")
                .optionA("Kyunki unhe WhatsApp forwards pe hansi aati hai")
                .optionB("Laughter Yoga / Oxygen boost ke liye")
                .optionC("Taaki late uthne wale bacchon ki neend kharab ho sake")
                .optionD("Pension credit hone ki khushi me")
                .correctOption("B")
                .explanation("Laughter yoga endorphins release karta hai aur lung capacity badhata hai.")
                .customRoast("Option C dil se bola na tune? Neend kharab hoti hai na subah?")
                .customPraise("Uncle ji approved knowledge!")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(gk)
                .questionText("Desi mummy ke mutabiq duniya ki 99.9% bimariyon aur pareshaniyon ka single root cause kya hai?")
                .optionA("Pollution aur junk food")
                .optionB("Din bhar mobile phone chalana")
                .optionC("Vitamin D deficiency")
                .optionD("Global warming")
                .correctOption("B")
                .explanation("'Saara din bas is phone me lage raho, isi ki wajah se tabiyat kharab hai' - Mother's universal thesis.")
                .customRoast("Ye bhi galat kar diya? Mummy ko bolta hoon abhi tera phone cheen lein.")
                .customPraise("Mummy ka gyaan amar hai! Bilkul sahi pakda!")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(gk)
                .questionText("Indian Shaadiyon me Fufaji ya Maosaji achanak muh phula ke mandap ke kone me kyu baith jaate hain?")
                .optionA("Kyunki unko dulha pasand nahi aaya")
                .optionB("Kyunki unki aarti sabse pehle nahi utari gayi aur paneer thanda tha")
                .optionC("Baraat late ho gayi")
                .optionD("Unka kurta tight tha")
                .correctOption("B")
                .explanation("Universal wedding law: Fufaji must be offended by at least one minor VIP disrespect.")
                .customRoast("Lagta hai kabhi kisi shaadi me ghaas nahi mili tujhe, isliye nahi pata.")
                .customPraise("Shaadiyon ke sociological expert nikle aap toh!")
                .difficulty("MEDIUM")
                .build()
        ));

        // ==========================================
        // 3. Bollywood & Meme Culture
        // ==========================================
        Category bollywood = Category.builder()
                .name("Bollywood & Meme Culture")
                .slug("bollywood-memes")
                .icon("film")
                .tagline("Hera Pheri, Gangs of Wasseypur, Panchayat & Iconic Dialogues")
                .colorAccent("purple")
                .description("If your brain is 90% memes and 10% oxygen, this is your arena.")
                .build();
        categoryRepository.save(bollywood);

        questionRepository.saveAll(List.of(
            Question.builder()
                .category(bollywood)
                .questionText("Hera Pheri me Shyam ne Babu Bhaiya ko kitne rupaye ka DD (Demand Draft) diya tha?")
                .optionA("40,000")
                .optionB("50,000")
                .optionC("35,000")
                .optionD("10,000")
                .correctOption("C")
                .explanation("'35,000 ka demand draft!' - Babu Bhaiya ki iconic reaction!")
                .customRoast("Hera Pheri 50 baar dekhne ke baad bhi DD amount bhool gaya? Disgrace!")
                .customPraise("Arey Babu Bhaiya! Ekdum accurate! Khopdi tod saale ka!")
                .difficulty("MEDIUM")
                .build(),
            Question.builder()
                .category(bollywood)
                .questionText("Gangs of Wasseypur me Ramadhir Singh ke mutabiq Hindustan me jab tak cinema hai tab tak kya hoga?")
                .optionA("Desh aage badhega")
                .optionB("Log chutiya bante rahenge")
                .optionC("Popcorn mehnga bikega")
                .optionD("Ticket black me bikegi")
                .correctOption("B")
                .explanation("'Jab tak is desh me saneema hai, log chutiya bante rahenge' - Ramadhir Singh.")
                .customRoast("Ramadhir Singh ne tere jaise logon ke liye hi ye dialogue bola tha.")
                .customPraise("Wasseypur ka asli baap nikla tu toh!")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(bollywood)
                .questionText("Welcome movie me Majnu Bhai (Anil Kapoor) ki famous painting me kya tha?")
                .optionA("Mona Lisa with moustache")
                .optionB("Ghode ke upar gadha (Donkey riding a Horse)")
                .optionC("Sunrise in Dubai desert")
                .optionD("Control Uday Control")
                .correctOption("B")
                .explanation("Ghadhe ke upar ghoda... ya ghode ke upar gadha! Modern art masterpiece.")
                .customRoast("Majnu Bhai ka art bhi nahi pehchan paaya? Tu to RD Sharma hi padh bhai.")
                .customPraise("Subhanallah! Masterpiece recognized!")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(bollywood)
                .questionText("Panchayat series me Banrakas (Bhushan) ka sabse favourite dialogue kya hai?")
                .optionA("'Gajab beizzati hai yaar'")
                .optionB("'Dekh raha hai Binod, kaise dimaag chalaya ja raha hai'")
                .optionC("'Aapke andar to Vidhayak banne ke lakshan hain'")
                .optionD("'Kheer khilao pehle'")
                .correctOption("B")
                .explanation("'Dekh raha hai Binod...' became the national meme of the decade.")
                .customRoast("Dekh raha hai Binod? Ye galat answer tick kar raha hai.")
                .customPraise("Phulera Gram Panchayat ka pradhan banne ke laayak hai tu.")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(bollywood)
                .questionText("Munna Bhai M.B.B.S. me Circuit ne anatomical body dissection ke liye dead body kahan se laayi thi?")
                .optionA("Mortuary se legal permission leke")
                .optionB("Chinese tourist ko kidnap karke")
                .optionC("Bhaade pe leke")
                .optionD("Kabristan se khod ke")
                .correctOption("B")
                .explanation("Circuit ne socha tourist so raha hai aur borie me pack karke Medical College le aaya.")
                .customRoast("Munna Bhai dekh ke bhi answer galat? Circuit ko bulau kya tere paas?")
                .customPraise("Bole toh ekdum rapchik answer maama!")
                .difficulty("MEDIUM")
                .build(),
            Question.builder()
                .category(bollywood)
                .questionText("Phir Hera Pheri me Anuradha (Bipasha Basu) ne Laxmi Chit Fund me kitne din me paisa double karne ka dawa kiya tha?")
                .optionA("15 din")
                .optionB("21 din")
                .optionC("25 din")
                .optionD("30 din")
                .correctOption("B")
                .explanation("'21 din me paisa double!' - Raju ka investment plan.")
                .customRoast("21 din me paisa double bhool gaya? Tu pakka cryptocurrency me loss karwane wala investor hai.")
                .customPraise("Zor zor se bol ke logo ko scheme bata de!")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(bollywood)
                .questionText("Welcome movie me RDX (Feroz Khan) ne aate hi sabse pehle kya iconic dialogue maara tha?")
                .optionA("'Main tum sabko jail bhej dunga'")
                .optionB("'Abhi hum zinda hain!'")
                .optionC("'Party shuru karo'")
                .optionD("'Majnu kidhar hai?'")
                .correctOption("B")
                .explanation("'Arey baap re baap... ABHI HUM ZINDA HAIN!' - Legendary RDX swag.")
                .customRoast("RDX ka dialogue bhool gaya? Majnu Bhai tere peeche shooter bhejenge.")
                .customPraise("Iconic swag recognized! Respect!")
                .difficulty("MEDIUM")
                .build()
        ));

        // ==========================================
        // 4. Dating, Simping & Delusion
        // ==========================================
        Category dating = Category.builder()
                .name("Dating, Simping & Heartbreak")
                .slug("dating-delusion")
                .icon("heart-crack")
                .tagline("Seen on WhatsApp, ghosting survivors & 'she is just a friend'")
                .colorAccent("rose")
                .description("For everyone whose heart is broken and whose Instagram screen time is 6 hours.")
                .build();
        categoryRepository.save(dating);

        questionRepository.saveAll(List.of(
            Question.builder()
                .category(dating)
                .questionText("Agar usne message 'Seen' pe chhod diya aur 4 ghante baad 'K' reply kiya, iska actual matlab kya hai?")
                .optionA("Wo bohot busy thi desh ka GDP sambhalne me")
                .optionB("Uski battery 1% pe thi")
                .optionC("Bhai tu uski priority list me 47th number pe hai")
                .optionD("Wo tere baare me hi soch rahi thi")
                .correctOption("C")
                .explanation("Hard truth of modern life: If they take 4 hours to type 'K', you are a backup of a backup.")
                .customRoast("Abhi bhi lagta hai wo busy thi? Thoda self-respect khareed le market se.")
                .customPraise("Reality check accepted! Moving on is the only way.")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(dating)
                .questionText("Agar koi ladka kisi ladki ke 4 saal purane Instagram post pe achanak like kare, to is criminal act ko kya kehte hain?")
                .optionA("Appreciation of vintage aesthetics")
                .optionB("Late-night deep stalking blunder")
                .optionC("Algorithmic bug")
                .optionD("Accidental double tap while coughing")
                .correctOption("B")
                .explanation("Raat ke 2:30 AM pe deep profile scroll karte waqt slip of finger = Stalker exposed.")
                .customRoast("Tune bhi kiya hai na ye kaand kisi ke profile pe? Pakda gaya!")
                .customPraise("Bhai forensic stalker hai tu toh!")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(dating)
                .questionText("Jab koi bolta hai 'I am not ready for a relationship right now', to silent bracket me kya hidden hota hai?")
                .optionA("(Because I need to focus on my career)")
                .optionB("(With you, lodu)")
                .optionC("(My parents are very strict)")
                .optionD("(Mercury is in retrograde)")
                .correctOption("B")
                .explanation("The universal translation of 'I am not ready for relationship' is 'with you'.")
                .customRoast("Option A soch raha tha na tu? Pura delusion me jee raha hai.")
                .customPraise("Dil toota hai par dimaag sahi kaam kar raha hai tera.")
                .difficulty("MEDIUM")
                .build(),
            Question.builder()
                .category(dating)
                .questionText("Dating apps (Tinder/Bumble) me bio me 'Looking for something casual, but let's see where it goes' ka real meaning?")
                .optionA("Future marriage partner search")
                .optionB("Free dinner and validation without commitment")
                .optionC("Philosophical soulmate hunting")
                .optionD("Gym workout buddy")
                .correctOption("B")
                .explanation("Classic modern dating trap: Free pizza, ego boost, zero responsibility.")
                .customRoast("Swipe right karte karte thumb me cramp aa gaya par akal nahi aayi.")
                .customPraise("Dating app veteran detected. Bach ke nikal gaya!")
                .difficulty("MEDIUM")
                .build(),
            Question.builder()
                .category(dating)
                .questionText("First date pe ladka bolta hai: 'Main baaki ladko jaisa nahi hoon'. Asliyat kya hai?")
                .optionA("Wo sach me dev manus hai")
                .optionB("Wo baaki ladko se bhi 10 guna bada red flag hai")
                .optionC("Wo Batman hai")
                .optionD("Uski kundli me grah shant hain")
                .correctOption("B")
                .explanation("'I'm not like other guys' is the international anthem of walking red flags.")
                .customRoast("Tune bhi ye line chipkayi thi na kisi ko? Pakda gaya fraud!")
                .customPraise("Psychology 101 cleared! Red flag detector on point.")
                .difficulty("EASY")
                .build(),
            Question.builder()
                .category(dating)
                .questionText("Instagram pe ladki ke photo pe 'Cute', 'My Queen', 'Didi Looking Gorgeous' comment karne wale laundon ki category?")
                .optionA("High value gentleman")
                .optionB("Parama-Berozgar Chronic Simp")
                .optionC("Fashion designer critique")
                .optionD("Cousin brother")
                .correctOption("B")
                .explanation("The tragic species of the internet: Infinite simping, zero replies.")
                .customRoast("Tu bhi comment section me dil wale emoji drop karta hai na? Sharam kar thodi.")
                .customPraise("Simp spotter champion! Keep that self-respect high!")
                .difficulty("EASY")
                .build()
        ));

        log.info("BakchodBrain: Seeding completed! Loaded 4 categories and {} questions.", questionRepository.count());
    }
}
