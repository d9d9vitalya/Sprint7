package courier;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegistrationCourierTest.class,
        RegistrationCourierWrongTest.class,
        RegistrationNoLoginNoPasswordCourierTest.class,
        LoginCourierTest.class,
        LoginNoLoginCourierTest.class,
        LoginNoPasswordCourierTest.class,
        LoginWrongLoginCourierTest.class,
        LoginWrongPasswordCourierTest.class,
        CreateOrderZeroColorTest.class,
        CreateOrderOneColorTest.class,
        CreateOrderTwoColorTest.class,
        CheckOrderTest.class,
        AcceptOrderCourierTest.class,
        DeleteCourierTest.class,

})
public class TestSuite {
    // Пустой класс, нужен только для организации тестов

}


