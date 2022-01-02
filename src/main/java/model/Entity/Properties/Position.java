package model.Entity.Properties;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     *
     * x getters and setters
     */
    public int getX() {
        return x;
    }

    public void setX(int x) {
//        if(x > 78)
        this.x = x;
//        else this.x = Math.max(x, 0);
    }

    /**
     *
     * y getters and setters
     */
    public int getY() {
        return y;
    }

    public void setY(int y) {
//        if(y > 23)
        this.y = y;
//        else this.y = Math.max(y, 0);
    }

    public void setPosition(Position position){
        setX(position.x);
        setY(position.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        Position p = (Position) o;
        return x == p.getX() && y == p.getY();
    }
}
