package powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import utils.TextureUtils;

public class WitchsNightPower extends AbstractPower {

    public static final String POWER_ID = "WitchsNightPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("WitchsNightPower");
    public static final String NAME = powerStrings.NAME;

    private static final Texture tex_48 = TextureUtils.getTexture("img/UI_Dora/IconSet_48.png");
    private static final Texture tex_128 = TextureUtils.getTexture("img/UI_Dora/IconSet_128.png");

    public WitchsNightPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.region48 = new TextureAtlas.AtlasRegion(tex_48, 672, 240, 48, 48);
        this.region128 = new TextureAtlas.AtlasRegion(tex_128, 1792, 640, 128, 128);
        updateDescription();
        AbstractDungeon.player.gameHandSize += amount;
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new GainEnergyAction(this.amount));
        flash();
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(powerStrings.DESCRIPTIONS[0]);
        sb.append(amount);
        sb.append(powerStrings.DESCRIPTIONS[1]);
        for (int i = 0; i < this.amount; i++) {
            sb.append("[R] ");
        }
        sb.append(powerStrings.DESCRIPTIONS[2]);
        this.description = sb.toString();
    }
}
