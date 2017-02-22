package factories;

import com.github.javafaker.Faker;
import models.DTO;

import java.util.LinkedList;

public abstract class Factory {
	static Faker faker = new Faker();
	public static Factory factory;

	@SuppressWarnings("unchecked")
	public <T> T make(int times) throws InvalidInputException {
		if (times == 1) {
			return (T) this.instantiate();
		}
		LinkedList<? extends DTO> data = new LinkedList<>();
		for (int i = 0; i < times; i++)
			data.add(this.instantiate());
		return (T) data;
	}

	public <T> T make() throws InvalidInputException {
		return this.make(1);
	}

	abstract  <T extends DTO> T instantiate() throws InvalidInputException;

}
