import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.runners.Parameterized;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

@RunWith(Parameterized.class)
public class Desafio4Test{
    FirefoxDriver wd;    
    private String producto;
     
    public Desafio4Test(String producto){
    	this.producto = producto;
    }
    @Before
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Parameters(name = "{index}: producto")  
    public static Collection testData() throws IOException {
    	return leerProductos("/Users/Cynthia/workspace/Desafio3_Selenium/Productos.csv");
    }
	
    @Test
    public void test()throws InterruptedException {
      
    	wd.get("http://demo.opencart.com/");
       
       //Buscar el producto el cual viene dado desde el archivo
       wd.findElement(By.name("search")).sendKeys(producto);
       
       //Click en el boton de buscar
       wd.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
       
       //Se desliza la pantalla.
       wd.findElement(By.id("grid-view")).click();       
       
       //Assert que comprueba que el nombre del producto del primer elemento es igual al nombre del producto pasado 
       //desde el archivo
       assertEquals(producto.trim(), wd.findElement(By.xpath("//*/div[4]/div/div/div[2]/h4/a")).getText());
       
       //Click en el elemento
       wd.findElement(By.xpath("//*/div[4]/div/div/div[2]/h4/a")).click();
       
       //Assert que comprueba que el titulo del producto que clickeamos coincide con el nombre del producto
       //pasado por parametro.
       assertEquals(producto.trim(), wd.findElement(By.xpath("//*[@id='content']/div/div[2]/h1")).getText());
    }  
  
 
    @After
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    //Lee los productos desde un archivo pasado por parámetro.	
	public static Collection<String[]> leerProductos(String fileName)throws IOException {
	
		List<String[]> datos = new ArrayList<String[]>();
		String producto;
		BufferedReader archivo = new BufferedReader(new FileReader(fileName));
		while ((producto = archivo.readLine()) != null ) {
			producto = producto.trim();
			if (producto.length() != 0){
				String carga[] = producto.split("\n");
				datos.add(carga);
			}
		}
		archivo.close();
		return datos;
	}

}
