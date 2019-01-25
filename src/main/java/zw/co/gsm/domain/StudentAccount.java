package zw.co.gsm.domain;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;


@Entity

public class StudentAccount extends BaseEntityId

{
    private Double amount;
    private Student student;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Enumerated
    private Level level;

    @Enumerated
    private PaymentType paymentType;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }


    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setLevel(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "StudentAccount{" +
                "amount=" + amount +
                ", student=" + student +
                ", level=" + level +
                ", paymentType=" + paymentType +
                '}';
    }
}