package seccionB;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Employee
{   
    private final float rmu = (float) 386.0;
    private float salary;
    private String currency;
    private float bonusPercentage;    
    private EmployeeType employeeType;    

    public Employee(float salary, String currency, 
        float bonusPercentage, EmployeeType employeeType){
        this.salary = salary;
        this.currency = currency;
        this.bonusPercentage = bonusPercentage;
        this.employeeType = employeeType;
    }
    public float cs() {
        float salario = 0;
        Date date = new Date();
        LocalDate localDate;
        localDate = date.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        if(currency == "USD"){salario = salary; }
        else{salario = (float) (salary * 0.95);}
        switch (employeeType)         
        {
            case Worker:
                return month%2==0?salario:salario + rmu/12*2;
            case Supervisor:
                float valueS = salario + (bonusPercentage * 0.35F);
                return month%2==0?valueS:valueS + rmu/12*2;
            case Manager:
                float valueM = salario + (bonusPercentage * 0.7F);
                return month%2==0?valueM:valueM + rmu/12*2;
        }
        return 0.0F;
    }

    public float CalculateYearBonus() {
        float salario = 0;
        if(currency == "USD"){salario = salary; }
        else{salario = (float) (salary * 0.95);}
        switch (employeeType) {
            case Worker:
                return rmu;
            case Supervisor:
                return salario + rmu * 0.5F;
            case Manager:
                return salario + rmu * 1.0F;
        }
        return 0.0F;
    }
}