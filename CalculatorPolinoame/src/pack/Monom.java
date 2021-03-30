package pack;

public class Monom implements Comparable<Monom> {
	private float coeficient;
	private int grad;
	public Monom(float coeficient,int grad) {
		this.coeficient=coeficient;
		this.grad=grad;
	}
	public float getCoeficient() {
		return coeficient;
	}
	public void setCoeficient(float coeficient) {
		this.coeficient = coeficient;
	}
	public int getGrad() {
		return grad;
	}
	public void setGrad(int grad) {
		this.grad = grad;
	}
	public void adunare(Monom monom) {
		int coef1=this.grad;
		int coef2=monom.getGrad();
		if(coef1==coef2) {
			this.coeficient=this.coeficient+monom.getCoeficient();
		}
		
	}
	public void derivare() {
		
		int grad=this.grad;
		float coef=this.coeficient;
		
		this.setCoeficient(coef*grad);
		this.setGrad(grad-1);
		
	}
	@Override
	public String toString()
	{   
		float coef=this.getCoeficient();
		int grad=this.getGrad();
		return String.format("%.2fx^%d + ", coef, grad);
	}
	public void inmultire(Monom monom)
	{
		this.coeficient =this.coeficient* monom.coeficient;
		this.grad =this.grad+ monom.grad;
	}
	
	public int compareTo(Monom monom)
	{
		return this.grad-monom.grad;
	}
	
	@Override
	public boolean equals(Object object) {
		
		if(object==null)
			return false;
		if(!(object instanceof Monom))
			return false;
		Monom monom=(Monom)object;
		 if(monom.getCoeficient()!=this.coeficient)
			return false;
		if(this.grad != monom.getGrad())
			return false;
		return true;
		
		
		
	}
	
	
	
	
	

}
