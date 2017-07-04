
package com.epayfull.stub;

import android.net.ParseException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.text.DecimalFormat;

public class NumberTextWatcher implements TextWatcher {

	private DecimalFormat df;
	private DecimalFormat dfnd;
	private boolean hasFractionalPart;
	int trailingZeroCount = 0;
	private EditText et;
	int a=0;
	public NumberTextWatcher(EditText et)
	{
		df = new DecimalFormat("#,###.##");
		df.setDecimalSeparatorAlwaysShown(true);
		dfnd = new DecimalFormat("#,###");
		this.et = et;
		hasFractionalPart = false;
	}

	@SuppressWarnings("unused")
	private static final String TAG = "NumberTextWatcher";

	@Override
	public void afterTextChanged(Editable s)
	{
		if(s.length()>0){
		et.removeTextChangedListener(this);
	
		try {
			int inilen, endlen;
			inilen = et.getText().length();

			String v = s.toString().replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
			Number n = df.parse(v);
			int cp = et.getSelectionStart();

			if (hasFractionalPart) {
				StringBuilder trailingZeros = new StringBuilder();
				while (trailingZeroCount-- > 0)
					trailingZeros.append('0');
				
				String txt = df.format(n);
				Log.v("TXT", txt + trailingZeros.toString());
				et.setText(txt + trailingZeros.toString());
			} else {
				String txt = dfnd.format(n);
				et.setText(txt);
			} 

			endlen = et.getText().length();
			int sel = (cp + (endlen - inilen));
			if (sel > 0 && sel <= et.getText().length()) {
				et.setSelection(sel);
			} else {
				// place cursor at the end?
				et.setSelection(et.getText().length() - 1);
			}
		} catch (NumberFormatException nfe) {
			// do nothing?
		} catch (ParseException e) {
			// do nothing?
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		et.addTextChangedListener(this);
	}}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after)
	{
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
		int index = s.toString().indexOf(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator()));
		trailingZeroCount = 0;
		Log.d("index",""+index);
		a=index;
		if (index > -1)
		{		Log.d("s.length()",""+s.length());
			for (index++; index < s.length(); index++) {
				Log.d("scharindex",""+index);

				if (s.charAt(index) == '0' && index-a<=2)
					trailingZeroCount++;
				else {
					trailingZeroCount = 0;
				}
			}

			hasFractionalPart = true;
		} else {
			hasFractionalPart = false;
		}



	}
}
