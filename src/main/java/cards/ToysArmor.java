package cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import pathes.AbstractCardEnum;


public class ToysArmor extends CustomCard {
    private static final CardStrings cardStrings
            = CardCrawlGame.languagePack.getCardStrings("ToysArmor");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final String ID = "ToysArmor";
    // 防御图片
    private static final String IMG_PATH = "img/cards_Dora/Default.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public ToysArmor() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Dora_COLOR,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = BLOCK_AMT;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //使用卡牌时触发的动作
        addToBot(new VFXAction(new BorderFlashEffect(Color.CHARTREUSE, true)));
        addToBot(new VFXAction(abstractPlayer, new MiracleEffect(Color.CHARTREUSE, Color.LIME,
                "BLOCK_GAIN_1"), 1.0F));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer,
                new PlatedArmorPower(abstractPlayer, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ToysArmor();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            upgradeName();
            upgradeMagicNumber(2);
        }
    }
}
