package dal.jdbcdao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.assertj.core.api.Assertions.assertThat;

public class JDBCUserDAOTest {

	private JDBCDAO jdbcdao;

	@Before
	public void setUp(){
		this.jdbcdao = new JDBCDAO("Weight");
	}

	@After
	public void tearDown(){
		this.jdbcdao = null;
	}

	@Test
	public void test(){

	}

}