package com.epayfull.stub;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;

public class CreateActivity extends AppCompatActivity {

    Spinner spinnerState, spinnerMarital, spinnerHowPaid, spinnerPayPeriod, spinnerExemptions, spinnerTurnOnOff,
            spinnerMaritalCheck, spinnerExemptionsCheck;
    String state = "", marital = "", howPaid = "", payPeriod = "", exemption = "", turnOnOff = "", maritalCheck = "", exemptionCheck = "";
    EditText editHourRate, editTotalHour, editcmpnyname, editcmpnyadrs1, editcmpnyadrs2, editEmployee, editEmployeeName,
            editAdrs1, editAdrs2, editRegularRate, editRegularHour, editRegularTotal, editRegularYtd, editOvertimeRate,
            editOvertimeHour, editOvertimeTotal, editOvertimeYtd, editFicaTotal, editFicaTotalYtd,
            editFicaSocialTotal, editFicaSocialTotalYtd, editFederalTotal, editFederalTotalYtd, editStateTotal, editStateTotalYtd,
            editTotal, editYtd, editTotalDed, editTotalYtdDed, editTotalNetPay, editTotalYtdNetPay, editSocialSecurityNumber;

    TextView textPeriodStart, textPeriodEnd, textPayDate;

    String[] arrMarital = {"Single", "Married"};
    String[] arrOftenPaid = {"Weekly", "Bi-Weekly", "Monthly", "Bi-Monthly", "Annually"};
    String[] arrPaid = {"Hourly", "Salary"};
    String[] arrExemption = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] arrAuto = {"Turn On Auto Calculation", "Turn Off Auto Calculation"};
    String[] arrEmpYtd = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    SharedPreferences _preference;


    int year, month, day;

    float totalOvertime = 0, totalRegular = 0, totalDed = 0, totalYtdDed = 0, defaultytd = 5000;

    String[] _currency = {"USD ($)", "EUR (€)", "AFN (؋)", "ALL (L)", "GBP (£)", "GGP (د.ج)", "AOA (Kz)", "AMD (֏)", "AWG (ƒ)", "BHD (د.ب)", "BDT (৳)", "BYR (Br)",
            "XOF (Fr)", "INR (₹)", "BOB (Bs)", "BWP (P)", "BRL (R$)", "BGN (лв)", "BIF (Fr)", "KHR (៛)", "CNY (¥)", "CRC (₡)", "HRK (kn)", "ANG (ƒ)", "CZK (Kč)", "DKK (kr)",
            "EGP (ج.م)", "JPY (¥)", "NGN (₦)", "Baht (฿)", "GHC (₵)", "ZAR (R)", "JOD (د.ا)", "KZT (₸)", "KES (Sh)", "KPW (₩)", "KWD (د.ك)", "KGS (лв)", "LAK (₭)", "LBP (ل.ل)",
            "JMD (J$)", "PKR (₨)", "PLN (zł)", "RON (lei)", "RUB (руб)", "SAR (﷼)", "MKD (ден)", "MGA (Ar)", "MYR (RM)", "MRO (UM)", "MNT (₮)", "MAD (د.م.)",
            "NIO (C$)", "TRY (₺)", "OMR (.ع.)", "PYG (₲)", "PHP (₱)", "SDG (ج.س.)", "TND (د.ت)", "AED (د.إ)",
            "LKR (₨)", "GTQ (Q)", "HTG (G)", "HNL (L)", "HKD ($)", "IDR (Rp)", "IRR (﷼)", "IQD (ع.د)", "ILS (₪)", "ZWD (Z$)", "UAH (₴) "};

    String[] _symbol = {"$", "€", "؋", "L", "£", "د.ج", "Kz", "֏", "ƒ", "د.ب", "৳", "Br", "Fr", "₹", "Bs", "P", "R$", "лв", "Fr", "៛", "¥", "₡", "kn", "ƒ", "Kč", "kr", "ج.م", "¥", "₦",
            "฿", "₵", "R", "د.ا", "₸", "Sh", "₩", "د.ك", "лв", "₭", "ل.ل)", "J$", "₨", "zł", "lei", "руб", "﷼", "ден", "Ar", "RM", "UM", "₮", "د.م.", "C$", "₺", ".ع.", "₲", "₱", "ج.س.", "د.ت",
            "د.إ", "₨", "Q", "G", "L", "$", "Rp", "﷼", "ع.د", "₪", "Z", "₴",};

    String _currencySet = "$";

    String[] arrStates = {"Alabama",
            "Alaska",
            "Arizona",
            "Arkansas",
            "California",
            "Colorado",
            "Connecticut",
            "Delaware",
            "Florida",
            "Georgia",
            "Hawaii",
            "Idaho",
            "Illinois",
            "Indiana",
            "Iowa",
            "Kansas",
            "Kentucky",
            "Louisiana",
            "Maine",
            "Maryland",
            "Massachusetts",
            "Michigan",
            "Minnesota",
            "Mississippi",
            "Missouri",
            "Montana",
            "Nebraska",
            "Nevada",
            "New Hampshire",
            "New Jersey",
            "New Mexico",
            "New York",
            "North Carolina",
            "North Dakota",
            "Ohio",
            "Oklahoma",
            "Oregon",
            "Pennsylvania",
            "Rhode Island",
            "South Carolina",
            "South Dakota",
            "Tennessee",
            "Texas",
            "Utah",
            "Vermont",
            "Virginia",
            "Washington",
            "West Virginia",
            "Wisconsin",
            "Wyoming"};

    String[] arrStates_abvr = {"AL",
            "AK",
            "AZ",
            "AR",
            "CA",
            "CO",
            "CT",
            "DE",
            "FL",
            "GA",
            "HI",
            "ID",
            "IL",
            "IN",
            "IA",
            "KS",
            "KY",
            "LA",
            "ME",
            "MD",
            "MA",
            "MI",
            "MN",
            "MS",
            "MO",
            "MT",
            "NE",
            "NV",
            "NH",
            "NJ",
            "NM",
            "NY",
            "NC",
            "ND",
            "OH",
            "OK",
            "OR",
            "PA",
            "RI",
            "SC",
            "SD",
            "TN",
            "TX",
            "UT",
            "VT",
            "VA",
            "WA",
            "WV",
            "WI",
            "WY"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        init();
    }


    public void init() {
        _preference = getSharedPreferences("epaystubpreference", MODE_PRIVATE);
        spinnerState = (Spinner) findViewById(R.id.spinnerstate);
        spinnerMarital = (Spinner) findViewById(R.id.spinnerstatus);
        spinnerHowPaid = (Spinner) findViewById(R.id.spinnerpaid);
        spinnerPayPeriod = (Spinner) findViewById(R.id.spinnerpayperiod);
        spinnerExemptions = (Spinner) findViewById(R.id.spinnerexemptions);
        spinnerTurnOnOff = (Spinner) findViewById(R.id.spinnerturnonoff);
        spinnerMaritalCheck = (Spinner) findViewById(R.id.spinnermarital);
        spinnerExemptionsCheck = (Spinner) findViewById(R.id.spinnerexemption);

        editHourRate = (EditText) findViewById(R.id.edittexthour);
        editTotalHour = (EditText) findViewById(R.id.edittexttotalhr);
        editcmpnyname = (EditText) findViewById(R.id.edittextcmpnyname);
        editcmpnyadrs1 = (EditText) findViewById(R.id.edittextcmpnyadrs1);
        editcmpnyadrs2 = (EditText) findViewById(R.id.edittextcmpnyadrs2);
        editEmployee = (EditText) findViewById(R.id.edittextemployee);
        editEmployeeName = (EditText) findViewById(R.id.edittextempname);
        editAdrs1 = (EditText) findViewById(R.id.edittextadrs1);
        editAdrs2 = (EditText) findViewById(R.id.edittextadrs2);
        editRegularRate = (EditText) findViewById(R.id.edittextregularrate);
        editRegularHour = (EditText) findViewById(R.id.edittextregularhour);
        editRegularTotal = (EditText) findViewById(R.id.edittextregulartotal);
        editRegularYtd = (EditText) findViewById(R.id.edittextregularytd);
        editOvertimeRate = (EditText) findViewById(R.id.edittextregularrateotm);
        editOvertimeHour = (EditText) findViewById(R.id.edittextregularhourotm);
        editOvertimeTotal = (EditText) findViewById(R.id.edittextregulartotalotm);
        editOvertimeYtd = (EditText) findViewById(R.id.edittextregularytdotm);
        editFicaTotal = (EditText) findViewById(R.id.edittexttotalfica);
        editFicaTotalYtd = (EditText) findViewById(R.id.edittexttotalficaytd);
        editFicaSocialTotal = (EditText) findViewById(R.id.edittexttotalficascl);
        editFicaSocialTotalYtd = (EditText) findViewById(R.id.edittexttotalficaytdscl);
        editFederalTotal = (EditText) findViewById(R.id.edittexttotalfed);
        editFederalTotalYtd = (EditText) findViewById(R.id.edittexttotalficaytdfed);
        editStateTotal = (EditText) findViewById(R.id.edittexttotalstate);
        editStateTotalYtd = (EditText) findViewById(R.id.edittexttotalytdstate);
        editTotal = (EditText) findViewById(R.id.edittexttotalgross);
        editYtd = (EditText) findViewById(R.id.edittexttotalgrossytd);
        editTotalDed = (EditText) findViewById(R.id.edittexttotalded);
        editTotalYtdDed = (EditText) findViewById(R.id.edittexttotalytd);
        editTotalNetPay = (EditText) findViewById(R.id.edittexttotalnet);
        editTotalYtdNetPay = (EditText) findViewById(R.id.edittexttotalytdnet);
        editSocialSecurityNumber = (EditText) findViewById(R.id.edittextsocialsecurity);

        textPeriodStart = (TextView) findViewById(R.id.textperiodstart);
        textPeriodEnd = (TextView) findViewById(R.id.textperiodend);
        textPayDate = (TextView) findViewById(R.id.textpaydate);

        editRegularTotal.addTextChangedListener(new NumberTextWatcher(editRegularTotal));
        editRegularYtd.addTextChangedListener(new NumberTextWatcher(editRegularYtd));
        editOvertimeTotal.addTextChangedListener(new NumberTextWatcher(editOvertimeTotal));
        editOvertimeYtd.addTextChangedListener(new NumberTextWatcher(editOvertimeYtd));
        editFicaTotal.addTextChangedListener(new NumberTextWatcher(editFicaTotal));
        editFicaTotalYtd.addTextChangedListener(new NumberTextWatcher(editFicaTotalYtd));
        editFicaSocialTotal.addTextChangedListener(new NumberTextWatcher(editFicaSocialTotal));
        editFicaSocialTotalYtd.addTextChangedListener(new NumberTextWatcher(editFicaSocialTotalYtd));
        editFederalTotal.addTextChangedListener(new NumberTextWatcher(editFederalTotal));
        editFederalTotalYtd.addTextChangedListener(new NumberTextWatcher(editFederalTotalYtd));
        editStateTotal.addTextChangedListener(new NumberTextWatcher(editStateTotal));
        editStateTotalYtd.addTextChangedListener(new NumberTextWatcher(editStateTotalYtd));
        editTotal.addTextChangedListener(new NumberTextWatcher(editTotal));
        editYtd.addTextChangedListener(new NumberTextWatcher(editYtd));
        editTotalDed.addTextChangedListener(new NumberTextWatcher(editTotalDed));

        editTotalYtdDed.addTextChangedListener(new NumberTextWatcher(editTotalYtdDed));
        editTotalNetPay.addTextChangedListener(new NumberTextWatcher(editTotalNetPay));
        editTotalYtdNetPay.addTextChangedListener(new NumberTextWatcher(editTotalYtdNetPay));

        setOldData();
        ArrayAdapter<String> spinnerStateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrStates);
        spinnerStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(spinnerStateAdapter);

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = arrStates[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> spinnerMaritalAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrMarital);
        spinnerMaritalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMarital.setAdapter(spinnerMaritalAdapter);

        spinnerMarital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                marital = arrMarital[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> spinnerHowPaidAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrOftenPaid);
        spinnerHowPaidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHowPaid.setAdapter(spinnerHowPaidAdapter);

        spinnerHowPaid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                howPaid = arrOftenPaid[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> spinnerPayPeriodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrPaid);
        spinnerPayPeriodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPayPeriod.setAdapter(spinnerPayPeriodAdapter);

        spinnerPayPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payPeriod = arrPaid[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> spinnerExemptionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrExemption);
        spinnerExemptionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExemptions.setAdapter(spinnerExemptionAdapter);

        spinnerExemptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exemption = arrExemption[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> spinnerAutoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrAuto);
        spinnerAutoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTurnOnOff.setAdapter(spinnerAutoAdapter);

        spinnerTurnOnOff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                turnOnOff = arrAuto[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> spinnerExemptionYtdAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrEmpYtd);
        spinnerExemptionYtdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExemptionsCheck.setAdapter(spinnerExemptionYtdAdapter);

        spinnerExemptionsCheck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exemptionCheck = arrExemption[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> spinnerMaritalCheckAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrMarital);
        spinnerMaritalCheckAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaritalCheck.setAdapter(spinnerMaritalCheckAdapter);

        spinnerMaritalCheck.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maritalCheck = arrMarital[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        textPeriodStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });

        textPeriodEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(2);
            }
        });

        textPayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(3);
            }
        });

        editRegularRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float regularHr = 0, regularRt = 0;
                totalRegular = 0;
                if (!editRegularHour.getText().toString().trim().equalsIgnoreCase("")) {
                    regularHr = Float.valueOf(editRegularHour.getText().toString().replaceAll(",", "").trim());
                }

                if (!editRegularRate.getText().toString().trim().equalsIgnoreCase("")) {
                    regularRt = Float.valueOf(editRegularRate.getText().toString().replaceAll(",", "").trim());
                }

                totalRegular = regularHr * regularRt;

                double alltotal = totalRegular + totalOvertime;

                editRegularTotal.setText(String.format("%.2f", totalRegular));
                editRegularYtd.setText(String.format("%.2f", (totalRegular + defaultytd)));
                editTotal.setText(String.format("%.2f", alltotal));
                editYtd.setText(String.format("%.2f", (alltotal + defaultytd)));

                double ficamedicare = (totalRegular + totalOvertime) * 6.2 / 100;
                double ficamedisocial = (totalRegular + totalOvertime) * 2.9 / 100;
                double federal = (totalRegular + totalOvertime) * 6.2 / 100;
                double state = (totalRegular + totalOvertime) * 5 / 100;

                double nettotal = alltotal - (ficamedicare + ficamedisocial + federal + state);

                double ficamedicareytd = (totalRegular + totalOvertime + defaultytd) * 1.45 / 100;
                double ficamedisocialytd = (totalRegular + totalOvertime + defaultytd) * 2.9 / 100;
                double federalytd = (totalRegular + totalOvertime + defaultytd) * 6.2 / 100;
                double stateytd = (totalRegular + totalOvertime + defaultytd) * 5 / 100;

                double nettotalytd = (alltotal + defaultytd) - (ficamedicareytd + ficamedisocialytd + federalytd + stateytd);

                editFicaTotal.setText(String.format("%.2f", ficamedicare));
                editFicaTotalYtd.setText(String.format("%.2f", ficamedicareytd));
                editFicaSocialTotal.setText(String.format("%.2f", ficamedisocial));
                editFicaSocialTotalYtd.setText(String.format("%.2f", ficamedisocialytd));
                editFederalTotal.setText(String.format("%.2f", federal));
                editFederalTotalYtd.setText(String.format("%.2f", federalytd));
                editStateTotal.setText(String.format("%.2f", state));
                editStateTotalYtd.setText(String.format("%.2f", stateytd));

                editTotalNetPay.setText(String.format("%.2f", nettotal));
                editTotalYtdNetPay.setText(String.format("%.2f", nettotalytd));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editRegularHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float regularHr = 0, regularRt = 0;
                totalRegular = 0;
                if (!editRegularHour.getText().toString().trim().equalsIgnoreCase("")) {
                    regularHr = Float.valueOf(editRegularHour.getText().toString().replaceAll(",", "").trim());
                }

                if (!editRegularRate.getText().toString().trim().equalsIgnoreCase("")) {
                    regularRt = Float.valueOf(editRegularRate.getText().toString().replaceAll(",", "").trim());
                }

                totalRegular = regularHr * regularRt;

                double alltotal = totalRegular + totalOvertime;

                editRegularTotal.setText(String.format("%.2f", totalRegular));
                editRegularYtd.setText(String.format("%.2f", (totalRegular + defaultytd)));
                editTotal.setText(String.format("%.2f", alltotal));
                editYtd.setText(String.format("%.2f", (alltotal + defaultytd)));

                double ficamedicare = (totalRegular + totalOvertime) * 1.45 / 100;
                double ficamedisocial = (totalRegular + totalOvertime) * 6.2 / 100;
                double federal = (totalRegular + totalOvertime) * 5.6 / 100;
                double state = (totalRegular + totalOvertime) * 5 / 100;

                double nettotal = alltotal - (ficamedicare + ficamedisocial + federal + state);

                double ficamedicareytd = (totalRegular + totalOvertime + defaultytd) * 1.45 / 100;
                double ficamedisocialytd = (totalRegular + totalOvertime + defaultytd) * 6.2 / 100;
                double federalytd = (totalRegular + totalOvertime + defaultytd) * 5.6 / 100;
                double stateytd = (totalRegular + totalOvertime + defaultytd) * 5 / 100;

                double nettotalytd = (alltotal + defaultytd) - (ficamedicareytd + ficamedisocialytd + federalytd + stateytd);

                editFicaTotal.setText(String.format("%.2f", ficamedicare));
                editFicaTotalYtd.setText(String.format("%.2f", ficamedicareytd));
                editFicaSocialTotal.setText(String.format("%.2f", ficamedisocial));
                editFicaSocialTotalYtd.setText(String.format("%.2f", ficamedisocialytd));
                editFederalTotal.setText(String.format("%.2f", federal));
                editFederalTotalYtd.setText(String.format("%.2f", federalytd));
                editStateTotal.setText(String.format("%.2f", state));
                editStateTotalYtd.setText(String.format("%.2f", stateytd));

                editTotalNetPay.setText(String.format("%.2f", nettotal));
                editTotalYtdNetPay.setText(String.format("%.2f", nettotalytd));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        editRegularRate.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                float regularHr = 0, regularRt = 0;
//                totalRegular = 0;
//                if(!editRegularHour.getText().toString().trim().equalsIgnoreCase("")){
//                    regularHr = Float.valueOf(editRegularHour.getText().toString().trim());
//                }
//
//                if(!editRegularRate.getText().toString().trim().equalsIgnoreCase("")){
//                    regularRt = Float.valueOf(editRegularRate.getText().toString().trim());
//                }
//
//                totalRegular = regularHr * regularRt;
//                editRegularTotal.setText(""+totalRegular);
//                editRegularYtd.setText(""+totalRegular);
//                editTotal.setText(""+(totalRegular+totalOvertime));
//                editTotalYtdDed.setText(""+(totalRegular+totalOvertime));
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        editRegularHour.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                float regularHr = 0, regularRt = 0;
//                totalRegular = 0;
//                if(!editRegularHour.getText().toString().trim().equalsIgnoreCase("")){
//                    regularHr = Float.valueOf(editRegularHour.getText().toString().trim());
//                }
//
//                if(!editRegularRate.getText().toString().trim().equalsIgnoreCase("")){
//                    regularRt = Float.valueOf(editRegularRate.getText().toString().trim());
//                }
//
//                totalRegular = regularHr * regularRt;
//                editRegularTotal.setText(""+totalRegular);
//                editRegularYtd.setText(""+totalRegular);
//                editTotal.setText(""+(totalRegular+totalOvertime));
//                editTotalYtdDed.setText(""+(totalRegular+totalOvertime));
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        editOvertimeRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float overtimeHr = 0, overtimeRt = 0;
                totalOvertime = 0;
                if (!editOvertimeHour.getText().toString().trim().equalsIgnoreCase("")) {
                    overtimeHr = Float.valueOf(editOvertimeHour.getText().toString().replaceAll(",", "").trim());
                }

                if (!editOvertimeRate.getText().toString().trim().equalsIgnoreCase("")) {
                    overtimeRt = Float.valueOf(editOvertimeRate.getText().toString().replaceAll(",", "").trim());
                }

                totalOvertime = overtimeHr * overtimeRt;

                double alltotal = totalRegular + totalOvertime;

                editOvertimeTotal.setText(String.format("%.2f", totalOvertime));
                editOvertimeYtd.setText(String.format("%.2f", totalOvertime));
                editTotal.setText(String.format("%.2f", alltotal));
                editYtd.setText(String.format("%.2f", (alltotal + defaultytd)));

                double ficamedicare = (totalRegular + totalOvertime) * 1.45 / 100;
                double ficamedisocial = (totalRegular + totalOvertime) * 6.2 / 100;
                double federal = (totalRegular + totalOvertime) * 5.6 / 100;
                double state = (totalRegular + totalOvertime) * 5 / 100;

                double nettotal = alltotal - (ficamedicare + ficamedisocial + federal + state);

                double ficamedicareytd = (totalRegular + totalOvertime + defaultytd) * 1.45 / 100;
                double ficamedisocialytd = (totalRegular + totalOvertime + defaultytd) * 6.2 / 100;
                double federalytd = (totalRegular + totalOvertime + defaultytd) * 5.6 / 100;
                double stateytd = (totalRegular + totalOvertime + defaultytd) * 5 / 100;

                double nettotalytd = (alltotal + defaultytd) - (ficamedicareytd + ficamedisocialytd + federalytd + stateytd);

                editFicaTotal.setText(String.format("%.2f", ficamedicare));
                editFicaTotalYtd.setText(String.format("%.2f", ficamedicareytd));
                editFicaSocialTotal.setText(String.format("%.2f", ficamedisocial));
                editFicaSocialTotalYtd.setText(String.format("%.2f", ficamedisocialytd));
                editFederalTotal.setText(String.format("%.2f", federal));
                editFederalTotalYtd.setText(String.format("%.2f", federalytd));
                editStateTotal.setText(String.format("%.2f", state));
                editStateTotalYtd.setText(String.format("%.2f", stateytd));

                editTotalNetPay.setText(String.format("%.2f", nettotal));
                editTotalYtdNetPay.setText(String.format("%.2f", nettotalytd));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editOvertimeHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float overtimeHr = 0, overtimeRt = 0;
                totalOvertime = 0;
                if (!editOvertimeHour.getText().toString().trim().equalsIgnoreCase("")) {
                    overtimeHr = Float.valueOf(editOvertimeHour.getText().toString().replaceAll(",", "").trim());
                }

                if (!editOvertimeRate.getText().toString().trim().equalsIgnoreCase("")) {
                    overtimeRt = Float.valueOf(editOvertimeRate.getText().toString().replaceAll(",", "").trim());
                }

                totalOvertime = overtimeHr * overtimeRt;

                double alltotal = totalRegular + totalOvertime;

                editOvertimeTotal.setText(String.format("%.2f", totalOvertime));
                editOvertimeYtd.setText(String.format("%.2f", totalOvertime));
                editTotal.setText(String.format("%.2f", alltotal));
                editYtd.setText(String.format("%.2f", alltotal));

                double ficamedicare = (totalRegular + totalOvertime) * 1.45 / 100;
                double ficamedisocial = (totalRegular + totalOvertime) * 6.2 / 100;
                double federal = (totalRegular + totalOvertime) * 5.6 / 100;
                double state = (totalRegular + totalOvertime) * 5 / 100;

                double nettotal = alltotal - (ficamedicare + ficamedisocial + federal + state);

                double ficamedicareytd = (totalRegular + totalOvertime + defaultytd) * 1.45 / 100;
                double ficamedisocialytd = (totalRegular + totalOvertime + defaultytd) * 6.2 / 100;
                double federalytd = (totalRegular + totalOvertime + defaultytd) * 5.6 / 100;
                double stateytd = (totalRegular + totalOvertime + defaultytd) * 5 / 100;

                double nettotalytd = (alltotal + defaultytd) - (ficamedicareytd + ficamedisocialytd + federalytd + stateytd);

                editFicaTotal.setText(String.format("%.2f", ficamedicare));
                editFicaTotalYtd.setText(String.format("%.2f", ficamedicareytd));
                editFicaSocialTotal.setText(String.format("%.2f", ficamedisocial));
                editFicaSocialTotalYtd.setText(String.format("%.2f", ficamedisocialytd));
                editFederalTotal.setText(String.format("%.2f", federal));
                editFederalTotalYtd.setText(String.format("%.2f", federalytd));
                editStateTotal.setText(String.format("%.2f", state));
                editStateTotalYtd.setText(String.format("%.2f", stateytd));

                editTotalNetPay.setText(String.format("%.2f", nettotal));
                editTotalYtdNetPay.setText(String.format("%.2f", nettotalytd));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editFicaTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float fica = 0, ficasocial = 0, federal = 0, state = 0;
                totalDed = 0;
                if (!editFicaTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    fica = Float.valueOf(editFicaTotal.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFicaSocialTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    ficasocial = Float.valueOf(editFicaSocialTotal.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFederalTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    federal = Float.valueOf(editFederalTotal.getText().toString().replaceAll(",", "").trim());
                }

                if (!editStateTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    state = Float.valueOf(editStateTotal.getText().toString().replaceAll(",", "").trim());
                }

                totalDed = fica + ficasocial + federal + state;
                editTotalDed.setText(String.format("%.2f", totalDed));

                double alltotal = totalRegular + totalOvertime - totalDed;
                editTotalNetPay.setText(String.format("%.2f", alltotal));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editFicaSocialTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float fica = 0, ficasocial = 0, federal = 0, state = 0;
                totalDed = 0;
                if (!editFicaTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    fica = Float.valueOf(editFicaTotal.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFicaSocialTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    ficasocial = Float.valueOf(editFicaSocialTotal.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFederalTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    federal = Float.valueOf(editFederalTotal.getText().toString().replaceAll(",", "").trim());
                }

                if (!editStateTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    state = Float.valueOf(editStateTotal.getText().toString().replaceAll(",", "").trim());
                }

                totalDed = fica + ficasocial + federal + state;
                editTotalDed.setText(String.format("%.2f", totalDed));

                double alltotal = totalRegular + totalOvertime - totalDed;
                editTotalNetPay.setText(String.format("%.2f", alltotal));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editFederalTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float fica = 0, ficasocial = 0, federal = 0, state = 0;
                totalDed = 0;
                if (!editFicaTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    fica = Float.valueOf(editFicaTotal.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFicaSocialTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    ficasocial = Float.valueOf(editFicaSocialTotal.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFederalTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    federal = Float.valueOf(editFederalTotal.getText().toString().replaceAll(",", "").trim());
                }

                if (!editStateTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    state = Float.valueOf(editStateTotal.getText().toString().replaceAll(",", "").trim());
                }

                totalDed = fica + ficasocial + federal + state;
                editTotalDed.setText(String.format("%.2f", totalDed));

                double alltotal = totalRegular + totalOvertime - totalDed;
                editTotalNetPay.setText(String.format("%.2f", alltotal));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editStateTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float fica = 0, ficasocial = 0, federal = 0, state = 0;
                totalDed = 0;
                if (!editFicaTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    fica = Float.valueOf(editFicaTotal.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFicaSocialTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    ficasocial = Float.valueOf(editFicaSocialTotal.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFederalTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    federal = Float.valueOf(editFederalTotal.getText().toString().replaceAll(",", "").trim());
                }

                if (!editStateTotal.getText().toString().trim().equalsIgnoreCase("")) {
                    state = Float.valueOf(editStateTotal.getText().toString().replaceAll(",", "").trim());
                }

                totalDed = fica + ficasocial + federal + state;
                editTotalDed.setText(String.format("%.2f", totalDed));

                double alltotal = totalRegular + totalOvertime - totalDed;
                editTotalNetPay.setText(String.format("%.2f", alltotal));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        editFicaTotalYtd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float ficaytd = 0, ficasocialytd = 0, federalytd = 0, stateytd = 0;
                totalYtdDed = 0;
                if (!editFicaTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    ficaytd = Float.valueOf(editFicaTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFicaSocialTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    ficasocialytd = Float.valueOf(editFicaSocialTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFederalTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    federalytd = Float.valueOf(editFederalTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                if (!editStateTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    stateytd = Float.valueOf(editStateTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                totalYtdDed = ficaytd + ficasocialytd + federalytd + stateytd;
                editTotalYtdDed.setText(String.format("%.2f", totalYtdDed));

                double alltotalytd = totalRegular + totalOvertime - totalYtdDed;
                editTotalYtdNetPay.setText(String.format("%.2f", alltotalytd));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editFicaSocialTotalYtd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float ficaytd = 0, ficasocialytd = 0, federalytd = 0, stateytd = 0;
                totalYtdDed = 0;
                if (!editFicaTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    ficaytd = Float.valueOf(editFicaTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFicaSocialTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    ficasocialytd = Float.valueOf(editFicaSocialTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFederalTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    federalytd = Float.valueOf(editFederalTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                if (!editStateTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    stateytd = Float.valueOf(editStateTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                totalYtdDed = ficaytd + ficasocialytd + federalytd + stateytd;
                editTotalYtdDed.setText(String.format("%.2f", totalYtdDed));

                double alltotalytd = totalRegular + totalOvertime - totalYtdDed;
                editTotalYtdNetPay.setText(String.format("%.2f", alltotalytd));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editFederalTotalYtd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float ficaytd = 0, ficasocialytd = 0, federalytd = 0, stateytd = 0;
                totalYtdDed = 0;
                if (!editFicaTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    ficaytd = Float.valueOf(editFicaTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFicaSocialTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    ficasocialytd = Float.valueOf(editFicaSocialTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFederalTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    federalytd = Float.valueOf(editFederalTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                if (!editStateTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    stateytd = Float.valueOf(editStateTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                totalYtdDed = ficaytd + ficasocialytd + federalytd + stateytd;
                editTotalYtdDed.setText(String.format("%.2f", totalYtdDed));

                double alltotalytd = totalRegular + totalOvertime - totalYtdDed;
                editTotalYtdNetPay.setText(String.format("%.2f", alltotalytd));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editStateTotalYtd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float ficaytd = 0, ficasocialytd = 0, federalytd = 0, stateytd = 0;
                totalYtdDed = 0;
                if (!editFicaTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    ficaytd = Float.valueOf(editFicaTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFicaSocialTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    ficasocialytd = Float.valueOf(editFicaSocialTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                if (!editFederalTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    federalytd = Float.valueOf(editFederalTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                if (!editStateTotalYtd.getText().toString().trim().equalsIgnoreCase("")) {
                    stateytd = Float.valueOf(editStateTotalYtd.getText().toString().replaceAll(",", "").trim());
                }

                totalYtdDed = ficaytd + ficasocialytd + federalytd + stateytd;
                editTotalYtdDed.setText(String.format("%.2f", totalYtdDed));

                double alltotalytd = totalRegular + totalOvertime - totalYtdDed;
                editTotalYtdNetPay.setText(String.format("%.2f", alltotalytd));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void sendNext() {


        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("hourrate", editHourRate.getText().toString().trim());
        hashMap.put("totalhour", editTotalHour.getText().toString().trim());
        hashMap.put("cmpnyname", editcmpnyname.getText().toString().trim());
        hashMap.put("cmpnyadr1", editcmpnyadrs1.getText().toString().trim());
        hashMap.put("cmpnyadr2", editcmpnyadrs2.getText().toString().trim());
        hashMap.put("emp", editEmployee.getText().toString().trim());
        hashMap.put("empname", editEmployeeName.getText().toString().trim());
        hashMap.put("adr1", editAdrs1.getText().toString().trim());
        hashMap.put("adr2", editAdrs2.getText().toString().trim());
        hashMap.put("regularrate", editRegularRate.getText().toString().trim());
        hashMap.put("regularhour", editRegularHour.getText().toString().trim());
        hashMap.put("regulartotal", editRegularTotal.getText().toString().trim());
        hashMap.put("regularytd", editRegularYtd.getText().toString().trim());
        hashMap.put("overtimerate", editOvertimeRate.getText().toString().trim());
        hashMap.put("overtimehour", editOvertimeHour.getText().toString().trim());
        hashMap.put("overtimetotal", editOvertimeTotal.getText().toString().trim());
        hashMap.put("overtimeytd", editOvertimeYtd.getText().toString().trim());
        hashMap.put("ficatotal", editFicaTotal.getText().toString().trim());
        hashMap.put("ficatotalytd", editFicaTotalYtd.getText().toString().trim());
        hashMap.put("ficasocialtotal", editFicaSocialTotal.getText().toString().trim());
        hashMap.put("ficasocialtotalytd", editFicaSocialTotalYtd.getText().toString().trim());
        hashMap.put("federaltotal", editFederalTotal.getText().toString().trim());
        hashMap.put("federaltotalytd", editFederalTotalYtd.getText().toString().trim());
        hashMap.put("statetotal", editStateTotal.getText().toString().trim());
        hashMap.put("statetotalytd", editStateTotalYtd.getText().toString().trim());
        hashMap.put("total", editTotal.getText().toString().trim());
        hashMap.put("ytd", editYtd.getText().toString().trim());
        hashMap.put("totalded", editTotalDed.getText().toString().trim());
        hashMap.put("totalytdded", editTotalYtdDed.getText().toString().trim());
        hashMap.put("totalnetpay", editTotalNetPay.getText().toString().trim());
        hashMap.put("totalytdnetpay", editTotalYtdNetPay.getText().toString().trim());
        hashMap.put("socialsecuritynumber", editSocialSecurityNumber.getText().toString().trim());

        hashMap.put("periodstart", textPeriodStart.getText().toString().trim());
        hashMap.put("periodend", textPeriodEnd.getText().toString().trim());
        hashMap.put("paydate", textPayDate.getText().toString().trim());

        hashMap.put("state", state);
        hashMap.put("marital", marital);
        hashMap.put("howpaid", howPaid);
        hashMap.put("payperiod", payPeriod);
        hashMap.put("exemption", exemption);
        hashMap.put("turnonoff", turnOnOff);
        hashMap.put("maritalcheck", maritalCheck);
        hashMap.put("exemptioncheck", exemptionCheck);

        hashMap.put("currency", _currencySet);

        SharedPreferences.Editor editor = _preference.edit();
        editor.putString("editHourRate", editHourRate.getText().toString().trim());
        editor.putString("editTotalHour", editTotalHour.getText().toString().trim());
        editor.putString("editcmpnyname", editcmpnyname.getText().toString().trim());
        editor.putString("editcmpnyadrs1", editcmpnyadrs1.getText().toString().trim());
        editor.putString("editcmpnyadrs2", editcmpnyadrs2.getText().toString().trim());
        editor.putString("editEmployee", editEmployee.getText().toString().trim());
        editor.putString("editEmployeeName", editEmployeeName.getText().toString().trim());
        editor.putString("editAdrs1", editAdrs1.getText().toString().trim());
        editor.putString("editAdrs2", editAdrs2.getText().toString().trim());
        editor.putString("editRegularRate", editRegularRate.getText().toString().trim());
        editor.putString("editRegularHour", editRegularHour.getText().toString().trim());
        editor.putString("editRegularTotal", editRegularTotal.getText().toString().trim());
        editor.putString("editRegularYtd", editRegularYtd.getText().toString().trim());
        editor.putString("editOvertimeRate", editOvertimeRate.getText().toString().trim());
        editor.putString("editOvertimeHour", editOvertimeHour.getText().toString().trim());
        editor.putString("editOvertimeTotal", editOvertimeTotal.getText().toString().trim());
        editor.putString("editOvertimeYtd", editOvertimeYtd.getText().toString().trim());
        editor.putString("editFicaTotal", editFicaTotal.getText().toString().trim());
        editor.putString("editFicaTotalYtd", editFicaTotalYtd.getText().toString().trim());
        editor.putString("editFicaSocialTotal", editFicaSocialTotal.getText().toString().trim());
        editor.putString("editFicaSocialTotalYtd", editFicaSocialTotalYtd.getText().toString().trim());
        editor.putString("editFederalTotal", editFederalTotal.getText().toString().trim());
        editor.putString("editFederalTotalYtd", editFederalTotalYtd.getText().toString().trim());
        editor.putString("editStateTotal", editStateTotal.getText().toString().trim());
        editor.putString("editStateTotalYtd", editStateTotalYtd.getText().toString().trim());
        editor.putString("editTotal", editTotal.getText().toString().trim());
        editor.putString("editYtd", editYtd.getText().toString().trim());
        editor.putString("editTotalDed", editTotalDed.getText().toString().trim());
        editor.putString("editTotalYtdDed", editTotalYtdDed.getText().toString().trim());
        editor.putString("editTotalNetPay", editTotalNetPay.getText().toString().trim());
        editor.putString("editTotalYtdNetPay", editTotalYtdNetPay.getText().toString().trim());
        editor.putString("editSocialSecurityNumber", editSocialSecurityNumber.getText().toString().trim());
        editor.apply();

        Intent intent = new Intent(CreateActivity.this, ReviewActivity.class);
        intent.putExtra("hashmap", hashMap);
        startActivity(intent);

    }

    public void back(View v) {
        finish();
    }

    public void summary(View v) {
        sendNext();
    }

    public void currency(View v) {
        customDiaCurrency();
    }

    public void logout(View v) {
        finish();
    }

    @Override

    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 1) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        } else if (id == 2) {
            return new DatePickerDialog(this, myDateListener1, year, month, day);
        } else if (id == 3) {
            return new DatePickerDialog(this, myDateListener2, year, month, day);
        }
        return null;

    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {
            // arg1 = year
            // arg2 = month
            // arg3 = day

            textPeriodStart.setText((month + 1) + "/" + day + "/" + year);
        }
    };

    private DatePickerDialog.OnDateSetListener myDateListener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {
            // arg1 = year
            // arg2 = month
            // arg3 = day

            textPeriodEnd.setText((month + 1) + "/" + day + "/" + year);
        }
    };

    private DatePickerDialog.OnDateSetListener myDateListener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {
            // arg1 = year
            // arg2 = month
            // arg3 = day

            textPayDate.setText((month + 1) + "/" + day + "/" + year);
        }
    };


    public void customDiaCurrency() {
        final Dialog dialog = new Dialog(CreateActivity.this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.currency_dia);


        ListView _listView = (ListView) dialog.findViewById(R.id.listView2);

        CustomAdapter adapter = new CustomAdapter(_currency);
        _listView.setAdapter(adapter);
        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                _currencySet = _symbol[position];
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public class CustomAdapter extends BaseAdapter {
        String[] _imageGet;

        public CustomAdapter(String[] _imagenew) {
            _imageGet = _imagenew;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return _imageGet.length;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            // TODO Auto-generated method stub
            View vie;
            LayoutInflater inflater = getLayoutInflater();
            vie = inflater.inflate(R.layout.currencyitem, parent, false);
            TextView _text;
            _text = (TextView) vie.findViewById(R.id.text);
            _text.setText(_currency[position]);
            return vie;
        }
    }

    public void setOldData() {
        editHourRate.setText(_preference.getString("editHourRate", ""));
        editTotalHour.setText(_preference.getString("editTotalHour", ""));
        editcmpnyname.setText(_preference.getString("editcmpnyname", ""));
        editcmpnyadrs1.setText(_preference.getString("editcmpnyadrs1", ""));
        editcmpnyadrs2.setText(_preference.getString("editcmpnyadrs2", ""));
        editEmployee.setText(_preference.getString("editEmployee", ""));
        editEmployeeName.setText(_preference.getString("editEmployeeName", ""));
        editAdrs1.setText(_preference.getString("editAdrs1", ""));
        editAdrs2.setText(_preference.getString("editAdrs2", ""));
        editRegularRate.setText(_preference.getString("editRegularRate", ""));
        editRegularHour.setText(_preference.getString("editRegularHour", ""));
        editRegularTotal.setText(_preference.getString("editRegularTotal", ""));
        editRegularYtd.setText(_preference.getString("editRegularYtd", ""));
        editOvertimeRate.setText(_preference.getString("editOvertimeRate", ""));
        editOvertimeHour.setText(_preference.getString("editOvertimeHour", ""));
        editOvertimeTotal.setText(_preference.getString("editOvertimeTotal", ""));
        editOvertimeYtd.setText(_preference.getString("editOvertimeYtd", ""));
        editFicaTotal.setText(_preference.getString("editFicaTotal", ""));
        editFicaTotalYtd.setText(_preference.getString("editFicaTotalYtd", ""));
        editFicaSocialTotalYtd.setText(_preference.getString("editFicaSocialTotalYtd", ""));
        editFederalTotal.setText(_preference.getString("editFederalTotal", ""));
        editFederalTotalYtd.setText(_preference.getString("editFederalTotalYtd", ""));
        editStateTotal.setText(_preference.getString("editStateTotal", ""));
        editStateTotalYtd.setText(_preference.getString("editStateTotalYtd", ""));
        editTotal.setText(_preference.getString("editTotal", ""));
        editYtd.setText(_preference.getString("editYtd", ""));
        editTotalDed.setText(_preference.getString("editTotalDed", ""));
        editTotalYtdDed.setText(_preference.getString("editTotalYtdDed", ""));
        editTotalNetPay.setText(_preference.getString("editTotalNetPay", ""));
        editTotalYtdNetPay.setText(_preference.getString("editTotalYtdNetPay", ""));
        editSocialSecurityNumber.setText(_preference.getString("editSocialSecurityNumber", ""));

    }


}
