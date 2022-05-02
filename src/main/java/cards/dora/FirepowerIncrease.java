package cards.dora;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import pathes.AbstractCardEnum;

/**
 * 朵菈能力-火力增幅
 * 获得两点力量
 */
public class FirepowerIncrease extends CustomCard {
    private static final CardStrings cardStrings
            = CardCrawlGame.languagePack.getCardStrings("FirepowerIncrease");
    private static final String ID = "FirepowerIncrease";
    private static final String IMG = "img/cards_Dora/Default.png";
    private static final int COST = 1;


    public FirepowerIncrease() {
        super(ID, cardStrings.NAME, IMG, COST, cardStrings.DESCRIPTION,
                CardType.POWER, AbstractCardEnum.Dora_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new VFXAction(abstractPlayer, new InflameEffect(abstractPlayer), 1.0F));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer,
                new StrengthPower(abstractPlayer, this.magicNumber), this.magicNumber));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FirepowerIncrease();
    }
}
