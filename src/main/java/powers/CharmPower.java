package powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import utils.TextureUtils;

public class CharmPower extends AbstractPower {

    public static final String POWER_ID = "CharmPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("CharmPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex_48 = TextureUtils.getTexture("img/UI_Dora/IconSet_48.png");
    private static final Texture tex_128 = TextureUtils.getTexture("img/UI_Dora/IconSet_128.png");


    public static AbstractMonster randomTarget = null;

    public CharmPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.DEBUFF;
        this.isTurnBased = true;
        this.region48 = new TextureAtlas.AtlasRegion(tex_48, 720, 240, 48, 48);
        this.region128 = new TextureAtlas.AtlasRegion(tex_128, 1920, 640, 128, 128);

        updateDescription();
    }



    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        System.out.println("source = " + source.name);
        if (source instanceof AbstractMonster) {
            AbstractMonster mo = (AbstractMonster) source;
            System.out.println("owner = " + this.owner.name);
            System.out.println("mo.getIntentBaseDmg() = " + mo.getIntentBaseDmg());
            System.out.println("power.type = " + power.type.name());
            if (mo.getIntentBaseDmg() >= 0 && source == this.owner
                    && power.type == AbstractPower.PowerType.DEBUFF) {
                AbstractMonster newTarget = randomTarget;
                if (newTarget == null) {
                    randomTarget = AbstractDungeon.getMonsters()
                            .getRandomMonster(null, true,
                                    AbstractDungeon.cardRandomRng);
                    newTarget = randomTarget;
                }
                power.owner = newTarget;
                AbstractDungeon.actionManager.currentAction.target = newTarget;
                System.out.println("newTarget = " + newTarget.name);
                if (power instanceof com.megacrit.cardcrawl.powers.HexPower) {
                    AbstractDungeon.actionManager.addToBottom(
                            new RemoveSpecificPowerAction(newTarget, newTarget, power.ID));
                }
            }
        }
    }

    @Override
    public void duringTurn() {
        addToBot(new AbstractGameAction() {
            public void update() {
                CharmPower.randomTarget = null;
                this.isDone = true;
            }
        });
    }

    @Override
    public void atEndOfRound() {
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(
                    new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        } else {
            AbstractDungeon.actionManager.addToBottom(
                    new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
