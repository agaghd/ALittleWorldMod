package powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import utils.TextureUtils;

public class DespairPower extends AbstractPower {

    public static final String POWER_ID = "DespairPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DespairPower");
    public static final String NAME = powerStrings.NAME;

    private static final Texture tex_48 = TextureUtils.getTexture("img/UI_Dora/IconSet_48.png");
    private static final Texture tex_128 = TextureUtils.getTexture("img/UI_Dora/IconSet_128.png");

    public DespairPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.region48 = new TextureAtlas.AtlasRegion(tex_48, 672, 240, 48, 48);
        this.region128 = new TextureAtlas.AtlasRegion(tex_128, 1792, 640, 128, 128);
        updateDescription();
        AbstractDungeon.player.gameHandSize += amount;
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new LoseEnergyAction(this.amount));
        flash();
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }

    @Override
    public void updateDescription() {
        System.out.println("powerStrings:" + new Gson().toJson(powerStrings));
        this.description = powerStrings.DESCRIPTIONS[0] +
                this.amount + powerStrings.DESCRIPTIONS[1];
    }
}
