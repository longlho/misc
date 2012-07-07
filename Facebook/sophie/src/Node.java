
public class Node implements Comparable {
	public int id;
	public double time;

	public Node(int id, double time) {
		this.id = id;
		this.time = time;
		
	}

	public int compareTo(Object o) {
		return ((Double)time).compareTo(((Node) o).time);
	}
}
