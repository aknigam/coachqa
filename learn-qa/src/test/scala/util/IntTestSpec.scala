package util

import java.io.File

import org.apache.log4j.Logger
import org.springframework.context.{ApplicationContext, ApplicationContextAware}
import org.springframework.test.context.support.{DependencyInjectionTestExecutionListener, DirtiesContextTestExecutionListener}
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration, TestContextManager, TestExecutionListeners}

@ContextConfiguration(Array("classpath:intTest.applicationContext.xml"))
@ActiveProfiles(Array("intTest"))
@TestExecutionListeners(Array(classOf[DependencyInjectionTestExecutionListener], classOf[DirtiesContextTestExecutionListener] ))
  class IntTestSpec extends FeatureSpec with GivenWhenThen with Matchers with ApplicationContextAware {
  //com.sun.management.HotSpotDiagnosticMXBean#setVMOption(String, String)
  var applicationContext: ApplicationContext = null
  val logger: Logger = Logger.getLogger(this.getClass())

  setProperties

  new TestContextManager(this.getClass()).prepareTestInstance(this)

  def setApplicationContext (appContext: ApplicationContext) = {applicationContext = appContext}
  def getApplicationContext = applicationContext

  private def setProperties={

    val dir = getBaseFolder
    val catalinaBase = new File(dir, "build").getAbsolutePath()
    prepareProperty("catalina.base", catalinaBase)
  }

  def prepareProperty (propertyName: String, defaultValue: String) = {

    val value = System.getProperty(propertyName)
    if (value == null) {

      System.setProperty(propertyName, defaultValue)

      logger.info("Current " + propertyName + " is: " + defaultValue)

    }
    else
    {
      logger.info("Current " + propertyName + " is: " + value)
    }
  }

  protected def getBaseFolder: File = {
    var dir = new File("").getAbsoluteFile()

    if (dir.getName().endsWith("service")) {
      dir = dir.getParentFile();
    }
    dir
  }
}

