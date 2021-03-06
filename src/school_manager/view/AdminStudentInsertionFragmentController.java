/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.view;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import school_manager.MainApp;
import school_manager.helpers.DatabaseManager;
import school_manager.helpers.MainReferenced;
import school_manager.model.Admin;
import school_manager.model.Student;
import school_manager.model.overviews.GroupOverview;

/**
 * FXML Controller class
 *
 * @author bepa
 */
public class AdminStudentInsertionFragmentController implements Initializable, MainReferenced {

    private MainApp mainApp;
    private Admin admin;
    private List<GroupOverview> groupOverview;
    
    @FXML
    private TextField tfFName;
    @FXML
    private TextField tfLName;
    @FXML
    private TextField tfPatronymic;
    @FXML
    private ComboBox<GroupOverview> cbGroup;
    @FXML
    private TextField tfBDay;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfNotes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initGroups();
    }
    
    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    public void  setAdmin(Admin admin){
        this.admin = admin;
    }
    
    @FXML 
    public void btnConfirmClicked(){
                
        String fname = tfFName.getText();
        String lname = tfLName.getText();
        String patronymic = tfPatronymic.getText();
        String bday = tfBDay.getText();
        String address = tfAddress.getText();
        String phone = tfPhone.getText();
        String notes = tfNotes.getText();
        int groupId = cbGroup.getSelectionModel().getSelectedItem().getId();
        
        Student added = new Student.Builder()
                .fName(fname)
                .lName(lname)
                .patronymic(patronymic)
                .bday(bday)
                .address(address)
                .phone(phone)
                .notes(notes)
                .idGroup(groupId)
                .build();
        
        btnClearClicked();
        if (DatabaseManager.insertStudent(added)) {
            btnClearClicked();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error inserting student");
            alert.setContentText("Check inputed data");
        }
        
    }
    
    @FXML
    public void btnClearClicked(){
        tfFName.clear();
        tfLName.clear();
        tfPatronymic.clear();
        cbGroup.setValue(null);
        tfBDay.clear();
        tfAddress.clear();
        tfPhone.clear();
        tfNotes.clear();
    }
    
    public void initGroups(){
        groupOverview = DatabaseManager.getGroupsList();
        ObservableList<GroupOverview> groupList = FXCollections.observableArrayList(groupOverview);
        cbGroup.setItems(groupList);
        cbGroup.setValue(groupList.get(0));
    }
}
