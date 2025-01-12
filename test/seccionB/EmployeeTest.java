package seccionB;
 
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class EmployeeTest {
    private static final float RMU = 386.0F;
    private static final String USD = "USD";
    private static final String EUR = "EUR";
    
    private int salary;
    private String currency;
    private float bonusPercentage;
    private LocalDate localDate;

    @BeforeEach
    void setUp() {
        salary = 400;
        currency = USD;
        bonusPercentage = 50.0F;
        localDate = LocalDate.now();
    }

    // Métodos auxiliares
    private boolean isMonthPar() {
        return localDate.getMonthValue() % 2 == 0;
    }

    private float calculateExpectedSalary(EmployeeType employee) {
        // Lógica de cálculo común de salario esperado
        float expectedSalary = salary;
        if (!isMonthPar()) {
            expectedSalary += RMU / 12 * 2;
        }
        if (employee== EmployeeType.Supervisor) {
            expectedSalary += bonusPercentage * 0.35F;
        } else if (employee == EmployeeType.Manager) {
            expectedSalary += bonusPercentage * 0.7F;
        }
        if (!currency.equals("USD")) {
            expectedSalary *= 0.95F; // Suponiendo un ajuste para EUR
        }
        return expectedSalary;
    }

    // Pruebas para `Worker`
    @Test
    void testCsWorkerUSD() {
        assumeTrue(!isMonthPar());
        Employee employee = new Employee(salary, currency, 0.0F, EmployeeType.Worker);
        float expectedSalary = calculateExpectedSalary(EmployeeType.Worker);
        assertEquals(expectedSalary, employee.cs(), 0.01);
    }

    // Pruebas para `Supervisor`
    @Test
    void testCsSupervisorUSD() {
        assumeTrue(!isMonthPar());
        Employee employee = new Employee(salary, currency, bonusPercentage, EmployeeType.Supervisor);
        float expectedSalary = calculateExpectedSalary(EmployeeType.Supervisor);
        assertEquals(expectedSalary, employee.cs(), 0.01);
    }

    // Pruebas para `Manager`
    @Test
    void testCsManagerUSD() {
        assumeTrue(!isMonthPar());
        Employee employee = new Employee(salary, currency, bonusPercentage, EmployeeType.Manager);
        float expectedSalary = calculateExpectedSalary(EmployeeType.Manager);
        assertEquals(expectedSalary, employee.cs(), 0.01);
    }

    // Validación con divisa EUR
    @Test
    void testCsCurrencyEUR() {
        currency = EUR;
        assumeTrue(!isMonthPar());
        Employee employee = new Employee(salary, currency, bonusPercentage, EmployeeType.Supervisor);
        float expectedSalary = calculateExpectedSalary(EmployeeType.Supervisor);
        assertEquals(expectedSalary, employee.cs(), 0.01);
    }

    // Comprobación del cálculo de bonos
    @Test
    void testCalculateYearBonus() {
        Employee employee = new Employee(1000.0F, USD, 200.0F, EmployeeType.Worker);
        float expectedBonus = 200.0F; 
        assertEquals(expectedBonus, employee.CalculateYearBonus(), 0.01);
    }

    // Prueba con salario cero
    @Test
    void testCalculateYearBonus_SalaryZero() {
        Employee employee = new Employee(0.0F, USD, 200.0F, EmployeeType.Manager);
        float expectedBonus = 200.0F;
        assertEquals(expectedBonus, employee.CalculateYearBonus(), 0.01);
    }
}