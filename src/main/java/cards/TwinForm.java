package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import pathes.AbstractCardEnum;

/**
 * 朵菈能力-玩具城堡
 * 照抄机宝
 */
public class TwinForm extends CustomCard {
    private static final CardStrings cardStrings
            = CardCrawlGame.languagePack.getCardStrings("TwinForm");
    private static final String ID = "TwinForm";
    private static final String IMG = "img/cards_Dora/Default.png";
    private static final int COST = 3;


    public TwinForm() {
        super(ID, cardStrings.NAME, IMG, COST, cardStrings.DESCRIPTION,
                CardType.POWER, AbstractCardEnum.Dora_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        // TODO 改为回合开始时，随机获取一张绝望魔法
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer,
                new BufferPower(abstractPlayer, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new TwinForm();
    }
}
