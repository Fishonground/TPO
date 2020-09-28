import java.util.List;

public class Room {
    List<Furniture> furnitures;
    List<Creatures> creatures;
    Catalogy catalogy;
    Room(List<Furniture> f,List<Creatures> c, Catalogy ca){
        furnitures = f;
        creatures = c;
        catalogy = ca;
    }

    public void addFurniture(Furniture furniture){
        furnitures.add(furniture);
    }
    public List<Creatures> addCreatures(List<Creatures> cre, Creatures creature){
        cre.add(creature);
        return cre;
    }
    public boolean isHere(Creatures creature){
        return creatures.contains(creature);
    }
    public boolean emptyCatalogy(Catalogy catalogy){
        if (catalogy.size==0)
        return true;
        else return false;
    }
}
