package powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
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
    }

    @Override
    public void atStartOfTurn() {
        int despairPowerAmount = 0;
        if (AbstractDungeon.player.hasPower(DespairPower.POWER_ID)) {
            despairPowerAmount = AbstractDungeon.player.getPower(DespairPower.POWER_ID).amount;
        }
        int effect = despairPowerAmount * this.amount;
        addToBot(new GainEnergyAction(effect));
        addToBot(new DrawCardAction(effect));
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(powerStrings.DESCRIPTIONS[0]);
        for (int i = 0; i < amount; i++) {
            sb.append(" [W] ");
        }
        sb.append(powerStrings.DESCRIPTIONS[1]).append(amount).append(powerStrings.DESCRIPTIONS[2]);
        this.description = sb.toString();
    }
}
