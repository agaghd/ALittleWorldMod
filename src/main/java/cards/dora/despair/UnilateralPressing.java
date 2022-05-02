package cards.dora.despair;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import pathes.ALittleWorldTags;
import pathes.AbstractCardEnum;
import powers.DespairPower;


public class UnilateralPressing extends CustomCard {
    private static final CardStrings cardStrings
            = CardCrawlGame.languagePack.getCardStrings("UnilateralPressing");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final String ID = "UnilateralPressing";
    // 防御图片
    private static final String IMG_PATH = "img/cards_Dora/Default.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public UnilateralPressing() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Dora_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.tags.add(ALittleWorldTags.TAG_DESPAIR);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DespairPower(abstractPlayer, this.magicNumber), magicNumber));
        addToBot(new VFXAction(new WhirlwindEffect(
                new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
        addToBot(new SkipEnemiesTurnAction());
        addToBot(new PressEndTurnButtonAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new UnilateralPressing();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            initializeDescription();
        }
    }
}
