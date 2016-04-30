import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import org.junit.runners.Parameterized;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

@RunWith(Parameterized.class)
public class ProductosTest{
    FirefoxDriver wd;    
    private String producto;
    
    public ProductosTest(String producto){
    	this.producto = producto;
    }
    
    @Before
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
     
    @Parameters(name = "{index}: producto")
    public static Iterable<Object[]> data1() {
		return Arrays.asList(new Object[][] { 
			{ "Mac"}, 
			{ "Iphone"}}
			);
	}

	@Test
    public void test() {
    
        wd.get("http://demo.opencart.com/");
        wd.findElement(By.name("search")).click();
        wd.findElement(By.name("search")).clear();
        wd.findElement(By.name("search")).sendKeys(producto);
        wd.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
        wd.findElement(By.id("grid-view")).click();
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
