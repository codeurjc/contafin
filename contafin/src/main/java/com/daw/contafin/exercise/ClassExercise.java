package com.daw.contafin.exercise;

import java.util.List;

public class ClassExercise {
		private String enunciado;
		private List<String> rutaImagenes;
		private List<String> textoImagenes;
		
		public ClassExercise() {
			
		}
		public ClassExercise(int tipo, String enunciado, List<String> rutaImagenes,  List<String> textoImagenes) {
			if(tipo == 1) {
				this.enunciado=enunciado;
				this.rutaImagenes = rutaImagenes;
				this.textoImagenes = textoImagenes;
			}
			else if(tipo == 2) {
				this.enunciado=enunciado;
				this.rutaImagenes = rutaImagenes;
				this.textoImagenes = textoImagenes;
			}
			else if(tipo == 3) {
				this.enunciado=enunciado;
				this.rutaImagenes = null;
				this.textoImagenes = textoImagenes;
			}
			else if(tipo == 4) {
				this.enunciado=enunciado;
				this.rutaImagenes = null;
				this.textoImagenes = textoImagenes;
			}
			else if(tipo == 5) {
				this.enunciado=enunciado;
				this.rutaImagenes = null;
				this.textoImagenes = textoImagenes;
			}
			else if(tipo == 6) {
				this.enunciado=enunciado;
				this.rutaImagenes = rutaImagenes;
				this.textoImagenes = textoImagenes;
			}
			else if(tipo == 7) {
				this.enunciado=enunciado;
				this.rutaImagenes = rutaImagenes;
				this.textoImagenes = textoImagenes;
			}
			else {
				System.out.println("Este tipo de ejercicio no existe");
			}
		}
}
