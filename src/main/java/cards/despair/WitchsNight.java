package cards.despair;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import pathes.ALittleWorldTags;
import pathes.AbstractCardEnum;
import powers.DespairPower;
import powers.WitchsNightPower;


public class WitchsNight extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("WitchsNight");
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = -1;
    private static final String ID = "WitchsNight";
    // 防御图片
    private static final String IMG_PATH = "img/cards_Dora/Default.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public WitchsNight() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Dora_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        this.tags.add(ALittleWorldTags.TAG_DESPAIR);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }
        if (upgraded) {
            effect += 1;
        }
        if (abstractPlayer.hasRelic("Chemical X")) {
            effect += 2;
            abstractPlayer.getRelic("Chemical X").flash();
        }
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer,
                new WitchsNightPower(abstractPlayer, effect), effect));
        addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer,
                new DespairPower(abstractPlayer, effect), effect));
        if (!this.freeToPlayOnce) {
            abstractPlayer.energy.use(EnergyPanel.totalCount);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new WitchsNight();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
