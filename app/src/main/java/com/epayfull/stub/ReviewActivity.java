package com.epayfull.stub;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;

public class ReviewActivity extends AppCompatActivity {

    TextView textCompanyName, textCompanyAdrs, textGrossRate, textGrossHour, textGrossPay, textOtmRate, textOtmHour, textOtmPay,
            textFicaTotal, textFicaYtd, textFicaSocialTotal, textFicaSocialYtd, textFederalTotal, textFederalYtd, textStateTotal, textStateYtd,
            textYtdGross, textYtdDeduction, textYtdNet, textTotal, textDeduction, textNetPay, textEmpName, textSsn, textReportingPeriod, textPayDate,
    textEmpId;

    HashMap<String , String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        hashMap  = (HashMap<String, String>) getIntent().getSerializableExtra("hashmap");

        init();

    }

    public void init(){

        textCompanyName = (TextView) findViewById(R.id.textcompanyname);
        textCompanyAdrs = (TextView) findViewById(R.id.textcompanyadrs);
        textGrossRate = (TextView) findViewById(R.id.textrate);
        textGrossHour = (TextView) findViewById(R.id.texthour);
        textGrossPay = (TextView) findViewById(R.id.textcurrentpay);
        textOtmRate = (TextView) findViewById(R.id.textrateotm);
        textOtmHour = (TextView) findViewById(R.id.texthourotm);
        textOtmPay = (TextView) findViewById(R.id.textcurrentpayotm);
        textFicaTotal = (TextView) findViewById(R.id.textficatotal);
        textFicaYtd = (TextView) findViewById(R.id.textficaytd);
        textFicaSocialTotal = (TextView) findViewById(R.id.textficasocialtotal);
        textFicaSocialYtd = (TextView) findViewById(R.id.textficasocialytd);
        textFederalTotal = (TextView) findViewById(R.id.textfedtotal);
        textFederalYtd = (TextView) findViewById(R.id.textfedytd);
        textStateTotal = (TextView) findViewById(R.id.textstatetotal);
        textStateYtd = (TextView) findViewById(R.id.textstateytd);
        textYtdGross = (TextView) findViewById(R.id.textytdgross);
        textYtdDeduction = (TextView) findViewById(R.id.textytddeductions);
        textYtdNet = (TextView) findViewById(R.id.textytdnetpay);
        textTotal = (TextView) findViewById(R.id.texttotal);
        textDeduction = (TextView) findViewById(R.id.textdeductions);
        textNetPay = (TextView) findViewById(R.id.textnetpay);
        textEmpName = (TextView) findViewById(R.id.textempname);
        textSsn = (TextView) findViewById(R.id.textssn);
        textReportingPeriod = (TextView) findViewById(R.id.textreportnnperiod);
        textPayDate = (TextView) findViewById(R.id.textpaydate);
        textEmpId = (TextView) findViewById(R.id.textemployeeid);

        setData();

    }

    public void setData(){

        textCompanyName.setText(hashMap.get("cmpnyname"));
        textCompanyAdrs.setText(hashMap.get("cmpnyadr1")+"\n"+hashMap.get("cmpnyadr2"));
        textGrossRate.setText(hashMap.get("currency")+hashMap.get("regularrate"));
        textGrossHour.setText(hashMap.get("regularhour"));
        textGrossPay.setText(hashMap.get("currency")+hashMap.get("regulartotal"));
        textOtmRate.setText(hashMap.get("currency")+hashMap.get("overtimerate"));
        textOtmHour.setText(hashMap.get("overtimehour"));
        textOtmPay.setText(hashMap.get("currency")+hashMap.get("overtimetotal"));
        textFicaTotal.setText(hashMap.get("currency")+hashMap.get("ficatotal"));
        textFicaYtd.setText(hashMap.get("currency")+hashMap.get("ficatotalytd"));
        textFicaSocialTotal.setText(hashMap.get("currency")+hashMap.get("ficasocialtotal"));
        textFicaSocialYtd.setText(hashMap.get("currency")+hashMap.get("ficasocialtotalytd"));
        textFederalTotal.setText(hashMap.get("currency")+hashMap.get("federaltotal"));
        textFederalYtd.setText(hashMap.get("currency")+hashMap.get("federaltotalytd"));
        textStateTotal.setText(hashMap.get("currency")+hashMap.get("statetotal"));
        textStateYtd.setText(hashMap.get("currency")+hashMap.get("statetotalytd"));
        textYtdGross.setText(hashMap.get("currency")+hashMap.get("ytd"));
        textYtdDeduction.setText(hashMap.get("currency")+hashMap.get("totalytdded"));
        textYtdNet.setText(hashMap.get("currency")+hashMap.get("totalytdnetpay"));
        textTotal.setText(hashMap.get("currency")+hashMap.get("total"));
        textDeduction.setText(hashMap.get("currency")+hashMap.get("totalded"));
        textNetPay.setText(hashMap.get("currency")+hashMap.get("totalnetpay"));
        textEmpName.setText(hashMap.get("empname")+"\n"+hashMap.get("adr1")+" "+hashMap.get("adr2"));
        textSsn.setText(hashMap.get("socialsecuritynumber"));
        textReportingPeriod.setText(hashMap.get("periodstart")+"-"+hashMap.get("periodend"));
        textPayDate.setText(hashMap.get("paydate"));
        textEmpId.setText(hashMap.get("emp"));

        Log.d("herecheck","herecheck"+hashMap.get("empname")+" "+hashMap.get("socialsecuritynumber"));

    }

    public void save(View v){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                takeScreenshot();

            } else {
                requestPermission();
            }
            //MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "epaystub" + System.currentTimeMillis() + ".jpg", "");


        }else{
            takeScreenshot();
        }

    }

    private void takeScreenshot() {
        Date now = new Date();
        DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file

//            File f = new File(Environment.getExternalStorageDirectory().toString() + "/fakebankstatement");
//            if (!f.exists()) {
//                f.mkdir();
//            }

//            String mPath = f.getAbsolutePath() + "/fakebankstatement" + System.currentTimeMillis() + ".jpg";

            // create bitmap screen capture
//            View v1 = getWindow().getDecorView().getRootView();
//            v1.setDrawingCacheEnabled(true);
//            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
//            v1.setDrawingCacheEnabled(false);

            LinearLayout scr = (LinearLayout) findViewById(R.id.lay_all);
            Bitmap bitmap = Bitmap.createBitmap(scr.getWidth(), scr.getHeight(), Bitmap.Config.ARGB_4444);
            Canvas c = new Canvas(bitmap);
            scr.draw(c);
            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "epaystub" + System.currentTimeMillis() + ".jpg", "");
            Toast.makeText(ReviewActivity.this, "Your generated Paycheck is saved to your Gallery", Toast.LENGTH_LONG).show();





//            File imageFile = new File(mPath);

//            FileOutputStream outputStream = new FileOutputStream(imageFile);
//            int quality = 100;
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
//            outputStream.flush();
//            outputStream.close();

        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            Toast.makeText(ReviewActivity.this, "Failed to capture", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    private void requestPermission() {
       requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);


    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takeScreenshot();

                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }
    }


