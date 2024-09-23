package br.com.tfurtado.p1;

import static java.lang.Double.parseDouble;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculateRetirementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculate_retirement);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listenToCalculateOnClick();
    }

    private void listenToCalculateOnClick() {
        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(view -> {
            EditText amountEndInvestment = findViewById(R.id.amount_end_investment);
            TextView error = findViewById(R.id.error);
            if (amountEndInvestment.getText().toString().isEmpty()) {
                amountEndInvestment.setError("Valor para investir é obrigatório");
                error.setText("Valor para investir é obrigatório");
                return;
            }
            EditText interestRate = findViewById(R.id.interest_rate);
            if (interestRate.getText().toString().isEmpty()) {
                interestRate.setError("Taxa de juros é obrigatória");
                error.setText("Taxa de juros é obrigatória");
                return;
            }

            EditText retirementTime = findViewById(R.id.retirement_time);
            if (retirementTime.getText().toString().isEmpty()) {
                retirementTime.setError("Tempo de aposentadoria é obrigatório");
                error.setText("Tempo de aposentadoria é obrigatório");
                return;
            }

            Intent intent = new Intent(this, MonthlyContributionActivity.class);
            intent.putExtra("amountEndInvestment", parseDouble(amountEndInvestment.getText().toString()));
            intent.putExtra("interestRate", parseDouble(interestRate.getText().toString()));
            intent.putExtra("retirementTime", parseDouble(retirementTime.getText().toString()));
            startActivity(intent);
        });
    }
}