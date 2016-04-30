import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PageObject {
	  static FirefoxDriver wd;    
	
	public PageObject(FirefoxDriver wd){
		this.wd = wd;		
	}
	
	//Accedemos a la URL de la p�gina
	public void open(){		
		 wd.get("http://demo.opencart.com/");
	}

	//Funci�n que busca el producto por nombre.
	public static void SearchByName(String producto) {
		wd.findElement(By.name("search")).sendKeys(producto);      
    }
	
	//Funci�n que busca por ID.
	public void SearchById() {
	   wd.findElement(By.id("grid-view")).click();;      
    }
	
	//Funci�n que busca el producto por xpath. 
	public void SearchByXpathElement() {
	    wd.findElement(By.xpath("//*/div[4]/div/div/div[2]/h4/a")).click();      
    }
	
	//Funci�n que b�sca el primer el elemento a trav�s del xpath
	public String SearchByXpathElementFirst() {
	   return  wd.findElement(By.xpath("//*/div[4]/div/div/div[2]/h4/a")).getText();      
    }
	
	//Funci�n que busca el botton de buscar y hace click en el.
	public void SearchByXpathButton() {
	    wd.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();;      
    }
	
	//Funci�n que busca el xpath del titulo del producto
	public String SearchByXpathTitle() { 
	    return wd.findElement(By.xpath("//*[@id='content']/div/div[2]/h1")).getText();      
    }
}
