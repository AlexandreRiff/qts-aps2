package testfrontend;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

@TestMethodOrder(OrderAnnotation.class)
public class SeleniumTest {

	static String USER_HOME = System.getProperty("user.home");
	static String USER_DIR = System.getProperty("user.dir");

	static String PATH_CHROME_DRIVER = String.join(File.separator, USER_HOME, "Desktop", "chromedriver_win32",
			"chromedriver.exe");

	static String PATH_PAGINA = String.join(File.separator, USER_DIR, "src", "main", "resources", "aps_2.html");

	static WebDriver driver = null;
	static WebElement nome = null;
	static WebElement endereco = null;
	static Select sexo = null;
	static WebElement idade = null;
	static WebElement btnSalvar = null;
	static WebElement resultado = null;
	static Alert alert = null;

	@BeforeAll
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", PATH_CHROME_DRIVER);

		driver = new ChromeDriver();
		driver.get(PATH_PAGINA);

		nome = driver.findElement(By.id("nome"));
		endereco = driver.findElement(By.id("endereco"));
		sexo = new Select(driver.findElement(By.id("sexo")));
		idade = driver.findElement(By.id("idade"));
		btnSalvar = driver.findElement(By.id("botao_somar"));
		resultado = driver.findElement(By.id("resultado"));

		nome.clear();
		endereco.clear();
		idade.clear();
	}

	@Test
	@Order(1)
	public void testValoresInvalidosNome() {
		btnSalvar.click();

		String resultadoExperado = "Preencha o campo nome";
		String resultadoPagina = resultado.getText();

		Assert.assertEquals(resultadoExperado, resultadoPagina);
	}

	@Test
	@Order(2)
	public void testValoresValidosNome() {
		nome.sendKeys("Slash");
		btnSalvar.click();

		String resultadoExperado = "Preencha o campo endereco";
		String resultadoPagina = resultado.getText();

		Assert.assertEquals(resultadoExperado, resultadoPagina);
	}

	@Test
	@Order(3)
	public void testValoresInvalidosEndereco() {
		btnSalvar.click();

		String resultadoExperado = "Preencha o campo endereco";
		String resultadoPagina = resultado.getText();

		Assert.assertEquals(resultadoExperado, resultadoPagina);
	}

	@Test
	@Order(4)
	public void testValoresValidosEndereco() {
		endereco.sendKeys("Rua dos Bobos, 171");
		btnSalvar.click();

		String resultadoExperado = "Selecione um valor para o campo sexo";
		String resultadoPagina = resultado.getText();

		Assert.assertEquals(resultadoExperado, resultadoPagina);
	}

	@Test
	@Order(5)
	public void testValoresInvalidosSexo() {
		btnSalvar.click();

		String resultadoExperado = "Selecione um valor para o campo sexo";
		String resultadoPagina = resultado.getText();

		Assert.assertEquals(resultadoExperado, resultadoPagina);
	}

	@Test
	@Order(6)
	public void testValoresValidosSexo() {
		sexo.selectByValue("m");
		btnSalvar.click();

		String resultadoExperado = "Preencha o campo idade, somente com numeros";
		String resultadoPagina = resultado.getText();

		Assert.assertEquals(resultadoExperado, resultadoPagina);
	}

	@Test
	@Order(7)
	public void testValoresInvalidosIdade() {
		btnSalvar.click();

		String resultadoExperado = "Preencha o campo idade, somente com numeros";
		String resultadoPagina = resultado.getText();

		Assert.assertEquals(resultadoExperado, resultadoPagina);

		idade.sendKeys("A");
		btnSalvar.click();
		Assert.assertEquals(resultadoExperado, resultadoPagina);

		idade.clear();

		idade.sendKeys("-1");
		btnSalvar.click();
		Assert.assertEquals(resultadoExperado, resultadoPagina);

	}

	@Test
	@Order(8)
	public void testValoresValidosIdade() {
		idade.clear();

		idade.sendKeys("50");
		btnSalvar.click();

		alert = driver.switchTo().alert();

		String resultadoExperado = "Cadastro realizado com sucesso";
		String resultadoPagina = alert.getText();

		Assert.assertEquals(resultadoExperado, resultadoPagina);

		alert.accept();
	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
	}

}
