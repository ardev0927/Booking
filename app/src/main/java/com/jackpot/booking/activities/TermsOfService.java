package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.jackpot.booking.R;

public class TermsOfService extends AppCompatActivity {

    private String content = "1. Terms\n" +
            "\n" +
            "By accessing this Website, accessible from pmbooking.com, you are agreeing to be bound by these Website Terms and Conditions of Use and agree that you are responsible for the agreement with any applicable local laws. If you disagree with any of these terms, you are prohibited from accessing this site. The materials contained in this Website are protected by copyright and trade mark law.\n" +
            "\n" +
            "2. Use License\n" +
            "\n" +
            "Permission is granted to temporarily download one copy of the materials on company name's Website for personal, non-commercial transitory viewing only. This is the grant of a license, not a transfer of title, and under this license you may not:\n" +
            "\n" +
            "    modify or copy the materials;\n" +
            "    use the materials for any commercial purpose or for any public display;\n" +
            "    attempt to reverse engineer any software contained on company name's Website;\n" +
            "    remove any copyright or other proprietary notations from the materials; or\n" +
            "    transferring the materials to another person or \"mirror\" the materials on any other server.\n" +
            "\n" +
            "This will let company name to terminate upon violations of any of these restrictions. Upon termination, your viewing right will also be terminated and you should destroy any downloaded materials in your possession whether it is printed or electronic format. These Terms of Service has been created with the help of the Terms Of Service Generator and the Privacy Policy Generator.\n" +
            "\n" +
            "3. Disclaimer\n" +
            "\n" +
            "All the materials on company name’s Website are provided \"as is\". company name makes no warranties, may it be expressed or implied, therefore negates all other warranties. Furthermore, company name does not make any representations concerning the accuracy or reliability of the use of the materials on its Website or otherwise relating to such materials or any sites linked to this Website.\n" +
            "\n" +
            "4. Limitations\n" +
            "\n" +
            "company name or its suppliers will not be hold accountable for any damages that will arise with the use or inability to use the materials on company name’s Website, even if company name or an authorize representative of this Website has been notified, orally or written, of the possibility of such damage. Some jurisdiction does not allow limitations on implied warranties or limitations of liability for incidental damages, these limitations may not apply to you.\n" +
            "\n" +
            "5. Revisions and Errata\n" +
            "\n" +
            "The materials appearing on company name’s Website may include technical, typographical, or photographic errors. company name will not promise that any of the materials in this Website are accurate, complete, or current. company name may change the materials contained on its Website at any time without notice. company name does not make any commitment to update the materials.\n" +
            "\n" +
            "6. Links\n" +
            "\n" +
            "company name has not reviewed all of the sites linked to its Website and is not responsible for the contents of any such linked site. The presence of any link does not imply endorsement by company name of the site. The use of any linked website is at the user’s own risk.\n" +
            "\n" +
            "7. Site Terms of Use Modifications\n" +
            "\n" +
            "company name may revise these Terms of Use for its Website at any time without prior notice. By using this Website, you are agreeing to be bound by the current version of these Terms and Conditions of Use.\n" +
            "\n" +
            "8. Your Privacy\n" +
            "\n" +
            "Please read our Privacy Policy.\n" +
            "\n" +
            "9. Governing Law\n" +
            "\n" +
            "Any claim related to company name's Website shall be governed by the laws of cn without regards to its conflict of law provisions.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_service);

        TextView tv_content = findViewById(R.id.tv_terms_content);
        tv_content.setText(content);
        tv_content.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onBack(View view) {
        Intent intent = new Intent(this, MainmenuActivity.class);
        startActivity(intent);
        finish();
    }
}