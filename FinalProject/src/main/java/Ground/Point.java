package Ground;

import Creature.Creature;

public class Point {
    private Creature onSite;

    public Point(){
        onSite = null;
    }

    public boolean isEmpty(){
        if(onSite == null)
            return true;
        else
            return false;
    }

    public void setPoint(Creature creature){
        onSite = creature;
    }

    public Creature getPoint(){
        return onSite;
    }

    public void clearPoint(){
        onSite = null;
    }
}
