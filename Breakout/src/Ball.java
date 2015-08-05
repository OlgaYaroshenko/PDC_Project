//hello world!

import java.awt.Color;

public class Ball {

    private double positionX;
    private double positionY;

    private double velocityX;
    private double velocityY;

    private double radius;
    private double deltaT;

    private Color color;

    /**
     * @return the positionX
     */
    public double getPositionX() {
        return positionX;
    }

    /**
     * @param positionX the positionX to set
     */
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    /**
     * @return the positionY
     */
    public double getPositionY() {
        return positionY;
    }

    /**
     * @param positionY the positionY to set
     */
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    /**
     * @return the velocityX
     */
    public double getVelocityX() {
        return velocityX;
    }

    /**
     * @param velocityX the velocityX to set
     */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    /**
     * @return the velocityY
     */
    public double getVelocityY() {
        return velocityY;
    }

    /**
     * @param velocityY the velocityY to set
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @return the deltaT
     */
    public double getDeltaT() {
        return deltaT;
    }

    /**
     * @param deltaT the deltaT to set
     */
    public void setDeltaT(double deltaT) {
        this.deltaT = deltaT;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    public void move() {
        positionX += deltaT * velocityX;
        positionY += deltaT * velocityY;
    }
    
    @Override
    public String toString(){
        return "position(" + positionX + ", " + positionY + "), velocity("
                           + velocityX + ", " + velocityY + "), deltaT(" 
                           + deltaT + "), radius(" + radius + ")";
    }

    public static void main(String[] args) {
        int iteration = -1;
        Ball ball = new Ball();

        ball.setPositionX(16 + 480 * Math.random());
        ball.setPositionY(16 + 480 * Math.random());
        ball.setVelocityX(10 + 5 * Math.random());
        ball.setVelocityY(10 + 5 * Math.random());
        
        //generating random color
        ball.setColor(Color.getHSBColor((float)Math.random(), 1.0f, 1.0f));
        
        ball.setRadius(20);
        ball.setDeltaT(1.0);
        
        while(iteration++ < 100) {
            System.out.println(ball);
            ball.move();
        }
    }
}
