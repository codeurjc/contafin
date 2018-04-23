package CONTaFin;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class adminTest {
	public void navegarTest() {
		String exePath = "C:\\selenium\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		String url = "https://localhost:8080/new";
		driver.get(url);
		System.out.println("Entra en la pagina");
		WebElement button = driver.findElement(By.id("dropdownBasic1"));
		button.click();
		button = driver.findElement(By.name("email"));
		button.sendKeys("adminemail@hotmail.es");
		button = driver.findElement(By.name("pass"));
		button.sendKeys("adminpass");
		button = driver.findElement(By.xpath("//input[@value='Iniciar Sesión']"));
		button.click();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		List<WebElement> linkElements = driver.findElements(By.tagName("a"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("ADMINISTRACIÓN")) {
				e.click();
				break;
			}
		}
		System.out.println("Prueba");
		esperarSegundos(driver, 2);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		linkElements = driver.findElements(By.id("add-users"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("SUBIR CONTENIDO")) {
				e.click();
				break;
			}
		}
		esperarSegundos(driver, 2);
		
		
		
		System.out.println("Entra en usuario");
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		button = driver.findElement(By.id("Unit"));
		button.click();
		System.out.println("Entra en las lecciones");
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		button = driver.findElement(By.cssSelector("a[href*='/new/Unit/1/Lessons/1/Exercise']"));
		button.click();
		System.out.println("Entra en las unidades");
		// Ejer1
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		button = driver.findElement(By.id("selectLesson"));
		button.click();
		System.out.println("Entra en los ejercicios");
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		// Bucle Calificar/Siguiente
		linkElements = driver.findElements(By.tagName("button"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("CALIFICAR")) {
				e.click();
			}
		}
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		linkElements = driver.findElements(By.tagName("button"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("SIGUIENTE")) {
				e.click();
			}
		}
		// Fin Bucle
		// Ejer 2

		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		button = driver.findElement(By.id("exercise2"));
		button.click();
		button.sendKeys("Este es un texto de prueba en el que comprobarlo");
		System.out.println("Entra en los ejercicios");
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		// Bucle Calificar/Siguiente
		linkElements = driver.findElements(By.tagName("button"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("CALIFICAR")) {
				e.click();
			}
		}

		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		linkElements = driver.findElements(By.tagName("button"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("SIGUIENTE")) {
				e.click();
			}
		}
		// Fin Bucle
		// Ejer 3

		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		linkElements = driver.findElements(By.id("exercise1"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("Patrimonio neto")) {
				e.click();
			}
		}
		System.out.println("Entra en los ejercicios");
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		// Bucle Calificar/Siguiente
		linkElements = driver.findElements(By.tagName("button"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("CALIFICAR")) {
				e.click();
			}
		}

		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		linkElements = driver.findElements(By.tagName("button"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("SIGUIENTE")) {
				e.click();
			}
		}
		// Fin Bucle
		// Ejer 4

		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		linkElements = driver.findElements(By.tagName("h5"));
		String respuesta = "La empresa compra un local por 10, dejándolo a deber a su provedor, al que pagará integramente dentro de 10 años.";
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals(respuesta)) {
				e.click();
			}
		}
		System.out.println("Entra en los ejercicios");
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		// Bucle Calificar/Siguiente
		linkElements = driver.findElements(By.tagName("button"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("CALIFICAR")) {
				e.click();
			}
		}

	    esperarSegundos(driver, 2);
		linkElements = driver.findElements(By.tagName("button"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("TERMINAR")) {
				e.click();
			}
		}
		esperarSegundos(driver, 2);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		// Fin Bucle
		//Leccion Completada
		linkElements = driver.findElements(By.tagName("a"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("CONTINUAR")) {
				e.click();
			}
		}
		esperarSegundos(driver, 2);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		linkElements = driver.findElements(By.tagName("a"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("CONTINUAR")) {
				e.click();
			}
		}
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	}
	public void esperarSegundos(WebDriver driver, int segundos){
	       
	      synchronized(driver){
	         try {
	            driver.wait(segundos * 1000);
	         } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
	   }
}
