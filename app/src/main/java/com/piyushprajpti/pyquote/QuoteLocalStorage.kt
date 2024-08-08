package com.piyushprajpti.pyquote

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class QuoteEntity(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "author")
    val author: String
)

@Entity
data class SaveEntity(
    @PrimaryKey
    val id: String,
)

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quoteEntity")
    fun getAllQuotesList(): List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertQuotes(quotes: List<QuoteEntity>)

    @Query(
        """
        SELECT q.* FROM QuoteEntity q
        INNER JOIN SaveEntity s ON q.id = s.id
    """
    )
    fun getAllSavedQuotes(): List<QuoteEntity>
}

@Dao
interface SaveDao {
    @Query("SELECT EXISTS(SELECT * FROM SaveEntity WHERE id = :id)")
    fun findIdInSaveTable(id: String): Boolean

    @Insert
    fun insertQuote(id: SaveEntity)

    @Delete
    fun deleteQuote(id: SaveEntity)
}

@Database(entities = [QuoteEntity::class, SaveEntity::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
    abstract fun saveDao(): SaveDao
}

val quotesList = listOf(
    QuoteEntity(
        id = "btV2j1uf2t",
        author = "Thomas Edison",
        content = "Faith, as well intentioned as it may be, must be built on facts, not fiction--faith in fiction is a damnable false hope."
    ),
    QuoteEntity(
        id = "_92j6kAvwd",
        author = "Thomas Edison",
        content = "I never did a day's work in my life. It was all fun."
    ),
    QuoteEntity(
        id = "An5NAXPrbN",
        author = "Thomas Edison",
        content = "Hell, there are no rules here-- we're trying to accomplish something."
    ),
    QuoteEntity(
        id = "QfeIDluRi_",
        author = "Thomas Edison",
        content = "There is no expedient to which a man will not go to avoid the labor of thinking."
    ),
    QuoteEntity(
        id = "bfNpGC2NI",
        author = "Thomas Edison",
        content = "As a cure for worrying, work is better than whisky."
    ),
    QuoteEntity(
        id = "jQHjwwHpLN",
        author = "Thomas Edison",
        content = "Results! Why, man, I have gotten a lot of results. I know several thousand things that won't work."
    ),
    QuoteEntity(
        id = "ijc0sPlYFd",
        author = "Thomas Edison",
        content = "The doctor of the future will give no medicine, but will interest her or his patients in the care of the human frame, in a proper diet, and in the cause and prevention of disease."
    ),
    QuoteEntity(
        id = "BzfxvRlA2Y",
        author = "Thomas Edison",
        content = "We don't know a millionth of one percent about anything."
    ),
    QuoteEntity(
        id = "EXXaEturCF",
        author = "Thomas Edison",
        content = "If I find 10,000 ways something won't work, I haven't failed. I am not discouraged, because every wrong attempt discarded is another step forward."
    ),
    QuoteEntity(
        id = "bZIw7gy0sR",
        author = "Thomas Edison",
        content = "I never did anything worth doing by accident, nor did any of my inventions come by accident."
    ),
    QuoteEntity(
        id = "_EC8bhSDQa",
        author = "Charles Dickens",
        content = "Minds, like bodies, will often fall into a pimpled, ill-conditioned state from mere excess of comfort."
    ),
    QuoteEntity(
        id = "qF9iB6wrCQ",
        author = "Charles Dickens",
        content = "Subdue your appetites, my dears, and you've conquered human nature."
    ),
    QuoteEntity(
        id = "ghVnmSpeAo",
        author = "Thomas Edison",
        content = "Everything comes to him who hustles while he waits."
    ),
    QuoteEntity(
        id = "niVz2fQWSH",
        author = "Thomas Edison",
        content = "Opportunity is missed by most people because it is dressed in overalls and looks like work."
    ),
    QuoteEntity(
        id = "RKl9iZrjfP",
        author = "Charles Dickens",
        content = "We need never be ashamed of our tears."
    ),
    QuoteEntity(
        id = "RTYhCs5Ka",
        author = "Charles Dickens",
        content = "I do not know the American gentleman, god forgive me for putting two such words together."
    ),
    QuoteEntity(
        id = "KMTJ0Ya3e9",
        author = "Thomas Edison",
        content = "To invent, you need a good imagination and a pile of junk."
    ),
    QuoteEntity(
        id = "XtZMaD2s28",
        author = "Thomas Edison",
        content = "If we all did the things we are capable of doing, we would literally astound ourselves."
    ),
    QuoteEntity(
        id = "iK-202u02W",
        author = "Thomas Edison",
        content = "Genius is one per cent inspiration, ninety-nine per cent perspiration."
    ),
    QuoteEntity(
        id = "as0ElEk5g7",
        author = "Charles Dickens",
        content = "No one is useless in this world who lightens the burdens of another."
    ),
    QuoteEntity(
        id = "R1DmP1kYtW",
        author = "Charles Dickens",
        content = "Reflect on your present blessings, of which every man has many; not on your past misfortunes, of which all men have some."
    ),
    QuoteEntity(
        id = "j7W6pP1XiG",
        author = "Charles Dickens",
        content = "Train up a fig tree in the way it should go, and when you are old sit under the shade of it."
    ),
    QuoteEntity(
        id = "aQSSYBocy9",
        author = "Albert Einstein",
        content = "Every day I remind myself that my inner and outer life are based on the labors of other men, living and dead, and that I must exert myself in order to give in the same measure as I have received and am still receiving."
    ),
    QuoteEntity(
        id = "Y1Zo7z8BPJ",
        author = "Albert Einstein",
        content = "My religion consists of a humble admiration of the illimitable superior spirit who reveals himself in the slight details we are able to perceive with our frail and feeble mind."
    ),
    QuoteEntity(
        id = "4FLFmHaK7-",
        author = "Albert Einstein",
        content = "It is the supreme art of the teacher to awaken joy in creative expression and knowledge."
    ),
    QuoteEntity(
        id = "8WhYatXQzF",
        author = "Albert Einstein",
        content = "If I had only known, I would have been a locksmith."
    ),
    QuoteEntity(
        id = "EG9yQpz6Ku",
        author = "Albert Einstein",
        content = "Few people are capable of expressing with equanimity opinions which differ from the prejudices of their social environment. Most people are even incapable of forming such opinions."
    ),
    QuoteEntity(
        id = "2ts3DrACVY",
        author = "Albert Einstein",
        content = "I never teach my pupils. I only attempt to provide the conditions in which they can learn."
    ),
    QuoteEntity(
        id = "2qpi1ZKL9Ko",
        author = "Albert Einstein",
        content = "Perfection of means and confusion of ends seems to characterize our age."
    ),
    QuoteEntity(
        id = "b437W9Z_RN_",
        author = "Albert Einstein",
        content = "Reading, after a certain age, diverts the mind too much from its creative pursuits. Any man who reads too much and uses his own brain too little falls into lazy habits of thinking."
    ),
    QuoteEntity(
        id = "iCcQwfTUU3",
        author = "Albert Einstein",
        content = "Laws alone can not secure freedom of expression; in order that every man present his views without penalty there must be spirit of tolerance in the entire population."
    ),
    QuoteEntity(
        id = "fC5Jr2NsYm",
        author = "Albert Einstein",
        content = "Gravitation cannot be held responsible for people falling in love. How on earth can you explain in terms of chemistry and physics so important a biological phenomenon as first love? Put your hand on a stove for a minute and it seems like an hour. Sit with that special girl for an hour and it seems like a minute. That's relativity."
    ),
    QuoteEntity(
        id = "inED6huctY",
        author = "Albert Einstein",
        content = "If you are out to describe the truth, leave elegance to the tailor."
    ),
    QuoteEntity(
        id = "mqbH2SBrIS",
        author = "Albert Einstein",
        content = "I know not with what weapons World War III will be fought, but World War IV will be fought with sticks and stones."
    ),
    QuoteEntity(
        id = "cOuSBYiscf",
        author = "Albert Einstein",
        content = "I never think of the future - it comes soon enough."
    ),
    QuoteEntity(
        id = "vbvqg3V6j8",
        author = "Albert Einstein",
        content = "It is a miracle that curiosity survives formal education."
    ),
    QuoteEntity(
        id = "mg8F-nsmPEj",
        author = "Albert Einstein",
        content = "Only two things are infinite, the universe and human stupidity, and I'm not sure about the former."
    ),
    QuoteEntity(
        id = "wFrlCzqagir",
        author = "Albert Einstein",
        content = "The ideals which have lighted my way, and time after time have given me new courage to face life cheerfully, have been Kindness, Beauty, and Truth. The trite subjects of human efforts, possessions, outward success, luxury have always seemed to me contemptible."
    ),
    QuoteEntity(
        id = "xWusiklxiy",
        author = "Albert Einstein",
        content = "Do not worry about your difficulties in Mathematics. I can assure you mine are still greater."
    ),
    QuoteEntity(
        id = "dCBRnkMsW",
        content = "Before God we are all equally wise - and equally foolish.",
        author = "Albert Einstein"
    ),
    QuoteEntity(
        id = "8Oj6iwQ1xp",
        content = "Ethical axioms are found and tested not very differently from the axioms of science. Truth is what stands the test of experience.",
        author = "Albert Einstein"
    ),
    QuoteEntity(
        id = "t5-eBEIfiB",
        content = "Persuasion is often more effectual than force.",
        author = "Aesop"
    ),
    QuoteEntity(
        id = "w4WpYMr4Kw",
        content = "Price is what you pay. Value is what you get.",
        author = "Warren Buffett"
    ),
    QuoteEntity(
        id = "V4L_CRgfaZ",
        content = "I violated the Noah rule: Predicting rain doesn't count; building arks does.",
        author = "Warren Buffett"
    ),
    QuoteEntity(
        id = "v1UpJ92Plf",
        content = "Most people get interested in stocks when everyone else is. The time to get interested is when no one else is. You can't buy what is popular and do well.",
        author = "Warren Buffett"
    ),
    QuoteEntity(
        id = "de2uy5VnrA",
        content = "If you have a harem of 40 women, you never get to know any of them very well.",
        author = "Warren Buffett"
    ),
    QuoteEntity(
        id = "Ykg69dlT1z",
        content = "It's only when the tide goes out that you discover who's been swimming naked.",
        author = "Warren Buffett"
    ),
    QuoteEntity(
        id = "kZt2i_QU_-",
        content = "The smaller the mind the greater the conceit.",
        author = "Aesop"
    ),
    QuoteEntity(
        id = "PlqVkGcCIp",
        content = "Beware lest you lose the substance by grasping at the shadow.",
        author = "Aesop"
    ),
    QuoteEntity(
        id = "uV8RyeIRf",
        content = "Better be wise by the misfortunes of others than by your own.",
        author = "Aesop"
    ),
    QuoteEntity(
        id = "Y93446BoHU",
        content = "I won't close down a business of subnormal profitability merely to add a fraction of a point to our corporate returns. I also feel it inappropriate for even an exceptionally profitable company to fund an operation once it appears to have unending losses in prospect. Adam Smith would disagree with my first proposition and Karl Marx would disagree with my second; the middle ground is the only position that leaves me comfortable.",
        author = "Warren Buffett"
    ),
    QuoteEntity(
        id = "6S4ONaVMZU",
        content = "Be content with your lot; one cannot be first in everything.",
        author = "Aesop"
    ),
    QuoteEntity(
        id = "rtTuQXyDh",
        content = "I don't look to jump over 7-foot bars; I look around for 1-foot bars that I can step over.",
        author = "Warren Buffett"
    ),
    QuoteEntity(
        id = "Sk4PFT-76M",
        content = "In the business world, the rearview mirror is always clearer than the windshield.",
        author = "Warren Buffett"
    ),
    QuoteEntity(
        id = "5S-INJNFCI",
        content = "It is with our passions, as it is with fire and water, they are good servants but bad masters.",
        author = "Aesop"
    ),
    QuoteEntity(
        id = "sR7TlnhbaS",
        content = "Injuries may be forgiven, but not forgotten.",
        author = "Aesop"
    ),
    QuoteEntity(
        id = "RGw_9OPsR_",
        content = "It is easy to be brave from a safe distance.",
        author = "Aesop"
    ),
    QuoteEntity(
        id = "483LDToxFU",
        content = "The only time to buy these is on a day with no 'y' in it.",
        author = "Warren Buffett"
    ),
    QuoteEntity(
        id = "eOGgo5Kj2tu",
        content = "When watching after yourself, you watch after others. When watching after others, you watch after yourself.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "LXc7FXl9o2g",
        content = "Whoever doesn't flare up at someone who's angry wins a battle hard to win.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "FJHapZ0U5ii",
        content = "Resolutely train yourself to attain peace.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "6LX3h3UamO5",
        content = "Should a person do good, let him do it again and again. Let him find pleasure therein, for blissful is the accumulation of good.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "ESJFchb_5h4",
        content = "The calmed say that what is well-spoken is best; second, that one should say what is right, not unrighteous; third, what's pleasing, not displeasing; fourth, what is true, not false.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "9sgo4-hjyyq",
        content = "Radiate boundless love towards the entire world — above, below, and across — unhindered, without ill will, without enmity.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "-3IqRLv81t-",
        content = "One is not called noble who harms living beings. By not harming living beings one is called noble.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "6_rYXTe8c4S",
        content = "May all beings have happy minds.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "rfJp893hM2Z",
        content = "Some do not understand that we must die, But those who do realize this settle their quarrels.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "qLn3nRwjVsb",
        content = "Know from the rivers in clefts and in crevices: those in small channels flow noisily, the great flow silent. Whatever's not full makes noise. Whatever is full is quiet.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "WnJz7QKVKzI",
        content = "The root of suffering is attachment.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "MPTVFOHP7ds",
        content = "The world is afflicted by death and decay. But the wise do not grieve, having realized the nature of the world.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "HhQM5yo-IxF",
        content = "Just as a solid rock is not shaken by the storm, even so the wise are not affected by praise or blame.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "fEr2kx4_Nvt",
        content = "Hatred is never appeased by hatred in this world. By non-hatred alone is hatred appeased. This is a law eternal.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "Zu5upLF-ANy",
        content = "He who can curb his wrath as soon as it arises, as a timely antidote will check snake's venom that so quickly spreads, — such a monk gives up the here and the beyond, just as a serpent sheds its worn-out skin.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "M_J8mramwqX",
        content = "Irrigators channel waters; fletchers straighten arrows; carpenters bend wood; the wise master themselves.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "aS2JIspazSE",
        content = "They blame those who remain silent, they blame those who speak much, they blame those who speak in moderation. There is none in the world who is not blamed.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "3_QolF6N53C",
        content = "If a man going down into a river, swollen and swiftly flowing, is carried away by the current — how can he help others across? – The Buddha",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "O-cGDBm4dOE",
        content = "All conditioned things are impermanent’ — when one sees this with wisdom, one turns away from suffering. This is the path to purification.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "49RSlw9W3Op",
        content = "Ardently do today what must be done. Who knows? Tomorrow, death comes.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "nP2Rx-1k-lv",
        content = "It is a man's own mind, not his enemy or foe, that lures him to evil ways.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "sT905eIjh2n",
        content = "Ceasing to do evil, Cultivating the good, Purifying the heart: This is the teaching of the Buddhas.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "mS7GMRZeNuX",
        content = "Let none find fault with others; let none see the omissions and commissions of others. But let one see one's own acts, done and undone.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "8rxNd1of_8I",
        content = "Whatever has the nature of arising has the nature of ceasing.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "sD1d2QmwBJA",
        content = "There is no fear for one whose mind is not filled with desires.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "4g-MIkyfFg9",
        content = "Should a seeker not find a companion who is better or equal, let them resolutely pursue a solitary course.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "d4jauwhMpVA",
        content = "Should you find a wise critic to point out your faults, follow him as you would a guide to hidden treasure.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "QLFvxnERU8M",
        content = "Radiate boundless love towards the entire world…",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "kFmm2Cv0V-",
        content = "It is in the nature of things that joy arises in a person free from remorse.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "K67NZgh4Zr0",
        content = "Speak only endearing speech, speech that is welcomed. Speech, when it brings no evil to others, is a pleasant thing.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "ExEfYwFimnJ",
        content = "Better it is to live one day seeing the rise and fall of things than to live a hundred years without ever seeing the rise and fall of things.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "0umC2Yk5UX",
        content = "One should train in deeds of merit—generosity, a balanced life, developing a loving mind—that yield long-lasting happiness.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "qvVLiNnyMsK",
        content = "A mind unruffled by the vagaries of fortune, from sorrow freed, from defilements cleansed, from fear liberated — this is the greatest blessing.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "n7QR1DkeA4V",
        content = "All tremble at violence; all fear death. Putting oneself in the place of another, one should not kill nor cause another to kill.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "wiVouCxUSmn",
        content = "The thing that is disliked by me is also disliked by others. Since I dislike this thing, how can I inflict it on someone else?",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "XrFIHIebkEW",
        content = "As a water bead on a lotus leaf, as water on a red lily, does not adhere, so the sage does not adhere to the seen, the heard, or the sensed.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "bejpH5vesL0",
        content = "Better than a thousand useless words is one useful word, upon hearing which one attains peace.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "Gl_8IjYm3ny",
        content = "It's a good thing to be satisfied with what one has.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "e-U_kGn45jq",
        content = "To support mother and father, to cherish partner and children, and to be engaged in peaceful occupation — this is the greatest blessing.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "7FWID1UNP8o",
        content = "As an elephant in the battlefield withstands arrows shot from bows all around, even so shall I endure abuse.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "82ovA4JWpM",
        content = "Purity and impurity depend on oneself; no one can purify another.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "sLWD2qRCK4",
        content = "Just as a mother would protect her only child with her life, even so let one cultivate a boundless love towards all beings.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "YeJpbyaNMs",
        content = "Meditate … do not delay, lest you later regret it.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "0LLB8uuMXb",
        content = "Ardently do today what must be done. Who knows? Tomorrow, death comes.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "E7Pt4hWgrH",
        content = "All experiences are preceded by mind, having mind as their master, created by mind.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "UGjIXpUCPv",
        content = "A noble one produces an abundance of merit by having a compassionate mind towards all living beings.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "hcCn5sAnUO",
        content = "Conquer anger with non-anger. Conquer badness with goodness. Conquer meanness with generosity. Conquer dishonesty with truth.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "DtjcWlyBGv",
        content = "Delight in heedfulness! Guard well your thoughts!",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "alB5Kgdk_S",
        content = "A wise person should be urgently moved on occasions that make for urgency.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "1YkUWV_0wT",
        content = "Give, even if you only have a little.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "Q6ecn_qg0",
        content = "'As I am, so are they; as they are, so am I.' Comparing others with oneself, do not kill nor cause others to kill.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "eC9gLWRKO-",
        content = "I do not dispute with the world; rather it is the world that disputes with me.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "smbfaOldHD",
        content = "A disciplined mind brings happiness.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "gI4gnBANili",
        content = "We will develop and cultivate the liberation of mind by lovingkindness, make it our vehicle, make it our basis, stabilize it, exercise ourselves in it, and fully perfect it.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "evSH-AgPIXv",
        content = "Both formerly and now, it is only suffering that I describe, and the cessation of suffering.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "0Lp0Xv7xP2D",
        content = "You yourself must strive. The Buddhas only point the way.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "Gljrsvf8-hQ",
        content = "Those who cling to perceptions and views wander the world offending people.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "YLwAq4ekdr",
        content = "If with a pure mind a person speaks or acts, happiness follows them like a never-departing shadow.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "XH8yFMgx_o",
        content = "There is no fear for one whose mind is not filled with desires.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "teQosHfPIYX",
        content = "True love is not just about feelings. It's a state of mind.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "q_iRPGdzVzM",
        content = "What we are today comes from our thoughts of yesterday, and our present thoughts build our life of tomorrow.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "jCRvE6uEBe7",
        content = "Peace comes from within. Do not seek it without.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "sqGhC_Kse80",
        content = "Even the gods envy those who are free from attachment.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "Yl5fPCvF5dE",
        content = "The mind is everything. What you think you become.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "cBlD2t4H1z2",
        content = "A wise person is a person who does not quarrel.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "fW0YEXgPPTt",
        content = "The only real failure in life is not to be true to the best one knows.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "e4rxq6gThA3",
        content = "The way is not in the sky. The way is in the heart.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "MGiJxCmBoe9",
        content = "To understand everything is to forgive everything.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "y7_EZUVG9Kt",
        content = "There is no path to happiness: happiness is the path.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "14QHWeWnAR1",
        content = "The root of suffering is attachment.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "8yFYwU9gVXM",
        content = "As long as you are tied to the things of the world, you will suffer.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "lvUb2q36oxR",
        content = "Those who have failed to work towards the truth have missed the point.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "h2JdYXgMJbc",
        content = "May all beings be happy; may all beings be without disease.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "dGm9s1lVRLo",
        content = "Life is a journey, not a destination.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "6p1LsFszZFS",
        content = "To conquer oneself is a greater victory than to conquer thousands in a battle.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "U48R6JlYYG8",
        content = "The trouble is, you think you have time.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "G7YtXQZyQau",
        content = "The best way to find yourself is to lose yourself in the service of others.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "8EZ-UzL0gDh",
        content = "As a well-trained horse does not break free of the bridle, a well-trained person will not break the precepts.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "pXfayDb3cMz",
        content = "The way is not in the sky. The way is in the heart.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "y16A7KjV57x",
        content = "Forgiveness is the attribute of the strong.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "bq6-Lp4PSfR",
        content = "The mind is everything. What you think you become.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "gADu00w1ARJ",
        content = "The wise man is one who knows what he does not know.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "0LD7b_o4khc",
        content = "Do not dwell in the past, do not dream of the future, concentrate the mind on the present moment.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "b37X53pl1o4",
        content = "Even the gods envy those who are free from attachment.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "UiyXfwLoG0e",
        content = "A wise man who knows his limitations is a wise man indeed.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "q1tCEjOxoGm",
        content = "Everything that has a beginning has an ending. Make your peace with that and all will be well.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "EK7z2wa5a5r",
        content = "You will not be punished for your anger, you will be punished by your anger.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "bQv1TYEp5E",
        content = "Wisdom is the path to happiness.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "Ub0mJz4EF46",
        content = "To be truly happy, one must be free from attachment and desire.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "8Rb2lddxn3W",
        content = "The past is already gone, the future is not yet here. There's only one moment for you to live.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "5-WgA4_CG0W",
        content = "In the end, only three things matter: how much you loved, how gently you lived, and how gracefully you let go of things not meant for you.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "qQ4AE02TG_G",
        content = "All things are on fire. All things are burning. Indeed, all things are on fire.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "A8G60M76Tna",
        content = "The more you know yourself, the more clarity there is. Self-knowledge has no end.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "69-TLr_aOTj",
        content = "The best way to find yourself is to lose yourself in the service of others.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "rhh8xg0o4Lw",
        content = "Nothing in the world can take the place of Persistence. Talent will not; nothing is more common than unsuccessful men with talent.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "fd3E5R8sgVo",
        content = "Do not judge others. You cannot judge the heart of another.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "wwSx7vN2y0S",
        content = "To enjoy good health, to bring true happiness to one’s family, to bring peace to all, one must first discipline and control one’s own mind.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "hF77EZ2Dhpa",
        content = "If you light a lamp for someone else it will also brighten your path.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "PVhpR_l7nb4",
        content = "Those who are free from attachment, are free from suffering.",
        author = "The Buddha"
    ),
    QuoteEntity(
        id = "AxNf6c-G_KI",
        content = "People ask me what I do in winter when there's no baseball. I'll tell you what I do. I stare out the window and wait for spring.",
        author = "Rogers Hornsby"
    ),
    QuoteEntity(
        id = "81hjl4B9wx2",
        content = "The only way to prove that you're a good sport is to lose.",
        author = "Ernie Banks"
    ),
    QuoteEntity(
        id = "f6PUk-AOBm",
        content = "If you fell down yesterday, stand up today.",
        author = "H. G. Wells"
    ),
    QuoteEntity(
        id = "l4SIHYrQpX",
        content = "Knowing is not enough; we must apply. Willing is not enough; we must do.",
        author = "Johann Wolfgang von Goethe"
    ),
    QuoteEntity(
        id = "y1iv94PsONA",
        content = "Our work is the presentation of our capabilities.",
        author = "Edward Gibbon"
    ),
    QuoteEntity(
        id = "J471lzY_ZWf",
        content = "You spend a good piece of your life gripping a baseball and in the end it turns out that it was the other way around all the time.",
        author = "Jim Bouton"
    ),
    QuoteEntity(
        id = "WPXF6M0Yn_Y",
        content = "Trust is the lubrication that makes it possible for organizations to work.",
        author = "Warren Bennis"
    ),
    QuoteEntity(
        id = "NIMf1RxZS1N",
        content = "One man practicing sportsmanship is far better than a hundred teaching it.",
        author = "Knute Rockne"
    ),
    QuoteEntity(
        id = "sBuAicwO4Z",
        content = "Ideas pull the trigger, but instinct loads the gun.",
        author = "Don Marquis"
    ),
    QuoteEntity(
        id = "E_uIJ03GjDQ",
        content = "To command is to serve, nothing more and nothing less.",
        author = "André Malraux"
    ),
    QuoteEntity(
        id = "3KGpfidF22D",
        content = "Most people never run far enough on their first wind to find out they've got a second.",
        author = "William James"
    ),
    QuoteEntity(
        id = "py0SqUMHGW9",
        content = "Don't worry about people stealing your ideas. If your ideas are any good, you'll have to ram them down people's throats.",
        author = "Howard H. Aiken"
    ),
    QuoteEntity(
        id = "Zrxblk3MazL",
        content = "Meetings are indispensable when you don't want to do anything.",
        author = "John Kenneth Galbraith"
    ),
    QuoteEntity(
        id = "9aunKqSWDu",
        content = "Gold medals aren't really made of gold. They're made of sweat, determination, and a hard-to-find alloy called guts.",
        author = "Dan Gable"
    ),
    QuoteEntity(
        id = "EqK6-7vPt",
        content = "When something is important enough, you do it even if the odds are not in your favor.",
        author = "Elon Musk"
    ),
    QuoteEntity(
        id = "2wnTElJ9STU",
        content = "My motto was always to keep swinging. Whether I was in a slump or feeling badly or having trouble off the field, the only thing to do was keep swinging.",
        author = "Hank Aaron"
    ),
    QuoteEntity(
        id = "scBvf0Y5W3B",
        content = "To me, it doesn't matter how good you are. Sport is all about playing and competing. Whatever you do in cricket and in sport, enjoy it, be positive and try to win.",
        author = "Ian Botham"
    ),
    QuoteEntity(
        id = "_WfHG0pIS0",
        content = "Aim for the moon. If you miss, you may hit a star.",
        author = "W. Clement Stone"
    ),
    QuoteEntity(
        id = "0p8bgUAkaWj",
        content = "Pitching is the art of instilling fear.",
        author = "Sandy Koufax"
    ),
    QuoteEntity(
        id = "wNd1PnDiIN",
        content = "Every day is a new opportunity. You can build on yesterday's success or put its failures behind and start over again. That's the way life is, with a new game every day, and that's the way baseball is.",
        author = "Bob Feller"
    ),
    QuoteEntity(
        id = "Gtt0T3mfPbx",
        content = "Champions keep playing until they get it right.",
        author = "Billie Jean King"
    ),
    QuoteEntity(
        id = "SnxKyCAPxV",
        content = "It's easy to make a buck. It's a lot tougher to make a difference.",
        author = "Tom Brokaw"
    ),
    QuoteEntity(
        id = "bcHXRb64gZH",
        content = "Sports do not build character. They reveal it.",
        author = "Heywood Broun"
    ),
    QuoteEntity(
        id = "Aooogq1lu-",
        content = "Happiness does not come from doing easy work but from the afterglow of satisfaction that comes after the achievement of a difficult task that demanded our best.",
        author = "Theodore Isaac Rubin"
    ),
    QuoteEntity(
        id = "yq5LCWQjDJU",
        content = "No enterprise is more likely to succeed than one concealed from the enemy until it is ripe for execution.",
        author = "Niccolò Machiavelli"
    ),
    QuoteEntity(
        id = "E24zrX6RYt",
        content = "An organization's ability to learn, and translate that learning into action rapidly, is the ultimate competitive advantage.",
        author = "Jack Welch"
    ),
    QuoteEntity(
        id = "_rEPkuDXaWo",
        content = "Finding good players is easy. Getting them to play as a team is another story.",
        author = "Casey Stengel"
    ),
    QuoteEntity(
        id = "Hpo_qh-q76A",
        content = "Fans don't boo nobodies.",
        author = "Reggie Jackson (basketball, born 1990)"
    ),
    QuoteEntity(
        id = "I4MksjS5zey",
        content = "Life is about timing.",
        author = "Carl Lewis"
    ),
    QuoteEntity(
        id = "eF07T22v0Te",
        content = "To find a man's true character, play golf with him.",
        author = "P. G. Wodehouse"
    )
)