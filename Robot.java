public class Robot// implements Contract
{
    private String name; 
    private int size;
    private int energyLevel;
    private int xPosition;
    private int yPosition;

    //Constructor
    public Robot(String name){
        this.name = name;
        System.out.println("Your robot's name is: " + name);
        this.size = 6;
        this.energyLevel = 100;
        this.xPosition = 0;
        this.yPosition = 0;
    }
    
    //void grab(String item){
        //add to array list
   // }

    //String drop(String item){
        //remove from arraylist
    //}

    //void examine(String item){
        // print string
        //use energy
    //}

   // void use(String item){
        // remove from arraylist
        // use energy
    //}

    boolean walk(String direction){ // use a string for direction?
        if(this.energyLevel >= 10){
            this.energyLevel -= 10;
            return true;
        } else{
            return false;
        }
    }

    boolean fly(int x, int y){
        if(x + this.xPosition <= 100 && y + this.yPosition <=100 ){
            this.xPosition += x;
            this.yPosition += y;
            this.energyLevel -= 10;
            System.out.println("Your x position is: " + this.xPosition + ", and your y position is: " + this.yPosition);
            return true;
        } else{
            return false;
        }
      //  this.energyLevel -= 10;
        /// return some boolean
    }

    Number shrink(){
        if(this.size >=2){
            this.size -= 1;
            System.out.println(this.name + " is now " + this.size + " feet tall.");
        } else{
            throw new RuntimeException(this.name + " cannot shrink under 1 foot tall.");
        }
        return this.size;
    }

    Number grow(){
        this.size += 1;
        System.out.println(this.name + " is now " + this.size + " feet tall.");
        return this.size;
    }

    void rest(){
        if(this.energyLevel <= 90){
            this.energyLevel += 10;
        } else{
            throw new RuntimeException(this.name + " already has full energy.");
        }
    }

    //void undo(){

    //}

    public static void main(String[] args) {
        Robot robbie = new Robot("Robbie");
        robbie.grow();
        robbie.shrink();
        robbie.fly(2, 3);
        robbie.rest();
        //robbie.walk("input");
    }
}
