import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.io.IOException;
import jxl.read.biff.BiffException;
import java.util.concurrent.TimeUnit;
import java.util.Collection;
import java.io.FileInputStream;
import java.io.InputStream;
import org.junit.runners.Parameterized;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

@RunWith(Parameterized.class)
public class ProductosArchivoTest{
    FirefoxDriver wd;    
    private String producto;
     
    public ProductosArchivoTest(String producto){
    	this.producto = producto;
    }
    
    @Before
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Parameters(name = "{index}: producto")  
    public static Collection spreadsheetData() throws IOException, BiffException {
    	
    	InputStream spreadsheet = new FileInputStream("/Users/Cynthia/workspace/Desafio2_Selenium/src/TestData/productos.xls");
        return new LeerExcel(spreadsheet).getData();
    }
    @Test
    public void test() {
    	
       wd.get("http://demo.opencart.com/");
       wd.findElement(By.name("search")).click();
       wd.findElement(By.name("search")).clear();
       wd.findElement(By.name("search")).sendKeys(producto);
       wd.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
       wd.findElement(By.id("grid-view")).click();
       assertEquals(producto, wd.findElement(By.xpath("//*/div[4]/div/div/div[2]/h4/a")).getText());
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

}
