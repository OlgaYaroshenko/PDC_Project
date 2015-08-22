
package simongame;

public enum Color {
    GREEN(1), RED(2), BLUE(3), YELLOW(4);   

    public int colorCode;
    
    private Color(int colorCode) {
        this.colorCode = colorCode;
    }
    
    public int getColorCode() {
        return colorCode;
    }
    
    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }
    
}
    

