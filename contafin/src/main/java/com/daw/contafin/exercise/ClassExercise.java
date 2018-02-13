package com.daw.contafin.exercise;

import java.util.List;

public class ClassExercise {
		private int kind;
		private String enunciado;
		private List<String> rutaImagenes;
		private List<String> textoImagenes;
		
		public ClassExercise() {
			
		}

		public ClassExercise(int kind, String enunciado, List<String> rutaImagenes,  List<String> textoImagenes) {
			if(kind == 1) {
				this.kind=kind;
				this.enunciado=enunciado;
				this.rutaImagenes = rutaImagenes;
				this.textoImagenes = textoImagenes;
			}
			else if(kind == 2) {
				this.kind=kind;
				this.enunciado=enunciado;
				this.rutaImagenes = rutaImagenes;
				this.textoImagenes = textoImagenes;
			}
			else if(kind == 3) {
				this.kind=kind;
				this.enunciado=enunciado;
				this.rutaImagenes = null;
				this.textoImagenes = textoImagenes;
			}
			else if(kind == 4) {
				this.kind=kind;
				this.enunciado=enunciado;
				this.rutaImagenes = null;
				this.textoImagenes = textoImagenes;
			}
			else if(kind == 5) {
				this.kind=kind;
				this.enunciado=enunciado;
				this.rutaImagenes = null;
				this.textoImagenes = textoImagenes;
			}
			else if(kind == 6) {
				this.kind=kind;
				this.enunciado=enunciado;
				this.rutaImagenes = rutaImagenes;
				this.textoImagenes = textoImagenes;
			}
			else if(kind == 7) {
				this.kind=kind;
				this.enunciado=enunciado;
				this.rutaImagenes = rutaImagenes;
				this.textoImagenes = textoImagenes;
			}
			else {
				System.out.println("Este tipo de ejercicio no existe");
			}
		}
		
		public int getKind() {
			return kind;
		}

		public void setKind(int kind) {
			this.kind = kind;
		}

		public String getEnunciado() {
			return enunciado;
		}
		public void setEnunciado(String enunciado) {
			this.enunciado = enunciado;
		}
		public List<String> getRutaImagenes() {
			return rutaImagenes;
		}
		public void setRutaImagenes(List<String> rutaImagenes) {
			this.rutaImagenes = rutaImagenes;
		}
		public List<String> getTextoImagenes() {
			return textoImagenes;
		}
		public void setTextoImagenes(List<String> textoImagenes) {
			this.textoImagenes = textoImagenes;
		}
}
