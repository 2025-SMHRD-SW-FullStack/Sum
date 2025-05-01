package Cash;

public class ObjectVO {
	private String object;
	private int objectSel;
	private int objectCount;

	public ObjectVO(String object, int objectSel, int objectCount) {
		super();
		this.object = object;
		this.objectSel = objectSel;
		this.objectCount = objectCount;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public int getObjectSel() {
		return objectSel;
	}

	public void setObjectSel(int objectSel) {
		this.objectSel = objectSel;
	}

	public int getObjectCount() {
		return objectCount;
	}

	public void setObjectCount(int objectCount) {
		this.objectCount = objectCount;
	}
}
