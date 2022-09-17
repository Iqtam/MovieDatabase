module com.example.part2finalproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.part2finalproject to javafx.fxml;
    exports com.example.part2finalproject;
    opens sample to javafx.graphics, javafx.fxml;
    exports sample;
    opens server to javafx.base;
    exports server;
}