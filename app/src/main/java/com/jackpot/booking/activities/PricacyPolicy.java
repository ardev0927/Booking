package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.jackpot.booking.R;

public class PricacyPolicy extends AppCompatActivity {

    String content = "Privacy Policy\n" +
            "\n" +
            "Last updated: \u200B(add date)\n" +
            "\n" +
            "My Company (change this)\u200B (\"us\", \"we\", or \"our\") operates \u200Bhttp://www.mysite.com (change this)\u200B (the \"Site\"). \n" +
            "This page informs you of our policies regarding the collection, use and disclosure of Personal Information we receive from users of the Site.\n" +
            "\n" +
            "We use your Personal Information only for providing and improving the Site. \n" +
            "By using the Site, you agree to the collection and use of information in accordance with this policy.\n" +
            "\n" +
            "Information Collection And Use\n" +
            "\n" +
            "While using our Site, we may ask you to provide us with certain personally identifiable information that can be used to contact or identify you. \n" +
            "Personally identifiable information may include, but is notlimited to your name (\"Personal Information\").\n" +
            "\n" +
            "Log Data\n" +
            "\n" +
            "Like many site operators, we collect information that your browser sends whenever you visit our Site(\"Log Data\").\n" +
            "\n" +
            "This Log Data may include information such as your computer's Internet Protocol (\"IP\") address,browser type, browser version, the pages of our Site that you visit, the time and date of your visit,the time spent on those pages and other statistics.\n" +
            "\n" +
            "In addition, we may use third party services such as Google Analytics that collect, monitor andanalyze this ...\n" +
            "\n" +
            "The Log Data section is for businesses that use analytics or tracking services in websites orapps, like Google Analytics. For the full disclosure section, \u200Bcreate your own Privacy Policy\u200B.\n" +
            "\n" +
            "Communications\n" +
            "\n" +
            "We may use your Personal Information to contact you with newsletters, marketing or promotionalmaterials and other information that ...\n" +
            "\n" +
            "The Communications section is for businesses that may contact users via email (emailnewsletters) or other methods. For the full disclosure section, \u200Bcreate your own Privacy Policy\u200B.\n" +
            "\n" +
            "Cookies\n" +
            "\n" +
            "Cookies are files with small amount of data, which may include an anonymous unique identifier.\n" +
            "Cookies are sent to your browser from a web site and stored on your computer's hard drive.\n" +
            "\n" +
            "Like many sites, we use \"cookies\" to collect information. You can instruct your browser to refuse allcookies or to indicate when a cookie is being sent. However, if you do not accept cookies, you maynot be able to use some portions of our Site.\n" +
            "\n" +
            "Security\n" +
            "\n" +
            "The security of your Personal Information is important to us, but remember that no method of transmission over the Internet, or method of electronic storage, is 100% secure. While we strive touse commercially acceptable means to protect your Personal Information, we cannot guarantee itsabsolute security.\n" +
            "\n" +
            "Changes To This Privacy Policy\n" +
            "\n" +
            "This Privacy Policy is effective as of \u200B(add date)\u200B and will remain in effect except with respect to anychanges in its provisions in the future, which will be in effect immediately after being posted on thispage.\n" +
            "\n" +
            "We reserve the right to update or change our Privacy Policy at any time and you should check this Privacy Policy periodically. Your continued use of the Service after we post any modifications to the Privacy Policy on this page will constitute your acknowledgment of the modifications and your consent to abide and be bound by the modified Privacy Policy.\n" +
            "\n" +
            "If we make any material changes to this Privacy Policy, we will notify you either through the emailaddress you have provided us, or by placing a prominent notice on our website.\n" +
            "\n" +
            "Contact Us\n" +
            "\n" +
            "If you have any questions about this Privacy Policy, please contact us.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricacy_policy);

        TextView tv_policy_content = findViewById(R.id.tv_policy_content);
        tv_policy_content.setText(content);
        tv_policy_content.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onBack(View view) {
        startActivity(new Intent(this, MainmenuActivity.class));
        finish();
    }
}