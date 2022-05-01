package cards.despair;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import pathes.AbstractCardEnum;


public class OverDoze extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("OverDoze");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final String ID = "OverDoze";
    // 防御图片
    private static final String IMG_PATH = "img/cards_Dora/Default.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public OverDoze() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.DESPAIR_COLOR, CardRarity.RARE, CardTarget.ALL);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        // TODO 添加特效

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo != null && !mo.isDead) {
                if (mo.hasPower("Strength")) {
                    AbstractPower power = mo.getPower("Strength");
                    int amout = power.amount;
                    if (amout > 0) {
                        amout = -amout * 2;
                        if (amout < -999) {
                            amout = -999;
                        }
                        addToBot(new ApplyPowerAction(mo, abstractPlayer,
                                new StrengthPower(mo, amout), amout));
                    }
                }
                if (mo.hasPower("Dexterity")) {
                    AbstractPower power = mo.getPower("Dexterity");
                    int amout = power.amount;
                    if (amout > 0) {
                        amout = -amout * 2;
                        if (amout < -999) {
                            amout = -999;
                        }
                        addToBot(new ApplyPowerAction(mo, abstractPlayer,
                                new DexterityPower(mo, amout), amout));
                    }
                }
                if (mo.hasPower("Regeneration")) {
                    AbstractPower power = mo.getPower("Regeneration");
                    int amout = power.amount;
                    addToBot(new RemoveSpecificPowerAction(mo, abstractPlayer, mo.getPower("Regeneration")));
                    addToBot(new ApplyPowerAction(mo, abstractPlayer, new PoisonPower(mo, abstractPlayer, amout), amout));
                }
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new OverDoze();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}
