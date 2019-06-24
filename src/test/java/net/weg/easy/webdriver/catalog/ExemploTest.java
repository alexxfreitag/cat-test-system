
package net.weg.easy.webdriver.catalog;

import org.openqa.selenium.Keys;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import junit.framework.TestCase;

public class ExemploTest extends TestCase {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testCarrinho() throws Exception {
		
		driver.get("http://www.gmail.com.br/");

		//insere o email
        driver.findElement(By.name("identifier")).sendKeys("testeseleniumcatolica@gmail.com" + Keys.ENTER);

        aguardar(By.name("password"), 8);

        //insere a senha
        driver.findElement(By.name("password")).sendKeys("master!@#" + Keys.ENTER);

        aguardar(By.cssSelector("div[data-tooltip='Caixa de entrada']"), 8);

		assertTrue(driver.findElement(By.cssSelector("div[data-tooltip='Caixa de entrada']")) != null 
		&& driver.findElement(By.cssSelector("div[aria-label='Principal'][role='tab']")) != null);

		//clica no botão de enviar email
		driver.findElement(By.cssSelector("div.aic div.z0 div[role='button']")).click();

		aguardar(By.cssSelector("textarea[name='to']"), 8);

		//insere o destinatário
        driver.findElement(By.cssSelector("textarea[name='to']")).sendKeys("testeseleniumcatolica@gmail.com" + Keys.ENTER);
        
        //insere o assunto
        driver.findElement(By.name("subjectbox")).sendKeys("Teste de Sistema");
        
        //insere o texto
        driver.findElement(By.cssSelector("div[aria-label='Corpo da mensagem'][role='textbox']")).sendKeys("Selenium webdrive é legal!");
        
        //clica no botão de evniar
        driver.findElement(By.cssSelector("div[role='button'][aria-label='Enviar ‪(Ctrl-Enter)‬']")).click();

		aguardar(By.cssSelector("div[role='tabpanel'] tbody>tr>td:nth-of-type(5) span[name='eu']"), 8);

		validar(1);
		
		//clica no botão de opções
		driver.findElement(By.cssSelector("div[role='tabpanel'] tbody>tr>td:nth-of-type(6)")).click();

		aguardar(By.cssSelector("div[role='button'][aria-label='Responder'][data-tooltip='Responder']"), 8);

		//seleciona a opção responder
		driver.findElement(By.cssSelector("div[role='button'][aria-label='Responder'][data-tooltip='Responder']")).click();

		aguardar(By.cssSelector("div[role='textbox'][aria-label='Corpo da mensagem']"), 8);

		//insere o texto de resposta
        driver.findElement(By.cssSelector("div[role='textbox'][aria-label='Corpo da mensagem']")).sendKeys("Ok, email lido. Obrigado.");
        
        //envia o email de resposta
        driver.findElement(By.cssSelector("div[role='button'][aria-label='Enviar ‪(Ctrl-Enter)‬']")).click();
		
        //volta na caixa de entrada
        driver.findElement(By.cssSelector("a[title='Caixa de entrada']")).click();

		aguardar(By.cssSelector("div[role='tabpanel'] tbody>tr>td:nth-of-type(5) span:nth-of-type(2)"), 8);

		validar(2);

		new Actions(driver)
				.contextClick(driver.findElement(By.cssSelector("div[role='tabpanel'] tbody>tr>td:nth-of-type(5)")))
				.perform();

		aguardar(By.cssSelector("body>div[role='menu']>div:nth-child(11)"), 8);

		driver.findElement(By.cssSelector("body>div[role='menu']>div:nth-child(11)")).click();

		Thread.sleep(450);

		//clica nas opções do email
		driver.findElement(By.cssSelector("header>div:nth-child(2)>div:nth-child(3)>div>div:nth-child(2) a")).click();

		Thread.sleep(450);

		//clica para sair
		driver.findElement(By.cssSelector("header>div:nth-child(2)>div:nth-child(5)>div:nth-child(4)>div:nth-child(2)> a")).click();

		aguardar(By.name("identifier"), 8);

		validar(3);
	}

	private void aguardar(By by, int outTime) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, outTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		Thread.sleep(450);
	}
	
	private void validar(int i) {
	
		if (i==1) assertTrue(driver.findElement(By.cssSelector("div[role='tabpanel'] tbody>tr>td:nth-of-type(5) span[name='eu']")) != null);
		else if (i==2) assertTrue(driver.findElement(By.cssSelector("div[role='tabpanel'] tbody>tr>td:nth-of-type(5) span:nth-of-type(2)")) != null);
		else if (i==3) assertTrue(driver.findElement(By.name("identifier")) != null);
	}
}