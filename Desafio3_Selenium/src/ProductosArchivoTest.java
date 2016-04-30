import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class ProductosArchivoTest{
    FirefoxDriver wd;    
    
    @Before
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void test() {
      StringBuilder texto = new StringBuilder();
      wd.get("http://demo.opencart.com/");    
      wd.findElement(By.name("search")).click();
      wd.findElement(By.name("search")).clear();
      
      //Busca el vacio en el buscador de productos ya que en este sistema al realizar esta acción
      //se despliegan todos los productos.
      wd.findElement(By.name("search")).sendKeys(" ");
      wd.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
      
      //Luego de tener todos los elementos, se selecciona para que se muestre una cantidad
      //de 100 productos, obteniendo de esta forma la mayor cantidad de posibles productos en la página.
      if (!wd.findElement(By.xpath("//select[@id='input-limit']//option[5]")).isSelected()) {
           wd.findElement(By.xpath("//select[@id='input-limit']//option[5]")).click();
       }
      
      //Se obtiene cada producto navegando en la pagina mediante la utlizacion de "className" de Selenium.
       List<WebElement> listaPro = wd.findElements(By.className("image"));
       for (WebElement p : listaPro){
   		  String  nomb =p.findElement(By.className("img-responsive")).getAttribute("title");
           texto.append(nomb);
           texto.append("\r\n");
           System.out.println(texto);
           escribirProductos(texto);
       }
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
    //Escribe los productos en un archivo "Productos.csv"
	public void escribirProductos(StringBuilder texto) {
		try {
			FileWriter ec = new FileWriter("Productos.csv");		
			ec.append(texto.toString()+"\n");
			ec.flush();
			ec.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
