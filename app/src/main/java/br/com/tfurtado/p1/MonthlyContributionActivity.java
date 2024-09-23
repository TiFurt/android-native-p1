package br.com.tfurtado.p1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MonthlyContributionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monthly_contribution);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        double amountEndInvestment = getIntent().getDoubleExtra("amountEndInvestment", 0);
        double interestRate = getIntent().getDoubleExtra("interestRate", 0);
        double retirementTime = getIntent().getDoubleExtra("retirementTime", 0);

        calculateMonthlyContribution(amountEndInvestment, interestRate, retirementTime);

        listenToGoBackOnClick();
    }

    private void calculateMonthlyContribution(double amountEndInvestment, double interestRate, double retirementTime) {
        double monthRetirementTime = retirementTime * 12;
        double monthInterestRate = interestRate / 12 / 100;
        double monthlyContribution = amountEndInvestment * (1 - monthInterestRate) / monthRetirementTime;
        TextView monthlyContributionTextView = findViewById(R.id.monthly_contribution);
        monthlyContributionTextView.setText(String.format("R$ %.2f", monthlyContribution));
        TextView totalTextView = findViewById(R.id.total);
        totalTextView.setText("Rendimento total: " + String.format("R$ %.2f", amountEndInvestment));
    }

    private void listenToGoBackOnClick() {
        findViewById(R.id.go_back_button).setOnClickListener(view -> finish());
    }

    private double roundTo2DecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}