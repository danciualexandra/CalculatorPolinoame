package pack;




import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;




public class TestPolinom {
	@Test
	public void adunareTest() {
		
		//adaugam in lista de monoame cateva monoame
		Polinom polinom1 = new Polinom();//are un constructor care creeaza o lista de monoame
		
		polinom1.adunareMonom(new Monom(1,6));
		polinom1.adunareMonom(new Monom(-3,3));
		polinom1.adunareMonom(new Monom(2,1));
		polinom1.adunareMonom(new Monom(-1,0));
		
		Polinom polinom2 = new Polinom();///un alt polinom
		
		polinom2.adunareMonom(new Monom(-2,3));
		polinom2.adunareMonom(new Monom(7,1));
		polinom2.adunareMonom(new Monom(-1,0));
		
		//testam adunarea a doua polinoame declarate ca o lista de monoame
		Polinom rezultat = Operatie.adunare(polinom1, polinom2);
		Polinom rezcorect = new Polinom();///luam un alt polinom pt verificare
		rezcorect.adunareMonom(new Monom(1,6));
		rezcorect.adunareMonom(new Monom(-3,3));
		rezcorect.adunareMonom(new Monom(-2,3));
		rezcorect.adunareMonom(new Monom(9,1));
		rezcorect.adunareMonom(new Monom(-2,0));
		rezultat.sort();///sortam cel pe care am aplicat operatia
		rezcorect.sort();///sortam
		assertEquals("Adunare test", rezultat, rezcorect);//comparam
	}
	@Test
	public void scadereTest() {
		
			Polinom p1 = new Polinom();
			p1.adunareMonom(new Monom(1,1));
			p1.adunareMonom(new Monom(-3,3));
			
			p1.adunareMonom(new Monom(2,1));
			p1.adunareMonom(new Monom(15,0));
			Polinom p2 = new Polinom();
			p2.adunareMonom(new Monom(6,5));
			p2.adunareMonom(new Monom(8,3));	
			p2.adunareMonom(new Monom(2,2));
			p2.adunareMonom(new Monom(-6,1));
			
			Polinom rezultat = Operatie.scadere(p1,p2);
			rezultat.sort();///sortam dupa aplicarea operatiei
			Polinom rezcorect = new Polinom();
			
			rezcorect.adunareMonom(new Monom(-6,5));
			rezcorect.adunareMonom(new Monom(1,1));
			
			rezcorect.adunareMonom(new Monom(-1,3));	
			rezcorect.adunareMonom(new Monom(-1,2));
			rezcorect.adunareMonom(new Monom(2,1));	
			rezcorect.adunareMonom(new Monom(6,0));	
			rezcorect.sort();
			assertEquals("Scadere test",rezultat,rezcorect);
		}
	@Test
	public void derivareTest()
	{
		Polinom pol1 = new Polinom();
		Monom m=new Monom(1,1);
		pol1.adunareMonom(m);
		Monom d=new Monom(7,4);
		pol1.adunareMonom(d);
		pol1.adunareMonom(new Monom(6,4));

		pol1.adunareMonom(new Monom(1,0));	
		
		Polinom rez = Operatie.derivare(pol1); //apelam derivare din clasa operatie
		
		Polinom corect = new Polinom();
		
		corect.adunareMonom(new Monom(60,7));
		Monom l=new Monom(28,3);
		corect.adunareMonom(l);
		corect.adunareMonom(new Monom(-6,1));
		
		corect.sort();
		rez.cleanPol();
		rez.sort();	
		assertEquals("Derivare test", rez, corect);
	}
	@Test
	public void inmultireTest()
	{
		Polinom pol1 = new Polinom();
		pol1.adunareMonom(new Monom(-2,7));
		pol1.adunareMonom(new Monom(2,1));
		
		Polinom pol2 = new Polinom();
		pol2.adunareMonom(new Monom(7,1));	
		Polinom rezultat = Operatie.inmultire(pol1, pol2);
		rezultat.sort();
		Polinom rezcorect = new Polinom();
		rezcorect.adunareMonom(new Monom(-4,5));
		rezcorect.adunareMonom(new Monom(2,3));
		rezcorect.sort();
		assertEquals("Inmultire test", rezultat, rezcorect);
	}
	
		
	}



