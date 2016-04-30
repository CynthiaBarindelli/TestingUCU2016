import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
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
    public void test() {
       PageObject object = new PageObject(wd);
       object.open();
       //Busca cada producto que viene en el archivo.
       object.SearchByName(producto);
       
       //Realiza click en el boton de buscar.
       object.SearchByXpathButton();
       
       //Se desliza la pantalla.
       object.SearchById();
       
       //Assert que comprueba que el nombre del producto del primer elemento es igual al nombre del producto
       //pasado desde el archivo
       assertEquals(producto,object.SearchByXpathElementFirst());
       
       //REaliza click en el elemento
       object.SearchByXpathElement();
       
       //Assert que comprueba que el titulo del producto que clickeamos coincide con el nombre del producto 
       //pasado por parametro.
       assertEquals(producto, object.SearchByXpathTitle());
       
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
	public static Collection<String[]> leerProductos(String fileName)throws IOException	{
		
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
