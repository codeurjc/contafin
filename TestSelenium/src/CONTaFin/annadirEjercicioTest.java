package CONTaFin;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class annadirEjercicioTest {
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
		esperarSegundos(driver, 1);
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
		button = driver.findElement(By.id("unitNameForm"));
		button.sendKeys("Unidad Nueva");
		button = driver.findElement(By.id("lessonNameForm0"));
		button.sendKeys("Lección Nueva 1");
		button = driver.findElement(By.id("lessonNameForm1"));
		button.sendKeys("Lección Nueva 2");
		button = driver.findElement(By.id("lessonNameForm2"));
		button.sendKeys("Lección Nueva 3");
		linkElements = driver.findElements(By.id("statementForm0"));
		int cont = 1;
		for (WebElement e : linkElements) {
			e.sendKeys("Enunciado Nuevo " + cont);
			cont++;
		}
		linkElements = driver.findElements(By.id("textForm0"));
		for (WebElement e : linkElements) {
			e.sendKeys("Opcion 1 ");
			cont++;
		}
		linkElements = driver.findElements(By.id("textForm1"));
		for (WebElement e : linkElements) {
			e.sendKeys("Opcion 2 ");
			cont++;
		}
		linkElements = driver.findElements(By.id("textForm2"));
		for (WebElement e : linkElements) {
			e.sendKeys("Opcion 3 ");
			cont++;
		}
		linkElements = driver.findElements(By.id("answerForm0"));
		for (WebElement e : linkElements) {
			e.sendKeys("uno");
			cont++;
		}
		// Cambiar ejer
		/*driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		linkElements = driver.findElements(By.className("nav-link"));
		for (WebElement e : linkElements) {
			System.out.println(e.getAttribute("id"));
			if (e.getAttribute("id").equals("ngb-tab-1")) {
			 e.click();
			}
		}*/
		button = driver.findElement(By.id("ngb-tab-1"));
		button.click();
			
		
		// Ejer 2
		linkElements = driver.findElements(By.id("statementForm1"));
		cont = 1;
		for (WebElement e : linkElements) {
			e.sendKeys("Enunciado Nuevo " + cont);
			cont++;
		}
		linkElements = driver.findElements(By.id("answerForm1"));
		for (WebElement e : linkElements) {
			e.sendKeys("Respuesta");
			cont++;
		}
		// Cmabiar ejer
		// Ejer3
		linkElements = driver.findElements(By.id("statementForm4"));
		cont = 1;
		for (WebElement e : linkElements) {
			e.sendKeys("Enunciado Nuevo " + cont);
			cont++;
		}
		linkElements = driver.findElements(By.id("textForm19"));
		for (WebElement e : linkElements) {
			e.sendKeys("Opcion 1 ");
			cont++;
		}
		linkElements = driver.findElements(By.id("textForm20"));
		for (WebElement e : linkElements) {
			e.sendKeys("Opcion 2 ");
			cont++;
		}
		linkElements = driver.findElements(By.id("textForm21"));
		for (WebElement e : linkElements) {
			e.sendKeys("Opcion 3 ");
			cont++;
		}
		linkElements = driver.findElements(By.id("answerForm0"));
		for (WebElement e : linkElements) {
			e.sendKeys("uno");
			cont++;
		}
		// Cmabiar ejer
		// Ejer4
		linkElements = driver.findElements(By.id("statementForm6"));
		cont = 1;
		for (WebElement e : linkElements) {
			e.sendKeys("Enunciado Nuevo " + cont);
			cont++;
		}
		linkElements = driver.findElements(By.id("textForm26"));
		for (WebElement e : linkElements) {
			e.sendKeys("Respuesta 1 ");
			cont++;
		}
		linkElements = driver.findElements(By.id("textForm27"));
		for (WebElement e : linkElements) {
			e.sendKeys("Opcion 2 ");
			cont++;
		}
		linkElements = driver.findElements(By.id("textForm28"));
		for (WebElement e : linkElements) {
			e.sendKeys("Opcion 3 ");
			cont++;
		}
		linkElements = driver.findElements(By.id("answerForm4"));
		for (WebElement e : linkElements) {
			e.sendKeys("uno");
			cont++;
		}
	}

	public void esperarSegundos(WebDriver driver, int segundos) {

		synchronized (driver) {
			try {
				driver.wait(segundos * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
