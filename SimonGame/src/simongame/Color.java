package simongame;

/**
 * enum class used to represent colors and their enumeration
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 */
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
    

