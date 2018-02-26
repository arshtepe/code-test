package model;

public class Dimension {

    private String width;

    private String height;

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    @Override public String toString() {
        return "Dimension{" +
            "width='" + width + '\'' +
            ", height='" + height + '\'' +
            '}';
    }
}
