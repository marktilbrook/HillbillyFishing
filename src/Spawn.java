import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private SpriteSheet ss;
    private Player player;

    private Random r = new Random();


    private int timer = 0;

    //constructor
    public Spawn(Handler handler, HUD hud,Player player) {
        this.handler = handler;
        this.hud = hud;
        this.player = player;




    }

    public void tick(){

        timer++;

        if (timer>=600){
            timer = 0;
            hud.setLevel(hud.getLevel()+1);
            //todo work on level design

            switch (hud.getLevel()){

                case 2:
                    System.out.println("Level 2");
                    handler.addObject(new SmallFish(r.nextInt(Game.WIDTH - 60),-r.nextInt(1200),ID.SmallFish,handler,ss,hud));
                    handler.addObject(new SmallFish(r.nextInt(Game.WIDTH - 60),-r.nextInt(1200),ID.SmallFish,handler,ss,hud));
                    handler.addObject(new SmallFish(r.nextInt(Game.WIDTH - 60),-r.nextInt(1200),ID.SmallFish,handler,ss,hud));
                    handler.addObject(new BeerPickUp(r.nextInt(Game.WIDTH - 60),-r.nextInt(1200),ID.BeerPickUp,handler,ss,hud,player));

                    break;

                case 3:
                    System.out.println("Level 3");
                    handler.addObject(new BigFish(r.nextInt(Game.WIDTH - 60),-r.nextInt(600),ID.BigFish,handler,ss,hud));
                    handler.addObject(new Snake(r.nextInt(Game.WIDTH - 60),-r.nextInt(1200),ID.BadFish,handler,ss,hud));
                    break;

                case 4:
                    System.out.println("Level 4");
                    handler.addObject(new Snake(r.nextInt(Game.WIDTH - 60),-r.nextInt(1200),ID.BadFish,handler,ss,hud));

                    break;

            }


        }

    }


}