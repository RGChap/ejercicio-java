
public class Estudiante {
//Atributos con visibilidad privada (Encapsulamiento)
	private String nombre;
	private int edad;
	private double nota;

 //Constructor para crear el objeto con sus datos
public Estudiante(String nombre, int edad, double nota) {
	this.nombre = nombre;
	this.edad = edad;
	this.nota = nota;
	
}

//Métodos para obtener los datos (getters) desde fuera
public String getNombre() {
	return nombre;
}
public int getEdad() {
	return edad;
}
public double getNota() {
	return nota;
}
//Método para mostrar la información del estudiante de forma legible
@Override
public String toString() {
	return "Nombre: " + nombre + ", Edad: " + edad + ", Nota final: " + nota;
}
}