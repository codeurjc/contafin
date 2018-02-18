package com.daw.contafin.unit;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.daw.contafin.exercise.Exercise;
import com.daw.contafin.exercise.ExerciseRepository;
import com.daw.contafin.lesson.Lesson;
import com.daw.contafin.lesson.LessonRepository;

@Controller
public class UnitController {
	
	@Autowired
	private UnitRepository unitRepository;
	
	@Autowired
	private LessonRepository lessonRepository;
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@PostConstruct
	public void init() {
		
		Unit unit;
		unit= new Unit("Unidad 1");
		unitRepository.save(unit);
		unit= new Unit("Unidad 2");
		unitRepository.save(unit);
		
		unit = unitRepository.findById(1);
		Lesson lesson1 = new Lesson("Lección 1 Unidad 1", unit);
		lessonRepository.save(lesson1);
		Lesson lesson2 = new Lesson("Lección 2 Unidad 1", unit);
		lessonRepository.save(lesson2);
		Lesson lesson3 = new Lesson("Lección 3 Unidad 1", unit);
		lessonRepository.save(lesson3);
		
		unit = unitRepository.findById(2);
		Lesson lesson4 = new Lesson("Lección 1 Unidad 2", unit);
		lessonRepository.save(lesson4);
		Lesson lesson5 = new Lesson("Lección 2 Unidad 2", unit);
		lessonRepository.save(lesson5);
		Lesson lesson6 = new Lesson("Lección 3 Unidad 2", unit);
		lessonRepository.save(lesson6);
		
		
		
		// A la hora de guardar los ejercicios hay que tener en cuenta que hay que saber la id de la leccion a la que quiere introducirselo habria que guardar 1 leccion y despues los 7 ejercicios para asi saber en cual lo metemos (Haciendo una consulta para calcular la ultima id de lecciones o algo asi
		
		Lesson lesson = lessonRepository.findById(1);
		List<String> images =  Arrays.asList("../img/machine.jpg","../img/land.jpg","../img/truck.jpg");
		List<String> texts = Arrays.asList("213.Maquinaria","210.Terrenos y bienes naturales","218. Elementos de transporte");
		exerciseRepository.save(new Exercise(1,"1.1.1 Seleccione el asiento",images,texts,null,lesson));
		
		exerciseRepository.save(new Exercise(2,"1.1.2 Escribe la denominación de la cuenta que recoge: " + "maquinarias para el proceso productivo de la empresa",null,null,null,lesson));
		
		texts = Arrays.asList("213.Pepe","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(3,"1.1.3 Toca los pares",null,texts,null,lesson));
		
		texts = Arrays.asList("213.Hola","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(4,"1.1.4 Realiza el asiento",null,texts,null,lesson));
		
		texts = Arrays.asList("Activo","Pasivo","Patrimonio neto");
		exerciseRepository.save(new Exercise(5,"1.1.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales" ,null,texts,null,lesson));
		
		texts = Arrays.asList("574. Bancos cuenta de ahorro","574. Bancos cuenta corriente","430. Clientes","140. Deudores");
		exerciseRepository.save(new Exercise(6,"1.1.6 Escoge la cuenta que falta: La empresa saca 5 de la cuenta corriente bancaria y lo ingresa en caja",null,texts,null,lesson));
		
		texts = Arrays.asList(" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.","La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años."," La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
		exerciseRepository.save(new Exercise(7,"1.1.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"" ,null,texts,null,lesson));
		
		lesson = lessonRepository.findById(2);
		images =  Arrays.asList("../img/machine.jpg","../img/land.jpg","../img/truck.jpg");
		texts = Arrays.asList("213.Maquinaria","210.Terrenos y bienes naturales","218. Elementos de transporte");
		exerciseRepository.save(new Exercise(1,"1.2.1 Seleccione el asiento",images,texts,null,lesson));
		
		exerciseRepository.save(new Exercise(2,"1.2.2 Escribe la denominación de la cuenta que recoge: " + "maquinarias para el proceso productivo de la empresa",null,null,null,lesson));
		
		texts = Arrays.asList("213.Pepe","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(3,"1.2.3 Toca los pares",null,texts,null,lesson));
		
		texts = Arrays.asList("213.Hola","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(4,"1.2.4 Realiza el asiento",null,texts,null,lesson));
		
		texts = Arrays.asList("Activo","Pasivo","Patrimonio neto");
		exerciseRepository.save(new Exercise(5,"1.2.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales" ,null,texts,null,lesson));
		
		texts = Arrays.asList("574. Bancos cuenta de ahorro","574. Bancos cuenta corriente","430. Clientes","140. Deudores");
		exerciseRepository.save(new Exercise(6,"1.2.6 Escoge la cuenta que falta: La empresa saca 5 de la cuenta corriente bancaria y lo ingresa en caja",null,texts,null,lesson));
		
		texts = Arrays.asList(" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.","La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años."," La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
		exerciseRepository.save(new Exercise(7,"1.2.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"" ,null,texts,null,lesson));
		
		lesson = lessonRepository.findById(3);
		images =  Arrays.asList("../img/machine.jpg","../img/land.jpg","../img/truck.jpg");
		texts = Arrays.asList("213.Maquinaria","210.Terrenos y bienes naturales","218. Elementos de transporte");
		exerciseRepository.save(new Exercise(1,"1.3.1 Seleccione el asiento",images,texts,null,lesson));
		
		exerciseRepository.save(new Exercise(2,"1.3.2 Escribe la denominación de la cuenta que recoge: " + "maquinarias para el proceso productivo de la empresa",null,null,null,lesson));
		
		texts = Arrays.asList("213.Pepe","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(3,"1.3.3 Toca los pares",null,texts,null,lesson));
		
		texts = Arrays.asList("213.Hola","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(4,"1.3.4 Realiza el asiento",null,texts,null,lesson));
		
		texts = Arrays.asList("Activo","Pasivo","Patrimonio neto");
		exerciseRepository.save(new Exercise(5,"1.3.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales" ,null,texts,null,lesson));
		
		texts = Arrays.asList("574. Bancos cuenta de ahorro","574. Bancos cuenta corriente","430. Clientes","140. Deudores");
		exerciseRepository.save(new Exercise(6,"1.3.6 Escoge la cuenta que falta: La empresa saca 5 de la cuenta corriente bancaria y lo ingresa en caja",null,texts,null,lesson));
		
		texts = Arrays.asList(" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.","La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años."," La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
		exerciseRepository.save(new Exercise(7,"1.3.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"" ,null,texts,null,lesson));
		
		lesson = lessonRepository.findById(4);
		images =  Arrays.asList("../img/machine.jpg","../img/land.jpg","../img/truck.jpg");
		texts = Arrays.asList("213.Maquinaria","210.Terrenos y bienes naturales","218. Elementos de transporte");
		exerciseRepository.save(new Exercise(1,"2.1.1 Seleccione el asiento",images,texts,null,lesson));
		
		exerciseRepository.save(new Exercise(2,"2.1.2 Escribe la denominación de la cuenta que recoge: " + "maquinarias para el proceso productivo de la empresa",null,null,null,lesson));
		
		texts = Arrays.asList("213.Pepe","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(3,"2.1.3 Toca los pares",null,texts,null,lesson));
		
		texts = Arrays.asList("213.Hola","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(4,"2.1.4 Realiza el asiento",null,texts,null,lesson));
		
		texts = Arrays.asList("Activo","Pasivo","Patrimonio neto");
		exerciseRepository.save(new Exercise(5,"2.1.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales" ,null,texts,null,lesson));
		
		texts = Arrays.asList("574. Bancos cuenta de ahorro","574. Bancos cuenta corriente","430. Clientes","140. Deudores");
		exerciseRepository.save(new Exercise(6,"2.1.6 Escoge la cuenta que falta: La empresa saca 5 de la cuenta corriente bancaria y lo ingresa en caja",null,texts,null,lesson));
		
		texts = Arrays.asList(" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.","La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años."," La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
		exerciseRepository.save(new Exercise(7,"2.1.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"" ,null,texts,null,lesson));
		
		lesson = lessonRepository.findById(5);
		images =  Arrays.asList("../img/machine.jpg","../img/land.jpg","../img/truck.jpg");
		texts = Arrays.asList("213.Maquinaria","210.Terrenos y bienes naturales","218. Elementos de transporte");
		exerciseRepository.save(new Exercise(1,"2.2.1 Seleccione el asiento",images,texts,null,lesson));
		
		exerciseRepository.save(new Exercise(2,"2.2.2 Escribe la denominación de la cuenta que recoge: " + "maquinarias para el proceso productivo de la empresa",null,null,null,lesson));
		
		texts = Arrays.asList("213.Pepe","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(3,"2.2.3 Toca los pares",null,texts,null,lesson));
		
		texts = Arrays.asList("213.Hola","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(4,"2.2.4 Realiza el asiento",null,texts,null,lesson));
		
		texts = Arrays.asList("Activo","Pasivo","Patrimonio neto");
		exerciseRepository.save(new Exercise(5,"2.2.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales" ,null,texts,null,lesson));
		
		texts = Arrays.asList("574. Bancos cuenta de ahorro","574. Bancos cuenta corriente","430. Clientes","140. Deudores");
		exerciseRepository.save(new Exercise(6,"2.2.6 Escoge la cuenta que falta: La empresa saca 5 de la cuenta corriente bancaria y lo ingresa en caja",null,texts,null,lesson));
		
		texts = Arrays.asList(" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.","La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años."," La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
		exerciseRepository.save(new Exercise(7,"2.2.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"" ,null,texts,null,lesson));
		
		lesson = lessonRepository.findById(6);
		images =  Arrays.asList("../img/machine.jpg","../img/land.jpg","../img/truck.jpg");
		texts = Arrays.asList("213.Maquinaria","210.Terrenos y bienes naturales","218. Elementos de transporte");
		exerciseRepository.save(new Exercise(1,"2.3.1 Seleccione el asiento",images,texts,null,lesson));
		
		exerciseRepository.save(new Exercise(2,"2.3.2 Escribe la denominación de la cuenta que recoge: " + "maquinarias para el proceso productivo de la empresa",null,null,null,lesson));
		
		texts = Arrays.asList("213.Pepe","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(3,"2.3.3 Toca los pares",null,texts,null,lesson));
		
		texts = Arrays.asList("213.Hola","210.Terrenos y bienes naturales","218. Elementos de transporte","206. Aplicaciones informáticas","213. Maquinaria","100. Capital social","Pasivo exigible","300. Mercaderías A","Patrimonio neto","Inmovilizado material","Existencias","Inmovilizado intangible");
		exerciseRepository.save(new Exercise(4,"2.3.4 Realiza el asiento",null,texts,null,lesson));
		
		texts = Arrays.asList("Activo","Pasivo","Patrimonio neto");
		exerciseRepository.save(new Exercise(5,"2.3.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales" ,null,texts,null,lesson));
		
		texts = Arrays.asList("574. Bancos cuenta de ahorro","574. Bancos cuenta corriente","430. Clientes","140. Deudores");
		exerciseRepository.save(new Exercise(6,"2.3.6 Escoge la cuenta que falta: La empresa saca 5 de la cuenta corriente bancaria y lo ingresa en caja",null,texts,null,lesson));
		
		texts = Arrays.asList(" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.","La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años."," La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
		exerciseRepository.save(new Exercise(7,"2.3.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"" ,null,texts,null,lesson));
		
	}	
}
