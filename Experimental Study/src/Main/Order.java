package Main;

public class Order {
	private String ID;
	private float length;


	public Order(String ID,float length) {
		this.ID = ID;
		this.length = length;
	}
	
	public String getID() {
		return ID;
	}

	public float getLength() {
		return length;
	}
	
	@Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Order)) {
            return false;
        }
        Order other = (Order) obj;
        return this.getID().equals(other.getID());
    }
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return ID;
    }

	
}
