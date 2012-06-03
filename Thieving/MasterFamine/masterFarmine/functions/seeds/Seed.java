package masterFamine.functions.seeds;

import java.io.IOException;

import masterFamine.functions.Methods;

public class Seed {

	private int id;
	private int price;
	private String name;
	private boolean drop;

	public Seed(int id, String name, boolean Drop) {
		this.setID(id);
		this.setName(name);
		this.setDrop(Drop);
		setPrice();
	}

	public int setPrice() {
		try {
			return Methods.getPrice(this.id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDrop() {
		return drop;
	}

	public void setDrop(boolean drop) {
		this.drop = drop;
	}
}
