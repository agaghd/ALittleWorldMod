package characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import pathes.ThmodClassEnum;
import pathes.AbstractCardEnum;
import java.util.ArrayList;

public class Dora extends CustomPlayer {
    //初始能量
    private static final int ENERGY_PER_TURN = 3;
    // 以下图片依次作用为[篝火休息处的角色背影2，篝火休息处的角色背影1，角色死亡后倒下的图片，角色平常站立时的图片]
    private static final String DORA_SHOULDER_2 = "img/char_Dora/shoulder2.png";
    private static final String DORA_SHOULDER_1 = "img/char_Dora/shoulder1.png";
    private static final String DORA_CORPSE = "img/char_Dora/fallen.png";
    private static final String DORA_STAND = "img/char_Dora/Dora.png";
    //各种素材，不是很懂
    private static final String[] ORB_TEXTURES = new String[] {
            "img/UI_Dora/EPanel/layer6.png",
            "img/UI_Dora/EPanel/layer5.png",
            "img/UI_Dora/EPanel/layer4.png",
            "img/UI_Dora/EPanel/layer3.png",
            "img/UI_Dora/EPanel/layer2.png",
            "img/UI_Dora/EPanel/layer1.png",
            "img/UI_Dora/EPanel/layer5d.png",
            "img/UI_Dora/EPanel/layer4d.png",
            "img/UI_Dora/EPanel/layer3d.png",
            "img/UI_Dora/EPanel/layer2d.png",
            "img/UI_Dora/EPanel/layer1d.png" };
    //
    private static final String ORB_VFX = "img/UI_Dora/energyBlueVFX.png";
    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };
    //初始生命，最大生命，初始金币,初始的充能球栏位（机器人）,最后一个应该是进阶14时的最大生命值下降
    private static final int STARTING_HP = 75;
    private static final int MAX_HP = 75;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 5;
    //返回一个颜色
    public static final Color SILVER = CardHelper.getColor(200, 200, 200);

    public Dora(String name) {
        //构造方法，初始化参数
        super(name, ThmodClassEnum.Dora_CLASS, ORB_TEXTURES, ORB_VFX, LAYER_SPEED, (String)null, (String)null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;
        initializeClass(DORA_STAND, DORA_SHOULDER_2, DORA_SHOULDER_1, DORA_CORPSE,
                getLoadout(),
                0.0F, 5.0F, 240.0F, 300.0F,
                new EnergyManager(ENERGY_PER_TURN));
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        //添加初始卡组
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("StrikeOfDora");
        retVal.add("StrikeOfDora");
        retVal.add("StrikeOfDora");
        retVal.add("DefendOfDora");
        retVal.add("DefendOfDora");
        retVal.add("DefendOfDora");
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        //添加初始遗物
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("CapOfDora");
        UnlockTracker.markRelicAsSeen("CapOfDora");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        //选英雄界面的文字描述
        String title="";
        String flavor="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "朵菈";
            flavor = "玩具世界的管理人朵菈";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            //当设定为中国台湾省，title和flavor为繁体描述
            title = "朵菈";
            flavor = "玩具世界的管理人朵菈";
        } else {
            //其他用英文替代
            title = "Dora";
            flavor = "The Manager Of Toys world";
        }

        return new CharSelectInfo(title, flavor, STARTING_HP, MAX_HP,HAND_SIZE , STARTING_GOLD, ASCENSION_MAX_HP_LOSS,
                this, getStartingRelics(), getStartingDeck(), false);
    }


    @Override
    public String getTitle(PlayerClass playerClass) {
        //应该是进游戏后左上角的角色名
        String title="";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            title = "朵菈";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            title = "朵菈";
        } else {
            title = "Dora";
        }

        return title;
    }

    @Override

    public AbstractCard.CardColor getCardColor() {
        //选择卡牌颜色
        return AbstractCardEnum.Dora_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        return SILVER;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    @Override
    public Color getCardTrailColor() {
        return SILVER;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {

    }
    public void updateOrb(int orbCount) {
        this.energyOrb.updateOrb(orbCount);
    }
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        String char_name;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            char_name = "朵菈";
        } else if (Settings.language == Settings.GameLanguage.ZHT) {
            char_name = "朵菈";
        } else {
            char_name = "Dora";
        }
        return char_name;
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Dora(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[10];
    }

    @Override
    public Color getSlashAttackColor() {
        return SILVER;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {

        return null;
    }
    public void applyEndOfTurnTriggers() {
        super.applyEndOfTurnTriggers();
    }
}