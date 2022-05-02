package powers;

import cards.ALittleWorldCardsManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import utils.TextureUtils;

public class TwinFormPower extends AbstractPower {

    public static final String POWER_ID = "TwinFormPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("TwinFormPower");
    public static final String NAME = powerStrings.NAME;

    private static final Texture tex_48 = TextureUtils.getTexture("img/UI_Dora/IconSet_48.png");
    private static final Texture tex_128 = TextureUtils.getTexture("img/UI_Dora/IconSet_128.png");

    private boolean isUpgrade;

    public TwinFormPower(AbstractCreature owner, int amount, boolean isUpgrade) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.region48 = new TextureAtlas.AtlasRegion(tex_48, 384, 288, 48, 48);
        this.region128 = new TextureAtlas.AtlasRegion(tex_128, 1024, 768, 128, 128);
        this.isUpgrade = isUpgrade;
        updateDescription();
        AbstractDungeon.player.gameHandSize += amount;
    }

    @Override
    public void atStartOfTurn() {
        int index = new Random().random(0, ALittleWorldCardsManager.DESPAIR_CARDS.size() - 1);
        AbstractCard abstractCard = ALittleWorldCardsManager.DESPAIR_CARDS.get(index);
        if(isUpgrade){
            abstractCard.upgrade();;
        }
        addToBot(new MakeTempCardInHandAction(abstractCard, 1));
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }
}
