package cards.dora.despair;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.ALittleWorldTags;
import pathes.AbstractCardEnum;
import powers.DespairPower;


public class GroudZero extends CustomCard {
    private static final CardStrings cardStrings
            = CardCrawlGame.languagePack.getCardStrings("GroudZero");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final String ID = "GroudZero";
    // 防御图片
    private static final String IMG_PATH = "img/cards_Dora/Default.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public GroudZero() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Dora_COLOR,
                CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 2;
        this.tags.add(ALittleWorldTags.TAG_DESPAIR);

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        // TODO 添加特效
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo != null && !mo.isDead) {
                addToBot(new ApplyPowerAction(mo, abstractPlayer,
                        new StunMonsterPower(mo, 1), 1));
            }
        }
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DespairPower(abstractPlayer, this.magicNumber), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new GroudZero();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            initializeDescription();
        }
    }
}
