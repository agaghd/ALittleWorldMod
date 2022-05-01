package pathes;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;

/**
 * 把角色的卡牌颜色注册到cardLibrary,必须有
 */
public class LibraryTypeEnum {
    /**
     * 朵菈-卡牌颜色
     */
    @SpireEnum
    public static CardLibrary.LibraryType Dora_COLOR;
    /**
     * 绝望魔法
     */
    @SpireEnum
    public static CardLibrary.LibraryType DESPAIR_COLOR;
}
