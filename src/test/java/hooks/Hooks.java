package hooks;

import com.automation.ecommerce.base.DriverFactory;
import com.automation.ecommerce.config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends ConfigReader {

        @Before
        public void setUp() {
            DriverFactory.initDriver("chrome");
            ConfigReader.getProperty("base.url");
        }

        @After
        public void tearDown() {
            DriverFactory.quitDriver();
        }

}
