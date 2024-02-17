package lk.ijse.CustomerManager.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_tel")
    private String customerTel;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_id")
    private String customerId;

    public Customer(String customerTel,String customerName,String customerAddress,String customerId){
        this.customerTel=customerTel;
        this.customerName=customerName;
        this.customerAddress=customerAddress;
        this.customerId=customerId;
    }

}
