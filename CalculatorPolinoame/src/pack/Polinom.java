package pack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Polinom {
	private List<Monom> listaMonoame;
	
	public Polinom() {
		listaMonoame=new ArrayList<Monom>();
	}
	public List<Monom> getTerms()
	{
		return listaMonoame;
	}
	public void adunareMonom(Monom monom) 
	{
	   int gasit = 0;//daca gasim un polinom cu acelasi grad gasit se va face 1 pentru a putea efectua adunarea
		for(Monom parcurgere: listaMonoame) ///patcurgem monom cu monom lista de monoame
		{
			if(monom.getGrad() == parcurgere.getGrad()) ///daca monomul din lista are acelasi grad cu monomul pe care vrem sa il adunam
			{   
				gasit = 1;
				parcurgere.adunare(monom);
				
			}
		}
		
		if(gasit==1)
			listaMonoame.add(monom); 
	}
	public Polinom copiere() //returneaza o copie a polinomului
	{
		Polinom copie = new Polinom();
		Monom copieMonom;
		for(Monom m : this.getTerms())
		{
			copieMonom = new Monom(m.getCoeficient(), m.getGrad());
			copie.adunareMonom(copieMonom);
		}
		return copie;
	}
	public void sort()
	{
		Collections.sort(this.listaMonoame);//sorteaza lista de monoame
	}
	public void derivareMonom()
	{
		for(Monom parcurgere: listaMonoame)
		{
			parcurgere.derivare();//aplicam metoda de derivare din monom
		}
	}
	public void clearPol() //stergere din lista
	{
		listaMonoame.clear();
	}
	
	@Override
	public String toString() //returns a formatted string containing the polynomial expression based on some cases
	{
		String string = "";
		int size = listaMonoame.size();
		if(size > 0) //check if the list contains at least one element
		{
			if(size == 1 && listaMonoame.get(0).getGrad() == 0) //polynomial has just the constant
			{
				if(listaMonoame.get(listaMonoame.size()-1).getCoeficient() > 0) 
					string =  String.format("%s%.2f", string, (float)listaMonoame.get(listaMonoame.size()-1).getCoeficient());
				else
					string =  String.format("%s%.2f", string, (float)listaMonoame.get(listaMonoame.size()-1).getCoeficient());
			}
			else if(size == 1 && listaMonoame.get(0).getGrad() > 0) //has just one variable
			{
				if(listaMonoame.get(listaMonoame.size()-1).getCoeficient() > 0) 
					string =  String.format("%s%.2fx^%d", string, (float)listaMonoame.get(size-1).getCoeficient(),listaMonoame.get(0).getGrad());
				else
					string =  String.format("%s%.2fx^%d", string, (float)listaMonoame.get(size-1).getCoeficient(),listaMonoame.get(0).getGrad());
			}
			else
			{
				string = String.format("%.2fx^%d",(float)listaMonoame.get(0).getCoeficient(),listaMonoame.get(0).getGrad()); //puts the first monomial without sign in case of +
				if(listaMonoame.get(size-1).getGrad() == 0) //checks if the last element has degree 0 (for printing just the coefficient, without the variable)
				{
					size--;
				}
				for(int i = 1; i < size; i++) //obtain the representation of the rest of the expression
				{
					if(listaMonoame.get(i).getCoeficient() > 0)
					{
						string = String.format("%s+%.2fx^%d", string, (float)listaMonoame.get(i).getCoeficient(),listaMonoame.get(i).getGrad());
					}
					else 
					{
						string = String.format("%s%.2fx^%d", string, (float)listaMonoame.get(i).getCoeficient(),listaMonoame.get(i).getGrad());
					}
				}
				if(size != listaMonoame.size()) //size will be different if the last element has degree 0
				{
					if(listaMonoame.get(size-1).getCoeficient() > 0) 
						string =  String.format("%s+%.2f", string, (float)listaMonoame.get(size-1).getCoeficient());
					else string =  String.format("%s%.2f", string, (float)listaMonoame.get(size-1).getCoeficient());
				}
			}
		}
		else string = "0"; //0 if the list is empty
		return string;
	}
	
	
	public void cleanPol() {//stergem elementele cu coeficient 0
		Polinom pol = this.copiere();///polinom auxiliar
		for(Monom monom : pol.listaMonoame)
		{
			if(monom.getGrad()<0||monom.getCoeficient()==0.0)
				this.listaMonoame.remove(monom);///stergem din lista daca gradul e <0 sau coeficientul e 0
		}
	}
	public void inmultireMonom(Monom m)
	{
		for(Monom parcurgere: listaMonoame)//parcurgem lista de monoame si efectuam inmultirea implementata in clasa Monom
		{
			parcurgere.inmultire(m);
		}
	}
	@Override
	public boolean equals(Object object)
	{ 
		///initial luam exceptiile care returneaza false
		if(object == null) //object null
			return false;
		if(!(object instanceof Polinom)) //object nu e de tip polinom
			return false;
		Polinom polinom = (Polinom) object;//facem cast la tipul POLINOM
		if(!(this.listaMonoame.equals(polinom.getTerms()))) //luam termenii si verificam daca vor corespunde
			return false;
		return true;
	}

}
