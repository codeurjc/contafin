package CONTaFin;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class anonimoTest {
	public void navegarTest() {
		String exePath = "C:\\selenium\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		String url = "https://localhost:8080/new";
		driver.get(url);
		System.out.println("Entra en la pagina");
		WebElement button = driver.findElement(By.id("button-style"));
		button.click();
		System.out.println("Entra en usuario anonimo");
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
		List<WebElement> linkElements = driver.findElements(By.tagName("button"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("CALIFICAR")) {
				e.click();
			}
		}
		linkElements = driver.findElements(By.tagName("button"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("SIGUIENTE")) {
				e.click();
			}
		}
		// Fin Bucle
		// Ejer 2
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
		linkElements = driver.findElements(By.tagName("button"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("SIGUIENTE")) {
				e.click();
			}
		}
		// Fin Bucle
		// Ejer 3
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
		linkElements = driver.findElements(By.tagName("button"));
		for (WebElement e : linkElements) {
			System.out.println(e.getText());
			if (e.getText().equals("SIGUIENTE")) {
				e.click();
			}
		}
		// Fin Bucle
		// Ejer 4
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
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		// Fin Bucle
		//Leccion Completada
		linkElements = driver.findElements(By.tagName("button"));
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
