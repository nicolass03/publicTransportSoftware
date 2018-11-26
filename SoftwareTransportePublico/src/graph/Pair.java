package graph;

public class Pair<T> implements Comparable{

	private T object;
	private Integer w;
	
	public Pair(T type,Integer n) {
		w = n;
		object = type;
	}

	/**
	 * @return the object
	 */
	public T getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(T object) {
		this.object = object;
	}

	/**
	 * @return the w
	 */
	public Integer getW() {
		return w;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(Integer w) {
		this.w = w;
	}

	@Override
	public int compareTo(Object o) {
		Pair<T> c = (Pair<T>) o;
		if(w < c.getW()) {
			return -1;
		}
		else if(w > c.getW()) {
			return 1;
		}
		else {
			return 0;			
		}
	}
	
	
}