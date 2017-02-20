package dal.contracts;

import dal.exceptions.NotConnectedException;

public interface Creatable<T> {
	void create(T object) throws NotConnectedException;
}
