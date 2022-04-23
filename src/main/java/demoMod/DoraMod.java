package demoMod;

import basemod.BaseMod;
import basemod.interfaces.*;
import cards.DefendOfDora;
import cards.StrikeOfDora;
import characters.Dora;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import pathes.AbstractCardEnum;
import pathes.ThmodClassEnum;
import relics.CapOfDora;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

@SpireInitializer
public class DoraMod implements RelicGetSubscriber, PostPowerApplySubscriber,
        PostExhaustSubscriber, PostBattleSubscriber, PostDungeonInitializeSubscriber,
        EditCharactersSubscriber, PostInitializeSubscriber, EditRelicsSubscriber, EditCardsSubscriber,
        EditStringsSubscriber, OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber,
        PostDrawSubscriber, PostEnergyRechargeSubscriber {
    //图片
    private static final String MOD_BADGE = "img/UI_Dora/badge.png";
    //攻击、技能、能力牌的图片(512)
    private static final String ATTACK_CC = "img/512/bg_attack_Dora_s.png";
    private static final String SKILL_CC = "img/512/bg_skill_Dora_s.png";
    private static final String POWER_CC = "img/512/bg_power_Dora_s.png";
    private static final String ENERGY_ORB_CC = "img/512/DoraOrb.png";
    //攻击、技能、能力牌的图片(1024)
    private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_Dora.png";
    private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_Dora.png";
    private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_Dora.png";
    private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/DoraOrb.png";
    public static final String CARD_ENERGY_ORB = "img/UI_Dora/energyOrb.png";
    //选英雄界面的角色图标、选英雄时的背景图片
    private static final String MY_CHARACTER_BUTTON = "img/charSelect/DoraButton.png";
    private static final String MARISA_PORTRAIT = "img/charSelect/DoraPortrait.png";
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);
    private ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();
    public static ArrayList<AbstractCard> recyclecards = new ArrayList<>();

    public DoraMod() {
        //构造方法，初始化各种参数
        BaseMod.subscribe((ISubscriber) this);
        BaseMod.addColor(AbstractCardEnum.Dora_COLOR, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER, SILVER,
                ATTACK_CC, SKILL_CC, POWER_CC, ENERGY_ORB_CC, ATTACK_CC_PORTRAIT, SKILL_CC_PORTRAIT, POWER_CC_PORTRAIT,
                ENERGY_ORB_CC_PORTRAIT, CARD_ENERGY_ORB);
    }

    @Override
    public void receiveEditCharacters() {
        //添加角色到MOD中
        BaseMod.addCharacter((AbstractPlayer) new Dora("Dora"), MY_CHARACTER_BUTTON, MARISA_PORTRAIT,
                ThmodClassEnum.Dora_CLASS);
    }

    //初始化整个MOD,一定不能删
    public static void initialize() {
        new DoraMod();
    }

    @Override
    public void receiveEditCards() {
        //将卡牌批量添加
        loadCardsToAdd();
        Iterator<AbstractCard> var1 = this.cardsToAdd.iterator();
        while (var1.hasNext()) {
            AbstractCard card = var1.next();
            BaseMod.addCard(card);
        }
    }

    @Override
    public void receivePostExhaust(AbstractCard c) {
    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower pow, AbstractCreature target, AbstractCreature owner) {

    }


    @Override
    public void receivePowersModified() {
    }


    @Override
    public void receivePostDungeonInitialize() {
    }


    @Override
    public void receivePostDraw(AbstractCard arg0) {
    }

    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }


    @Override
    public void receiveEditKeywords() {

    }

    @Override
    public void receiveEditStrings() {
        //读取遗物，卡牌，能力，药水，事件的JSON文本

        String relic = "", card = "", power = "", potion = "", event = "";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            card = "localization/ALittleWorldMod_Dora_cards-zh.json";
            relic = "localization/ALittleWorldMod_Dora_relics-zh.json";
            power = "localization/ALittleWorldMod_Dora_powers-zh.json";
            //potion = "localization/ALittleWorldMod_YM_potions-zh.json";
            //event = "localization/ALittleWorldMod_YM_events-zh.json";
        } else {
            //其他语言配置的JSON
        }

        String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
//     String potionStrings = Gdx.files.internal(potion).readString(String.valueOf(StandardCharsets.UTF_8));
//     BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
//     String eventStrings = Gdx.files.internal(event).readString(String.valueOf(StandardCharsets.UTF_8));
//     BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
    }

    private void loadCardsToAdd() {
        //将自定义的卡牌添加到这里
        this.cardsToAdd.clear();
        this.cardsToAdd.add(new StrikeOfDora());
        this.cardsToAdd.add(new DefendOfDora());
//        this.cardsToAdd.add(new SavePower());
//        this.cardsToAdd.add(new Printf());
    }

    //添加一度
    @Override
    public void receiveEditRelics() {
        //将自定义的遗物添加到这里
        BaseMod.addRelicToCustomPool((AbstractRelic) new CapOfDora(), AbstractCardEnum.Dora_COLOR);
    }

    @Override
    public void receiveRelicGet(AbstractRelic relic) {
        //移除遗物,这里移除了小屋子，太垃圾了。

        if (AbstractDungeon.player.name == "Dora") {
            AbstractDungeon.shopRelicPool.remove("TinyHouse");
        }
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {

    }

    @Override
    public void receivePostBattle(AbstractRoom r) {

    }

    @Override
    public void receivePostInitialize() {

    }

    @Override
    public void receivePostEnergyRecharge() {
        Iterator<AbstractCard> var1 = recyclecards.iterator();

        while (var1.hasNext()) {
            AbstractCard c = var1.next();
            AbstractCard card = c.makeStatEquivalentCopy();
            AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, false, true, true));
        }
        recyclecards.clear();
    }

    class Keywords {
        Keyword[] keywords;
    }
}