package pack;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Operatie {
	public static Polinom derivare(Polinom polinom)
	{
		Polinom polCopie = polinom.copiere();//copiem polinomul
		polCopie.derivareMonom();
		//derivam direct pe copie
		
		return polCopie;//returnam copia care este deja rezultatul
	}
	public static boolean readPolinom(String expresie, Polinom p) //stores a string of a polynomial expression in a object of type Polinom; returns true if the string was valid or false otherwise
	{
		Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
		Matcher matcher = pattern.matcher(expresie);
		String[] string;
		String string2;
		for(int i = 0; i < expresie.length(); i++) //first check if the expression contains illegal characters
		{
			char current = expresie.charAt(i);
			if(!Character.isDigit(current) && current != 'x' && current != '-' && current != '+' && current != '^' && current != '.')
			{
				return false;
			}
			if(!Character.isDigit(current) && i < expresie.length() - 1) //check for duplicates other than digits
			{
				if(current == 'x' && expresie.charAt(i+1) == 'x' || expresie.charAt(i+1) == '.') return false;
				else if(current == '+' && (expresie.charAt(i+1) == '+' || expresie.charAt(i+1) == '-' || expresie.charAt(i+1) == '^')) 
					return false;
				else if(current == '-' && (expresie.charAt(i+1) == '+' || expresie.charAt(i+1) == '-' || expresie.charAt(i+1) == '^')) return false;
				else if(current == '^' && (expresie.charAt(i+1) == '+' || expresie.charAt(i+1) == '-' || expresie.charAt(i+1) == '^')) return false;
				else if(current == '.' && !Character.isDigit(expresie.charAt(i+1))) return false;
			}
			if(Character.isDigit(current) && i < expresie.length() - 1) //check for digit^
			{
				if(expresie.charAt(i+1) == '^') return false;
			}
		}
		while (matcher.find()) 
		{
			if(matcher.group().contains("x^"))
			{
				string = matcher.group().split("x\\^"); //get the degree and coefficient for x^
				if(string[0].length() == 1 && (string[0].contains("-") || string[0].contains("+"))) //case +-x^n
				{
					if(string[0].charAt(0) == '-')	string[0] = "-1";
					else string[0] = "1";
				}
				else 
				{
					if(string[0].length() < 1) 
						string[0] = "1"; //caz x^n//setam coef 1
				}
				p.adunareMonom(new Monom(Float.parseFloat(string[0]), Integer.parseInt(string[1])));
			}
			else if (matcher.group().contains("x"))
			{
				string = matcher.group().split("x");
				if(string.length == 0) //caz x
				{
					p.adunareMonom(new Monom(1,1));
				}
				else
				{
				if(string[0].length() == 1 && (string[0].contains("-") || string[0].contains("+"))) //case +-x
				{
					if(string[0].charAt(0) == '-')	
						string[0] = "-1";
					else string[0] = "1";
				}
				Monom d=new Monom(Float.parseFloat(string[0]), 1);
				p.adunareMonom(d);
				}
			}
			else
			{
				string2 = matcher.group();
				Monom m=new Monom(Float.parseFloat(string2), 0);
				p.adunareMonom(m);
			}
		}
		return true;
	}
	public static Polinom adunare(Polinom polinom1, Polinom polinom2)
	{
		Polinom copierePolinom1 = polinom1.copiere();
		
		for(Monom parcurgere : polinom2.getTerms())
		{
			copierePolinom1.adunareMonom(parcurgere);
		}
		return copierePolinom1;
	}
	public static Polinom inmultire(Polinom polinom1, Polinom polinom2)
	{
		Polinom rezultat=new Polinom();	//cream un nou polinom pt a stoca rezultatul inmultirii
		Polinom copierePol1;//facem o copie a primului polinom
		for(Monom monom: polinom2.getTerms())
		{
			copierePol1 = polinom1.copiere();
			copierePol1.inmultireMonom(monom);
			
			rezultat = Operatie.adunare(rezultat, copierePol1);
		}
		return rezultat;
	}
	public static Polinom scadere(Polinom polinom1, Polinom polinom2)
	{
		Polinom copie1 = polinom1.copiere();///copiem primul polinom
		Polinom copie2= polinom2.copiere();//copiem al doilea polinom
		
		for(Monom monom : copie1.getTerms())///parcurgem prima copie
		{ 
			//efectual operatie de scadere ca o adunare cu -
			//a-b=a+(-b)
			float coef=monom.getCoeficient();
			monom.setCoeficient(-1 * coef);
		}
		
		for(Monom monom : copie2.getTerms())//parcurgem a doua copie
		{
			copie1.adunareMonom(monom);//add
		}
		return copie1;
	}

}
