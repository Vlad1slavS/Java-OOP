module com.example.first {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports com.example.first.Controllers;
    exports com.example.first.Models;
    exports com.example.first.Services;


    opens com.example.first to javafx.fxml;
    opens com.example.first.Controllers to javafx.fxml;  // Открываем пакет для использования в FXML
    opens com.example.first.Services to javafx.fxml;  // Открываем пакет для использования в FXML
    opens com.example.first.Models to com.google.gson;
    exports com.example.first;
}