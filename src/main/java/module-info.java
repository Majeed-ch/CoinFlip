module com.example.coinflip {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.majeed_ch.coinflip to javafx.fxml;
    exports com.majeed_ch.coinflip;
}