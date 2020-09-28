import java.util.List;

public class Creatures {
        public String name;
        public Parametres parametres;
        //public Room room;
        public boolean isReading = false;

        Creatures(String n, Parametres p /*,Room r*/){
            name = n;
            parametres = p;
            //room = r;
        }
    public List<Creatures> addSomeone(List<Creatures> creatures,String name, Parametres parametres/*, Room room*/){
        Creatures someone = new Creatures(name,parametres/*,room*/);
        creatures.add(someone);
        return  creatures;
    }
    public List<Creatures> delSomeone(List<Creatures> creature, String name){
        for (Creatures creatures: creature
        ) {
            if (creatures.name.equals(name)) creature.remove(creatures);
        }
       //creature.room = null;
       return creature;
    }

    public Creatures getByname(List<Creatures> cre, String name){
        for (Creatures creatures: cre
        ) {
            if (creatures.name.equals(name)) return creatures;
        }
        return null;
    }
}

