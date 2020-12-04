package Main;

public class Location {
	
	/**
	 * location ID
	 */
	private final String ID;
	  /**
     * The x cord
     */
    private float x;

    /**
     * The y cord
     */
    private float y;

    /**
     * Instantiates a new location.
     *
     * @param x the x
     * @param y the y
     */
    public Location(float x, float y,String ID) {
        this.x = x;
        this.y = y;
        this.ID = ID;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Location)) {
            return false;
        }
        Location loc = (Location) obj;

        return (loc.x == x) && (loc.y == y);
    }


    /**
     * Gets the y.
     *
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the y.
     *
     * @param y the new y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Gets the x.
     *
     * @return the x
     */
    public float getX() {
        return x;
    }

    public String getID() {
    	return ID;
    }
    /**
     * Sets the x.
     *
     * @param x the new x
     */
    public void setX(float x) {
        this.x = x;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "X = " + x + "   Y = " + y;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     * clones this object
     */
    @Override
    public Location clone() {
        float xt = x;
        float yt = y;
        String IDN = ID;
        return new Location(xt, yt,IDN);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     * allows this to be used as key in a HashMap
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (x >= 0) ? 1 : -1;
        result = prime * result + Float.floatToIntBits(x);
        result = prime * result + Float.floatToIntBits(y);
        return result;
    }
}
