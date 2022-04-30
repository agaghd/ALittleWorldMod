package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

/**
 * 朵菈-爆发射击
 * 抄袭战士
 */
public class BurstShot extends CustomCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BurstShot");
    private static final String ID = "BurstShot";
    private static final String IMG = "img/cards_Dora/Default.png";
    private static final int COST = 1;
    private static final int DAMAGE = 2;

    private static final int COUNT = 5;

    public BurstShot() {
        super(ID, cardStrings.NAME, IMG, COST, cardStrings.DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.Dora_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = COUNT;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int index = 0; index < this.magicNumber; index++) {
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BurstShot();
    }
}
