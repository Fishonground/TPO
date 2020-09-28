public class Furniture {
    String name;
    Material material;
    Furniture(String n, Material ma){
        name = n;
        material = ma;
    }
    public Furniture createFurniture(String name, Material material){
        return new Furniture(name, material);
    }
}
