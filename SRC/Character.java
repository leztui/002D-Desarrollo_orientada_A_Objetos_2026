
public class Character {
    String name;
    private int Hit_Point;
    int Magic_Points;
    boolean is_ally;


    public void asignar_vida(int Hit_Point){
        if (Hit_Point< 0) {
            this.Hit_Point = 0;
        }else{
            this.Hit_Point = Hit_Point;

        }




    }
    public int mostrarvida(

    ){return Hit_Point;

    }
}
