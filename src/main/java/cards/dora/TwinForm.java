package cards.dora;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.ALittleWorldTags;
import pathes.AbstractCardEnum;
import powers.DespairPower;
import powers.TwinFormPower;

/**
 * 朵菈能力-双生模式
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
        this.magicNumber = this.baseMagicNumber = 2;
        this.tags.add(ALittleWorldTags.TAG_DESPAIR);
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer,
                new TwinFormPower(abstractPlayer, 1, this.upgraded),
                1));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new DespairPower(abstractPlayer, this.magicNumber), magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new TwinForm();
    }
}
