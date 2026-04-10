
//importación de utilidades: el asterisco importa todo lo necesario de una vez.
import java.util.*;

public class Principal {

	public static void main(String[] args) {
		// Herramienta para leer datos del teclado
		Scanner teclado = new Scanner(System.in);

		// declaración de estructura: Array (estática)
		Estudiante[] estudiantesArray = new Estudiante[3];

		// Decalración de estructura: ArrayList (dinámica)
		ArrayList<Estudiante> estudiantesArrayList = new ArrayList<>();

		// Variable para el control de los menús
		int opcionPrincipal = 0;

		System.out.println("**Sistema de Gestión de Estudiantes Iniciado**");

		// Comienza el bucle del menú principal
		do {
			System.out.println("\n Menú Principal:");
			System.out.println("1. Usar Array");
			System.out.println("2. Usar ArrayList");
			System.out.println("3. Salir");
			// Escudo seguridad
			try {
				System.out.println("Selecciona una opción: ");
				opcionPrincipal = teclado.nextInt();
				// Si el usuario escribe una letra, salta al catch de abajo
			} catch (Exception e) {
				System.out.println("   Error: Debes introducir un número del 1 al 3, no letras");
				teclado.next();// Limpia el error del teclado
				opcionPrincipal = 0;// ponemos la opción 0 pra que el bucle se repita

			}
			switch (opcionPrincipal) {
			case 1:
				// Submenú del Array
				int opcionArray = 0;
				do {
					System.out.println("\n Menú Array:");
					System.out.println("1. Agregar estudiante");
					System.out.println("2. ver todos");
					System.out.println("3. Volver al menú principal");
					try {
						System.out.println("Opción :    ");
						opcionArray = teclado.nextInt();
					} catch (Exception e) {
						System.out.println("Error: ¡Introduce solo numero del 1 al 3, no letras!");
						teclado.nextLine(); // Limpia la palabra del error
						opcionArray = 0; // Reinicia la variable para no salir del bucle
						continue; // Salta directamente al inicio del do para mostrar el menú otra vez
					}

					if (opcionArray == 1) {
						// lógica para buscar hueco vacío en el Array
						boolean lleno = true;
						for (int i = 0; i < estudiantesArray.length; i++) {
							// Gestión manual de memoria: buscamos una referencia nula (espacio vacío) para
							// insertar
							if (estudiantesArray[i] == null) {
								// ingreso de datos
								System.out.println("Nombre: ");
								String n = teclado.next();
								System.out.println("Edad: ");
								int e = teclado.nextInt();
								System.out.println("Nota: ");
								double nt = teclado.nextDouble();

								// Guardamos el objeto en la posición encontrada
								estudiantesArray[i] = new Estudiante(n, e, nt);
								System.out.println("**Estudiante agregado correctamente**");
								lleno = false;
								break;
								// Salimos del for tras guardar
							}

						}
						if (lleno)
							System.out.println("Array lleno");
					} else if (opcionArray == 2) {
						// Mostramos solo las posiciones que no sean nulas
						System.out.println("Listado Array");
						for (Estudiante est : estudiantesArray) {
							if (est != null)
								/*
								 * Reutilización de código: Java llama automáticamente al método toString()
								 * definido en la clase Estudiante para mostrar los datos
								 */
								System.out.println(est);
						}
					}

				} while (opcionArray != 3);
				break;

			case 2:
				// Submenú del ArrayList
				int opcionAl = 0;
				do {
					System.out.println("\n Menú ArrayList:");
					System.out.println("1. Agregar estudiante");
					System.out.println("2. Ver todos");
					System.out.println("3. Eliminar por nombre");
					System.out.println("4. Buscar por nombre");
					System.out.println("5. Volver al menú principal");
					try {
						System.out.println("\nOpción ArrayList:");
						opcionAl = teclado.nextInt();
					} catch (Exception e) {
						System.out.println("   Error introduce un número del 1 al 5, no letras   ");
						teclado.nextLine();
						opcionAl = 0;
						continue;
					}

					switch (opcionAl) {
					case 1:
						// Agregar al ArrayList (sin límite)
						System.out.println("Nombre: ");
						String n = teclado.next();
						System.out.println("Edad: ");
						int e = teclado.nextInt();
						System.out.println("Nota: ");
						double nt = teclado.nextDouble();
						estudiantesArrayList.add(new Estudiante(n, e, nt));
						System.out.println("**Estudiante agregado correctamente**");
						break;
					case 2:
						// Mostrar toda la colección
						for (Estudiante est : estudiantesArrayList)
							System.out.println(est);
						break;
					case 3:
						// Eliminar estudiante
						System.out.println("Nombre a eliminar: ");
						String eliminar = teclado.next();
						// Operación agregada (filtro): Elimina elementos que cumplan el predicado
						// (nombre coincidente)
						boolean borrado = estudiantesArrayList
								.removeIf(est -> est.getNombre().equalsIgnoreCase(eliminar));
						System.out.println(borrado ? "**Estudiante eliminado correctamente**"
								: "**No se puede eliminar, el nombre no existe**");
						break;
					case 4:
						// Operación agregada (reducción: Filtra por nombre y reduce el flujo al primer
						// elemento encontrado null
						System.out.println("Nombre a buscar: ");
						String buscar = teclado.next();
						Estudiante estudianteEncontrado = estudiantesArrayList.stream()
								.filter(estudiante -> estudiante.getNombre().equalsIgnoreCase(buscar))
								.reduce((first, second) -> first).orElse(null);

						if (estudianteEncontrado == null) {
							System.out.println("**Estudiante no encontrado**");
						} else {
							System.out.println("Encontrado: " + estudianteEncontrado);
						}

						break;

					}

				} while (opcionAl != 5);
				break;

			case 3:
				System.out.println("Fin de la ejecución...¡Hasta pronto!");
				break;
			default:
				// solo mostramos este mensaje si el usuario pone un número que no existe en la
				// lista propuesta
				// si opcionPrincipal es 0, ya avisamos de error en el catch
				if (opcionPrincipal != 0) {
					System.out.println("Opción no válida en el menú principal.");
				}
				break;
			}
		} while (opcionPrincipal != 3); // el ucle se repite mientras no se la opción 3

		teclado.close(); // Cerramos el recurso del sistema
	}

}
//Bibliografía
//      Oracle: https://docs.oracle.com/javase/tutorial/collections/streams/index.html
//            : docs.oracle.com - Class ArrayList
//            : https://docs.oracle.com/en/java/javase/17/docs/api/
//            : https://docs.oracle.com/javase/tutorial/collections/streams/index.html
//            : docs.oracle.com - Package java.util.stream
//            : https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html
// Unidad didactica 6: Aplicaciones de las estructuras de alamacenamiento/Modulo profesional programacion/CESUR
