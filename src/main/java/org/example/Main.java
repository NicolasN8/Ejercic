import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class MagentoPurchaseTest {

    public static void main(String[] args) {
        // Configuración del sistema para encontrar el controlador de Chrome
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        // Inicializar el navegador
        WebDriver driver = new ChromeDriver();

        try {
            // Paso 1: Abrir el navegador e ir al sitio web de Magento
            driver.get("https://magento.softwaretestingboard.com/");

            // Paso 2: Seleccionar la prenda "Radiant Tee"
            driver.findElement(By.xpath("//a[text()='Radiant Tee']")).click();

            // Paso 3: Configurar las opciones de la prenda
            Select sizeDropdown = new Select(driver.findElement(By.name("super_attribute[136]")));
            sizeDropdown.selectByVisibleText("L");

            Select colorDropdown = new Select(driver.findElement(By.name("super_attribute[93]")));
            colorDropdown.selectByVisibleText("Azul");

            WebElement quantityInput = driver.findElement(By.name("qty"));
            quantityInput.clear();
            quantityInput.sendKeys("2");

            // Paso 4: Hacer clic en "Add to Cart"
            driver.findElement(By.xpath("//span[text()='Add to Cart']")).click();

            // Paso 5: Hacer clic en el enlace "shopping cart"
            driver.findElement(By.xpath("//a[text()='shopping cart']")).click();

            // Paso 6: Hacer clic en "Proceed to Checkout"
            driver.findElement(By.xpath("//span[text()='Proceed to Checkout']")).click();

            // Paso 7: Completar el formulario de dirección de envío
            driver.findElement(By.id("billing:firstname")).sendKeys("Nombre");
            driver.findElement(By.id("billing:lastname")).sendKeys("Apellido");
            // Completa los demás campos según sea necesario

            // Paso 8: Seleccionar la primera opción en los Métodos de Envío
            driver.findElement(By.id("s_method_flatrate_flatrate")).click();

            // Paso 9: Hacer clic en "Next"
            driver.findElement(By.xpath("//span[text()='Next']")).click();

            // Paso 10: Hacer clic en "Place Order"
            driver.findElement(By.xpath("//span[text()='Place Order']")).click();

            // Validaciones
            String title = driver.findElement(By.xpath("//h1[text()='Thank you for purchase!']")).getText();
            boolean continueShoppingButtonEnabled = driver.findElement(By.xpath("//span[text()='Continue Shopping']")).isEnabled();
            boolean createAccountButtonVisible = driver.findElement(By.xpath("//span[text()='Create an Account']")).isDisplayed();
            String orderNumber = driver.findElement(By.xpath("//p[contains(text(),'Your order # is:')]")).getText().split(" ")[3];

            // Imprimir resultados de las validaciones
            System.out.println("Title: " + title);
            System.out.println("Continue Shopping Button Enabled: " + continueShoppingButtonEnabled);
            System.out.println("Create Account Button Visible: " + createAccountButtonVisible);
            System.out.println("Order Number: " + orderNumber);
        } finally {
            // Cerrar el navegador al finalizar
            driver.quit();
        }
    }
}
