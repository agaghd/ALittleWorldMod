package cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

/**
 * 朵拉--打击
 */
public class StrikeOfDora extends CustomCard {

    //  .json文件提取
    private static final CardStrings cardStrings
            = CardCrawlGame.languagePack.getCardStrings("StrikeOfDora");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    //  打击图片
    public static final String IMG_PATH = "img/cards_Dora/Strike.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    public static final String ID = "StrikeOfDora";

    public StrikeOfDora() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.Dora_COLOR, CardRarity.BASIC, CardTarget.ENEMY);
        //添加基础攻击标签和将伤害设为6
        this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.baseDamage = ATTACK_DMG;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //使用卡牌时触发的动作
        AbstractDungeon.actionManager.addToBottom(new DamageAction(abstractMonster,
                new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public AbstractCard makeCopy() {
        return new StrikeOfDora();
    }

    @Override
    public boolean isStrike() {
        return true;
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
