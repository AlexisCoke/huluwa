package GeneralTools;

import Creature.Creature;
import Ground.Battle;
import Ground.BattleGround;

public interface Move {
    boolean move(BattleGround ground, int x, int y);
    void randomMove();
}
