	## -- UNIT CONTROLLER FILE --

	@RequestMapping("/UnitCreation")
	public String unit(Model model, @RequestParam String unitName, @RequestParam(value="lessonName[]") String[] lessonName,
			@RequestParam(value="images[]") MultipartFile[] images, @RequestParam(value="texts[]") String[] texts, @RequestParam(value="statements[]") String[] statements,
			@RequestParam(value="answers[]") String[] answers) throws IOException {

		Unit unit;
		unit = new Unit(unitName);
		unitService.save(unit);

		Lesson lesson1 = new Lesson(lessonName[0], unit);
		lessonService.save(lesson1);
		Lesson lesson2 = new Lesson(lessonName[1], unit);
		lessonService.save(lesson2);
		Lesson lesson3 = new Lesson(lessonName[2], unit);
		lessonService.save(lesson3);
		
		//Lesson 1
		Lesson lesson = lesson1;
		List<String> myTexts = Arrays.asList(texts[0], texts[1], texts[2]);
		//List<String> myImages = Arrays.asList(images[0], images[1], images[2]);
		Answer answer = new Answer(answers[0]);
		//exerciseService.save(new Exercise(1, statements[0], myImages, myTexts, answer, lesson));
		bytes1 = imageService.uploadImage(images[0]);
		bytes2 = imageService.uploadImage(images[1]);
		bytes3 = imageService.uploadImage(images[2]);
		exercise=new Exercise(1, statements[0], myTexts, answer, lesson);
		exercise.setImage1(bytes1);
		exercise.setImage2(bytes2);
		exercise.setImage3(bytes3);
		exerciseService.save(exercise);

		answer = new Answer(answers[1]);
		exerciseService.save(new Exercise(2, statements[1], null, answer, lesson));

		/*
		 * myTexts = Arrays.asList(texts[3], texts[4], texts[5], texts[6], texts[7],
		 * texts[8], texts[9], texts[10]); exerciseRepository.save(new Exercise(3,
		 * statements[2], null, myTexts, null, lesson));
		 * 
		 * myTexts = Arrays.asList(texts[11], texts[12], texts[13], texts[14],
		 * texts[15], texts[16], texts[17], texts[18]); exerciseRepository.save(new
		 * Exercise(4, statements[3], null, myTexts, null, lesson));
		 */

		myTexts = Arrays.asList(texts[3], texts[4], texts[5]);
		answer = new Answer(answers[2]);
		exerciseService.save(new Exercise(5, statements[2], myTexts, answer, lesson));

		/*
		 * myTexts = Arrays.asList(texts[22], texts[23], texts[24], texts[25]); answer =
		 * new Answer(answers[3]); exerciseRepository.save(new Exercise(6,
		 * statements[5], null, myTexts, answer, lesson));
		 */

		myTexts = Arrays.asList(texts[6], texts[7], texts[8]);
		answer = new Answer(answers[3]);
		exerciseService.save(new Exercise(7, statements[3], myTexts, answer, lesson));

		//Lesson 2
		lesson = lesson2;
		myTexts = Arrays.asList(texts[9], texts[10], texts[11]);
		//myImages = Arrays.asList(images[3], images[4], images[5]);
		answer = new Answer(answers[4]);
		//exerciseService.save(new Exercise(1, statements[4], myImages, myTexts, answer, lesson));
		bytes1 = imageService.uploadImage(images[3]);
		bytes2 = imageService.uploadImage(images[4]);
		bytes3 = imageService.uploadImage(images[5]);
		exercise=new Exercise(1, statements[4], myTexts, answer, lesson);
		exercise.setImage1(bytes1);
		exercise.setImage2(bytes2);
		exercise.setImage3(bytes3);
		exerciseService.save(exercise);

		answer = new Answer(answers[5]);
		exerciseService.save(new Exercise(2, statements[5], null, answer, lesson));

		/*
		 * myTexts = Arrays.asList(texts[32], texts[33], texts[34], texts[35],
		 * texts[36], texts[37], texts[38], texts[39]); exerciseRepository.save(new
		 * Exercise(3, statements[9], null, myTexts, null, lesson));
		 * 
		 * myTexts = Arrays.asList(texts[40], texts[41], texts[42], texts[43], 
		 * texts[44], texts[45], texts[46], texts[47]); exerciseRepository.save(new
		 * Exercise(4, statements[10], null, myTexts, null, lesson));
		 */

		myTexts = Arrays.asList(texts[12], texts[13], texts[14]);
		answer = new Answer(answers[6]);
		exerciseService.save(new Exercise(5, statements[6], myTexts, answer, lesson));

		/*
		 * myTexts = Arrays.asList(texts[51], texts[52], texts[53], texts[54]); answer =
		 * new Answer(answers[8]); exerciseRepository.save(new Exercise(6,
		 * statements[12], null, myTexts, answer, lesson));
		 */

		myTexts = Arrays.asList(texts[15], texts[16], texts[17]);
		answer = new Answer(answers[7]);
		exerciseService.save(new Exercise(7, statements[7], myTexts, answer, lesson));

		lesson = lesson3;
		myTexts = Arrays.asList(texts[18], texts[19], texts[20]);
		//myImages = Arrays.asList(images[6], images[7], images[8]);
		answer = new Answer(answers[8]);
		//exerciseService.save(new Exercise(1, statements[8], myImages, myTexts, answer, lesson));
		bytes1 = imageService.uploadImage(images[6]);
		bytes2 = imageService.uploadImage(images[7]);
		bytes3 = imageService.uploadImage(images[8]);
		exercise=new Exercise(1, statements[8], myTexts, answer, lesson);
		exercise.setImage1(bytes1);
		exercise.setImage2(bytes2);
		exercise.setImage3(bytes3);
		exerciseService.save(exercise);
		

		answer = new Answer(answers[9]);
		exerciseService.save(new Exercise(2, statements[9], null, answer, lesson));

		/*
		 * myTexts = Arrays.asList(texts[61], texts[62], texts[63], texts[64],
		 * texts[65], texts[66], texts[67], texts[68]); exerciseRepository.save(new
		 * Exercise(3, statements[16], null, myTexts, null, lesson));
		 * 
		 * myTexts = Arrays.asList(texts[69], texts[70], texts[71], texts[72],
		 * texts[73], texts[74], texts[75], texts[76]); exerciseRepository.save(new
		 * Exercise(4, statements[17], null, myTexts, null, lesson));
		 */

		myTexts = Arrays.asList(texts[21], texts[22], texts[23]);
		answer = new Answer(answers[10]);
		exerciseService.save(new Exercise(5, statements[10], myTexts, answer, lesson));

		/*
		 * myTexts = Arrays.asList(texts[80], texts[81], texts[82], texts[83]); answer =
		 * new Answer(answers[13]); exerciseRepository.save(new Exercise(6,
		 * statements[19], null, myTexts, answer, lesson));
		 */

		myTexts = Arrays.asList(texts[24], texts[25], texts[26]);  
		answer = new Answer(answers[11]);
		exerciseService.save(new Exercise(7, statements[11], myTexts, answer, lesson));

		return "adminHome";
	}

	@PostConstruct
	public void init() throws IOException  {


		Unit unit;
		unit = new Unit("Unidad 1");
		unitService.save(unit);
		unit = new Unit("Unidad 2");
		unitService.save(unit);

		unit = unitService.findById(1);
		Lesson lesson1 = new Lesson("Lección 1 Unidad 1", unit);
		lessonService.save(lesson1);
		Lesson lesson2 = new Lesson("Lección 2 Unidad 1", unit);
		lessonService.save(lesson2);
		Lesson lesson3 = new Lesson("Lección 3 Unidad 1", unit);
		lessonService.save(lesson3);

		unit = unitService.findById(2);
		Lesson lesson4 = new Lesson("Lección 1 Unidad 2", unit);
		lessonService.save(lesson4);
		Lesson lesson5 = new Lesson("Lección 2 Unidad 2", unit);
		lessonService.save(lesson5);
		Lesson lesson6 = new Lesson("Lección 3 Unidad 2", unit);
		lessonService.save(lesson6);

		// A la hora de guardar los ejercicios hay que tener en cuenta que hay que saber
		// la id de la leccion a la que quiere introducirselo habria que guardar 1
		// leccion y despues los 7 ejercicios para asi saber en cual lo metemos
		// (Haciendo una consulta para calcular la ultima id de lecciones o algo asi
		
		//Unit 1 Lesson 1
		Lesson lesson = lessonService.findById(1);
		
		//Exercise 1
		Answer answer = new Answer("uno");
		List<String> texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
		exercise = new Exercise(1, "1.1.1 Seleccione el asiento", texts, answer, lesson);
		// Save the images in the database
		imageService.saveImages(exercise, Paths.get(".//src//main//resources//static/img/machine.jpg"),
				Paths.get(".//src//main//resources//static/img/land.jpg"),
				Paths.get(".//src//main//resources//static/img/truck.jpg"));
		
		//Exercise 2
		answer = new Answer("Este es un texto de prueba en el que comprobarlo");
		exerciseService.save(new Exercise(2, "1.1.2 Escribe la denominación de la cuenta que recoge: "
				+ "maquinarias para el proceso productivo de la empresa", null, answer, lesson));

		/*
		 * answer = new Answer("3"); texts = Arrays.asList("213.Pepe",
		 * "210.Terrenos y bienes naturales", "218. Elementos de transporte",
		 * "206. Aplicaciones informáticas", "213. Maquinaria", "100. Capital social",
		 * "Pasivo exigible", "300. Mercaderías A", "Patrimonio neto",
		 * "Inmovilizado material", "Existencias", "Inmovilizado intangible");
		 * exerciseRepository.save(new Exercise(3, "1.1.3 Toca los pares", null, texts,
		 * answer, lesson));
		 * 
		 * answer = new Answer("4"); texts = Arrays.asList("213.Hola",
		 * "210.Terrenos y bienes naturales", "218. Elementos de transporte",
		 * "206. Aplicaciones informáticas", "213. Maquinaria", "100. Capital social",
		 * "Pasivo exigible", "300. Mercaderías A", "Patrimonio neto",
		 * "Inmovilizado material", "Existencias", "Inmovilizado intangible");
		 * exerciseRepository.save(new Exercise(4, "1.1.4 Realiza el asiento", null,
		 * texts, answer, lesson));
		 */

		//Exercise 5
		answer = new Answer("tres");
		texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
		exerciseService.save(
				new Exercise(5, "1.1.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
						 texts, answer, lesson));

		/*
		 * answer = new Answer("6"); texts =
		 * Arrays.asList("574. Bancos cuenta de ahorro", "574. Bancos cuenta corriente",
		 * "430. Clientes", "140. Deudores"); exerciseRepository.save(new Exercise(6,
		 * "1.1.6 Escoge la cuenta que falta: La empresa saca 5 de la cuenta corriente bancaria y lo ingresa en caja"
		 * , null, texts, answer, lesson));
		 */

		////Exercise 7
		answer = new Answer("dos");
		texts = Arrays.asList(
				" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
				"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
				" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
		exerciseService.save(new Exercise(7,
				"1.1.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
				 texts, answer, lesson));

		//Unit 1 Lesson 2
		lesson = lessonService.findById(2);
		
		//Exercise 1
		answer = new Answer("uno");
		texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
		exercise = new Exercise(1, "1.1.1 Seleccione el asiento", texts, answer, lesson);
		//Save the images in the database
		imageService.saveImages(exercise, Paths.get(".//src//main//resources//static/img/machine.jpg"),
				Paths.get(".//src//main//resources//static/img/land.jpg"),
				Paths.get(".//src//main//resources//static/img/truck.jpg"));
		
		//Exercixe 2
		answer = new Answer("Este es un texto de prueba en el que comprobarlo");
		exerciseService.save(new Exercise(2, "1.2.2 Escribe la denominación de la cuenta que recoge: "
				+ "maquinarias para el proceso productivo de la empresa", null, answer, lesson));

		/*
		 * answer = new Answer("10"); texts = Arrays.asList("213.Pepe",
		 * "210.Terrenos y bienes naturales", "218. Elementos de transporte",
		 * "206. Aplicaciones informáticas", "213. Maquinaria", "100. Capital social",
		 * "Pasivo exigible", "300. Mercaderías A", "Patrimonio neto",
		 * "Inmovilizado material", "Existencias", "Inmovilizado intangible");
		 * exerciseRepository.save(new Exercise(3, "1.2.3 Toca los pares", null, texts,
		 * answer, lesson));
		 * 
		 * answer = new Answer("11"); texts = Arrays.asList("213.Hola",
		 * "210.Terrenos y bienes naturales", "218. Elementos de transporte",
		 * "206. Aplicaciones informáticas", "213. Maquinaria", "100. Capital social",
		 * "Pasivo exigible", "300. Mercaderías A", "Patrimonio neto",
		 * "Inmovilizado material", "Existencias", "Inmovilizado intangible");
		 * exerciseRepository.save(new Exercise(4, "1.2.4 Realiza el asiento", null,
		 * texts, answer, lesson));
		 */

		//Exercise 5
		answer = new Answer("uno");
		texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
		exerciseService.save(
				new Exercise(5, "1.2.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
						 texts, answer, lesson));

		/*
		 * answer = new Answer("13"); texts =
		 * Arrays.asList("574. Bancos cuenta de ahorro", "574. Bancos cuenta corriente",
		 * "430. Clientes", "140. Deudores"); exerciseRepository.save(new Exercise(6,
		 * "1.2.6 Escoge la cuenta que falta: La empresa saca 5 de la cuenta corriente bancaria y lo ingresa en caja"
		 * , null, texts, answer, lesson));
		 */

		////Exercise 7
		answer = new Answer("uno");
		texts = Arrays.asList(
				" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
				"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
				" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
		exerciseService.save(new Exercise(7,
				"1.2.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
				 texts, answer, lesson));

		//Unit 1 Lesson 3
		lesson = lessonService.findById(3);
		
		//Exercise 1
		answer = new Answer("uno");
		texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
		exercise = new Exercise(1, "1.3.1 Seleccione el asiento",  texts, answer, lesson);
		//Save the images in the database
		imageService.saveImages(exercise, Paths.get(".//src//main//resources//static/img/machine.jpg"),
				Paths.get(".//src//main//resources//static/img/land.jpg"),
				Paths.get(".//src//main//resources//static/img/truck.jpg"));
				
		//Exercise 2
		answer = new Answer("Este es un texto de prueba en el que comprobarlo");
		exerciseService.save(new Exercise(2, "1.3.2 Escribe la denominación de la cuenta que recoge: "
				+ "maquinarias para el proceso productivo de la empresa", null, answer, lesson));

		/*
		 * answer = new Answer("17"); texts = Arrays.asList("213.Pepe",
		 * "210.Terrenos y bienes naturales", "218. Elementos de transporte",
		 * "206. Aplicaciones informáticas", "213. Maquinaria", "100. Capital social",
		 * "Pasivo exigible", "300. Mercaderías A", "Patrimonio neto",
		 * "Inmovilizado material", "Existencias", "Inmovilizado intangible");
		 * exerciseRepository.save(new Exercise(3, "1.3.3 Toca los pares", null, texts,
		 * answer, lesson));
		 * 
		 * answer = new Answer("18"); texts = Arrays.asList("213.Hola",
		 * "210.Terrenos y bienes naturales", "218. Elementos de transporte",
		 * "206. Aplicaciones informáticas", "213. Maquinaria", "100. Capital social",
		 * "Pasivo exigible", "300. Mercaderías A", "Patrimonio neto",
		 * "Inmovilizado material", "Existencias", "Inmovilizado intangible");
		 * exerciseRepository.save(new Exercise(4, "1.3.4 Realiza el asiento", null,
		 * texts, answer, lesson));
		 */

		//Exercise 5
		answer = new Answer("uno");
		texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
		exerciseService.save(
				new Exercise(5, "1.3.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
						 texts, answer, lesson));

		/*
		 * answer = new Answer("20"); texts =
		 * Arrays.asList("574. Bancos cuenta de ahorro", "574. Bancos cuenta corriente",
		 * "430. Clientes", "140. Deudores"); exerciseRepository.save(new Exercise(6,
		 * "1.3.6 Escoge la cuenta que falta: La empresa saca 5 de la cuenta corriente bancaria y lo ingresa en caja"
		 * , null, texts, answer, lesson));
		 */

		//Exercise 7
		answer = new Answer("uno");
		texts = Arrays.asList(
				" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
				"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
				" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
		exerciseService.save(new Exercise(7,
				"1.3.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
				 texts, answer, lesson));

		//Unit 2 Lesson 1
		lesson = lessonService.findById(4);
		
		//Exercise 1
		answer = new Answer("uno");
		texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
		exercise =new Exercise(1, "2.1.1 Seleccione el asiento", texts, answer, lesson);
		//Save the images in the database
		imageService.saveImages(exercise, Paths.get(".//src//main//resources//static/img/machine.jpg"),
				Paths.get(".//src//main//resources//static/img/land.jpg"),
				Paths.get(".//src//main//resources//static/img/truck.jpg"));
		
		//Exercise 2
		answer = new Answer("Este es un texto de prueba en el que comprobarlo");
		exerciseService.save(new Exercise(2, "2.1.2 Escribe la denominación de la cuenta que recoge: "
				+ "maquinarias para el proceso productivo de la empresa", null, answer, lesson));

		/*
		 * texts = Arrays.asList("213.Pepe", "210.Terrenos y bienes naturales",
		 * "218. Elementos de transporte", "206. Aplicaciones informáticas",
		 * "213. Maquinaria", "100. Capital social", "Pasivo exigible",
		 * "300. Mercaderías A", "Patrimonio neto", "Inmovilizado material",
		 * "Existencias", "Inmovilizado intangible"); exerciseRepository.save(new
		 * Exercise(3, "2.1.3 Toca los pares", null, texts, null, lesson));
		 * 
		 * texts = Arrays.asList("213.Hola", "210.Terrenos y bienes naturales",
		 * "218. Elementos de transporte", "206. Aplicaciones informáticas",
		 * "213. Maquinaria", "100. Capital social", "Pasivo exigible",
		 * "300. Mercaderías A", "Patrimonio neto", "Inmovilizado material",
		 * "Existencias", "Inmovilizado intangible"); exerciseRepository.save(new
		 * Exercise(4, "2.1.4 Realiza el asiento", null, texts, null, lesson));
		 */

		//Exercise 5
		answer = new Answer("uno");
		texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
		exerciseService.save(
				new Exercise(5, "2.1.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
						 texts, answer, lesson));

		/*
		 * texts = Arrays.asList("574. Bancos cuenta de ahorro",
		 * "574. Bancos cuenta corriente", "430. Clientes", "140. Deudores");
		 * exerciseRepository.save(new Exercise(6,
		 * "2.1.6 Escoge la cuenta que falta: La empresa saca 5 de la cuenta corriente bancaria y lo ingresa en caja"
		 * , null, texts, null, lesson));
		 */

		//Exercise 7
		answer = new Answer("uno");
		texts = Arrays.asList(
				" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
				"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
				" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
		exerciseService.save(new Exercise(7,
				"2.1.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
				 texts, answer, lesson));

		//Unit 2 Lesson 2
		lesson = lessonService.findById(5);
		
		//Exercise 1
		answer = new Answer("uno");
		texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
		exercise =new Exercise(1, "2.2.1 Seleccione el asiento", texts, answer, lesson);
		//Save the images in the database
		imageService.saveImages(exercise, Paths.get(".//src//main//resources//static/img/machine.jpg"),
				Paths.get(".//src//main//resources//static/img/land.jpg"),
				Paths.get(".//src//main//resources//static/img/truck.jpg"));

		//Exercise 2
		answer = new Answer("Este es un texto de prueba en el que comprobarlo");
		exerciseService.save(new Exercise(2, "2.2.2 Escribe la denominación de la cuenta que recoge: "
				+ "maquinarias para el proceso productivo de la empresa", null, answer, lesson));

		/*
		 * texts = Arrays.asList("213.Pepe", "210.Terrenos y bienes naturales",
		 * "218. Elementos de transporte", "206. Aplicaciones informáticas",
		 * "213. Maquinaria", "100. Capital social", "Pasivo exigible",
		 * "300. Mercaderías A", "Patrimonio neto", "Inmovilizado material",
		 * "Existencias", "Inmovilizado intangible"); exerciseRepository.save(new
		 * Exercise(3, "2.2.3 Toca los pares", null, texts, null, lesson));
		 * 
		 * texts = Arrays.asList("213.Hola", "210.Terrenos y bienes naturales",
		 * "218. Elementos de transporte", "206. Aplicaciones informáticas",
		 * "213. Maquinaria", "100. Capital social", "Pasivo exigible",
		 * "300. Mercaderías A", "Patrimonio neto", "Inmovilizado material",
		 * "Existencias", "Inmovilizado intangible"); exerciseRepository.save(new
		 * Exercise(4, "2.2.4 Realiza el asiento", null, texts, null, lesson));
		 */

		//Exercise 5
		answer = new Answer("uno");
		texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
		exerciseService.save(
				new Exercise(5, "2.2.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
						 texts, answer, lesson));

		/*
		 * texts = Arrays.asList("574. Bancos cuenta de ahorro",
		 * "574. Bancos cuenta corriente", "430. Clientes", "140. Deudores");
		 * exerciseRepository.save(new Exercise(6,
		 * "2.2.6 Escoge la cuenta que falta: La empresa saca 5 de la cuenta corriente bancaria y lo ingresa en caja"
		 * , null, texts, null, lesson));
		 */

		//Exercise 7
		answer = new Answer("uno");
		texts = Arrays.asList(
				" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
				"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
				" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
		exerciseService.save(new Exercise(7,
				"2.2.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
				 texts, answer, lesson));

		//Unit 2 Lesson 3
		lesson = lessonService.findById(6);
		
		//Exercise 1
		answer = new Answer("uno");
		texts = Arrays.asList("213.Maquinaria", "210.Terrenos y bienes naturales", "218. Elementos de transporte");
		exercise = new Exercise(1, "2.3.1 Seleccione el asiento", texts, answer, lesson);
		// Save the images in the database
		imageService.saveImages(exercise, Paths.get(".//src//main//resources//static/img/machine.jpg"),
				Paths.get(".//src//main//resources//static/img/land.jpg"),
				Paths.get(".//src//main//resources//static/img/truck.jpg"));
		
		//Exercise 2
		answer = new Answer("Este es un texto de prueba en el que comprobarlo");
		exerciseService.save(new Exercise(2, "2.3.2 Escribe la denominación de la cuenta que recoge: "
				+ "maquinarias para el proceso productivo de la empresa", null, answer, lesson));

		/*
		 * texts = Arrays.asList("213.Pepe", "210.Terrenos y bienes naturales",
		 * "218. Elementos de transporte", "206. Aplicaciones informáticas",
		 * "213. Maquinaria", "100. Capital social", "Pasivo exigible",
		 * "300. Mercaderías A", "Patrimonio neto", "Inmovilizado material",
		 * "Existencias", "Inmovilizado intangible"); exerciseRepository.save(new
		 * Exercise(3, "2.3.3 Toca los pares", null, texts, null, lesson));
		 * 
		 * texts = Arrays.asList("213.Hola", "210.Terrenos y bienes naturales",
		 * "218. Elementos de transporte", "206. Aplicaciones informáticas",
		 * "213. Maquinaria", "100. Capital social", "Pasivo exigible",
		 * "300. Mercaderías A", "Patrimonio neto", "Inmovilizado material",
		 * "Existencias", "Inmovilizado intangible"); exerciseRepository.save(new
		 * Exercise(4, "2.3.4 Realiza el asiento", null, texts, null, lesson));
		 */

		//Exercise 5
		answer = new Answer("uno");
		texts = Arrays.asList("Activo", "Pasivo", "Patrimonio neto");
		exerciseService.save(
				new Exercise(5, "2.3.5 Escoge la respuesta correcta para la cuenta: 210. Terrenos y bienes naturales",
						 texts, answer, lesson));

		/*
		 * texts = Arrays.asList("574. Bancos cuenta de ahorro",
		 * "574. Bancos cuenta corriente", "430. Clientes", "140. Deudores");
		 * exerciseRepository.save(new Exercise(6,
		 * "2.3.6 Escoge la cuenta que falta: La empresa saca 5 de la cuenta corriente bancaria y lo ingresa en caja"
		 * , null, texts, null, lesson));
		 */

		//Exercise 7
		answer = new Answer("uno");
		texts = Arrays.asList(
				" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años a través de la letra de cambio.",
				"La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.",
				" La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará en un plazo no superior a un año.");
		exerciseService.save(new Exercise(7,
				"2.3.7 Escoge el enunciado correcto para el asiento: \"10 211. Construcciones a 174. Provedores de inmovilizado a l/p 10\"",
			  texts, answer, lesson));

	}
}
