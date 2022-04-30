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
 * 朵菈-电磁炮
 */
public class RailGun extends CustomCard {

    private static final CardStrings cardStrings
            = CardCrawlGame.languagePack.getCardStrings("RailGun");
    private static final String ID = "RailGun";
    private static final String IMG = "img/cards_Dora/Default.png";
    private static final int COST = 0;
    private static final int DAMAGE = 6;

    public RailGun() {
        super(ID, cardStrings.NAME, IMG, COST, cardStrings.DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.Dora_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        // TODO 造成6点伤害，当前能量与上限每差一点增加3点伤害
        addToBot(new DamageAction(abstractMonster,
                new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RailGun();
    }
}
