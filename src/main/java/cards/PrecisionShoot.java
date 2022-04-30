package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import pathes.AbstractCardEnum;

/**
 * 朵菈-精密射击
 * 造成7点伤害，给与一层虚弱
 */
public class PrecisionShoot extends CustomCard {

    private static final CardStrings cardStrings
            = CardCrawlGame.languagePack.getCardStrings("PrecisionShoot");
    private static final String ID = "PrecisionShoot";
    private static final String IMG = "img/cards_Dora/Default.png";
    private static final int COST = 1;
    private static final int DAMAGE = 7;

    public PrecisionShoot() {
        super(ID, cardStrings.NAME, IMG, COST, cardStrings.DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.Dora_COLOR, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer,
                new WeakPower(abstractMonster, this.magicNumber, false),
                this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PrecisionShoot();
    }
}
