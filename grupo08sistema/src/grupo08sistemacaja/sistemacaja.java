package grupo08sistemacaja;

public class sistemacaja {
private double montoInicial;
private double montoagregar;
private static final double montoretiro=400.0;
private static final double montolimite=1000.0;

public sistemacaja() {
	
	this.montoInicial=600.0;
	this.montoagregar=0.0;
}

public double getMontoInicial() {
	return montoInicial;
}

public double getmontoagregar() {
	return montoagregar;
}

public static double getMontoretiro() {
	return montoretiro;
}

public static double getMontolimite() {
	return montolimite;
}

public double Montoactual() {
	
	return montoInicial+montoagregar;
}
public boolean agregarmonto(double valorproducto) { //100
	double montototal = Montoactual()+ valorproducto;
	if(montototal <=montolimite) {
		montoagregar+=valorproducto;
		return true;
	}else {
		return false;
	}
	
}
public boolean autorizarretiro() {
	
	return Montoactual() >=montoretiro;
}
public boolean realizarretiro() {
	if(autorizarretiro()) {
		montoInicial= Montoactual()-montoretiro;
		montoagregar=0.0;
		return true;
	}else {
		return false;
	}
}

}
