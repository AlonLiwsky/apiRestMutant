package com.example.main.services;

import java.util.Arrays;
import org.springframework.stereotype.Service;

import com.example.main.dtos.ListadoDTO;
import com.example.main.entities.Humano;
import com.example.main.repositories.HumanoRepository;

@Service
public class HumanoService {

	private HumanoRepository humanoRepository;

	public HumanoService(HumanoRepository humanoRepository) {
		this.humanoRepository = humanoRepository;
	}

	

	public boolean isMutant(ListadoDTO ADN) {
		// Se utiliza la clase ListadoDTO a modo de wrapper
		String[] dna = ADN.dna;

		boolean mutante = validarAdn(dna);
		Humano humano = new Humano(Arrays.asList(dna), mutante);
		humanoRepository.save(humano);
		return mutante;
	}

	private boolean validarAdn(String[] dna) {

		int largo = dna.length;

		// Instanciar el array
		char[][] adn = new char[largo][largo];

		// String a Array y validamos que cada fila sea igual de larga que las columnas
		for (int i = 0; i < largo; i++) {
			adn[i] = dna[i].toCharArray();
			if (adn[i].length != largo) {
				throw new IllegalArgumentException("La matriz no cumple con NxN");
			}
		}

		int cont = 0;

		// Se recorre la matriz de forma ordenada, verificando si en las 4 direcciones
		// posibles (horizontal a la derecha, vertical para abajo y en diagonal para la
		// izquierda y derecha)
		// existen 4 elementos consecutivos iguales, excepto en los casos donde ya no
		// entrarian en la matriz 4 elemtnos en esa direccion
		// Se verifica si ya encontro mas de dos columnas validas y corta el loop con un
		// return
		// No estoy muy seguro si aporta relamente esa verificacion de si ya lleva dos
		// columnas validas. Otra manera de hacerlo seria con un solo if que englobe
		// todo y pregunte si
		// ya encontro dos, pero con el metodo aplicado solo entra a ese if maximo dos
		// veces.
		// Si encuentra una columna valida la transforma en "O" para luego no volver a
		// contarla
		for (int i = 0; i < largo; i++) {
			for (int j = 0; j < largo; j++) {
				// Como se recorrera de arriba a abajo de izquierda a derecha, no hace falta
				// verificar hacia arriba ni a la izquierda porq ya se verifico cuando se paso
				// por ahi

				// revision horzontal
				if (j <= largo - 4) {
					if (adn[i][j] == adn[i][j + 1] && adn[i][j] == adn[i][j + 2] && adn[i][j] == adn[i][j + 3]
							&& adn[i][j] != 'o') {
						cont++;

						if (cont >= 2) {
							return true;
						}

						adn[i][j] = 'o';
						adn[i][j + 1] = 'o';
						adn[i][j + 2] = 'o';
						adn[i][j + 3] = 'o';
					}
				}

				if (i <= largo - 4) {

					// Revision vertical
					if (adn[i][j] == adn[i + 1][j] && adn[i][j] == adn[i + 2][j] && adn[i][j] == adn[i + 3][j]
							&& adn[i][j] != 'o') {
						cont++;

						if (cont >= 2) {
							return true;
						}

						adn[i][j] = 'o';
						adn[i + 1][j] = 'o';
						adn[i + 2][j] = 'o';
						adn[i + 3][j] = 'o';
					}

					// Revision diagonal postiva
					if (j >= 4) {
						if (adn[i][j] == adn[i + 1][j - 1] && adn[i][j] == adn[i + 2][j - 2]
								&& adn[i][j] == adn[i + 3][j - 3] && adn[i][j] != 'o') {
							cont++;

							if (cont >= 2) {
								return true;
							}

							adn[i][j] = 'o';
							adn[i + 1][j - 1] = 'o';
							adn[i + 2][j - 2] = 'o';
							adn[i + 3][j - 3] = 'o';
						}
					}

					// Revision diagonal negativa
					if (j <= largo - 4) {
						if (adn[i][j] == adn[i + 1][j + 1] && adn[i][j] == adn[i + 2][j + 2]
								&& adn[i][j] == adn[i + 3][j + 3] && adn[i][j] != 'o') {
							cont++;

							if (cont >= 2) {
								return true;
							}

							adn[i][j] = 'o';
							adn[i + 1][j + 1] = 'o';
							adn[i + 2][j + 2] = 'o';
							adn[i + 3][j + 3] = 'o';
						}
					}
				}
			}

		}

		return false;
	}


}
