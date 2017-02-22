package models;

public class Role implements DTO {

	private Integer id;
	private String name;

	public Role(String name, Integer id){
		this.name = name;
		this.id = id;
	}

	public Role(String name) {
		this(name, null);
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
