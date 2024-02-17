package lk.ijse.CustomerManager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.CustomerManager.Entity.Customer;
import lk.ijse.CustomerManager.model.CustomerModel;

import java.io.IOException;
import java.util.regex.Pattern;

public class ManageCustomerFormController {

    private final CustomerModel customerModel = new CustomerModel();
    @FXML
    private AnchorPane CustomerPane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields(){
        txtTel.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtId.clear();
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String customerTel = txtTel.getText();
        boolean isTxtIdExist = !customerTel.isEmpty();

        if (!isTxtIdExist){
            new Alert(Alert.AlertType.ERROR, "Enter a  tel").show();
            return;
        }

        Customer customer = customerModel.searchCustomer(String.format(customerTel));
        if(customer == null){
            new Alert(Alert.AlertType.ERROR, "Customer Not Found").show();
            return;
        }

        boolean isDeleted = customerModel.deleteCustomer(String.format(customerTel));
        if (!isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Didn't Delete").show();
        }

        new Alert(Alert.AlertType.INFORMATION, "Customer Deleted Successfully").show();
        clearFields();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isValidate = validateFields();
        if (!isValidate) {
            return;
        }

        String customerTel = txtTel.getText();
        String customerName = txtName.getText();
        String customerAddress = txtAddress.getText();
        String customerId = txtId.getId();

        Customer customer = new Customer(customerTel,customerName,customerAddress,customerId);
        boolean isSaved = customerModel.saveCustomer(customer);

        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Saved Successfully").show();
            clearFields();
        }

    }

    private boolean validateFields() {

        String customerTel = txtTel.getText();
        boolean isCustomerTelValid = Pattern.matches("[0-9]{10}",customerTel);
        if (!isCustomerTelValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer Tel").show();
            return false;
        }

        String customerName = txtName.getText();
        boolean isCustomerNameValidated = Pattern.matches("^[A-Za-z\\s]+$", customerName);
        if (!isCustomerNameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer Name").show();
            return false;
        }
        String customerAddress = txtAddress.getText();
        boolean isCustomerAddressValidated = Pattern.matches("^\\d+/\\d+,\\s?[A-Za-z0-9\\s.,'-]+|^[A-Za-z]+$", customerAddress);
        if (!isCustomerAddressValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer address").show();
            return false;
        }

        String CustomerId = txtId.getText();
        boolean isCustomerIDValidated = Pattern.matches("[C][0-9]{3,}", CustomerId);
        if (!isCustomerIDValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer ID!").show();
            return false;
        }

        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isValidate = validateFields();

        String customerTel = txtTel.getText();

        boolean isTxtIdExist = !customerTel.isEmpty();

        if (!isValidate || !isTxtIdExist) {
            return;
        }

        boolean isUserExist = customerModel.searchCustomer(String.format(customerTel)) != null;
        if (!isUserExist) {
            new Alert(Alert.AlertType.ERROR, "Invalid tel").show();
            return;
        }

        customerTel = txtTel.getText();
        String customerName = txtName.getText();
        String customerAddress = txtAddress.getText();
        String customerId = txtId.getId();

        Customer customer = new Customer(customerTel,customerName,customerAddress,customerId);

        boolean isUpdated = customerModel.updateCustomer(String.format(customerTel),customer);

        if (!isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Didn't Update").show();
        }

        new Alert(Alert.AlertType.INFORMATION, "Customer Updated Successfully").show();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        boolean isTxtIdExist = !txtId.getText().isEmpty();

        if (!isTxtIdExist) {
            new Alert(Alert.AlertType.ERROR, "Enter a  tel").show();
            return;
        }

        String customerTel = String.format(txtTel.getText());
        Customer customer = customerModel.searchCustomer(customerTel);
        if (customer == null) {
            new Alert(Alert.AlertType.ERROR, "Customer Not Found").show();
            clearFields();
            return;
        }

        txtTel.setText(customer.getCustomerTel());
        txtName.setText(customer.getCustomerName());
        txtAddress.setText(customer.getCustomerAddress());
        txtId.setText(customer.getCustomerId());
    }



}
