package GeneralTools;

import Creature.Creature;

public interface Attack extends Move{
    void attack(Creature enemy);
}
