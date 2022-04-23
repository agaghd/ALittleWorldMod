package cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pathes.AbstractCardEnum;

/**
 * 朵拉-防御
 */
public class DefendOfDora extends CustomCard {
    //从.json文件中提取
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("DefendOfDora");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    public static final String ID = "DefendOfDora";
    // 防御图片
    public static final String IMG_PATH = "img/cards_Dora/Defend.png";

    public DefendOfDora() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Dora_COLOR,
                CardRarity.BASIC, CardTarget.SELF);
        //添加基础防御标签和将格挡设为5
        this.tags.add(BaseModCardTags.BASIC_DEFEND);
        this.baseBlock = BLOCK_AMT;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //使用卡牌时触发的动作
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(abstractPlayer,
                abstractMonster, this.block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DefendOfDora();
    }

    @Override
    public boolean isDefend() {
        return true;
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点伤害
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_BLOCK);
        }
    }
}
