package cards;

import cards.despair.*;
import cards.dora.*;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.List;

public class ALittleWorldCardsManager {
    public static List<AbstractCard> DORA_CARDS = new ArrayList<>();
    public static List<AbstractCard> DESPAIR_CARDS = new ArrayList<>();

    static {
        DORA_CARDS.add(new StrikeOfDora());
        DORA_CARDS.add(new DefendOfDora());
        DORA_CARDS.add(new GashaponMachine());
        DORA_CARDS.add(new ToysCastle());
        DORA_CARDS.add(new ToysHelmet());
        DORA_CARDS.add(new TwinShoot());
        DORA_CARDS.add(new RangeLock());
        DORA_CARDS.add(new PumpkinAtomicBomb());
        DORA_CARDS.add(new FireAllBullets());
        DORA_CARDS.add(new PenetrateBullet());
        DORA_CARDS.add(new ButtHit());
        DORA_CARDS.add(new TwinForm());
        DORA_CARDS.add(new BurstShot());
        DORA_CARDS.add(new Ricochet());
        DORA_CARDS.add(new RailGun());
        DORA_CARDS.add(new PrecisionShoot());
        DORA_CARDS.add(new FirepowerIncrease());
        DORA_CARDS.add(new Dismantling());
        DORA_CARDS.add(new ConveyorBelt());
        DORA_CARDS.add(new ToysArmor());
        DORA_CARDS.add(new Pachinko());
        DORA_CARDS.add(new Refill());
        DORA_CARDS.add(new RollerCoaster());
        DORA_CARDS.add(new HauntedHouseAdventure());
        DORA_CARDS.add(new RussianDoll());
        DORA_CARDS.add(new ClawMachine());

        DESPAIR_CARDS.add(new DeathAll());
        DESPAIR_CARDS.add(new UnilateralPressing());
//        DESPAIR_CARDS.add(new EXCharm());
        DESPAIR_CARDS.add(new DanceInTheDark());
        DESPAIR_CARDS.add(new GroudZero());
        DESPAIR_CARDS.add(new ChildPlay());
        DESPAIR_CARDS.add(new BreakLife());
        DESPAIR_CARDS.add(new OverDoze());
        DESPAIR_CARDS.add(new WitchsNight());
    }
}
