package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.ModelContent;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.MatrixCheck;
import exception.ClickException;

public class MatrixCheckTest {

	private static ModelContent mc;
	private static int n;
	private static int m;
	private static int nrpozpunct;
	private MatrixCheck matrix;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before class");
		n = 5;
		m = 5;
		nrpozpunct = 4;
		initializareJoc();
	}

	private static void initializareJoc() {
		System.out.println("initializare joc");
		mc = new ModelContent();

		mc.setNrpozpunct(nrpozpunct);

		initializareMatriceJoc();

		initializareJucatori();
	}

	private static void initializareJucatori() {
		System.out.println("initializare jucatori");
		mc.setScorjucator1(0);
		mc.setScorjucator2(0);
		mc.setJucator(0);
		if (mc.isPrimu() == true)
			mc.setJocnou(false);
		else
			mc.setJocnou(true);
		mc.setPrimu(true);
	}

	private static void initializareMatriceJoc() {
		System.out.println("initializare matrice joc");
		for (int i = 0; i < n; i++) {
			List<Integer> aux = new ArrayList<Integer>();
			mc.addPeLinii(aux);
		}

		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				mc.addPeColoane(i, 0);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After class");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Before test");
		matrix = new MatrixCheck();
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				mc.setIJElement(i, j, 0);
		matrix.setMc(mc);
		mc.setScorjucator1(0);
		mc.setScorjucator2(0);
		mc.setJocnou(true);
		mc.setJucator(0);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("After test");
	}

	@Test
	public void testOver() {
		System.out.println("Testare verificare over");
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				mc.setIJElement(i, j, 1);

		matrix.verifOver();
		assertTrue(mc.isGata());
	}

	@Test
	public void testColoana() {
		System.out.println("Testare verificare coloana marcata");
		int x = 3, y = 0;
		for (int i = 0; i <= x; i++) {
			mc.setIJElement(i, y, 1);
		}

		matrix.verifCol(x, y);

		boolean b = true;

		for (int i = 0; i <= x; i++) {
			if (mc.getIJElement(i, y) != -1) {
				b = false;
				break;
			}
		}

		assertTrue(b);
	}

	@Test
	public void testDiag1() {
		System.out.println("Testare verificare diagonala 1");
		int x = 3;
		for (int i = 0; i <= x; i++) {
			mc.setIJElement(i, i, 1);
		}

		matrix.verifDiag(x, x);

		boolean b = true;

		for (int i = 0; i <= x; i++) {
			if (mc.getIJElement(i, i) != -1) {
				b = false;
				break;
			}
		}

		assertTrue(b);
	}

	@Test
	public void testDiag2() {
		System.out.println("Testare verificare diagonala 2");
		int x = 3;
		for (int i = 0; i <= x; i++) {
			mc.setIJElement(i, n - i - 1, 1);
		}

		matrix.verifDiag2(x, n - x - 1);

		boolean b = true;

		for (int i = 0; i <= x; i++) {
			if (mc.getIJElement(i, n - i - 1) != -1) {
				b = false;
				break;
			}
		}

		assertTrue(b);
	}

	@Test(expected = ClickException.class)
	public void testModifyThrowsException() throws ClickException {
		System.out.println("Testare modificare care arunca exception");
		mc.setIJElement(0, 0, 1);
		matrix.modify(0, 0);
	}

	@Test
	public void testModifyMatrix() {
		System.out.println("Testare moficare normala valoare matrice");
		try {
			matrix.modify(0, 0);
		} catch (ClickException e) {
			System.out.println(e);
		}
		assertEquals(1, matrix.getIJElement(0, 0));
	}

	@Test
	public void testModifyPlayer() {
		System.out.println("Testare modificare normala valoare jucator");
		try {
			matrix.modify(0, 0);
		} catch (ClickException e) {
			System.out.println(e);
		}
		assertEquals(1, matrix.getMc().getJucator());
	}
	
	@Test
	public void testModifyScorePlayer(){
		System.out.println("Testare modificare normala scor jucator");
		System.out.println("Testare verificare diagonala 1");
		int x = 3;
		for (int i = 0; i < x; i++) {
			matrix.getMc().setIJElement(i, i, 1);
		}
		
		try {
			matrix.modify(x, x);
		} catch (ClickException e) {
			System.out.println(e);
		}
		
		assertEquals(1, matrix.getMc().getScorjucator1());
	}
}
