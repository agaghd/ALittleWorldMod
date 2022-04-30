package cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.SkewerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

/**
 * 朵拉--全弹发射
 */
public class FireAllBullets extends CustomCard {

    //  .json文件提取
    private static final CardStrings cardStrings
            = CardCrawlGame.languagePack.getCardStrings("FireAllBullets");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    //  打击图片
    public static final String IMG_PATH = "img/cards_Dora/Default.png";
    private static final int COST = -1;
    private static final int ATTACK_DMG = 7;
    private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = "FireAllBullets";

    public FireAllBullets() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.Dora_COLOR, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = ATTACK_DMG;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //使用卡牌时触发的动作
        AbstractDungeon.actionManager.addToBottom(new SkewerAction(abstractPlayer, abstractMonster,
                this.damage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FireAllBullets();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

}
