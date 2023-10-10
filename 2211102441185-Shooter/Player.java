import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Characters
{
    int speed = 13;
    int bulletspeed = 15;
    int cooldown = 10;
    int lastShotTimer = 0;
    public Player(){
        GreenfootImage img = this.getImage();
        img.scale(60, 60);
        this.setImage(img);
    }
    
    private void tembak(){
        World wrld = this.getWorld();
        Bullets bullet = new Bullets(bulletspeed);
        bullet.setRotation(270);
        if (wrld instanceof MyWorld){
            ((MyWorld) wrld).incShotDone();
        }
        wrld.addObject(bullet, this.getX(), this.getY());
    }
    
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.isKeyDown("up")){
            this.setLocation(this.getX(), this.getY()-speed);
        }
        
        if(Greenfoot.isKeyDown("down")){
            this.setLocation(this.getX(), this.getY()+speed);
        }
        
        if(Greenfoot.isKeyDown("left")){
            this.setLocation(this.getX()-speed, this.getY());
        }
        
        if(Greenfoot.isKeyDown("right")){
            this.setLocation(this.getX() +speed, this.getY());
        }
        
        if(lastShotTimer < cooldown && lastShotTimer > 0){
            lastShotTimer ++;
        }
        
        if(Greenfoot.isKeyDown("space") && lastShotTimer == 0){
            tembak();
            lastShotTimer++;
        }
        
        if(lastShotTimer == cooldown){
            lastShotTimer = 0;
        }
        
        if(this.isTouching(Enemies.class)){
            World wrld = this.getWorld();
            Dead d = new Dead();
            wrld.addObject(d, this.getX(), this.getY());
            wrld.removeObject(this);
        }
    }
}
