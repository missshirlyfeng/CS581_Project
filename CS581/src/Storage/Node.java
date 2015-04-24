package Storage;

public class Node {

	private int id;
	private float x;
	private float y;

	protected enum COLORS {
		WHITE, GREY, BLACK
	};

	private COLORS color;
	private int distance;
	private Node parent;

	public Node(int node_id, float latitude, float longtitude) {
		id = node_id;
		x = latitude;
		y = longtitude;
	}

	// Getter
	public int getId() {
		return id;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

}
