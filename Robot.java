/** 
 * Creates a robot and provides several methods.
 * @author Logan Hankla
 */

import java.util.ArrayList;

public class Robot implements Contract{
    /** Name of the robot */
    private String name; 
    /** Robot size */
    private int size;
    /** Robot's energy level out of 100 */
    private int energyLevel;
    /** Robot's X Position */
    private int xPosition;
    private int yPosition;
    private ArrayList<String> inventory;

    //Constructor
    public Robot(String name){
        this.name = name;
        System.out.println("Your robot's name is " + name + ". Please note that " + name + " must adhere to the Three Laws of Robotics: \n 1. A robot may not injure a human being or, through inaction, allow a human being to come to harm. \n 2. A robot must obey orders given it by human beings except where such orders would conflict with the First Law. \n 3. A robot must protect its own existence as long as such protection does not conflict with the First or Second Law.");
        this.size = 6;
        this.energyLevel = 100;
        this.xPosition = 0;
        this.yPosition = 0;
        this.inventory = new ArrayList<String>();
    }
    
    public void grab(String item){
        this.inventory.add(item);
    }

    public String drop(String item){
        if(this.inventory.contains(item)){
            this.inventory.remove(item);
            return item + "removed from" + this.name + "'s inventory.";
        } else{
            throw new RuntimeException(item + " is not in " + this.name + "'s inventory.");
        }
    }

    public void examine(String item){
        this.energyLevel -= 10;
        System.out.println(this.name + " is examining " + item +". To put " + item + " in the inventory, tell " + this.name + " to grab() it. " + this.name + "'s energy level is " + this.energyLevel + "/100.");
    }

    public void use(String item){
        if(this.inventory.contains(item)){
            this.inventory.remove(item);
            this.energyLevel -= 10;
            System.out.println(this.name + " has used " + item + ". " + this.name + "'s energy level is " + this.energyLevel + "/100.");
        } else{
            throw new RuntimeException(item + " is not in the inventory. To use it, please grab() it first.");
        }
    }

    public boolean walk(String direction){
        if(this.energyLevel >= 10){
            this.energyLevel -= 10;
            this.xPosition += 5;
            System.out.println(this.name + " walked " + direction + ", and " + this.name + "'s new X position is " + this.xPosition + ".");
            return true;
        } else{
            return false;
        }
    }

    public boolean fly(int x, int y){
        if(x + this.xPosition <= 100 && y + this.yPosition <=100 ){
            this.xPosition += x;
            this.yPosition += y;
            this.energyLevel -= 10;
            System.out.println(this.name + "'s X Position is " + this.xPosition + ", and " + this.name + "'s Y Position is " + this.yPosition + ".");
            return true;
        } else{
            return false;
        }
    }

    public Number shrink(){
        if(this.size >=2){
            this.size -= 1;
            System.out.println(this.name + " is now " + this.size + " feet tall.");
        } else{
            throw new RuntimeException(this.name + " cannot shrink under 1 foot tall.");
        }
        return this.size;
    }

    public Number grow(){
        this.size += 1;
        System.out.println(this.name + " is now " + this.size + " feet tall.");
        return this.size;
    }

    public void rest(){
        if(this.energyLevel <= 90){
            this.energyLevel += 10;
            System.out.println(this.name + "'s energy level after resting is " + this.energyLevel + "/100.");
        } else{
            throw new RuntimeException(this.name + " already has full energy.");
        }
    }

    public void undo(){

    }

    public static void main(String[] args) {
        Robot robbie = new Robot("Robbie");
        robbie.grow();
        robbie.shrink();
        robbie.fly(2, 3);
        robbie.walk("north");
        //robbie.undo();
        robbie.grab("Phone");
        //robbie.drop("Laptop");
       // robbie.drop("Phone");
        robbie.examine("T-shirt");
        robbie.use("Phone");
        System.out.println("Robbie's inventory: " + robbie.inventory);
        robbie.rest();
        robbie.rest();
        robbie.rest();
        robbie.rest();
    }
}
