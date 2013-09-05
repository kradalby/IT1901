package helperclass;

public class Sheep {
	private final int id;
	private String name;
	private Farmer farmer;

	public Sheep(int id, String name, Farmer farmer) {
		this.id = id;
		this.name = name;
		this.farmer = farmer;
	}
}
